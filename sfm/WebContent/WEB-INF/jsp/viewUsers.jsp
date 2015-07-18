<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
	<legend>List of Users</legend>
	<p style="color: green; font-weight: bold;">
		To add a New Student please click <a
			href="<c:url value='/addUser' />"> <img
			src="<c:url value='/images/vcard_add.png' />"
			title="Add a New Student" />
		</a>
	</p>
	<c:url var="action" value="/user/add.html"></c:url>

<table class="bookTable">
				<tr>
					<th width="10">UserID</th>
					<th width="40">Name</th>
					<th width="20">Stream</th>
					<th width="20">Session</th>
					<th width="10">Action</th>
				</tr>
	<c:choose>	
    <c:when test="${!empty userList}">			
				<c:forEach items="${userList}" var="user">
					<tr>
						<td>${user.id}</td>
						<td><a href="<c:url value='/edituser/${user.id}' />">${user.firstName}&nbsp;${user.middleName}&nbsp;${user.lastName}/${user.fatherName }</a></td>
						<td>${user.userProfile.stream}</td>
						<td>${user.session} \ ${user.batch}</td>
						<td><img src="<c:url value='/images/vcard_delete.png' />"
							title="Delete User" onclick="javascript:deleteUser(${user.id})" />
							<a href="<c:url value='/edituser/${user.id}' />"> <img
								src="<c:url value='/images/vcard_add.png' />" title="Edit User" />
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
