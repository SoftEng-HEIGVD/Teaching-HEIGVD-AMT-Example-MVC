package ch.heigvd.amt.mvcdemo.util;

import ch.heigvd.amt.mvcdemo.services.TestDataManagerLocal;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * This class shows that it is possible to implement all sorts of listeners in
 * Java EE. These listeners implement callback methods, which are invoked by the
 * application server when "interesting" events happen. It is through this
 * mechanism that we know that the servlet container has been initialized (when
 * we know that, we immediately generate test data so that the user does not
 * need to go in the menu).
 *
 * @author Olivier Liechti
 */
@WebListener
public class MonitoringListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

  TestDataManagerLocal testDataManager; // = lookupTestDataManagerLocal();

  private final AtomicLong numberOfSessionsCreated = new AtomicLong(0);
  private final AtomicLong numberOfSessionsDestroyed = new AtomicLong(0);
  private final AtomicLong numberOfRequestsCreated = new AtomicLong(0);
  private final AtomicLong numberOfRequestsDestroyed = new AtomicLong(0);

  public static final String STARTUP_TIME = "startupTime";
  public static final String MONITORING_LISTENER = "monitoringListener";

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    testDataManager = lookupTestDataManagerLocal();
    testDataManager.generateTestData();
    sce.getServletContext().setAttribute(STARTUP_TIME, System.currentTimeMillis());
    sce.getServletContext().setAttribute(MONITORING_LISTENER, this);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }

  @Override
  public void sessionCreated(HttpSessionEvent se) {
    numberOfSessionsCreated.incrementAndGet();
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    numberOfSessionsDestroyed.incrementAndGet();
  }

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    numberOfRequestsCreated.incrementAndGet();
  }

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    numberOfRequestsDestroyed.incrementAndGet();
  }

  public long getNumberOfSessionsCreated() {
    return numberOfSessionsCreated.get();
  }

  public long getNumberOfSessionsDestroyed() {
    return numberOfSessionsDestroyed.get();
  }

  public long getNumberOfRequestsCreated() {
    return numberOfRequestsCreated.get();
  }


  public long getNumberOfRequestsDestroyed() {
    return numberOfRequestsDestroyed.get();
  }
  

  private TestDataManagerLocal lookupTestDataManagerLocal() {
    try {

      Context c = new InitialContext();
      //String lookupValue ="java:module/TestDataManagerLocal";
      //String lookupValue = "java:global/MVCDemo/TestDataManager!ch.heigvd.amt.mvcdemo.services.TestDataManagerLocal";
      //String lookupValue = "java:global/MVCDemo/TestDataManager";
      // this works String lookupValue = "java:global/MVCDemo-1.0-SNAPSHOT/TestDataManager";
      String lookupValue = "java:module/TestDataManager";

      return (TestDataManagerLocal) c.lookup(lookupValue);
    } catch (NamingException ne) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
      throw new RuntimeException(ne);
      //return null;
    }
  }

}
