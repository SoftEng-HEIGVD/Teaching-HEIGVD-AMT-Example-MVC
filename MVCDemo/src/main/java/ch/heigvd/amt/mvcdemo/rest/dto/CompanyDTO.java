package ch.heigvd.amt.mvcdemo.rest.dto;

/**
 * This DTO provides more information than the CompanySummaryDTO. Note that it is
 * possible for a DTO to extend another one. If you look at the 
 * @author Olivier Liechti
 */
public class CompanyDTO extends CompanySummaryDTO {
  
  private double highestSalary;
  private double averageSalary;

  public double getHighestSalary() {
    return highestSalary;
  }

  public void setHighestSalary(double highestSalary) {
    this.highestSalary = highestSalary;
  }

  public double getAverageSalary() {
    return averageSalary;
  }

  public void setAverageSalary(double averageSalary) {
    this.averageSalary = averageSalary;
  }

}
