package ch.heigvd.amt.mvcdemo.util;

import org.apache.commons.lang.ArrayUtils;

/**
 * This utility class is used to generate random values.
 * 
 * @author Olivier Liechti
 */
public class Chance {
  
  private static final String[] firstNamesMale = {"John", "Peter", "Sacha", "Olivier", "Raymond", "Steve", "Kyttim"};
  private static final String[] firstNamesFemale = {"Sarah", "Fabienne", "Claire", "Clara", "Isabelle", "Choua", "Lydie", "Désirée", "Laure"};
  private static final String[] lastNames = {"Smith", "Jones", "Durand", "Liechti", "Deriaz", "Guignard", "Braig"};

  /**
   * Basic random generator for first names
   * @return a random first name (can be either male or female)
   */
  public static String randomFirstName() {
    return pickRandom((String[])ArrayUtils.addAll(firstNamesMale, firstNamesFemale));
  }
  
  /**
   * Basic random generator for last names
   * @return a random last name
   */
  public static String randomLastName() {
    return pickRandom(lastNames);
  }

  /**
   * Select a random element within an array of elements
   * @param elements the array in which to select a random element
   * @return one element of the array, selected randomly
   */
  public static String pickRandom(String[] elements) {
    return elements[(int)(Math.random() * elements.length)];
  }
  
  /**
   * Returns a number value between min and max
   * @param min the min value
   * @param max the max value
   * @return a random value between min and max
   */
  public static double randomDouble(double min, double max) {
    return (long)(Math.random() * (max - min)) + min;
  }

}
