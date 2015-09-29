package ch.heigvd.amt.uat.selenium.pages;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the "Login" page in the MVCDemo app.
 * 
 * @author Olivier Liechti
 */
public class LoginPage extends AbstractMVCDemoPage {

  By tfEmailLocator = By.id("inputEmail");
  By tfPasswordLocator = By.id("inputPassword");
  By bSigninLocator = By.id("bSignIn");

  public LoginPage(WebDriver driver) {
    super(driver);

    // Check that we're on the right page.
    if (!"Login Page".equals(driver.getTitle())) {
      throw new IllegalStateException("This is not the correct page");
    }
  }

  public LoginPage typeEmailAddress(String email) {
    driver.findElement(tfEmailLocator).sendKeys(email);
    return this;
  }

  public LoginPage typePassword(String password) {
    driver.findElement(tfPasswordLocator).sendKeys(password);
    return this;
  }

  public Page submitForm(Class<? extends Page> expectedPageClass) {
    driver.findElement(bSigninLocator).click();
    Page targetPage = null;
    try {
      targetPage = expectedPageClass.getConstructor(WebDriver.class).newInstance(driver);
    } catch (Exception ex) {
      Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
      throw new RuntimeException("Exception when using reflection: " + ex.getMessage());
    }
    return targetPage;
  }

  public LoginPage submitFormExpectingFailure() {
    driver.findElement(bSigninLocator).click();
    return this; //new LoginPage(driver);
  }
}
