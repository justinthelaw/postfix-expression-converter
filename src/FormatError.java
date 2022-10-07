/*
 * file: FormatError.java
 * class: EN.605.202.82.FA22
 */

/**
 * This class provides standard formatting of an exception
 *
 * @author Justin Law
 * @version 1.0
 */

public class FormatError {

  /**
   * Formats the exception and prints to console
   *
   * @param Exception
   * @param suggestion string
   */
  public static void printError(Exception e, String problem) {
    // catch exception and provide possible suggested fixes
    System.out.println("EXCEPTION: " + e.toString() + " during " + e.getStackTrace()[0].toString());
    System.out.println("PROBLEM: " + problem + "\n");
    return;
  }
}
