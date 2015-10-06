package ch.heigvd.amt.mvcdemo.model.entities;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * This class implements the Company domain model object. Notice that it looks
 * like a standard Java class (so it can be instantiated like any other), but
 * has a couple of JPA annotations.
 *
 * Notice that before the class, we have defined a number of JPA Named Queries.
 * It is possible to write the JPQL requests directly in the code, but many
 * developers like to define them all at the same place. We have to give names
 * to JPQL queries and it is a good practice to start the name with "Company."
 * to avoid naming conflicts with other JPA entities.
 *
 * Notice that we have defined a countEmployeesForCompanyId. In the generic DAO,
 * there is a method to retrieve all entities (all companies in our case). So
 * why not simply use that, iterate over the list and get the number of
 * companies? We have implemented this extra JQPL query for performance reasons.
 * It is faster to do the count(e) than to iterate over 20'000 records.
 *
 * @author Olivier Liechti
 */
@Entity
@NamedQueries({
  @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name"),
  @NamedQuery(name = "Company.findEmployeesPageForCompanyId", query = "SELECT e FROM Employee e WHERE e.company.id = :id"),
  @NamedQuery(name = "Company.findAllEmployeesForCompanyId", query = "SELECT e FROM Employee e WHERE e.company.id = :id"),
  @NamedQuery(name = "Company.countEmployeesForCompanyId", query = "SELECT count(e) FROM Employee e WHERE e.company.id = :id"),
  @NamedQuery(name = "Company.computeSalaryStatistics", query = "SELECT coalesce(MAX(e.basicSalary), 0), coalesce(AVG(e.basicSalary), 0) FROM Employee e WHERE e.company.id = :id"),
  @NamedQuery(name = "Company.findEmployeesByTitle", query = "SELECT e FROM Employee e WHERE e.company.id = :id AND e.title = :title")
})

public class Company extends AbstractDomainModelEntity<Long> {

  /*
   * We specify some constraints: the name of the company must be unique and
   * cannot be null. If we try to persist a Company object where this contraints
   * are violated, we will get an exception.
   */
  @Column(nullable = false, unique = true)
  private String name;

  /*
   * We define a many-to-many relationship between companies and sectors (a company
   * can be active in several industry sectors). We indicate that we want to do an
   * eager fetch, which means that every time we will load a company from the DB,
   * we will immediately load sectors. We do that because we know that there are not
   * many sectors and that we are pretty sure that every time we look at a company,
   * we will want to know the sectors it is active in.
   */
  @ManyToMany(fetch = FetchType.EAGER)
  private List<Sector> sectors = new LinkedList<>();

  /*
   * We define a one-to-many relationship between a company and departments. We do not
   * specify eager or lazy, so the default applies (lazy for this type of relationship).
   */
  @OneToMany
  private List<Department> departments = new LinkedList<>();

  /*
   * Again, a one-to-many relationship. We don't have to specify LAZY (because it is the
   * default for one-to-many relationships), but we like to make it clear for developers
   * (especially those not very familiar with JPA). Also, note that we have to specify 
   * the 'mappedBy' parameter. This is because the relationship is bi-directional. 'company'
   * is the name of the attributre in the Employee class.
   */
  @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
  private final List<Employee> employees = new LinkedList<>();

  /**
   * Every JPA entity must define an empty constructor
   */
  public Company() {
  }

  /**
   * But it is often useful to have other constructors as well
   */
  public Company(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Sector> getSectors() {
    return sectors;
  }

  public void addSector(Sector sector) {
    this.sectors.add(sector);
  }

  public void addDepartment(Department department) {
    this.departments.add(department);
  }

  public List<Department> getDepartments() {
    return departments;
  }

  public void addEmployee(Employee employee) {
    employees.add(employee);
  }

  public List<Employee> getEmployees() {
    return employees;
  }
}
