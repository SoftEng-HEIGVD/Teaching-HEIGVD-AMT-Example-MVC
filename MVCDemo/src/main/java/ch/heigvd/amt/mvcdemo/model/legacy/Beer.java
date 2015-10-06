package ch.heigvd.amt.mvcdemo.model.legacy;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is a model class. Something that is often called a Plain Old Java Object
 * (POJO). 
 * 
 * Something that is also called a Java Bean (not an Enterprise Java Bean!),
 * because it follows certain conventions for method names. You will notice that
 * for each property, there is a method starting with "get". This is what is called
 * a "getter" function. Later in the course, we will talk about reflection (the ability
 * to dynamically inspect and manipulate objects and classes from your code). You will
 * understand why it is useful to have this type of conventions at this point.
 * 
 * Note that we have not defined "setter" methods for this Java Bean. In general, it
 * is a good practice to work with immutable objects. Until you have the need for changing
 * the state of an object after its creation, you should use this approach.
 * 
 * @author Olivier Liechti
 */
@XmlRootElement
public class Beer {
  
  private final String name;
  private final String brewery;
  private final String country;
  private final String style;

  public Beer(String name, String brewery, String country, String style) {
    this.name = name;
    this.brewery = brewery;
    this.country = country;
    this.style = style;
  }

  public String getName() {
    return name;
  }

  public String getBrewery() {
    return brewery;
  }

  public String getCountry() {
    return country;
  }

  public String getStyle() {
    return style;
  }
  
  

}
