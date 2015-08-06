package com.sfm.controller;

import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sfm.model.Attendance;
import com.sfm.model.Faculty;
import com.sfm.service.FacultyService;
import com.sfm.util.PdfWriterUtil;
import com.sfm.util.Utils;


@Controller
public class FacultyController {

	private static final Logger logger = LoggerFactory
			.getLogger(FacultyController.class);

	@Autowired
	private FacultyService facultyService;



	@RequestMapping(value = "/getFacultyName",  produces="application/json", method = RequestMethod.GET)
	public @ResponseBody
	List<Faculty> getFaculties() {
		List<Faculty> faculties= facultyService.listFaculty();
		return faculties;

	}


	@RequestMapping(value = "/getAttendanceByFacultyId/{facultyId}/{month}",  produces="application/json", method = RequestMethod.POST)
	public @ResponseBody
	List<Faculty> listAttendanceByFacultyId(@PathVariable("facultyId")Integer facultyId,@PathVariable("month")Integer month) {
		List<Faculty> faculties= facultyService.listAttendanceByFaculty(facultyId,month);
		return faculties;

	}

	@RequestMapping("/addFacultyPage")
	public String addFaculty(HttpServletRequest request, HttpServletResponse response,		
			Map<String, Object> map)
	{	
		List<Faculty> facultyList = facultyService.listFaculty();
		PagedListHolder pagedListHolder = new PagedListHolder(facultyList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 10;
		pagedListHolder.setPageSize(pageSize);
		map.put("pagedListHolder", pagedListHolder);

		map.put("action","add");		
		map.put("faculty", new Faculty());			
		return "addFacultyView";
	}

	@RequestMapping("/editFaculty/{facultyId}")
	public String editFaculty(
			@PathVariable("facultyId")Integer facultyId, HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> map)
	{
		List<Faculty> facultyList = facultyService.listFaculty();
		PagedListHolder pagedListHolder = new PagedListHolder(facultyList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 10;
		pagedListHolder.setPageSize(pageSize);
		map.put("pagedListHolder", pagedListHolder);		
		map.put("action","edit");
		Faculty faculty = facultyService.getFacultyById(facultyId);
		map.put("faculty", faculty);
		return "addFacultyView";
	}	

	@RequestMapping(value = "/saveFaculty", method = RequestMethod.POST)
	public String saveFaculty(@ModelAttribute("faculty") 
	Faculty faculty,Map<String, Object> map) {			
		facultyService.addFaculty(faculty);			
		return "redirect:/addFacultyPage";
	}

	@RequestMapping(value="/saveAttendance/{id}",  produces="application/json", method = RequestMethod.POST)
	public @ResponseBody List<Faculty> saveAttendance(
			@PathVariable("id")Integer facultyId,
			HttpServletRequest request, HttpServletResponse response,Map<String, Object> map)
			{
				String id =  request.getParameter("id");		
				String attendanceDate = request.getParameter("attendanceDate");
				String noOfHours = request.getParameter("noOfHours");				
				Faculty faculty = facultyService.getFacultyById(facultyId);
				Attendance attendance = new Attendance(id,faculty, Utils.formatDate(attendanceDate), new Double(noOfHours));				
				facultyService.saveAttendance(attendance);				
				return listAttendanceByFacultyId(facultyId, Utils.formatDate(attendanceDate).getMonth()+1);
			}
	
	@RequestMapping(value="/saveAttendancePage/{id}" , method = RequestMethod.POST)
	public String saveAttendancePage(
			@PathVariable("id")Integer facultyId,
			HttpServletRequest request, HttpServletResponse response,Map<String, Object> map)
			{
				String id =  request.getParameter("id");		
				String attendanceDate = request.getParameter("attendanceDate");
				String noOfHours = request.getParameter("noOfHours");				
				Faculty faculty = facultyService.getFacultyById(facultyId);
				Attendance attendance = new Attendance(id,faculty, Utils.formatDate(attendanceDate), new Double(noOfHours));				
				facultyService.saveAttendance(attendance);				
				return "redirect:/detailAttendanceById/"+facultyId+"/-1";
			}
	
	@RequestMapping(value = "/pdf/{report_name}/{month}/{facultyId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadAttendanceReport(HttpServletRequest request,
    		@PathVariable("report_name") String reportName, 
    		@PathVariable("month") Integer month,
    		@PathVariable("facultyId") Integer facultyId) throws Exception {
    	
    	System.out.println(reportName +""+month +""+ facultyId);
    	Map<Object, Object> params = new HashMap<Object, Object>();
    	params.put("faculty", facultyService.getFacultyById(facultyId));
    	params.put("attendanceList", facultyService.listAttendanceByFaculty(facultyId,month));   	
    	
        String logoURL = Utils.getContextPath(request,"/images/logo.png");
        FileInputStream fin = new FileInputStream(PdfWriterUtil.createPdf(reportName,logoURL,params));
        byte[] contents = IOUtils.toByteArray(fin);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = reportName+".pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
        return response;
    	
    }
    
    @RequestMapping("/getFacultiesListView")
	public String facultiesListView(HttpServletRequest request, HttpServletResponse response,		
			Map<String, Object> map)
	{	
    	List<Faculty> facultyList = facultyService.listFaculty();
		PagedListHolder pagedListHolder = new PagedListHolder(facultyList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 10;
		pagedListHolder.setPageSize(pageSize);
		map.put("pagedListHolder", pagedListHolder);
    	return "facultiesListView";
	}
    
    @RequestMapping("/deleteFaculty/{id}")
	public @ResponseBody String deleteFaculty(
			@PathVariable("id") Integer id)
	{
    	Faculty faculty = facultyService.getFacultyById(id);
    	facultyService.removeFaculty(faculty);
		return "/sfm/getFacultiesListView";
	}
    
    
    @RequestMapping(value="detailAttendanceById/{facultyId}/{month}")
	public String detailAttendanceById(@PathVariable("facultyId")Integer facultyId, @PathVariable("month")Integer month, HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {		
		if(month==-1){
			month = new Date().getMonth() + 1;
		}
    	List<Faculty> facultyList = facultyService.listAttendanceByFaculty(facultyId,month);
		PagedListHolder pagedListHolder = new PagedListHolder(facultyList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		map.put("pagedListHolder", pagedListHolder);
		map.put("facultyList", facultyList);
		Attendance attendance = new Attendance();
		attendance.setFaculty(facultyService.getFacultyById(facultyId));
		map.put("attendance", attendance);		
		return "viewAttendancePage";
	}
    
    @RequestMapping("/deleteFacultyAttendance/{facultyId}/{attendanceId}")
	public @ResponseBody String deleteFacultyAttendance(
			@PathVariable("facultyId") Integer facultyId, @PathVariable("attendanceId") Integer attendanceId)
	{    	
    	facultyService.removeFacultyAttendance(attendanceId);
		return "/sfm/detailAttendanceById/"+facultyId+"/-1";
	}
    
    @RequestMapping("/editFacultyAttendance/{facultyId}/{attendanceId}/{month}")
	public String editFacultyAttendance(
			@PathVariable("facultyId")Integer facultyId, @PathVariable("attendanceId")Integer attendanceId, @PathVariable("month")Integer month, HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> map)
	{
    	if(month==-1){
			month = new Date().getMonth() + 1;
		}
    	List<Faculty> facultyList = facultyService.listAttendanceByFaculty(facultyId,month);
		PagedListHolder pagedListHolder = new PagedListHolder(facultyList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		map.put("pagedListHolder", pagedListHolder);
		map.put("facultyList", facultyList);
		Attendance attendance = facultyService.getAttendanceById(attendanceId);
		map.put("facultyId", facultyId);
		map.put("attendance", attendance);		
		return "viewAttendancePage";
	}
}