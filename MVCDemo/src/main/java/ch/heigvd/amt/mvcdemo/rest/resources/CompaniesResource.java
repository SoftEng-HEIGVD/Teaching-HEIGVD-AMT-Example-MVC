package ch.heigvd.amt.mvcdemo.rest.resources;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import ch.heigvd.amt.mvcdemo.model.entities.SalaryStatistics;
import ch.heigvd.amt.mvcdemo.model.entities.Sector;
import ch.heigvd.amt.mvcdemo.rest.dto.CompanyCreationDTO;
import ch.heigvd.amt.mvcdemo.rest.dto.CompanyDTO;
import ch.heigvd.amt.mvcdemo.rest.dto.CompanySummaryDTO;
import ch.heigvd.amt.mvcdemo.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.mvcdemo.services.dao.CompaniesDAOLocal;
import ch.heigvd.amt.mvcdemo.services.dao.EmployeesDAOLocal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
@Path("/companies")
public class CompaniesResource {

  @Context
  UriInfo uriInfo;

  @EJB
  private CompaniesDAOLocal companiesDAO;

  @EJB
  private EmployeesDAOLocal employeesDAO;

  @GET
  @Produces("application/json")
  public List<CompanySummaryDTO> getCompanies() {
    List<CompanySummaryDTO> result = new ArrayList<>();
    List<Company> companies = companiesDAO.findAll();
    for (Company company : companies) {
      long companyId = company.getId();
      CompanySummaryDTO dto = new CompanySummaryDTO();
      populateSummaryDTOFromEntity(company, dto);
      result.add(dto);
    }
    return result;
  }

  @POST
  @Consumes("application/json")
  public Response createCompany(CompanyCreationDTO dto) throws BusinessDomainEntityNotFoundException {
    String[] sectors = dto.getSectors();
    String name = dto.getName();
   
    Company newCompany = new Company();
    newCompany.setName(name);
    long companyId = companiesDAO.create(newCompany);
    for (String sector : sectors) {
      companiesDAO.assignSector(name, sector);
    }
     
    URI href = uriInfo
      .getBaseUriBuilder()
      .path(CompaniesResource.class)
      .path(CompaniesResource.class, "getCompany")
      .build(companyId);

    return Response
      .created(href)
      .build();
  }

  @GET
  @Path("/{id}")
  @Produces("application/json")
  public CompanyDTO getCompany(@PathParam(value = "id") long id) throws BusinessDomainEntityNotFoundException {
    Company company = companiesDAO.findById(id);
    CompanyDTO dto = new CompanyDTO();
    populateDTOFromEntity(company, dto);
    return dto;
  }

  private CompanySummaryDTO populateSummaryDTOFromEntity(Company company, CompanySummaryDTO dto) {
    long companyId = company.getId();
    URI companyHref = uriInfo
      .getAbsolutePathBuilder()
      .path(CompaniesResource.class, "getCompany")
      .build(companyId);
    dto.setHref(companyHref);
    dto.setName(company.getName());
    dto.setNumberOfEmployees(companiesDAO.countEmployees(company.getId()));
    List<String> sectorsDTO = new ArrayList<>();
    for (Sector sector : company.getSectors()) {
      sectorsDTO.add(sector.getName());
    }
    dto.setSectors(sectorsDTO);

    List<Employee> employees = companiesDAO.findEmployeesByTitle(companyId, "CEO");
    if (employees.size() == 1) {
      Employee ceo = employees.get(0);
      dto.setCeo(ceo.getFirstName() + " " + ceo.getLastName());
    } else if (employees.isEmpty()) {
      dto.setCeo("There is no CEO");
    } else {
      dto.setCeo("There are " + employees.size() + " co-CEOs");
    }

    return dto;

  }

  private void populateDTOFromEntity(Company company, CompanyDTO dto) {
    populateSummaryDTOFromEntity(company, dto);
    SalaryStatistics stats = companiesDAO.getSalaryStatistics(company.getId());
    dto.setHighestSalary(stats.getHighestSalary());
    dto.setAverageSalary(stats.getAverageSalary());
  }

}
