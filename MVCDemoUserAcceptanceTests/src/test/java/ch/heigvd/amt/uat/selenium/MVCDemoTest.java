package ch.heigvd.amt.uat.selenium;

import ch.heigvd.amt.uat.selenium.pages.AboutPage;
import ch.heigvd.amt.uat.selenium.pages.HomePage;
import ch.heigvd.amt.uat.selenium.pages.LoginPage;
import io.probedock.client.annotations.ProbeTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
public class MVCDemoTest {

  private String baseUrl = "http://localhost:8080/MVCDemo-1.0-SNAPSHOT";
  private WebDriver driver;

  @Before
  public void openBrowser() {
    //driver = new FirefoxDriver();
    System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
    driver = new ChromeDriver();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void itShouldNotBePossibleToSigninWithAnInvalidEmail() {
    driver.get(baseUrl);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("this is not a valid email address");
    loginPage.typePassword("any password");
    loginPage.submitFormExpectingFailure();
  }
  
  @Test
  @ProbeTest(tags = "WebUI")
  public void successfulSigninShouldBringUserToHomePage() {
    driver.get(baseUrl);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("a@a.com");
    loginPage.typePassword("any password");
    HomePage homePage = (HomePage)loginPage.submitForm(HomePage.class);
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void aUserTryingToGetToAboutPageShouldBeRedirectedThereAfterSignin() {
    driver.get(baseUrl + "/pages/about");
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("a@a.com");
    loginPage.typePassword("any password");
    AboutPage aboutPage = (AboutPage)loginPage.submitForm(AboutPage.class);
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void aUserShouldBeAbleToVisitAllPagesAfterSignin() {
    driver.get(baseUrl);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("a@a.com");
    loginPage.typePassword("any password");
    HomePage homePage = (HomePage)loginPage.submitForm(HomePage.class);
    homePage.goToBeersPageViaMenu()
      .goToBeersPageViaMenu()
      .goToAJAXPageViaMenu()
      .goToGenerateTestDataPageViaMenu()
      .goToCorporateInformationPageViaMenu();
  }

  @Test
  @ProbeTest(tags = "WebUI")
  public void aUserShouldBeAbleToGetDetailsInformationAboutACompany() {
    driver.get(baseUrl);
    LoginPage loginPage = new LoginPage(driver);
    loginPage.typeEmailAddress("a@a.com");
    loginPage.typePassword("any password");
    HomePage homePage = (HomePage)loginPage.submitForm(HomePage.class);
    homePage.goToCorporateInformationPageViaMenu()
      .clickOnFirstCompanyLinkInCompaniesTable();
  }
  
  @After
  public void closeBrowser() {
    driver.close();
  }
}
