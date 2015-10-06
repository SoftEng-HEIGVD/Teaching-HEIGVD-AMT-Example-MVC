package ch.heigvd.amt.mvcdemo.model.entities;

/**
 *
 * @author Olivier Liechti
 */
public class SalaryStatistics {

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
