<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
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
<%-- // use our pagedListHolder --%>
<jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder"/>
<%-- // create link for pages, "~" will be replaced later on with the proper page number --%>
<c:url value="/viewUserList" var="pagedLink">
	<c:param name="action" value="list"/>
    <c:param name="p" value="~"/>
</c:url>

<%-- // load our paging tag, pass pagedListHolder and the link --%>
<div style="padding-left:40%">
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
</div>

<%-- // show only current page worth of data --%>
<table width="100%" >
<tr>
	<th width="10">UserID</th>
	<th width="40">Name</th>
	<th width="20">Stream</th>
	<th width="20">Session</th>
	<th width="10">Action</th>
</tr>
    <c:choose>	
    <c:when test="${!empty userList}">			
				<c:forEach items="${pagedListHolder.pageList}" var="user">
					<tr>
						<td>${user.id}</td>
						<td><a href="<c:url value='/edituser/${user.id}' />">${user.firstName}&nbsp;${user.middleName}&nbsp;${user.lastName}/${user.fatherName }</a></td>
						<td>${user.userProfile.streamName}</td>
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

<%-- // load our paging tag, pass pagedListHolder and the link --%>
<div style="padding-left:40%">
<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
</div>	
</fieldset>