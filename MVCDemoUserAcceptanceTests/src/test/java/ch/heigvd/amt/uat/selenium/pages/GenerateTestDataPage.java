package ch.heigvd.amt.uat.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the "Generate Test Data" page in the MVCDemo app.
 * 
 * @author Olivier Liechti
 */
public class GenerateTestDataPage extends AbstractMVCDemoPage {

  public GenerateTestDataPage(WebDriver driver) {
    super(driver);

    // Check that we're on the right page.
    if (!"Test Data Generation".equals(driver.getTitle())) {
      throw new IllegalStateException("This is not the correct page");
    }
  }
  
}
