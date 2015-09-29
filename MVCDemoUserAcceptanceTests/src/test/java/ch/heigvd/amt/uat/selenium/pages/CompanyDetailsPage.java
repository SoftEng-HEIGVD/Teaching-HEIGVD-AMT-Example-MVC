package ch.heigvd.amt.uat.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the "Company Details" page in the MVCDemo app.
 * 
 * @author Olivier Liechti
 */
public class CompanyDetailsPage extends AbstractMVCDemoPage {

    By menuExamplesLocator = By.id("menuExamples");

  public CompanyDetailsPage(WebDriver driver) {
    super(driver);

    // Check that we're on the right page.
    if (!"Company Details".equals(driver.getTitle())) {
      throw new IllegalStateException("This is not the correct page");
    }
  }
  
 
  
}
