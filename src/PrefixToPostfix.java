/*
 * file: PrefixToPostfix.java
 * class: EN.605.202.82.FA22
 */

/**
 * This class predefines the rules and processes of prefix to postfix
 * conversions
 *
 * @author Justin Law
 * @version 1.0
 */

public class PrefixToPostfix {

  /**
   * Checks if input is an Operator or Operand
   * Ignores new line character
   *
   * @param char
   */
  public static boolean isOperandOrOperator(char x) {
    return (isOperand(x) || isOperator(x));
  }

  /**
   * Checks if a character is an operator
   *
   * @param char
   */
  public static boolean isOperator(char x) {
    switch (x) {
      case '+':
      case '-':
      case '/':
      case '*':
      case '^':
      case '$':
        return true;
    }
    return false;
  }

  /**
   * Checks if a character is an operand (letter)
   *
   * @param char
   */
  public static boolean isOperand(char x) {
    return Character.isAlphabetic(x);
  }

  /**
   * Transforms the prefix expression to a postfix expression
   *
   * @param GenericStack original prefix expression
   * @return String representation of new postfix expression or an error
   */
  public static String convert(GenericStack<Character> prefix, int lineNumber) throws ArrayIndexOutOfBoundsException {
    // scope the size of the prefix and postfix expression stacks
    int length = prefix.getCurrentSize();
    GenericStack<String> postfix = new GenericStack<>(length);
    // reading from right to left
    try {
      for (int i = length - 1; i >= 0; i--) {
        char c = prefix.pop();
        // check if symbol is an operator
        if (isOperator(c)) {
          // pop two operands from stack
          String firstOperand = postfix.peek();
          postfix.pop();
          String secondOperand = postfix.peek();
          postfix.pop();
          // generate sub-expression and push into stack
          String temp = firstOperand + secondOperand + c;
          postfix.push(temp);
        } else {
          // push the operand to the stack
          // empty quotes to turn character into String
          postfix.push(c + "");
        }
      }
      // returns the final String postfix expression
      return postfix.peek();
    } catch (ArrayIndexOutOfBoundsException e) {
      FormatError.printError(e, "Incorrectly formatted prefix expression at line #" + lineNumber);
      return "";
    }

  }

}
