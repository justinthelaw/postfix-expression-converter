/*
 * file: Project01.java
 * class: EN.605.202.82.FA22
 */

/**
 * This class contains the main executable and is the entrypoint for the user.
 * The class takes in an input of prefix expressions, uses the conversion
 * rulesets along with the GenericStack and RuntimeMetrics modules to produce an
 * output containing postfixes expressions and operations metrics.
 *
 * @author Justin Law
 * @version 1.0
 */

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Project01 {

  public static void main(String[] args) throws FileNotFoundException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, IOException, NoSuchMethodException, SecurityException, InputMismatchException,
      ArrayIndexOutOfBoundsException {

    String input = "";
    String output = "";
    try {
      input = args[0];
      output = args[1];
    } catch (Exception e) {
      FormatError.printError(e, "Please enter 2 command line arguments for (1) input file and (2) output location");
      return;
    }

    System.out.println("\nAttempting to ingest and convert all prefix expressions in \"" + input
        + "\" to postfix expressions and output results to \"" + output + "\"\n");

    // empty file contents if file already exists
    if ((new File(output)).exists()) {
      PrintWriter writer = new PrintWriter(output);
      writer.print("");
      writer.close();
      writer = new PrintWriter(output + "-metrics.csv");
      writer.print("");
      writer.close();
    }

    // get the data validator from the PrefixToPostfix conversion class
    Method check = null;
    String methodName = "isOperandOrOperator";
    try {
      check = PrefixToPostfix.class.getMethod(methodName, char.class);
    } catch (NoSuchMethodException e) {
      FormatError.printError(e, "No method exists: " + methodName);
    } catch (SecurityException e) {
      FormatError.printError(e, "Unsafe method call: " + methodName);
    }

    // perform read and conversion, line-by-line
    int linesToSkip = (int) Files.lines(Paths.get(args[0])).count();

    // prepare a new consolidated metrics object for CSV export
    Metrics metrics = new Metrics(linesToSkip);

    for (int i = 0; i < linesToSkip; i++) {
      // build prefix stack from input file, character-by-character
      GenericStack<Character> prefixStack = ReadWrite.bufferedRead(input, i, check);
      // begin building the output's result string for this line
      String result = prefixStack.toReversedString();

      // start metric collection
      RuntimeMetric metric = new RuntimeMetric(prefixStack.getCurrentSize());
      metric.start();

      // conversion happens in the PrefixtoPostfix class
      String postfixOutput = "\n\tPOSTFIX EXPRESSION: " + PrefixToPostfix.convert(prefixStack, (i + 1));

      // end metric collection
      metric.end();

      // concatenates original prefix expression details with new postfix expression
      result += postfixOutput;

      // write postfix to file if there are no errors (empty postfix)
      ReadWrite.bufferedWrite(output, "LINE #" + (i + 1) + "\n");
      if (postfixOutput.length() > 22) {
        // only store good metrics
        metrics.storeMetric(metric);
        String padding = "";
        if ((i + 1) != linesToSkip) {
          padding = "\n";
        }
        ReadWrite.bufferedWrite(output, result + padding);
      } else {
        ReadWrite.bufferedWrite(output, "\tINPUT ERROR, SEE CONSOLE FOR DETAILS\n");
      }
    }
    // write metrics to separate file associated with output
    ReadWrite.bufferedWrite(output + "-metrics.csv", metrics.toString());
  }

}
