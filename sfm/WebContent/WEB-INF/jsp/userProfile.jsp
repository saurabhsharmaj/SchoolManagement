<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
  	<legend>User Profile</legend>	
<form:form method="post" action="${action}" commandName="userProfile" cssClass="bookForm">
	<table>
	<c:if test="${!empty userProfile.id}">
	<tr>
		<td>
			<form:label path="id" cssClass="userNameLabel">
				<spring:message code="label.userProfileId" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />			
			<form:hidden path="userId" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="roleId" cssClass="nameLabel">
				<spring:message code="label.role" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:select id="roleId" path="roleId">
				<form:option value="0" label="-Select Role-"/>			
			    <form:options items="${roleList}" />
			</form:select>			
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="gender" cssClass="nameLabel">
				<spring:message code="label.gender" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:radiobutton path="gender" value="M"/>Male 
			<form:radiobutton path="gender" value="F"/>Female		
		</td> 
	</tr>
	
	

	<tr>
		<td>
			<form:label path="imageUrl" cssClass="nameLabel">
				<spring:message code="label.imageURL" />
			</form:label>
		</td>
		<td>
			<form:input path="imageUrl" placeholder="imageURL"/>
		</td>		
	</tr>		
	
	<tr>
		<td>
			<form:label path="dob" cssClass="nameLabel">
				<spring:message code="label.dob" />
			</form:label>
		</td>
		<td>
			<form:input path="dob" placeholder="dob"/>
		</td>		
	</tr>
	
	<tr>
		<td>
			<form:label path="email" cssClass="nameLabel">
				<spring:message code="label.email" />
			</form:label>
		</td>
		<td>
			<form:input path="email" placeholder="email"/>
		</td>		
	</tr>
	
	<tr>
		<td>
			<form:label path="contactno" cssClass="nameLabel">
				<spring:message code="label.contactNo" />
			</form:label>
		</td>
		<td>
			<form:input path="contactno" placeholder="contactNo"/>
		</td>		
	</tr>
	
</table>
<a href="<c:url value='/edituser/${userId}' />">Back</a>
</form:form>
</fieldset>
