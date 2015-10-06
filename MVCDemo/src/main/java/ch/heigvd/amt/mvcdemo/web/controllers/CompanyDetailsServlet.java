package ch.heigvd.amt.mvcdemo.web.controllers;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import ch.heigvd.amt.mvcdemo.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.mvcdemo.services.dao.CompaniesDAOLocal;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@WebServlet(name = "CompanyDetailsServlet", urlPatterns = {"/pages/companyDetails"})
public class CompanyDetailsServlet extends HttpServlet {

  @EJB
  private CompaniesDAOLocal companiesDAO;

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
    long companyId = Long.parseLong(request.getParameter("id"));

    Company company = null;
    try {
      company = companiesDAO.findById(companyId);
    } catch (BusinessDomainEntityNotFoundException ex) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
      //throw new ServletException(ex);
    }
    request.setAttribute("company", company);

    int pageSize = 0;
    try {
      pageSize = Integer.parseInt(request.getParameter("employeesPageSize"));
    } catch (NumberFormatException e) {
      pageSize = 10;
    }
    int pageIndex = 0;
    try {
      pageIndex = Integer.parseInt(request.getParameter("employeesPageIndex"));
    } catch (NumberFormatException e) {
      pageIndex = 0;
    }

    List<Employee> employees = companiesDAO.findEmployeesPageForCompanyId(companyId, pageSize, pageIndex);
    long numberOfPages = (companiesDAO.countEmployees(companyId) + pageSize - 1) / pageSize;
    
    request.setAttribute("pageTitle", "Company Details");
    request.setAttribute(("employees"), employees);
    
    request.setAttribute("employeesFirstPageLink", "/pages/companyDetails?id=" + companyId +"&employeesPageSize=" + pageSize + "&employeesPageIndex=0");
    request.setAttribute("employeesPreviousPageLink", "/pages/companyDetails?id=" + companyId + "&employeesPageSize=" + pageSize + "&employeesPageIndex=" + Math.max(0, pageIndex - 1));
    request.setAttribute("employeesNextPageLink", "/pages/companyDetails?id=" + companyId +"&employeesPageSize=" + pageSize + "&employeesPageIndex=" + Math.min(pageIndex + 1, numberOfPages - 1));
    request.setAttribute("employeesLastPageLink", "/pages/companyDetails?id=" + companyId +"&employeesPageSize=" + pageSize + "&employeesPageIndex=" + (numberOfPages-1));
    request.setAttribute("employeesPageCount", numberOfPages);

    request.getRequestDispatcher("/WEB-INF/pages/companyDetails.jsp").forward(request, response);
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
