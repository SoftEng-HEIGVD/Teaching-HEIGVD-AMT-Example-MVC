package ch.heigvd.amt.mvcdemo.services.dao;

import ch.heigvd.amt.mvcdemo.model.entities.Sector;
import javax.ejb.Local;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Local
public interface SectorsDAOLocal extends IGenericDAO<Sector, Long>{

  public Sector findByNameOrCreateIfNotFound(String name);

  Sector findByName(String name) throws BusinessDomainEntityNotFoundException;

}
