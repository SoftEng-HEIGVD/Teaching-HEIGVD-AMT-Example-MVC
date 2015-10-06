package ch.heigvd.amt.mvcdemo.services.business;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import ch.heigvd.amt.mvcdemo.rest.dto.HiringFormDTO;
import ch.heigvd.amt.mvcdemo.services.dao.BusinessDomainEntityNotFoundException;
import javax.ejb.Local;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Local
public interface HumanResourcesServiceLocal {

  Employee hireEmployee(Company company, HiringFormDTO hiringForm) throws BusinessDomainEntityNotFoundException;
  
}
