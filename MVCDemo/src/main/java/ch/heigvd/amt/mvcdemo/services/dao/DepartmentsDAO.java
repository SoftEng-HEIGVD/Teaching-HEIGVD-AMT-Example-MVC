package ch.heigvd.amt.mvcdemo.services.dao;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Department;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
public class DepartmentsDAO extends GenericDAO<Department, Long> implements DepartmentsDAOLocal {

  @Override
  public Department findByName(String name) throws BusinessDomainEntityNotFoundException {
    Department result = null;
    try {
      result = (Department) em.createNamedQuery("Department.findByName").setParameter("name", name).getSingleResult();
    } catch (NoResultException e) {
      throw new BusinessDomainEntityNotFoundException();
    }
    return result;
  }

  @Override
  public Department findByManagerId(Long id) throws BusinessDomainEntityNotFoundException {
    Department result = null;
    try {
      result = (Department) em.createNamedQuery("Department.findByManagerId").setParameter("id", id).getSingleResult();
    } catch (NoResultException e) {
      throw new BusinessDomainEntityNotFoundException();
    }
    return result;
  }

  @Override
  public void assignEmployeeToDepartment(Department department, Employee employee) {
    department.addMember(employee);
  }

}
