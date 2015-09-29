package ch.heigvd.amt.uat.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the "AJAX Example" page in the MVCDemo app.
 * 
 * @author Olivier Liechti
 */
public class AJAXPage extends AbstractMVCDemoPage {

  public AJAXPage(WebDriver driver) {
    super(driver);

    // Check that we're on the right page.
    if (!"AJAX Example".equals(driver.getTitle())) {
      throw new IllegalStateException("This is not the correct page");
    }
  }
  
}
