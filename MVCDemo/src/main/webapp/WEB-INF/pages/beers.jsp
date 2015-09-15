<%@include file="includes/header.jsp" %>

<h2>Beers</h2>

<ul>
<c:forEach items="${beers}" var="beer">
  <li>${beer.name}, ${beer.brewery}, ${beer.country}, ${beer.style}</li>
</c:forEach>
</ul>
<%@include file="includes/footer.jsp" %>
