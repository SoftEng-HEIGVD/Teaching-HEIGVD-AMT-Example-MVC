package ch.heigvd.amt.mvcdemo.services.dao;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import ch.heigvd.amt.mvcdemo.model.entities.SalaryStatistics;
import ch.heigvd.amt.mvcdemo.model.entities.Sector;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * This SLSB implements the DAO design pattern. This class implements the data
 * access for the Company JPA entity. It extends the GenericDAO and with
 * <Company, Long>, it declares that manages Company entities, which have a Long
 * primary key.
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
public class CompaniesDAO extends GenericDAO<Company, Long> implements CompaniesDAOLocal {

  @EJB
  SectorsDAOLocal sectorsDAO;

  /**
   * This method shows one way to connect two JPA entities. In this case, we do
   * not pass instances of the JPA entity as arguments, so there is not question
   * about whether they are managed or not. In the method, we first use a finder
   * to retrieve the Company and the Sector (with a special twist: we have
   * created a special method, so that sectors can be created on the fly if they
   * don't exist yet). In the method, c and s are managed entities (they are in
   * the JPA persistence context). No need to call persist - the update is
   * automatically done in the DB when the transaction commits.
   *
   * @param companyName the name of the company, to do the lookup
   * @param sectorName the name of the sector, to do the lookup (if it does not
   * exist, it will be created)
   *
   * @throws
   * ch.heigvd.amt.mvcdemo.services.dao.BusinessDomainEntityNotFoundException if
   * there is no company with that name
   */
  @Override
  public void assignSector(String companyName, String sectorName) throws BusinessDomainEntityNotFoundException {
    Company c = findByName(companyName);
    Sector s = sectorsDAO.findByNameOrCreateIfNotFound(sectorName);
    c.addSector(s);
  }

  /**
   * This is another example, of a method that shows how JPA code can be
   * dangerous if it is not well understood. For this to work, the arguments
   * company and employee MUST be managed entities. The code will work if the
   * method is called from a SLSB and if a persist, a merge or a find has been
   * done on these entities. If the method is called from a servlet, no
   * exception will be thrown (the java code is valid), BUT no change will be
   * propagated to the DB!! (for JPA, they do not exist in the persistence
   * context). In the method, we could have used the merge() method to bring the
   * arguments in the persistence context.
   *
   * @param company
   * @param employee
   */
  @Override
  public void hire(Company company, Employee employee) {
    employee.setCompany(company);
    company.addEmployee(employee);
  }

  @Override
  public Company findByName(String name) throws BusinessDomainEntityNotFoundException {
    Company result = null;
    try {
      result = (Company) em.createNamedQuery("Company.findByName").setParameter("name", name).getSingleResult();
    } catch (NoResultException e) {
      throw new BusinessDomainEntityNotFoundException();
    }
    return result;
  }

  @Override
  public List<Employee> findEmployeesPageForCompanyId(long companyId, int pageSize, int pageIndex) {
    List<Employee> result;
    result = em.createNamedQuery("Company.findEmployeesPageForCompanyId").setParameter("id", companyId).setMaxResults(pageSize).setFirstResult(pageIndex * pageSize).getResultList();
    return result;
  }
  
  @Override
  public List<Employee> findAllEmployeesForCompanyId(long companyId) {
    List<Employee> result;
    result = em.createNamedQuery("Company.findAllEmployeesForCompanyId").setParameter("id", companyId).getResultList();
    return result;
  }  

  @Override
  public long countEmployees(long companyId) {
    return (long) em.createNamedQuery("Company.countEmployeesForCompanyId").setParameter("id", companyId).getSingleResult();
  }

  @Override
  public List<Employee> findEmployeesByTitle(long companyId, String title) {
    List<Employee> result;
    result = em.createNamedQuery("Company.findEmployeesByTitle")
      .setParameter("id", companyId)
      .setParameter("title", title)
      .getResultList();
    return result;
  }

  @Override
  public SalaryStatistics getSalaryStatistics(long companyId) {
    Object[] queryResult = (Object[])em.createNamedQuery("Company.computeSalaryStatistics").setParameter("id", companyId).getSingleResult();
    SalaryStatistics result = new SalaryStatistics();
    result.setHighestSalary((double)queryResult[0]);
    result.setAverageSalary((double)queryResult[1]);
    return result;
  }


}
