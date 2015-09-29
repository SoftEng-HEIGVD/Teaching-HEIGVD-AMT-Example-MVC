package ch.heigvd.amt.mvcdemo.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Olivier Liechti
 */
public class ExceptionSummarizer {
  
  public static List<String> summarize(Exception e) {
    List<String> summary = new ArrayList<>();
    summary.add(e.getMessage());
    Throwable cause = e.getCause();
    while (cause != null) {
      summary.add(cause.getMessage());
      cause = cause.getCause();
    }
    
    return summary;
    
  }

}
