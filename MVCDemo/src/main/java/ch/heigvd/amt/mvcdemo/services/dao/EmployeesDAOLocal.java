package ch.heigvd.amt.mvcdemo.services.dao;

import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import javax.ejb.Local;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Local
public interface EmployeesDAOLocal extends IGenericDAO<Employee, Long>{

  
}
