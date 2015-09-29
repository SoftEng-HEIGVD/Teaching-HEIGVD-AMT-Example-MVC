<%-- 
--%>

<%@include file="includes/header.jsp" %>

<h2>Company Information: ${company.name}</h2>

<div class="container">
  <h3>Sectors</h3>
  <table class="table">
    <thead>
    <th>Sectors</th>
    </thead>
    <c:forEach items="${company.sectors}" var="sector">
      <tr><td>${sector.name}</td></tr> 
    </c:forEach>
  </table>
</div>

<div class="container">
  <h3>Departments</h3>
  <table class="table">
    <thead>
    <th class="col-md-4">Department</th>
    <th class="col-md-6">Manager</th>
    </thead>
    <c:forEach items="${company.departments}" var="department">
      <tr>
        <td>
          ${department.name}
        </td>
        <td>
          ${department.manager.firstName} ${department.manager.firstName}, ${department.manager.title}
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

<div class="container">
  <h3>Employees</h3>
  <table class="table">
    <thead>
    <th class="col-md-1">ID</th>
    <th class="col-md-3">Employee name</th>
    <th class="col-md-6">Title</th>
    </thead>
    <c:forEach items="${employees}" var="employee">
      <tr>
        <td>
          ${employee.id}
        </td>
        <td>
          ${employee.firstName} ${employee.lastName}
        </td>
        <td>
          ${employee.title}
        </td>
      </tr>
    </c:forEach>
  </table>
  <nav>
    <ul class="pager">
      <li><a href="<c:url value="${employeesFirstPageLink}"/>">Page 1</a></li>
      <li><a href="<c:url value="${employeesPreviousPageLink}"/>">Previous page</a></li>
      <li><a href="<c:url value="${employeesNextPageLink}"/>">Next page</a></li>
      <li><a href="<c:url value="${employeesLastPageLink}"/>">Page ${employeesPageCount}</a></li>
    </ul>
  </nav>
</div>


<%@include file="includes/footer.jsp" %>
