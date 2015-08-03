<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<script>
function getPendingValue(value){

$('#pendingFees').val($('#studentFees').val() - value);
}

function validate(){
	var action = $('form').attr('action');
	var patt = new RegExp("[0-9]+$");    
	if(!patt.test(action)){
			$('form').attr('action', action+$('#userId').val());
	}
	if($('#userId').val()==undefined ||$('#userId').val()==null || $('#userId').val()=='' ){
		alert('User id cannot left blank. Please select any user.');
		return false;
	}
}
</script>
<fieldset>
  	<legend>fees</legend>
  	<c:url var="action" value="/saveFees/${user.id}" ></c:url>
  	<form:form method="post" action="${action}" commandName="fees" cssClass="bookForm" onsubmit="return validate();">
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
			<form:label path="user.fullName" cssClass="userNameLabel">
				<spring:message code="label.username" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="user.fullName" id="userName" cssClass="autocompletesearch"  placeholder="User Name"/>
		</td> 
	</tr>
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
			<form:label path="user.studentFees" cssClass="nameLabel">
				<spring:message code="label.studentFees" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:input path="user.studentFees" id="studentFees" readonly="true" disabled="true" placeholder="Total Fees"/>		
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="noOfInstallment" cssClass="nameLabel">
				<spring:message code="label.noOfInstallment" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:input path="noOfInstallment" id="noOfInstallment"  value = "1" />		
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="paidFees" cssClass="nameLabel">
				<spring:message code="label.paidFees" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:input path="paidFees" id="paidFees"  placeholder="paidFees" onblur="getPendingValue(this.value);"/>		
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
	<tr>
	<td>
		<c:if test="${!empty user.id}">
				<input type="submit"
					value="<spring:message code="label.editfees"/>" class="button"/>
			</c:if>
			<c:if test="${empty user.id}">
				<input type="submit"
					value="<spring:message code="label.addfees"/>" class="button"/>
			</c:if>        
	</td>
	<td>
		<c:if test="${!empty user.id}">
			<a href="<c:url value="/viewFeesByUserId/${user.id}" />" class="button">Cancel</a>
		</c:if>
	</td>
	</tr>	
</table>
</form:form>
</fieldset>
<fieldset>
	<legend>View Fees Payment</legend>
	<p style="color: green; font-weight: bold;">
		Add Fees <a
			href="<c:url value='/addFees' />"> <img
			src="<c:url value='/images/vcard_add.png' />"
			title="Add fees" />
		</a>
	</p>
<jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder"/>
<c:url value="/viewFeesByUserId/${fees.user.id}" var="pagedLink">
	<c:param name="action" value="list"/>
    <c:param name="p" value="~"/>
</c:url>
<div style="padding-left:40%">
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
</div>
<table class="bookTable">
				<tr>					
					<th>Fee ID</th>
					<th>Addition Fees</th>
					<th>Paid Fees</th>
					<th>Pending Fees</th>
					<th>Next Due</th>
					<th>Action</th>
				</tr>
	<c:choose>	
    <c:when test="${!empty pagedListHolder.pageList}">			
				<c:forEach items="${pagedListHolder.pageList}" var="fees">
					<tr>
						<td align="center"><a href="<c:url value='/editFees/${fees.user.id}/${fees.id}' />">${fees.id}</a></td>
						<td>${fees.additionCharges}</td>
						<td>${fees.paidFees}</td>
						<td>${fees.pendingFees}</td>						
						<td><fmt:formatDate pattern="dd-MMM-yyyy" value="${fees.nextPaymentDueDate}" /></td>
						<td><img src="<c:url value='/images/vcard_delete.png' />"
							title="Delete User" onclick="javascript:deleteUser(${fees.id})" />
							<a href="<c:url value='/editFees/${fees.user.id}/${fees.id}' />"> <img
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
	<c:choose>		
	 <c:when test="${!empty pagedListHolder.pageList}">		
		<a href="${pageContext.request.contextPath}/pdf/user_fees_report/${fees.user.id}" css="button">user Fees Report</a>
	</c:when>
	</c:choose>
</fieldset>