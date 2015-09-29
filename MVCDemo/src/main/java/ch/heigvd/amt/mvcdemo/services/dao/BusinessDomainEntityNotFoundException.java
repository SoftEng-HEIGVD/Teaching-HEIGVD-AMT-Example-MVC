package ch.heigvd.amt.mvcdemo.services.dao;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
public class BusinessDomainEntityNotFoundException extends Exception {

  /**
   * Creates a new instance of
   * <code>BusinessDomainEntityNotFoundException</code> without detail message.
   */
  public BusinessDomainEntityNotFoundException() {
  }

  /**
   * Constructs an instance of
   * <code>BusinessDomainEntityNotFoundException</code> with the specified
   * detail message.
   *
   * @param msg the detail message.
   */
  public BusinessDomainEntityNotFoundException(String msg) {
    super(msg);
  }
}
