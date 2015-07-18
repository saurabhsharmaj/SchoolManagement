<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<th width="60">UserID</th>
					<th width="160">UserName</th>
					<th width="160">Total Fees</th>
					<th width="160">Total Expenses</th>
					<th width="160">Paid Fees</th>
					<th width="160">Pending Fees</th>
					<th width="160">Next Due</th>
					<th width="60">Action</th>
				</tr>
	<c:choose>	
    <c:when test="${!empty feesPaymentList}">			
				<c:forEach items="${feesPaymentList}" var="fees">
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
			<td colspan=5 style="  text-align: center;border: 1px solid;">
				No Record Available
			</td>
		</tr>
	</c:otherwise>	
	</c:choose>
	</table>
</fieldset>
