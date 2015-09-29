package ch.heigvd.amt.uat.selenium.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;

/**
 * This class is used to test the "Corporate Information" page in the MVCDemo
 * app.
 *
 * @author Olivier Liechti
 */
public class CorporateInformationPage extends AbstractMVCDemoPage {

  /*
   * See: http://elementalselenium.com/tips/25-tables
  */
  By allCompanyLinksInCompaniesTableLocator = By.cssSelector("#companiesTable tbody tr td:nth-of-type(1) a");

  public CorporateInformationPage(WebDriver driver) {
    super(driver);

    // Check that we're on the right page.
    if (!"Corporate Information".equals(driver.getTitle())) {
      throw new IllegalStateException("This is not the correct page");
    }
  }
  
  public CompanyDetailsPage clickOnFirstCompanyLinkInCompaniesTable() {
    List<WebElement> links = driver.findElements(allCompanyLinksInCompaniesTableLocator);
    
    /**
     * Let's assume that we want to enforce that there is always at least one
     * company in the list. This assertion would fail if we run the test before
     * generating the test data.
     */
    assertFalse(links.isEmpty());
    
    /**
     * Click on the first company link in the table
     */
    links.get(0).click();
    
    return new CompanyDetailsPage(driver);
  }

}
