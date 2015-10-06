package ch.heigvd.amt.mvcdemo.rest.dto;

/**
 *
 * @author Olivier Liechti
 */
public class SystemInformationDTO {
  
  private long startupTime;
  
  private long numberOfSessionsCreated;
  private long numberOfSessionsDestroyed;
  private long numberOfRequestsCreated;
  private long numberOfRequestsDestroyed;

  public SystemInformationDTO() {
  }
  
  public SystemInformationDTO(long startupTime) {
    this.startupTime = startupTime;
  }
  
  public long getStartupTime() {
    return startupTime;
  }

  public void setStartupTime(long startupTime) {
    this.startupTime = startupTime;
  }

  public long getNumberOfSessionsCreated() {
    return numberOfSessionsCreated;
  }

  public void setNumberOfSessionsCreated(long numberOfSessionsCreated) {
    this.numberOfSessionsCreated = numberOfSessionsCreated;
  }

  public long getNumberOfSessionsDestroyed() {
    return numberOfSessionsDestroyed;
  }

  public void setNumberOfSessionsDestroyed(long numberOfSessionsDestroyed) {
    this.numberOfSessionsDestroyed = numberOfSessionsDestroyed;
  }

  public long getNumberOfRequestsCreated() {
    return numberOfRequestsCreated;
  }

  public void setNumberOfRequestsCreated(long numberOfRequestsCreated) {
    this.numberOfRequestsCreated = numberOfRequestsCreated;
  }

  public long getNumberOfRequestsDestroyed() {
    return numberOfRequestsDestroyed;
  }

  public void setNumberOfRequestsDestroyed(long numberOfRequestsDestroyed) {
    this.numberOfRequestsDestroyed = numberOfRequestsDestroyed;
  }


  
  

}
