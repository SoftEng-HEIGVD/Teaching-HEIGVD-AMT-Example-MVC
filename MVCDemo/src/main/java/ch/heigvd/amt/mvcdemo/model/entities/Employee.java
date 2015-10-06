package ch.heigvd.amt.mvcdemo.model.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * This class implements the Employee domain model object.
 *
 * Notice the basicSalary and bonus attributes. We have added these attributes
 * because they are an example of sensitive information that you may not want to
 * disclose in all of your interfaces (UIs but also REST APIs). This is one
 * reason why we recommend the use of Data Transer Objects (DTOs).
 *
 * @author Olivier Liechti
 */
@Entity
public class Employee extends AbstractDomainModelEntity<Long> {

  private String firstName;
  private String lastName;
  private String title;

  private double basicSalary;
  private double bonus;

  @ManyToOne
  private Company company;

  public Employee() {
  }

  public Employee(String firstName, String lastName, String title, double basicSalary, double bonus) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.title = title;
    this.basicSalary = basicSalary;
    this.bonus = bonus;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public double getBasicSalary() {
    return basicSalary;
  }

  public void setBasicSalary(double basicSalary) {
    this.basicSalary = basicSalary;
  }

  public double getBonus() {
    return bonus;
  }

  public void setBonus(double bonus) {
    this.bonus = bonus;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

}
