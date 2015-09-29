package ch.heigvd.amt.mvcdemo.services.dao;

import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import javax.ejb.Stateless;

/**
 * The example for the simplest DAO. In this case, all the functionality is
 * implemented in generic DAO (we get the CRUD methods, with the findById, the
 * findAll and the findAllByPage methods).
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
public class EmployeesDAO extends GenericDAO<Employee, Long> implements EmployeesDAOLocal {

}
