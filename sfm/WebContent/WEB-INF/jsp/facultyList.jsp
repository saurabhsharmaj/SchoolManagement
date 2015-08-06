<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<fieldset>
<legend>Expenses List:</legend>
<p style="color: green; font-weight: bold;">
		To add a New Faculty please click <a
			href="<c:url value='/addFacultyPage' />"> <img
			src="<c:url value='/images/vcard_add.png' />"
			title="Add a New Faculty" />
		</a>
</p>
	
<jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder"/>
<c:url value="/viewFacultyAttendanceById/${faculty.id}" var="pagedLink">
	<c:param name="action" value="list"/>
    <c:param name="p" value="~"/>
</c:url>
<div style="padding-left:40%">
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
</div>
<table class="bookTable">
				<tr>
					<th width="60">ID</th>
					<th width="160">Name</th>
					<th width="160">Subject</th>					
					<th width="160">Hourly Rate</th>					
					<th width="60">Action</th>
				</tr>
	<c:choose>	
    <c:when test="${!empty pagedListHolder.pageList}">			
				<c:forEach items="${pagedListHolder.pageList}" var="faculty">
					<tr>
						<td>${faculty.id}</td>
						<td><a href="<c:url value='/editFaculty/${faculty.id}' />">${faculty.facultyName}</a></td>
						<td>${faculty.subject}</td>
						<td>${faculty.facultyHourlyRate}</td>
						<td>
							<a href="#" id ="${faculty.id}" url='/sfm/deleteFaculty/${faculty.id}' class="deleteButton"> <img
								src="<c:url value='/images/vcard_delete.png' />" title="Delete Faculty" />
							</a>
							<a href="<c:url value='/editFaculty/${faculty.id}' />"> <img
								src="<c:url value='/images/vcard_add.png' />" title="Edit Faculty" />
							</a>
							<a class="downloadPdf" id="${faculty.id}"><img
									src="<c:url value='/images/pdf.png' />" title="Download Attendance Report" />
							</a>
							<a href="/sfm/detailAttendanceById/${faculty.id}/-1"><img
									src="<c:url value='/images/view_detail.png' />" title="Attendance Detail" />
							</a>
						</td>
					</tr>
				</c:forEach>
			
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan=5 style="  text-align: center;border: 1px solid;">
				No Record Available
			</td>
		</tr>
	</c:otherwise>	
	</c:choose>
	</table>	
</fieldset>
<div id="downloadAttendanceReportDialog">
	<form name="downloadReportForm" id="downloadReportForm">
		<table>
		<tr><td>
		<input type="hidden" id="action" name="action" />
		<input type="hidden" id="facultyId"/>
		Faculty Id:</td><td><input type="text" readonly="true" id="vfacultyId"/>
		</td>
		</tr>
		<tr><td>Select Month:</td>
		 <td><select id="month">
			<option value="1">Jan</option>
			<option value="2">Feb</option>
			<option value="3">Mar</option>
			<option value="4">Apr</option>
			<option value="5">May</option>
			<option value="6">Jun</option>
			<option value="7">Jul</option>
			<option value="8">Aug</option>
			<option value="9">Sep</option>
			<option value="10">Oct</option>
			<option value="11">Nov</option>
			<option value="12">Dec</option>
		</select>
		</td>
		</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function() {

jQuery.download = function(url, data, method){
    //url and data options required
    if( url && data ){
        //data can be string of parameters or array/object
        data = typeof data == 'string' ? data : jQuery.param(data);
        //split params into form inputs
        var inputs = '';
        jQuery.each(data.split('&'), function(){
            var pair = this.split('=');
            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />';
        });
        //send request
        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>')
        .appendTo('body').submit().remove();
    }
}

function downloadReport(){
//href="<c:url value='${pageContext.request.contextPath}/pdf/faculty_attendance_report/${faculty.id}' />"
	$('#downloadAttendanceReportDialog').dialog('open');
}

$('.downloadPdf').on('click',function(ref){
 	$('#facultyId').val(ref.currentTarget.id);
 	$('#vfacultyId').val(ref.currentTarget.id);
 	$('#month').val(new Date().getMonth()+1);
	$('#downloadAttendanceReportDialog').dialog('open');
});
 $('#downloadAttendanceReportDialog').dialog({
        resizable: false,
        autoOpen: false,
        title: 'Add Event',
        width: 400,
        buttons: {
            Save: function() {
             var action = '/sfm/pdf/faculty_attendance_report/'+$('#month').val()+"/"+$('#facultyId').val();             
             $('#action').val(action);             
             $.download(action,'faculty_attendance_report','GET');
              $(this).dialog('close');           
            },
            Cancel: function() {
                $(this).dialog('close');
            }
        }
    });
    
 $("#downloadAttendanceReportDialog").dialog("option", "title", "Download Attendance Report");
 
});
</script>