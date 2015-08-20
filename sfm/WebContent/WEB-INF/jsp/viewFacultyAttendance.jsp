<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<script>
function validate(){
if($('#id').val()==undefined || $('#id').val()=='' ){
alert('Please select attendance before save.');
return false;
}
else {
return true;
}
}
</script>
<fieldset>
  	<legend>Faculty Attendance</legend>
  	<c:url var="action" value="/saveAttendancePage/${facultyId}" ></c:url>
  	<form:form method="post" action="${action}" commandName="attendance" cssClass="bookForm" onsubmit="return validate();">
	<table>
	<c:if test="${!empty attendance.id}">
	<tr>
		<td>
			<form:label path="id" cssClass="userNameLabel">
				<spring:message code="label.attendanceId" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />			
			
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>			
			<form:label path="faculty.facultyName" cssClass="userNameLabel">
				<spring:message code="label.facultyName" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="faculty.facultyName" id="facultyName" readonly="true" placeholder="Faculty Name"/>
		</td> 
	</tr>
	<tr>
		<td>			
			<form:label path="attendanceDate" cssClass="userNameLabel">
				<spring:message code="label.attendanceDate" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="attendanceDate" id="attendanceDate"  cssClass="datepicker" placeholder="attendanceDate"/>
		</td> 
	</tr>
	
	<tr>
		<td>
			<form:label path="noOfHours" cssClass="userNameLabel">
				<spring:message code="label.noOfHours" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="noOfHours" id="noOfHours"  placeholder="noOfHours"/>
		</td> 
	</tr>
	
	
	
	
	<tr>
	<td>
		<c:if test="${empty id}">
				<input type="submit"
					value="<spring:message code="label.editAttendance"/>" class="button"/>
			</c:if>
			<c:if test="${!empty id}">
				<input type="submit"
					value="<spring:message code="label.addAttendance"/>" class="button"/>
			</c:if>        
	</td>
	<td>
		<c:if test="${empty id}">
				<a href="<c:url value="/getFacultiesListView" />" class="button">Cancel</a>
		</c:if>
	</td>
	</tr>
</table>
</form:form>
</fieldset>

<fieldset>
<legend>Expenses List:</legend>
<p style="color: green; font-weight: bold;">
		To add a New Attendance please click <a
			href="<c:url value='/detailAttendanceById/${attendance.faculty.id}/-1' />"> <img
			src="<c:url value='/images/vcard_add.png' />"
			title="Add a New Faculty" />
		</a>
</p>
	
<jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder"/>
<c:url value="/detailAttendanceById/${attendance.faculty.id}/-1" var="pagedLink">
	<c:param name="action" value="list"/>
    <c:param name="p" value="~"/>
</c:url>
<div style="padding-left:40%">
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
</div>
<table class="bookTable">
				<tr>
					<th width="60">ID</th>
					<th width="160">Attendance Date</th>
					<th width="160">Hours</th>									
					<th width="60">Action</th>
				</tr>
	<c:choose>	
    <c:when test="${!empty pagedListHolder.pageList}">			
				<c:forEach items="${pagedListHolder.pageList}" var="attendance">
					<tr>
						<td>${attendance.id}</td>
						<td><a href="<c:url value='/editFacultyAttendance/${attendance.faculty.id}/${attendance.id}/-1' />">
						<fmt:formatDate pattern="dd-MMM-yyyy" value="${attendance.attendanceDate}" />
						</a></td>
						<td>${attendance.noOfHours}</td>						
						<td>
							<a href="#" id="${attendance.id}" url='/sfm/deleteFacultyAttendance/${attendance.faculty.id}/${attendance.id}' class="deleteButton"> <img
								src="<c:url value='/images/vcard_delete.png' />" title="Delete Attendance" />
							</a>
							<a href="<c:url value='/editFacultyAttendance/${attendance.faculty.id}/${attendance.id}/-1' />"> <img
								src="<c:url value='/images/vcard_add.png' />" title="Edit Attendance" />
							</a>							
						</td>
					</tr>
				</c:forEach>
			
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan=4 style="  text-align: center;border: 1px solid;">
				No Record Available
			</td>
		</tr>
	</c:otherwise>	
	</c:choose>
	</table>	
</fieldset>
