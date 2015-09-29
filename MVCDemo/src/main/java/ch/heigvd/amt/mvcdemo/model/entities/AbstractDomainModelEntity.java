package ch.heigvd.amt.mvcdemo.model.entities;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * This class was added to avoid redundant code in the project. In the simplest
 * implementation, a JPA entity does not need to extend any class. It can manage
 * its @Id property. Here, we are saying that all our "business domain objects"
 * should inherit from a common abstract class (where we manage the @Id).
 *
 * Notice the use of the generic type. <PK> is the type of the unique key for
 * the JPA entity that extends this class. In practice, we like to always use
 * Long as a type for unique keys, but we like to offer the choice to
 * subclasses.
 *
 * @author Olivier Liechti
 */
@MappedSuperclass
public abstract class AbstractDomainModelEntity<PK> implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private PK id;

  public PK getId() {
    return id;
  }

  public void setId(PK id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Company)) {
      return false;
    }
    AbstractDomainModelEntity other = (AbstractDomainModelEntity) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return getClass().getCanonicalName() + "[ id=" + id + " ]";
  }

}
