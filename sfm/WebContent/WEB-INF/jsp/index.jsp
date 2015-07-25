<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="login-box">
<script type="text/javascript">
function validateform(){
	var msg ='';
	var valid = true;
	if($('#username').val()==''){
		valid = false;
		msg = 'Please Enter valid User name. <br>';
	}
	
	if($('#password').val()==''){
		valid = false;
		msg = msg +'Please Enter valid password.';
	}
	 
   if(!valid){
   	 alert(msg);
   }
   return valid;
}
</script>

	<h3>Login with Username and Password</h3>

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>

	<form name='loginForm' action="<c:url value='/login' />" method='POST' onsubmit="validateform();">

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