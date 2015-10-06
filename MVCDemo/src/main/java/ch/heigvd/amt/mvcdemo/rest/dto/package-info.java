package ch.heigvd.amt.mvcdemo.rest.dto;

/**
 * This package contains classes that implement the Data Transfer Object (DTO).
 * Whether to use DTOs or not is an architecture choice that you have to make.
 *
 * Some people (and wizards) prefer to only use the model classes (i.e. the JPA
 * entities). Their main argument is that implementing DTOs requires extra
 * effort (implementation and maintenance) and that you end up with two
 * redundant class hierarchies.
 *
 * There is indeed some extra work required when using DTOs, but in my opinion
 * the approach has important benefits that justify the effort. In my
 * experience, creating a DTO layer actually saves time, reduces debugging time
 * (using JPA entities across all tiers can lead to tricky bugs and technical
 * problems and makes your code base cleaner.
 *
 * Here are the main benefits for using DTOs:
 *
 * 1) You explicitly control what goes in an out of your server application and
 * have the possibility to FILTER some of the business domain state. In this
 * application, look at the Employee business object. The JPA entity defines
 * sensitive properties, such as basicSalary and bonus. This is clearly not
 * something that you want to leak out of your server on all of your REST APIs
 * (/employees/2838 should in general NOT return sensitive properties). When you
 * create a DTO (such as EmployeeDTO), you clearly state what information will
 * be sent to clients. Your code will be easier to read and to test.
 *
 * 2) You can reduce the chattiness of your application and greatly improve
 * performance. Chattiness refers to the number of request-response steps needed
 * to implement a given use case (such as displaying a somewhat complex web
 * page). The key to reduce chattiness is to aggregate small objects into
 * coarser objects when implementing the client-server interface. In the
 * application, think about the relationship between "Company", "Employee" and
 * "Sector". Imagine that you want to implement a REST client that displays the
 * a page with the company information (name of the company, name of the CEO,
 * list of industry sector names). If you only use the JPA entities and do not
 * introduce a DTO layer, then you will most likely end up calling REST
 * endpoints in a sequence like this:
 *
 * GET /companies/883 (returns a JSON payload with links to sector URIs) GET
 * /companies/883/employees?byTitle=CEO GET /companies/883/sectors/893 GET
 * /companies/883/sectors/291 GET /companies/883/sectors/112
 *
 * This is both painful to write (as a developer using the REST API) and very
 * inefficient. If you create a DTO layer, then you can create a CompanyDTO
 * class, which contains all the properties required to display the page. On the
 * server side, when you receive a request like
 *
 * GET /companies/883 (returns a JSON payload with links to sector URIs),
 *
 * then you will issue a series of JPA calls (via the DAOs)d and get the
 * company, sectors and employee entities. You will use this to build the
 * CompanyDTO aggregate, which you will return in a single response. In other
 * words, the REST client will get a rich JSON payload with all the information.
 * The developer of the REST client will have a much simpler life and the
 * performances will be much better.
 *
 * 3) If you try to use JPA entities from your REST API layer (i.e. serialize
 * and deserialize JPA entities), you will quickly encounter issues related to
 * circular references. Sure, they can be fixed (with @XmlTransient annotations
 * and other subtleties that we will not discuss in details here). Trust me,
 * sooner or later, you will decide to make the switch to a DTO layer.
 *
 *
 *
 */
