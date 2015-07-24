<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
	<legend>View Fees Payment</legend>
	<p style="color: green; font-weight: bold;">
		Add Fees <a
			href="<c:url value='/addFees' />"> <img
			src="<c:url value='/images/vcard_add.png' />"
			title="Add fees" />
		</a>
	</p>

<table class="bookTable">
				<tr>
					<th width="5">UserID</th>
					<th width="20">UserName</th>
					<th width="12">Total Fees</th>
					<th width="12.5">Total Expenses</th>
					<th width="12.5">Paid Fees</th>
					<th width="12.5">Additional Fees</th>
					<th width="12.5">Pending Fees</th>
					<th width="12.5">Next Due</th>
					<th width="12.5">Action</th>
				</tr>
	<c:choose>	
    <c:when test="${!empty feesList}">			
				<c:forEach items="${feesList}" var="fees">
					<tr>
						<td>${fees.id}</td>
						<td><a href="<c:url value='/viewFeesByUserId/${fees.id}' />">${fees.fullName}&nbsp; / &nbsp;${fees.fatherName}</a></td>
						<td>${fees.totalFees}</td>
						<td>${fees.totalExpenses}</td>
						<td>${fees.totalPaidFees}</td>
						<td>${fees.totalAdditionCharges}</td>
						<td>${fees.totalPendingFees}</td>
						<td>${fees.nextDueDate}</td>
						<td>
							<a href="<c:url value='/viewFeesByUserId/${fees.id}' />">Show Detail </a>
						</td>
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
</fieldset>
<a href="${pageContext.request.contextPath}/pdf/fees_report/${user.id}" css="button">Fees Report</a>
