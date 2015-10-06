package ch.heigvd.amt.mvcdemo.web.controllers;

import ch.heigvd.amt.mvcdemo.services.legacy.BeersManagerLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet illustrates the MVC design pattern with a simple example.
 *
 * The first thing to know is that Inversion of Control (IoC) is realized with a
 * declaration of a mapping in the web.xml deployment descriptor. This is where
 * we tell the application server that whenever it receives an HTTP request
 * where the URL is equal to /pages/beers, then it should invoke a callback
 * implemented by this class.
 *
 * When this happens, the servlet acts as a controller. Its role is to invoke a
 * service (in the business tier), to receive a model (a Java object, or a graph
 * of Java objects), to make it available to the view and finally to delegate
 * the generation of HTML markup to a JSP page.
 *
 * The service is an EJB, injected in the servlet (dependency injection). No
 * need to worry about that at this stage, it is a topic that will be studied
 * later in the course. All you need to know is that the servlet can get a list
 * of beers by calling beersManager.getAllBeers.
 *
 * Important: the servlet does not know how the service is implemented. In most
 * applications, invoking the getAllBeers method would result in a query being
 * sent to a database. This could take "some" time (hence affect performance).
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
public class BeersServlet extends HttpServlet {

  @EJB
  BeersManagerLocal beersManager;

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
     Firstly, we need to get a model. It is not the responsibility of the servlet
     to build the model. In other words, you should avoid to put business logic
     and database access code directly in the controller. In this example, the
     beersManager takes care of the model construction.
     */
    Object model = beersManager.getAllBeers();

    /*
     Secondly, we need to find a way to share the model with the JSP that will
     render the view. We have to select a scope (in general, request or session, 
     sometimes in application or page). 
    
     See https://docs.oracle.com/javaee/5/tutorial/doc/bnafo.html for information
     about this topic.
    
     The following code puts the model in the request scope. The JSP page will be
     able to retrive it by using the "beers" name in an EL expression.
     */
    request.setAttribute("beers", model);
    request.setAttribute("pageTitle", "Beers List");

    /*
     The following code would place the model in the session. The good thing is
     that we could cache the response sent by beersManager.getAllBeers and increase
     performance. The bad thing is that we would consume memory on the server. Think
     about what would happen if sessions have a duration of 30 minutes, that we
     can have 100'000 concurrent sessions and that the beers list is a 1 MB data
     structure.
     */
    //request.getSession().setAttribute("beers", model);
    /*
     Thirdly, this is how we delegate the rendering of the view to the JSP page.
    
     Notice that we specify the full path starting at the project root (in the
     file system). It is important to place the JSP pages within the WEB-INF
     directory, for security reasons. The Servlet speciciation states that when
     this is the case, then it is NOT possible for an HTTP client to access the
     JSP directly (short-cutting the MVC pattern). In other words, the JSP can
     only be accessed via a controller servlet.
     */
    request.getRequestDispatcher("/WEB-INF/pages/beers.jsp").forward(request, response);
  }

}
