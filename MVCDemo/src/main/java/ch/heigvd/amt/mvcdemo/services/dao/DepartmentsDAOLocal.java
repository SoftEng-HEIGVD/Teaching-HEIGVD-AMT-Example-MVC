package ch.heigvd.amt.mvcdemo.services.dao;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Department;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import javax.ejb.Local;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Local
public interface DepartmentsDAOLocal extends IGenericDAO<Department, Long> {

  Department findByName(String name) throws BusinessDomainEntityNotFoundException;

  Department findByManagerId(Long id) throws BusinessDomainEntityNotFoundException;

  void assignEmployeeToDepartment(Department department, Employee employee);

}
