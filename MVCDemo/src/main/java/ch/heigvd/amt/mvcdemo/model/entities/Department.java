package ch.heigvd.amt.mvcdemo.model.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This class implements the Department domain model object.
 *
 * Look at the findByManagerId named query (which is used when you want to
 * retrieve a department managed by a certain employee and you know the id of
 * this employee). The query shows that with JQPL, you can traverse the object
 * graph with the dot notation
 *
 * @author Olivier Liechti
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Department.findByName", query = "SELECT d FROM Department d WHERE d.name = :name"),
  @NamedQuery(name = "Department.findByManagerId", query = "SELECT d FROM Department d WHERE d.manager.id = :id")
})
public class Department extends AbstractDomainModelEntity<Long> {

  private String name;

  @ManyToOne
  private Employee manager;

  @ManyToMany
  private List<Employee> members;

  public Department() {
  }

  public Department(Company company, String name, Employee manager) {
    company.addDepartment(this);
    this.name = name;
    this.manager = manager;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Employee getManager() {
    return manager;
  }

  public void setManager(Employee manager) {
    this.manager = manager;
  }

  public void addMember(Employee employee) {
    members.add(employee);
  }

}
