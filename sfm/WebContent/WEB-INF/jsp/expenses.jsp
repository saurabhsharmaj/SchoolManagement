<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
  	<legend>Expenses</legend>
  	<c:url var="action" value="/saveExpense/{user.id}" ></c:url>
  	<form:form method="post" action="${action}" commandName="expense" cssClass="bookForm">
	<table>
	<c:if test="${!empty expense.id}">
	<tr>
		<td>
			<form:label path="id" cssClass="userNameLabel">
				<spring:message code="label.expensId" />
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
			<form:label path="user.fullName" cssClass="userNameLabel">
				<spring:message code="label.username" />
			</form:label>
		</td>
		<td colspan="2">
			<form:input path="user.fullName" id="userName" cssClass="autoComplte"  placeholder="User Name"/>
		</td> 
	</tr>
	
	<tr>
		<td>
			<form:label path="expenseType" cssClass="nameLabel">
				<spring:message code="label.expenseType" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:select id="expenseType" path="expenseType">
				<form:option value="0" label="-Select expenseType-"/>			
			    <form:options items="${expenseTypeList}" />
			</form:select>			
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="description" cssClass="nameLabel">
				<spring:message code="label.description" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:input path="description" id="description"  placeholder="description"/>		
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="amount" cssClass="nameLabel">
				<spring:message code="label.amount" />
			</form:label>
		</td>
		
		<td colspan="2">
			<form:input path="amount" id="amount"  placeholder="amount"/>		
		</td> 
	</tr>
	<tr>
	<td>
		<c:if test="${!empty user.id}">
				<input type="submit"
					value="<spring:message code="label.editexpense"/>" class="button"/>
			</c:if>
			<c:if test="${empty user.id}">
				<input type="submit"
					value="<spring:message code="label.addexpense"/>" class="button"/>
			</c:if>        
	</td>
	<td>
		<c:if test="${!empty user.id}">
				<a href="<c:url value="/viewExpensesByUserId/${user.id}" />" class="button">Cancel</a>
		</c:if>
	</td>
	</tr>
</table>
</form:form>
</fieldset>

<fieldset>
<legend>Expenses List:</legend>
<table class="bookTable">
				<tr>
					<th width="60">UserID</th>
					<th width="160">UserName</th>					
					<th width="160">Amount</th>
					<th width="60">Action</th>
				</tr>
	<c:choose>	
    <c:when test="${!empty expensesList}">			
				<c:forEach items="${expensesList}" var="expense">
					<tr>
						<td>${expense.id}</td>
						<td><a href="<c:url value='/editExpense/${expense.user.id}/${expense.id}' />">${expense.user.firstName} &nbsp;/ &nbsp;${expense.user.fatherName}</a></td>
						<td>${expense.amount}</td>
						<td><img src="<c:url value='/images/vcard_delete.png' />"
							title="Delete User" onclick="javascript:deleteUser(${expense.id})" />
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
	<a href="${pageContext.request.contextPath}/pdf/user_exense_report/${expense.user.id}" css="button">user Expenses Report</a>
</fieldset>