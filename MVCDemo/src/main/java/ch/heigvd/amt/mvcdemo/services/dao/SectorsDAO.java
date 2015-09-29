package ch.heigvd.amt.mvcdemo.services.dao;

import ch.heigvd.amt.mvcdemo.model.entities.Company;
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

  public Sector findByNameOrCreateIfNotFound(String name) throws BusinessDomainEntityNotFoundException {
    Sector result = null;
    try {
      result = (Sector)em.createNamedQuery("Sector.findByName").setParameter("name", name).getSingleResult();
    } catch (NoResultException e) {
      result = createAndReturnManagedEntity(new Sector(name));
    }
    return result;
  }

}
