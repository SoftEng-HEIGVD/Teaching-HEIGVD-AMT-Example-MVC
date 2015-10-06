package ch.heigvd.amt.mvcdemo.rest.resources;

import ch.heigvd.amt.mvcdemo.rest.dto.SystemInformationDTO;
import ch.heigvd.amt.mvcdemo.util.MonitoringListener;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Path("system")
public class SystemResource {
  
  @Context
  private ServletContext context;

  /**
   * Creates a new instance of SystemResource
   */
  public SystemResource() {
  }

  /**
   * Retrieves representation of an instance of
   * ch.heigvd.amt.mvcdemo.rest.SystemResource
   *
   * @return an instance of java.lang.String
   */
  @GET
  @Produces("application/json")
  public SystemInformationDTO getMonitoredValues() {
    SystemInformationDTO dto = new SystemInformationDTO();
    dto.setStartupTime((long) context.getAttribute(MonitoringListener.STARTUP_TIME));
    MonitoringListener monitoring = (MonitoringListener) context.getAttribute(MonitoringListener.MONITORING_LISTENER);
    dto.setNumberOfSessionsCreated(monitoring.getNumberOfSessionsCreated());
    dto.setNumberOfSessionsDestroyed(monitoring.getNumberOfSessionsDestroyed());
    dto.setNumberOfRequestsCreated(monitoring.getNumberOfRequestsCreated());
    dto.setNumberOfRequestsDestroyed(monitoring.getNumberOfRequestsDestroyed());
    return dto;
  }
  
}
