package ch.heigvd.amt.mvcdemo.web.controllers;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import ch.heigvd.amt.mvcdemo.services.dao.CompaniesDAOLocal;
import ch.heigvd.amt.mvcdemo.services.dao.EmployeesDAOLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is a controller servlet. Notice the @WebServlet annotation at the class
 * level. This is how Inversion of Control (IoC) is implemented in this case.
 *
 * It is an alternative to the method used by other servlets in the project,
 * which declare the information in the web.xml deployment descriptor.
 *
 * The annotation states that whenever the application server receives an HTTP
 * request with a URL equal to "/pages/about", it should invoke the callback
 * method implemented by this class.
 *
 * As specified by the MVC design pattern, the servlet does not generate any
 * HTML itself. Rather, it delegates this task to a JSP page. The
 * RequestDispatcher class of the Servlet API is used for that purpose.
 *
 * Note that in this case, there is no model involved. In other words, the
 * servlet does not prepare any data that would be made available to the JSP
 * view. In other words, the JSP will only display static content.
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@WebServlet(name = "AboutServlet", urlPatterns = {"/pages/about"})
public class AboutServlet extends HttpServlet {

  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    request.setAttribute("pageTitle", "About");
    request.getRequestDispatcher("/WEB-INF/pages/about.jsp").forward(request, response);
  }

}
