<%-- 
The purpose of this page is to redirect users who are trying to access the
following URL: http://localhost:8080/MVCDemo/. Because we have decided that all
of our pages would be available under the /pages/ prefix, we redict them to
/pages/home. If we were not doing that, users would get a 404.

Notice that we use the Java Standard Tag Library for implementing the redirection.
This is one of the methods available to us.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<c:redirect url="/pages/home"/>