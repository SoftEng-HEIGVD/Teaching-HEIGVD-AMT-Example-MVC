package ch.heigvd.amt.mvcdemo.services.legacy;

import ch.heigvd.amt.mvcdemo.model.legacy.Beer;
import java.util.List;
import javax.ejb.Local;

/**
 * @see BeersManager
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Local
public interface BeersManagerLocal {
  
  /**
   *
   * @return
   */
  List<Beer> getAllBeers();
  
}
