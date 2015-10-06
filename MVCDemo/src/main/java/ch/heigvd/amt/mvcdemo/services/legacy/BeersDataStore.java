package ch.heigvd.amt.mvcdemo.services.legacy;

import ch.heigvd.amt.mvcdemo.model.legacy.Beer;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Singleton;

/**
 * This is a mockup for a data access service. In a normal application, this is
 * where you would find code to interact with a database (with the help of the
 * JDBC or JPA APIs). In this case, we implement a rudimentary in-memory
 * database.
 *
 * Notice the @Singleton annotation. Later in the course, we will talk about
 * Enterprise Java Beans (EJBs). The annotation is one way to declare an EJB,
 * for which there will be one and only one instance (unlike the @Stateless and
 * @Statefull annotations).
 *
 * In the implementation, we only provide a method to retrieve data from the
 * data store service. Usually, there are also methods for Creating, Updating
 * and Deleting data (CRUD). There are also more methods for retrieving data
 * (depending on certain criteria). We could for instance think about a method
 * named findBeersByCountry().
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Singleton
public class BeersDataStore implements BeersDataStoreLocal {

  private final List<Beer> catalog = new LinkedList<>();

  /**
   *
   */
  public BeersDataStore() {
    catalog.add(new Beer("Cardinal", " Feldschlösschen", "Switzlerland", "Lager"));
    catalog.add(new Beer("Punk IPA", " BrewDog", "Scotland", "India Pale Ale"));
    catalog.add(new Beer("Mikkeller I Hardcore You", " BrewDog", "Scotland", "Imperial India Pale Ale"));
  }

  /**
   *
   * @return
   */
  @Override
  public List<Beer> getAllBeers() {
    return new LinkedList<>(catalog);
  }
}
