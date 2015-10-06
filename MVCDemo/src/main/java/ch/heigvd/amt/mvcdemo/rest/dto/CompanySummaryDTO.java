package ch.heigvd.amt.mvcdemo.rest.dto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO which is used to create the JSON fragments used in list (when the REST client
 * requests a list of companies, we provide some of the company properties). Couple of points:
 * 
 * - The CEO property is a string (first name + last name). We could also have defined an EmployeeDTO
 *   and referenced it (this would have created a JSON object under the ceo property name).
 * 
 * - The DTO is an example of AGGREGATING information stored in multiple JPA entities / database tables. When
 *   creating an instance of this DTO, the service will need to fetch a Company, but also the related Sectors
 *   and one Employee (the CEO).
 * 
 * - The numberOfEmployees property will need to be computed (there is a special JPA query for that).
 * 
 * 
 * @author Olivier Liechti
 */
public class CompanySummaryDTO {
  
  private URI href;
  private List<String> sectors = new ArrayList<>();
  private String name;
  private long numberOfEmployees;
  private String ceo;

  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public List<String> getSectors() {
    return sectors;
  }

  public String getName() {
    return name;
  }

  public long getNumberOfEmployees() {
    return numberOfEmployees;
  }

  public String getCeo() {
    return ceo;
  }

  public void setSectors(List<String> sectors) {
    this.sectors = sectors;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNumberOfEmployees(long numberOfEmployees) {
    this.numberOfEmployees = numberOfEmployees;
  }

  public void setCeo(String ceo) {
    this.ceo = ceo;
  }
  
  
  
  

  
  

}
