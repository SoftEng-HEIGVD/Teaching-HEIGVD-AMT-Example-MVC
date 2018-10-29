package ch.heigvd.amt.uat.fluentlenium;

import ch.heigvd.amt.uat.fluentlenium.pages.BeersFluentPage;
import ch.heigvd.amt.uat.fluentlenium.pages.CompanyDetailsFluentPage;
import ch.heigvd.amt.uat.fluentlenium.pages.CorporateInformationFluentPage;
import ch.heigvd.amt.uat.fluentlenium.pages.HomeFluentPage;
import ch.heigvd.amt.uat.fluentlenium.pages.LoginFluentPage;
import io.probedock.client.annotations.ProbeTest;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.fluentlenium.core.annotation.Page;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
public class MVCDemoFluentTest extends FluentTest {

  private final String baseUrl = "http://localhost:8080/MVCDemo-1.0-SNAPSHOT";

  @Page
  public LoginFluentPage loginPage;

  @Page
  public HomeFluentPage homePage;

  @Page
  public BeersFluentPage beersPage;
  
  @Page
  public CorporateInformationFluentPage corporateInformationPage;
  
  @Page
  public CompanyDetailsFluentPage companyDetailsPage;


  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToSigninWithAnInvalidEmail() {
    goTo(baseUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("not a valid email");
    loginPage.typePassword("any password");
    loginPage.clickSignin();
    loginPage.isAt();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void successfulSigninShouldBringUserToHomePage() {
    goTo(baseUrl);
    loginPage.isAt();
    loginPage.typeEmailAddress("a@a.com");
    loginPage.typePassword("any password");
    loginPage.clickSignin();
    homePage.isAt();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldBePossibleToGetDetailsForACompanyAfterSignin() {
    goTo(corporateInformationPage);
    loginPage.isAt(); // we have not logged in, so we should be redirected
    loginPage.typeEmailAddress("a@a.com");
    loginPage.typePassword("any password");
    loginPage.clickSignin();
    corporateInformationPage.isAt(); // we should be redirected toward the original target after signin
    corporateInformationPage.clickOnFirstCompanyLinkInCompaniesTable();
    companyDetailsPage.isAt();
  }

  
  @Override
  public WebDriver getDefaultDriver() {
    //return new FirefoxDriver();
    System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
    return new ChromeDriver();
  }

  @Override
  public String getDefaultBaseUrl() {
    return baseUrl;
  }
  
}
