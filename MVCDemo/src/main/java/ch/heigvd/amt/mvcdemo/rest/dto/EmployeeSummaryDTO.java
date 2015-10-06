package ch.heigvd.amt.mvcdemo.rest.dto;

import java.net.URI;

/**
 *
 * @author Olivier Liechti
 */
public class EmployeeSummaryDTO {

  private URI href;
  String firstName;
  String lastName;
  String title;

  public EmployeeSummaryDTO() {
  }

  public EmployeeSummaryDTO(String firstName, String lastName, String title) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.title = title;
  }

  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
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
  
  
}
