package ch.heigvd.amt.mvcdemo.model.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This class implements the Sector domain model object. A Sector represents
 * an industry sector (such as energy, IT, telecommunications, etc.), that a
 * company can be active in. Note that a company can be active in more than
 * one sector.
 *
 * @author Olivier Liechti
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Sector.findByName", query = "SELECT s FROM Sector s WHERE s.name = :name")
})
public class Sector extends AbstractDomainModelEntity<Long> {

  private String name;

  public Sector() {
  }

  public Sector(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

}
