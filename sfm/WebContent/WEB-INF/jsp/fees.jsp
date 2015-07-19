<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
  	<legend>fees</legend>
  	<form:form method="post" action="${action}" commandName="fees" cssClass="bookForm">
	<table>
	<c:if test="${!empty fees.id}">
	<tr>
		<td>
			<form:label path="id" cssClass="userNameLabel">
				<spring:message code="label.feesId" />
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
			<form:label path="user.id" cssClass="userNameLabel">
				<spring:message code="label.userId" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="user.id" id="userId"  placeholder="user Id"/>
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="totalFees" cssClass="nameLabel">
				<spring:message code="label.totalFees" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:input path="totalFees" id="totalFees"  placeholder="Total Fees"/>		
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="noOfInstallment" cssClass="nameLabel">
				<spring:message code="label.noOfInstallment" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:select id="noOfInstallment" path="noOfInstallment">
				<form:option value="0" label="-Select No Of Intallment-"/>			
			    <form:options items="${noOfInstallmentList}" />
			</form:select>			
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="paidFees" cssClass="nameLabel">
				<spring:message code="label.paidFees" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:input path="paidFees" id="paidFees"  placeholder="paidFees"/>		
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="pendingFees" cssClass="nameLabel">
				<spring:message code="label.pendingFees" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:input path="pendingFees" id="pendingFees"  placeholder="pendingFees"/>		
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="additionCharges" cssClass="nameLabel">
				<spring:message code="label.additionalCharge" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:input path="additionCharges" id="additionCharges"  placeholder="additionCharges"/>		
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="nextPaymentDueDate" cssClass="nameLabel">
				<spring:message code="label.nextPaymentDueDate" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:input path="nextPaymentDueDate" id="nextPaymentDueDate" cssClass="datepicker" placeholder="nextPaymentDueDate"/>		
		</td> 
	</tr>	
</table>
</form:form>
</fieldset>
<fieldset>
	<legend>View Fees Payment</legend>
	<p style="color: green; font-weight: bold;">
		Add Fees <a
			href="<c:url value='/addfees' />"> <img
			src="<c:url value='/images/vcard_add.png' />"
			title="Add fees" />
		</a>
	</p>
	<c:url var="action" value="/user/add.html"></c:url>

<table class="bookTable">
				<tr>
					<th width="5">UserID</th>
					<th width="20">UserName</th>
					<th width="12">Total Fees</th>
					<th width="12.5">Total Expenses</th>
					<th width="12.5">Paid Fees</th>
					<th width="12.5">Pending Fees</th>
					<th width="12.5">Next Due</th>
					<th width="12.5">Action</th>
				</tr>
	<c:choose>	
    <c:when test="${!empty user.feeses}">			
				<c:forEach items="${user.feeses}" var="fees">
					<tr>
						<td>${fees.user.id}</td>
						<td><a href="<c:url value='/editfees/${fees.user.id}/${fees.id}' />">${fees.user.firstName} &nbsp;${fees.user.middleName} &nbsp;${fees.user.lastName}</a></td>
						<td>${fees.totalFees}</td>
						<td>${fees.paidFees}</td>
						<td>${fees.pendingFees}</td>
						<td>${fees.additionCharges}</td>
						<td>${fees.nextPaymentDueDate}</td>
						<td><img src="<c:url value='/images/vcard_delete.png' />"
							title="Delete User" onclick="javascript:deleteUser(${fees.id})" />
							<a href="<c:url value='/editfees/${fees.user.id}/${fees.id}' />"> <img
								src="<c:url value='/images/vcard_add.png' />" title="Edit Fees" />
						</a></td>
					</tr>
				</c:forEach>
			
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan=8 style="  text-align: center;border: 1px solid;">
				No Record Available
			</td>
		</tr>
	</c:otherwise>	
	</c:choose>
	</table>
	<a href="${pageContext.request.contextPath}/pdf/user_fees_report/${user.id}" css="button">user Fees Report</a>
</fieldset>