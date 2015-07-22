<header id="header" class="clear">

	<div class="top-header">
		<div class="header-logos">
			<span class="pull-left"><img src="${pageContext.request.contextPath}/images/logo.png"
				alt="GE Logo" /></span>
			<!-- <nav>
				<ul>
					<li><a href="#">Text Link</a></li>
					<li><a href="#">Text Link</a></li>
					<li><a href="#">Text Link</a></li>
					<li><a href="#">Text Link</a></li>
					<li class="last"><a href="#">Text Link</a></li>
				</ul>
			</nav> -->
			<c:if test="${pageContext.request.userPrincipal.name != null}">
			<span class="pull-right top-links register-btn"><!-- <span><a
					href="./login.html">Sign In</a></span><span> or </span --><span
				class="register-btn"><a href="${pageContext.request.contextPath}/logout"
					title="Logout">Logout</a></span></span>
			</c:if>
		</div>
	</div>
</header>