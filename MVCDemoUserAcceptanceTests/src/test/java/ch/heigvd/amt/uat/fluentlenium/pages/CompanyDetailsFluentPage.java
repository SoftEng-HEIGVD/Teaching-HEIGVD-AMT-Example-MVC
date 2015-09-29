package ch.heigvd.amt.uat.fluentlenium.pages;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class is used to test the "Company Details" page in the MVCDemo app.
 *
 * @author Olivier Liechti
 */
public class CompanyDetailsFluentPage extends AbstractMVCDemoFluentPage {

  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Company Details");
  }

}
