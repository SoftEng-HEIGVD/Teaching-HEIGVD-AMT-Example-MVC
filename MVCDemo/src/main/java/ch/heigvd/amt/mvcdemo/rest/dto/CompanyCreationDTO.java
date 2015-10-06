package ch.heigvd.amt.mvcdemo.rest.dto;

/**
 *
 * @author Olivier Liechti
 */
public class CompanyCreationDTO {
  
  private String name;
  private String[] sectors;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String[] getSectors() {
    return sectors;
  }

  public void setSectors(String[] sectors) {
    this.sectors = sectors;
  }

}
