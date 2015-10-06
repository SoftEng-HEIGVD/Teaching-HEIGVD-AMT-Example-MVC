package ch.heigvd.amt.mvcdemo.services.legacy;

import ch.heigvd.amt.mvcdemo.model.legacy.Beer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This is a mockup for a business service, which would typically fetch data
 * in a database. In this case, we use static data stored in memory. We also
 * don't support all CRUD operations (Create, Read, Update, Delete), but only
 * provide a method get the list of all beers.
 * 
 * The service is implemented as a Stateless Session Bean (EJB). We will study
 * this type of component later in the course.
 * 
 * The mockup gives you the possibility to simulate a delay incurred by the
 * database processing. You can specify a maximum delay in ms. For every invocation,
 * random value between 0 and this maximum value will be computed and a delay
 * will be added (the processing will be suspended). How will that affect 
 * performance (also of requests submitted by other people)? How will that impact
 * scalability? This is where you want to experiment with thread pools in the
 * application server. 
 * 
 * asadmin> set server.thread-pools.thread-pool.http-thread-pool.max-thread-pool-size=5 (default value)
 * asadmin> set server.thread-pools.thread-pool.http-thread-pool.max-thread-pool-size=30
 * 
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
public class BeersManager implements BeersManagerLocal {
  
  private static final int MAX_DATABASE_DELAY_MS = 0;
  //private static final int MAX_DATABASE_DELAY_MS = 10000;
  @EJB
  BeersDataStoreLocal beersDataStore;
  
  /**
   * This method returns the list of all beers in the data store.
   * @return a list of beers
   */
  @Override
  public List<Beer> getAllBeers() {
    simulateDatabaseDelay();
    return beersDataStore.getAllBeers();
  }
  
  private void simulateDatabaseDelay() {
    long simulatedDatabaseLookupTime = (long)(Math.random() * MAX_DATABASE_DELAY_MS);
    try {
      Thread.sleep(simulatedDatabaseLookupTime);
    } catch (InterruptedException ex) {
      Logger.getLogger(BeersManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
  
}
