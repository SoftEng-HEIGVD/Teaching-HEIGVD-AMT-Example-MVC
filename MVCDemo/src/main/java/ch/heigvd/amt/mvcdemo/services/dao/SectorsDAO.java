package ch.heigvd.amt.mvcdemo.services.dao;

import ch.heigvd.amt.mvcdemo.model.entities.Sector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
public class SectorsDAO extends GenericDAO<Sector, Long> implements SectorsDAOLocal {

  @PersistenceContext
  EntityManager em;

  public Sector findByNameOrCreateIfNotFound(String name) {
    Sector result = null;
    try {
      result = (Sector) em.createNamedQuery("Sector.findByName").setParameter("name", name).getSingleResult();
    } catch (NoResultException e) {
      result = createAndReturnManagedEntity(new Sector(name));
    }
    return result;
  }

  @Override
  public Sector findByName(String name) throws BusinessDomainEntityNotFoundException {
    Sector result;
    try {
      result = (Sector) em.createNamedQuery("Sector.findByName").setParameter("name", name).getSingleResult();
      return result;
    } catch (NoResultException e) {
      throw new BusinessDomainEntityNotFoundException("Sector " + name + " not found.");
    }
  }

}
