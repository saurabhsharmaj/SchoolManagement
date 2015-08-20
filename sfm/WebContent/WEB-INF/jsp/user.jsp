<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="action" value="/user/add.html" ></c:url>

<form:form method="post" action="${action}" enctype="multipart/form-data"  commandName="user" cssClass="bookForm">
<table>
	<tr>
	<td>
	<fieldset style="height: 365px;">
  	<legend>Basic Profile</legend>	
	<table>
	<c:if test="${!empty user.firstName}">
	<tr>
		<td>
			<form:label path="id" cssClass="userNameLabel">
				<spring:message code="label.userId" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="id" id="id" readonly="true" size="8"  disabled="true" />
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
			<form:input path="userName" id="username" placeholder="username" />
		</td> 
	</tr>
	<tr>	
		<td>
			<form:label path="password" cssClass="nameLabel">
				<spring:message code="label.password" />
			</form:label>
		</td>
		<td>
			<form:password path="password" id="password" showPassword="true" placeholder="password" />
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
			<form:label path="studentFees" cssClass="nameLabel">
				<spring:message code="label.studentFees" />
			</form:label>
		</td>
		<td>
			<form:input path="studentFees"  placeholder="total Fees" />
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
	</table>
	</fieldset>
	</td>
	<td>
	<fieldset style="height:365px;">
  	<legend>Additional Profile</legend>
  	<table>	
	<tr>
		<td>
			<form:hidden path="userProfile.id" readonly="true" size="8"  />	
			<form:label path="userProfile.roleId" cssClass="nameLabel">
				<spring:message code="label.role" />
			</form:label>
		</td>
		
		<td>
			<form:select id="roleId" path="userProfile.roleId">
				<form:option value="0" label="-Select Role-"/>			
			    <form:options items="${roleList}" />
			</form:select>			
		</td> 
		<td rowspan=4>
		   <%--  <img src="<c:url value='${pageContext.request.contextPath}\${userProfile.imageUrl}'/>" class="profile-image"/> --%>
		   <img src='${pageContext.request.contextPath}${user.userProfile.imageUrl}' class="profile-image"/>			
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="userProfile.gender" cssClass="nameLabel">
				<spring:message code="label.gender" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:radiobutton path="userProfile.gender" value="true" />Male 
			<form:radiobutton path="userProfile.gender" value="false"/>Female		
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="userProfile.imageUrl" cssClass="nameLabel">
				<spring:message code="label.imageURL" />
			</form:label>
		</td>
		<td>
			<form:hidden path="userProfile.imageUrl" placeholder="imageURL"/>
			<input type="hidden" id="fileName" name="fileName"/>
			<input type="file" name="file" />
		</td>		
	</tr>		
	
	<tr>
		<td>
			<form:label path="userProfile.dob" cssClass="datepicker">
				<spring:message code="label.dob" />
			</form:label>
		</td>
		<td>
			<form:input path="userProfile.dob" placeholder="dob" cssClass="datepicker"/>
		</td>		
	</tr>
	
	<tr>
		<td>
			<form:label path="userProfile.email" cssClass="nameLabel">
				<spring:message code="label.email" />
			</form:label>
		</td>
		<td>
			<form:input path="userProfile.email" placeholder="email"/>
		</td>		
	</tr>
	
	<tr>
		<td>
			<form:label path="userProfile.contactno" cssClass="nameLabel">
				<spring:message code="label.contactNo" />
			</form:label>
		</td>
		<td>
			<form:input path="userProfile.contactno" placeholder="contactNo"/>
		</td>		
	</tr>
	<tr>
		<td>
			<form:label path="userProfile.stream" cssClass="nameLabel">
				<spring:message code="label.stream" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:select id="stream" path="userProfile.stream">
				<form:option value="0" label="-Select Stream-"/>			
			    <form:options items="${streamList}" />
			</form:select>			
		</td> 
	</tr>
	
	<tr>
		<td>
			<form:label path="userProfile.percentage" cssClass="nameLabel">
				<spring:message code="label.percentage" />
			</form:label>
		</td>
		<td>
			<form:input path="userProfile.percentage" placeholder="percentage"/>
		</td>		
	</tr>
	
	<tr>
		<td>
			<form:label path="userProfile.marksInEnglish" cssClass="nameLabel">
				<spring:message code="label.marksInEnglish" />
			</form:label>
		</td>
		<td>
			<form:input path="userProfile.marksInEnglish" placeholder="Marks In English"/>
		</td>		
	</tr>
	
	<tr>
		<td>
			<form:label path="userProfile.groupType" cssClass="nameLabel">
				<spring:message code="label.groupType" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:radiobutton path="userProfile.groupType" value="1" />X Group
			<form:radiobutton path="userProfile.groupType" value="2"/>Y	Group	
		</td> 
	</tr>
	</table>
	</fieldset>
	</td></tr>
	<!-- End Profile -->
	<tr>
	<td>
		<c:if test="${!empty user.id}">
				<input type="submit"
					value="<spring:message code="label.edituser"/>" class="button-orange"/>
			</c:if>
			<c:if test="${empty user.id}">
				<input type="submit"
					value="<spring:message code="label.adduser"/>" class="button-orange"/>
			</c:if>        
	</td>
	<td>
		<a href="<c:url value="/viewUserList" />" class="button-orange">Cancel</a>
	</td>
	</tr>	
</table>
</form:form>

<script type="text/javascript">
$(document).ready(function() {
	if($'#id').val()!=undefined||$'#id').val()!=''){
		$'#username').val('');
		$'#password').val('');
		
	
	}

});