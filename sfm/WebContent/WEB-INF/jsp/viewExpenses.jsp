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
					<th width="160">Amount</th>
					<th style="width:90px">Action</th>
				</tr>
	<c:choose>	
    <c:when test="${!empty expensesList}">			
				<c:forEach items="${expensesList}" var="expense">
					<tr>
						<td>${expense.id}</td>
						<td><a href="<c:url value='/viewExpensesByUserId/${expense.id}' />">${expense.fullName} &nbsp;/ &nbsp;${expense.fatherName}</a></td>
						<td>${expense.totalAmount}</td>
						<td>
							<a href="<c:url value='/viewExpensesByUserId/${expense.id}' />"> Show Details
							</a>
						</td>
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
