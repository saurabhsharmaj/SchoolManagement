<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
  	<legend>Registration</legend>

<form:form method="post" action="${action}" commandName="user" cssClass="bookForm">
	<table>
	<c:if test="${!empty user.firstName}">
	<tr>
		<td>
			<form:label path="id" cssClass="userNameLabel">
				<spring:message code="label.userId" />
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
			<form:label path="firstName" cssClass="nameLabel">
				<spring:message code="label.firstname" />
			</form:label>
		</td>
		<td>
			<form:input path="firstName" placeholder="first name"/>
		</td>		 
	</tr>
	<tr>
		<td>
			<form:label path="middleName" cssClass="nameLabel">
				<spring:message code="label.middlename" />
			</form:label>
		</td>
	<td>
			<form:input path="middleName"  placeholder="middle name" />
		</td> 
    </tr>
    <tr>
		<td>
			<form:label path="lastName" cssClass="nameLabel">
				<spring:message code="label.lastname" />
			</form:label>
		</td>
		<td>
			<form:input path="lastName"  placeholder="last name" />
		</td>
	</tr>
	<tr>	
		<td>
			<form:label path="fatherName" cssClass="nameLabel">
				<spring:message code="label.fathername" />
			</form:label>
		</td>	
		<td>
			<form:input path="fatherName"  placeholder="fatherName" />
		</td> 
	</tr>	
	<tr>	
		<td>
			<form:label path="userName" cssClass="nameLabel">
				<spring:message code="label.username" />
			</form:label>
		</td>	
		<td>
			<form:input path="userName"  placeholder="username" />
		</td> 
	</tr>
	<tr>	
		<td>
			<form:label path="password" cssClass="nameLabel">
				<spring:message code="label.password" />
			</form:label>
		</td>
		<td>
			<form:input path="password"  placeholder="password" />
		</td> 
	</tr>
	
	<tr>
		<td>
			<form:label path="batch" cssClass="nameLabel">
				<spring:message code="label.batch" />
			</form:label>
		</td>
		<td colspan="2">
			<form:select id="batch" path="batch">
				<form:option value="0" label="-Select Batch-"/>			
			    <form:options items="${batchList}" />
			</form:select>
			<form:select id="session" path="session">
				<form:option value="0" label="-Select Session Year-"/>			
			    <form:options items="${sessionYearList}" />
			</form:select>
		</td> 
	</tr>
	<tr>	
		<td>
			<form:label path="AddmissionDate" cssClass="nameLabel">
				<spring:message code="label.addmissiondate" />
			</form:label>
		</td>	
		<td>
			<form:input path="AddmissionDate" cssClass="datepicker" placeholder="Addmission Date" />
		</td>
	</tr>
	<tr> 
		<td>
			<form:label path="status" cssClass="nameLabel">
				<spring:message code="label.status" />
			</form:label>
		</td>
		<td>
			<form:select id="status" path="status">
						<form:option value="0" label="-Select Status-"/>			
					    <form:options items="${statusList}" />
					</form:select>
		</td> 
	</tr>
	<tr>
	<td>
		<c:if test="${!empty user.id}">
				<input type="submit"
					value="<spring:message code="label.edituser"/>" class="button"/>
			</c:if>
			<c:if test="${empty user.id}">
				<input type="submit"
					value="<spring:message code="label.adduser"/>" class="button"/>
			</c:if>        
	</td>
	<td>
		<a href="<c:url value="/viewUserList" />" class="button">Cancel</a>
	</td>
	</tr>
</table>
<c:if test="${!empty user.id}">
<a href="<c:url value="/editProfile/${user.id}" />">next</a>
</c:if>
</form:form>
</fieldset>