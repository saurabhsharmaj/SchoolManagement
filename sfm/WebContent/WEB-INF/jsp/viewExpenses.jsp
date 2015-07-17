<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
	<legend>View expenses</legend>
	<p style="color: green; font-weight: bold;">
		Add expense <a
			href="<c:url value='/addExpense' />"> <img
			src="<c:url value='/images/vcard_add.png' />"
			title="Add expense" />
		</a>
	</p>
	<c:url var="action" value="/user/add.html"></c:url>

<table class="bookTable">
				<tr>
					<th width="60">UserID</th>
					<th width="160">UserName</th>
					<th width="160">Expense Type</th>
					<th width="160">Description</th>
					<th width="160">Amount</th>
					<th width="60">Action</th>
				</tr>
	<c:choose>	
    <c:when test="${!empty expensesList}">			
				<c:forEach items="${expensesList}" var="expense">
					<tr>
						<td>${expense.user.id}</td>
						<td><a href="<c:url value='/editExpense/${expense.user.id}/${expense.id}' />">${expense.user.firstName} &nbsp;${expense.user.middleName} &nbsp;${expense.user.lastName}</a></td>
						<td>${expense.expenseType}</td>
						<td>${expense.description}</td>
						<td>${expense.amount}</td>
						<td><img src="<c:url value='/images/vcard_delete.png' />"
							title="Delete User" onclick="javascript:deleteexpensees(${expense.id})" />
							<a href="<c:url value='/editExpense/${expense.user.id}/${expense.id}' />"> <img
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
