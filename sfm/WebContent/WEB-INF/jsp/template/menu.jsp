<h2 class="title">Menus</h2>
      <nav>
        <ul>
          <li><a href="${pageContext.request.contextPath}/viewUserList">User Management</a></li>
          <li><a href="${pageContext.request.contextPath}/viewFeesPayments">Fees Management</a></li>
          <li><a href="${pageContext.request.contextPath}/viewExpenses">Expenses</a></li>
          <li><a href="${pageContext.request.contextPath}/paymentRecived">Reports</a></li>         
        </ul>
      </nav>
      <!-- /nav -->
      <h2 class="title">Today Important:</h2>
      <section class="last" >
        <marquee direction="up" onmouseover="stop()" onmouseout="start()" >
        <div>
        <a href="#">
        <span>Saurabh Sharma</span>
        <span>16-07-2015</span></a>
        </div>
        <div>
        <a href="#">
        <span>Ravi Sharma</span>
        <span>16-07-2015</span></a>
        </div>
        <div>
        <a href="#">
        <span>Shanker Sharma</span>&nbsp;
        <span>16-07-2015</span></a>
        </div>
        </marquee>
      </section>