package ch.heigvd.amt.mvcdemo.rest.dto;

import java.net.URI;

/**
 *
 * @author Olivier Liechti
 */
public class SectorDTO {
  
  private String name;

  private URI href;
  
  public SectorDTO() {
  }

  public SectorDTO(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

}
