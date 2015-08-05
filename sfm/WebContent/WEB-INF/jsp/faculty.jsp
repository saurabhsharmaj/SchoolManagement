<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>

<fieldset>
  	<legend>Expenses</legend>
  	<c:url var="action" value="/saveFaculty" ></c:url>
  	<form:form method="post" action="${action}" commandName="faculty" cssClass="bookForm">
	<table>
	<c:if test="${!empty faculty.id}">
	<tr>
		<td>
			<form:label path="id" cssClass="userNameLabel">
				<spring:message code="label.facultyId" />
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
			<form:label path="facultyName" cssClass="userNameLabel">
				<spring:message code="label.facultyName" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="facultyName" id="facultyName" placeholder="Faculty Name"/>
		</td> 
	</tr>
	
	<tr>
		<td>
			<form:label path="facultyHourlyRate" cssClass="userNameLabel">
				<spring:message code="label.facultyHourlyRate" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="facultyHourlyRate" id="facultyHourlyRate"  placeholder="faculty Hourly Rate"/>
		</td> 
	</tr>
	
	
	
	<tr>
		<td>
			<form:label path="subject" cssClass="nameLabel">
				<spring:message code="label.subject" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="subject" id="subject"  placeholder="subject"/>
		</td>
		 
	</tr>	
	<tr>
	<td>
		<c:if test="${!empty id}">
				<input type="submit"
					value="<spring:message code="label.editfaculty"/>" class="button"/>
			</c:if>
			<c:if test="${empty id}">
				<input type="submit"
					value="<spring:message code="label.addfaculty"/>" class="button"/>
			</c:if>        
	</td>
	<td>
		<c:if test="${!empty id}">
				<a href="<c:url value="/getAttendanceByFacultyId/${id}" />" class="button">Cancel</a>
		</c:if>
	</td>
	</tr>
</table>
</form:form>
</fieldset>
<%@ include file="/WEB-INF/jsp/facultyList.jsp" %>
