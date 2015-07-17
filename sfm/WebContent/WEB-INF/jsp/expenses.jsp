<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
  	<legend>Expenses</legend>
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
</table>
</form:form>
</fieldset>
