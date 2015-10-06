package ch.heigvd.amt.mvcdemo.services.business;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import ch.heigvd.amt.mvcdemo.rest.dto.HiringFormDTO;
import ch.heigvd.amt.mvcdemo.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.mvcdemo.services.dao.CompaniesDAOLocal;
import ch.heigvd.amt.mvcdemo.services.dao.EmployeesDAOLocal;
import ch.heigvd.amt.mvcdemo.util.Chance;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This is an example of business service. In the application, there is not
 * much business logic and controllers interact with DAO services directly.
 * This class implements a typical business service, where business rules
 * are applied during the processing of a client request. Concretely, this
 * is where we decide how much money new employees should make. This is clearly
 * not something that should be implemented in a DAO, but rather in the
 * business layer.
 * 
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
public class HumanResourcesService implements HumanResourcesServiceLocal {
  
  @EJB
  private EmployeesDAOLocal employeesDAO;
  
  @EJB
  private CompaniesDAOLocal companiesDAO;
  
  @Override
  public Employee hireEmployee(Company company, HiringFormDTO hiringForm) throws BusinessDomainEntityNotFoundException {
    
    company = companiesDAO.findById(company.getId());
    Employee newHire = new Employee();
            
    newHire.setFirstName(hiringForm.getFirstName());
    newHire.setLastName(hiringForm.getLastName());
    
    newHire.setTitle(hiringForm.getTitle());
    assignStartingSalary(newHire);    
    
    newHire = employeesDAO.createAndReturnManagedEntity(newHire);
    companiesDAO.hire(company, newHire);
    return newHire;
   
  }
  
  private void assignStartingSalary(Employee employee) {
    String title = employee.getTitle();
    switch (title) {
      case "CEO":
        employee.setBasicSalary(Chance.randomDouble(1, 200000));
        employee.setBonus(Chance.randomDouble(50000, 5000000));
        break;
      case "software engineer":
        employee.setBasicSalary(80000);
        employee.setBonus(1000);
        break;
      case "senior software engineer":
        employee.setBasicSalary(120000);
        employee.setBonus(10000);
        break;
      default:
        employee.setBasicSalary(Chance.randomDouble(50000, 150000));
        employee.setBonus(Chance.randomDouble(0, 50000));
    }
  }

  
}
