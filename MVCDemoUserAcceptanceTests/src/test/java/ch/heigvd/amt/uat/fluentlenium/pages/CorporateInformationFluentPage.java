package ch.heigvd.amt.uat.fluentlenium.pages;

import ch.heigvd.amt.uat.selenium.pages.*;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
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
public class CorporateInformationFluentPage extends AbstractMVCDemoFluentPage {

  /*
   * See: http://elementalselenium.com/tips/25-tables
   */
  private static final String allCompanyLinks = "#companiesTable tbody tr td:nth-of-type(1) a";

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Corporate Information");
  }

  public void clickOnFirstCompanyLinkInCompaniesTable() {
    find(allCompanyLinks).first().click();
  }

  public String getUrl() {
    return "/pages/corporateInformation";
  }

}
