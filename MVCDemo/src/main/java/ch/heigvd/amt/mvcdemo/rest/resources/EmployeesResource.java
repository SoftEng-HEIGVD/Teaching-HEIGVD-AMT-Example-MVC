package ch.heigvd.amt.mvcdemo.rest.resources;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import ch.heigvd.amt.mvcdemo.model.entities.Sector;
import ch.heigvd.amt.mvcdemo.rest.dto.CompanySummaryDTO;
import ch.heigvd.amt.mvcdemo.rest.dto.EmployeeSummaryDTO;
import ch.heigvd.amt.mvcdemo.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.mvcdemo.services.dao.CompaniesDAOLocal;
import ch.heigvd.amt.mvcdemo.services.dao.EmployeesDAOLocal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
@Path("/companies/{companyId}/employees")
public class EmployeesResource {

  @Context
  UriInfo uriInfo;

  @EJB
  private EmployeesDAOLocal employeesDAO;

  @EJB
  private CompaniesDAOLocal companiesDAO;

  @GET
  @Produces("application/json")
  public List<EmployeeSummaryDTO> getEmployees(@PathParam(value="companyId") long companyId) {
    List<EmployeeSummaryDTO> result = new ArrayList<>();
    List<Employee> employees = companiesDAO.findAllEmployeesForCompanyId(companyId);
    for (Employee employee : employees) {
      long employeeId = employee.getId();

      URI href = uriInfo
        .getAbsolutePathBuilder()
        .path(EmployeesResource.class, "getEmployee")
        .build(employeeId);

      EmployeeSummaryDTO dto = new EmployeeSummaryDTO();
      dto.setHref(href);
      dto.setFirstName(employee.getFirstName());
      dto.setLastName(employee.getLastName());
      dto.setTitle(employee.getTitle());
      result.add(dto);
    }
    return result;
  }

  @GET
  @Path("/{id}")
  @Produces("application/json")
  public EmployeeSummaryDTO getEmployee(@PathParam(value="id") long id) throws BusinessDomainEntityNotFoundException {
      Employee employee = employeesDAO.findById(id);
      return new EmployeeSummaryDTO(employee.getFirstName(), employee.getLastName(), employee.getTitle());
  }


}
