package ch.heigvd.amt.mvcdemo.rest.resources;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import ch.heigvd.amt.mvcdemo.rest.dto.HiringFormDTO;
import ch.heigvd.amt.mvcdemo.services.business.HumanResourcesServiceLocal;
import ch.heigvd.amt.mvcdemo.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.mvcdemo.services.dao.CompaniesDAOLocal;
import java.net.URI;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
@Path("/companies/{companyId}/hiringForms")
public class HiringFormsResource {

  @EJB
  private CompaniesDAOLocal companiesDAO;

  @EJB
  private HumanResourcesServiceLocal humanResourcesService;

  @Context
  UriInfo uriInfo;

  @POST
  @Consumes("application/json")
  public Response submitHiringForm(HiringFormDTO hiringForm, @PathParam("companyId") long companyId) throws BusinessDomainEntityNotFoundException {
    Company company = companiesDAO.findById(companyId);
    Employee employee = humanResourcesService.hireEmployee(company, hiringForm);
    

    URI newHireURI = uriInfo
      .getBaseUriBuilder()
      .path(EmployeesResource.class)
      .path(EmployeesResource.class, "getEmployee")
      .build(company.getId(), employee.getId());
    
    return Response
      .created(newHireURI)
      .build();
    
  }

}
