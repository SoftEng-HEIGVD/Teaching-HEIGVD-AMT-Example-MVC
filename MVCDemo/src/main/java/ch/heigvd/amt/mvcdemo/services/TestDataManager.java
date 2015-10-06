package ch.heigvd.amt.mvcdemo.services;

import ch.heigvd.amt.mvcdemo.services.dao.SectorsDAOLocal;
import ch.heigvd.amt.mvcdemo.services.dao.CompaniesDAOLocal;
import ch.heigvd.amt.mvcdemo.model.entities.Company;
import ch.heigvd.amt.mvcdemo.model.entities.Department;
import ch.heigvd.amt.mvcdemo.model.entities.Employee;
import ch.heigvd.amt.mvcdemo.model.entities.Sector;
import ch.heigvd.amt.mvcdemo.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.mvcdemo.services.dao.DepartmentsDAOLocal;
import ch.heigvd.amt.mvcdemo.services.dao.EmployeesDAOLocal;
import ch.heigvd.amt.mvcdemo.util.Chance;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This SLSB is used to generate test data.
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
public class TestDataManager implements TestDataManagerLocal {

  @EJB
  SectorsDAOLocal sectorsDAO;

  @EJB
  CompaniesDAOLocal companiesDAO;

  @EJB
  EmployeesDAOLocal employeesDAO;

  @EJB
  DepartmentsDAOLocal departmentsDAO;

  @Override
  public void generateTestData() {

    /**
     * Create some industry sectors. This example shows that sometimes, you
     * don't care about the returned value. No need to worry about "managed
     * objects".
     */
    sectorsDAO.create(new Sector("Health Care"));
    sectorsDAO.create(new Sector("IT"));
    sectorsDAO.create(new Sector("Telecommunications"));
    sectorsDAO.create(new Sector("Energy"));
    sectorsDAO.create(new Sector("Materials"));
    sectorsDAO.create(new Sector("Financials"));

    /**
     * Create companies. We will use the Company objects when we create other
     * types objects (we will pass them in constructor arguments). We use the
     * DAO method which returns a managed object.
     *
     * This is tricky part of JPA. You have to remember that here, we are in
     * SLSB, calling another SLSB. By default, all calls are done in the same
     * transaction and therefore share the same JPA persistence context. This
     * means that the apple object created in the first call is still managed by
     * JPA (if we modify it, it will automatically be synced with the DB).
     * 
     * If were making the same call from a servlet, this would be different. The
     * apple object would not be managed by JPA anymore.
     */
    Company apple = companiesDAO.createAndReturnManagedEntity(new Company("Apple"));
    Company ibm = companiesDAO.createAndReturnManagedEntity(new Company("IBM"));
    Company hp = companiesDAO.createAndReturnManagedEntity(new Company("HP"));
    Company ubs = companiesDAO.createAndReturnManagedEntity(new Company("UBS"));
    Company cs = companiesDAO.createAndReturnManagedEntity(new Company("Crédit Suisse"));
    Company swisscom = companiesDAO.createAndReturnManagedEntity(new Company("Swisscom"));
    Company salt = companiesDAO.createAndReturnManagedEntity(new Company("Salt"));

    /**
     * The following code is not an example to follow, but rather a place to experiment
     * with exceptions and transactions. Depending on the type of exception and whether
     * it is caught or not, the transaction will rollback some of the changes you
     * have made in this method (before the exception is thrown).
     */
    try {
      companiesDAO.assignSector("UBS", "Financials");
      companiesDAO.assignSector("Apple", "IT");
      companiesDAO.assignSector("Apple", "Entertainment");
      companiesDAO.assignSector("Apple", "Telecommunications");
    } catch (BusinessDomainEntityNotFoundException ex) {
      Logger.getLogger(TestDataManager.class.getName()).log(Level.SEVERE, null, ex);
    }

    Employee ceo = employeesDAO.createAndReturnManagedEntity(new Employee("Tim", "Cook", "CEO", 1.0, 5000000.0));
    Employee cfo = employeesDAO.createAndReturnManagedEntity(new Employee("Luca", "Maestri", "CFO", 200000.0, 1000000.0));
    Employee coo = employeesDAO.createAndReturnManagedEntity(new Employee("Evan", "Peterson", "COO", 200000.0, 1000000.0));
    Employee sse1 = employeesDAO.createAndReturnManagedEntity(new Employee("Helen", "Dupont", "Senior Software engineer", 150000.0, 20000.0));
    Employee se1 = employeesDAO.createAndReturnManagedEntity(new Employee("Jamie", "Jones", "Software engineer", 100000.0, 5000.0));
    Employee se2 = employeesDAO.createAndReturnManagedEntity(new Employee("Paul", "Martin", "Software engineer", 100000.0, 5000.0));

    
    /**
     * This example shows that you can define methods to "connect" entities. For this to work,
     * apple and ceo need to be managed entities. So calling this method works in a SLSB, but
     * it would fail in a servlet!
     */
    companiesDAO.hire(apple, ceo);
    companiesDAO.hire(apple, cfo);
    companiesDAO.hire(apple, coo);
    companiesDAO.hire(apple, se1);
    companiesDAO.hire(ibm, sse1);
    companiesDAO.hire(ibm, se2);

    Department hq = departmentsDAO.createAndReturnManagedEntity(new Department(apple, "HQ", ceo));
    Department finance = departmentsDAO.createAndReturnManagedEntity(new Department(apple, "Finance", cfo));
    Department engineering = departmentsDAO.createAndReturnManagedEntity(new Department(apple, "Engineering", coo));
    Department it = departmentsDAO.createAndReturnManagedEntity(new Department(apple, "IT", coo));
    Department hr = departmentsDAO.createAndReturnManagedEntity(new Department(apple, "HR", coo));

    for (int i = 0; i < 38; i++) {
      Employee e = employeesDAO.createAndReturnManagedEntity(new Employee(Chance.randomFirstName(), Chance.randomLastName(), "Staff member", 40000 + Math.random() * 100000, Math.random() * 100000));
      companiesDAO.hire(apple, e);
      it.addMember(e);
    }
    for (int i = 0; i < 43; i++) {
      Employee e = employeesDAO.createAndReturnManagedEntity(new Employee(Chance.randomFirstName(), Chance.randomLastName(), "Staff member", 40000 + Math.random() * 100000, Math.random() * 100000));
      companiesDAO.hire(apple, e);
      engineering.addMember(e);
    }

  }

}
