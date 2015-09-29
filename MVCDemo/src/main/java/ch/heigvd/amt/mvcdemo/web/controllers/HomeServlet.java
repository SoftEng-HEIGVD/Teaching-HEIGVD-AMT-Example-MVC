package ch.heigvd.amt.mvcdemo.web.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This a very simple controller. There is no service to invoke, no model to
 * prepare for the view. We simply delegate rendering of a static view to a
 * JSP page.
 * 
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
public class HomeServlet extends HttpServlet {
  
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    /*
     * This is a place to experiment. From the JSP page, it is possible to retrieve models from different
     * scopes (request, session, application). The developer can either explicitely tell which scope should be
     * used for the lookup, or it can use a syntax to find the model in any of the scopes (from the most specific
     * to the most general). The following code shows that it is possible to override a session-scoped model with
     * a request-scoped model (it shows that it can be risky to use the implicit syntax...).
    
      request.setAttribute("principal", "Overriden principal value");
     
     * see https://docs.oracle.com/javaee/6/tutorial/doc/bnahu.html#bnahw
    */
    request.setAttribute("pageTitle", "Welcome");
    request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
  }

}
