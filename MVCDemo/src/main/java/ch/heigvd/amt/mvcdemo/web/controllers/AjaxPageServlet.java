package ch.heigvd.amt.mvcdemo.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is a simple controller, which simply delegates the rendering of a 
 * static page to a JSP view. This page contains Javascript code that will
 * issue AJAX requests. These requests will invoke the GraphGenerator servlet
 * and the Json servlet.
 * 
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@WebServlet(name = "AjaxPageServlet", urlPatterns = {"/pages/ajax"})
public class AjaxPageServlet extends HttpServlet {

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
    request.setAttribute("pageTitle", "AJAX Example");
    request.getRequestDispatcher("/WEB-INF/pages/ajax.jsp").forward(request, response);
  }

}
