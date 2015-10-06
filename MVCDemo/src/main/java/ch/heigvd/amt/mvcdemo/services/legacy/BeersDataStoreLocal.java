package ch.heigvd.amt.mvcdemo.services.legacy;

import ch.heigvd.amt.mvcdemo.model.legacy.Beer;
import java.util.List;
import javax.ejb.Local;

/**
 * @see BeersDataStore
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Local
public interface BeersDataStoreLocal {

  /**
   *
   * @return
   */
  List<Beer> getAllBeers();

}
