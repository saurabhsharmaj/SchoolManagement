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
