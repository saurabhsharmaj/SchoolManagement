<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Fees Management System</title>
<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css" type="text/css">
<script type="text/javascript">
function validateform(){
	var msg ='';
	var valid = true;
	if($('#username').val()==undefined || $('#username').val()==''){
		valid = false;
		msg = 'Please Enter valid User name. \n';
	}
	
	if($('#password').val()==undefined || $('#password').val()==''){
		valid = false;
		msg = msg +'Please Enter valid password.';
	}
	 
   if(!valid){
   	 alert(msg);
   }
   return valid;
}
</script>
</head>
<body onload='document.loginForm.username.focus();'>

	<h1 style="padding-left:45%;padding-top:2%;"><span style="padding-left: 5%;"><img src="./images/logo.png"/></span><p>Fees Management</p></h1>
	
	<div id="login-box">

		<h3>Login with Username and Password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm' action="<c:url value='/login' />" method='POST' onsubmit="return validateform();">

			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' id="username" name='username' value=''></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' id="password" name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>

			<input type="hidden" class="button" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
	</div>

</body>
</html>