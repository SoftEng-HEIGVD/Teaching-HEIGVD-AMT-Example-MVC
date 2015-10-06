package ch.heigvd.amt.mvcdemo.services.dao;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import ch.heigvd.amt.mvcdemo.model.entities.SalaryStatistics;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Local
public interface CompaniesDAOLocal extends IGenericDAO<Company, Long> {

  void assignSector(String companyName, String sectorName) throws BusinessDomainEntityNotFoundException;

  void hire(Company company, Employee employee);

  Company findByName(String name) throws BusinessDomainEntityNotFoundException;

  public List<Employee> findEmployeesPageForCompanyId(long companyId, int pageSize, int pageIndex);

  public List<Employee> findEmployeesByTitle(long companyId, String title);

  public long countEmployees(long companyId);

  public SalaryStatistics getSalaryStatistics(long companyId);

  List<Employee> findAllEmployeesForCompanyId(long companyId);
  
}
