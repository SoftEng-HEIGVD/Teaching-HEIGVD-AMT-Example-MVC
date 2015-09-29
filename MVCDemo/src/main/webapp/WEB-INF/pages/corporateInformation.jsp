<%-- 
--%>

<%@include file="includes/header.jsp" %>

<h2>Corporate Information</h2>

<table id="companiesTable" class="table">
  <thead>
  <th>Company</th>
  <th>Sectors</th>
</thead>
<tbody>
  <c:forEach items="${companies}" var="company">
  <tr>
    <td>
      <a href="pages/companyDetails?id=${company.id}">${company.name}</a>
    </td>
    <td>
      <c:forEach items="${company.sectors}" var="sector">
        ${sector.name} &nbsp;
      </c:forEach>
    </td>
  </tr>
</c:forEach>
</tbody>
</table>


<%@include file="includes/footer.jsp" %>
