package ch.heigvd.amt.mvcdemo.web.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is used to experiment how concurrency issues can appear when
 * using servlets. The key point is to remember that a servlet is NOT thread-safe
 * and that application servers often create only one instance of every servlet.
 * For these reasons, access to shared resources (class variables) need to be
 * synchronized!
 * 
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@WebServlet(name = "ConcurrencyServlet", urlPatterns = {"/pages/concurrency"})
public class ConcurrencyServlet extends HttpServlet {
  
  private long numberOfRequests = 0;
  
  private long numberOfRequests2 = 0;
  
  private void incrementNumberOfRequests() {
    long tempValue = numberOfRequests;
    tempValue = tempValue + 1;
    numberOfRequests = tempValue;
  }

  private synchronized void incrementNumberOfRequests2() {
    long tempValue = numberOfRequests2;
    tempValue = tempValue + 1;
    numberOfRequests2 = tempValue;
  }

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    incrementNumberOfRequests();
    incrementNumberOfRequests2();
    request.setAttribute("numberOfRequests", numberOfRequests);
    request.setAttribute("numberOfRequests2", numberOfRequests2);
    request.getRequestDispatcher("/WEB-INF/pages/concurrency.jsp").forward(request, response);
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
