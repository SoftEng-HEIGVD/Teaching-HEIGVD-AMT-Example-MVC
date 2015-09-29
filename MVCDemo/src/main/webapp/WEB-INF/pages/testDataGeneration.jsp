<%-- 
This is a very basic JSP page. Notice that we can include other JSPs (so that we
can use the header and the footer across different pages). In this case, it is
a static include, which means that the header and footers will be included when
the JSP is compiled into a servlet.

The servlet (controller) that received the initial HTTP request and delegated
rendering of the view to this JSP did not send us any model. We only display
static content.
--%>

<%@include file="includes/header.jsp" %>

<h2>Test data generation</h2>

<c:choose>
  <c:when test="${successful}">
    <div class="alert alert-success" role="alert">
      The test data has been generated with success.
    </div>  
  </c:when>
  <c:otherwise>
    <div class="alert alert-danger" role="alert">
      An error has occurred during data generation. Check the Glassfish logs for the details!.
    </div>  
    <div class="alert alert-danger" role="alert">
      <ul>
        <c:forEach var="message" items="${details}">
          <li>${message}</li>
        </c:forEach>
      </ul>
    </div>  
  </c:otherwise>
</c:choose>

<%@include file="includes/footer.jsp" %>
