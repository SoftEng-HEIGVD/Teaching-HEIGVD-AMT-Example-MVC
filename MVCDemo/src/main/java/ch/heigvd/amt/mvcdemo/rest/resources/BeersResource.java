package ch.heigvd.amt.mvcdemo.rest.resources;

import ch.heigvd.amt.mvcdemo.model.legacy.Beer;
import ch.heigvd.amt.mvcdemo.services.legacy.BeersManagerLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
@Path("/beers")
public class BeersResource {

  @EJB
  BeersManagerLocal beersManager;

  @GET
  @Produces("application/json")
  public List<Beer> getBeers() {
    return beersManager.getAllBeers();
  }

  @Path("/{id}")
  public BeerResource getBeerResource(@PathParam("id") long id) {
    return new BeerResource(id);
  }

  /**
   * 
   */
  public class BeerResource {

    private long id;

    public BeerResource(long id) {
      this.id = id;
    }

    @GET
    @Produces("application/json")
    public Beer getBeer() {
      return new Beer("Punk IPA", "Brewdog", "Scottland", "IPA");
    }

  }

}
