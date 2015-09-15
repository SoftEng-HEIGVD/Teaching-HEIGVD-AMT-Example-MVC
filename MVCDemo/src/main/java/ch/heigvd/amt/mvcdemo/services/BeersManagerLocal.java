package ch.heigvd.amt.mvcdemo.services;

import ch.heigvd.amt.mvcdemo.model.Beer;
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
