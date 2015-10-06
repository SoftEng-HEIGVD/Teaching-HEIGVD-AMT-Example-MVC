package ch.heigvd.amt.mvcdemo.rest.config;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Application;

/**
 *
 * @author Olivier Liechti
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new java.util.HashSet<>();
    addRestResourceClasses(resources);

    /*
     * The following block is needed in order to use jackson as the JSON provider for
     * Jersey. It is also required to add jersey-media-json-jackson as a dependency for
     * this to work. Using jackson allows us to use the @JsonAnySetter annotation, so
     * that we can handle dynamic property names.
     */
    Class jsonProvider;
    try {
      jsonProvider = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
      resources.add(jsonProvider);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ApplicationConfig.class.getName()).log(Level.SEVERE, "*** Problem while configuring JSON!", ex);
    }
    
    
    return resources;
  }

  /**
   * Do not modify addRestResourceClasses() method.
   * It is automatically populated with
   * all resources defined in the project.
   * If required, comment out calling this method in getClasses().
   */
  private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(ch.heigvd.amt.mvcdemo.rest.config.MyExceptionMapper.class);
    resources.add(ch.heigvd.amt.mvcdemo.rest.config.MyObjectMapperProvider.class);
    resources.add(ch.heigvd.amt.mvcdemo.rest.resources.BeersResource.class);
    resources.add(ch.heigvd.amt.mvcdemo.rest.resources.BeersResource.BeerResource.class);
    resources.add(ch.heigvd.amt.mvcdemo.rest.resources.CompaniesResource.class);
    resources.add(ch.heigvd.amt.mvcdemo.rest.resources.EmployeesResource.class);
    resources.add(ch.heigvd.amt.mvcdemo.rest.resources.HiringFormsResource.class);
    resources.add(ch.heigvd.amt.mvcdemo.rest.resources.SectorsResource.class);
    resources.add(ch.heigvd.amt.mvcdemo.rest.resources.SystemResource.class);
  }

}
