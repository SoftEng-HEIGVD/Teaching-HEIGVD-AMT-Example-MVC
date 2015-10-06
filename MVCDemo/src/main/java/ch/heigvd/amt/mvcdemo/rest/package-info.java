package ch.heigvd.amt.mvcdemo.rest;

/**
 * This package contains the JAX-RS resource classes. From a conceptual point of
 * view, these classes belong to the PRESENTATION tier. Indeed, a REST API is
 * one way to access business services (a Web UI is another way).
 * 
 * From a technical point of view, if you look at the source code, we have
 * implemented the JAX-RS resource classes as Stateless Session Beans. Why? The
 * reason is that it makes our life easier: we can inject EJBs (business services
 * and DAOs) into the JAX-RS resources classes, we have access to the JPA
 * persistence context. In the course, we will not talk about Context and Dependency
 * Injection (CDI). This is often used as an alternative to EJB-based dependency
 * injection (CDI was added to Java EE later).
 * 
 * You should read the code in the following order:
 * - SystemResource.java
 * - BeersResource.java
 * - SectorsResource.java
 * - CompaniesResource.java
 * - EmployeesResource.java
 * - HiringFormsResource.java
 * 
 * The .dto package contains Data Transfer Object (DTO) classes, which define
 * what data is exchanged with HTTP clients via the REST API endpoint (in and
 * out).
 * 
 * The .config package contains configuration files, which you might want to adapt
 * if you have special serialization/deserialization needs.
 */