<h2 class="title">Menus</h2>
      <nav>
        <ul>
          <li><a href="${pageContext.request.contextPath}/viewUserList">User Management</a></li>
          <li><a href="${pageContext.request.contextPath}/viewExpenses">Expenses</a></li>
          <li><a href="${pageContext.request.contextPath}/viewFees">Fees Management</a></li>
          <%-- <li><a href="${pageContext.request.contextPath}/viewFeeDetailByUserId/18">Fees Management</a></li> --%>          
          <li><a href="${pageContext.request.contextPath}/paymentRecived">Reports</a></li>         
        </ul>
      </nav>
      <!-- /nav -->
      <h2 class="title">Today Important:</h2>
      <section class="last" >
        <marquee direction="up" onmouseover="stop()" onmouseout="start()" height="300px;">
        <div class="opinion_hmlist1">
		<ul  style="background-color:#FFFFF;">
			<li><a href="">Saurabh Sharma</a></li>
			<li><a href="">Jitendra</a></li>
			<li><a href="">Shanker</a></li>
			<li><a href="">Laxman</a></li>
		</ul>
		</div>
	</marquee>
      </section>