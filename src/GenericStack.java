// supression of unchecked typecast warnings
// initial cast happens at stack instantiation, without possible modification
@SuppressWarnings("unchecked")

/*
 * file: GenericStack.java
 * class: EN.605.202.82.FA22
 */

/**
 * This class is a Generics version of a standard stack data structure
 * implemented with an array and top-pointer. This implementation also includes
 * data deletion (null) and automatic stack resizing.
 *
 * @author Justin Law
 * @version 1.0
 */
public class GenericStack<T> {

  private T[] stack;
  private int maxSize;
  private int currentSize;
  private int top;

  /**
   * Constructor for the stack, requires argument for the stack MaxSize
   *
   * @param maxSize
   */
  public GenericStack(int maxSize) {
    this.maxSize = maxSize;
    this.currentSize = 0;
    // locks in the data type for this instantiation of the stack
    // where <T> can be any type
    this.stack = (T[]) new Object[maxSize];
    this.top = -1;
  }

  /**
   * Pushes an element into the stack and returns the value
   *
   * @param element
   *
   * @return element
   */
  public T push(T element) {
    top++;
    stack[top] = element;
    currentSize++;
    // do a post-op check for capacity
    // increase the MaxSize when max capacity reached
    if (this.isFull()) {
      this.increaseMaxSize();
    }
    return element;
  }

  /**
   * Removes the top value and returns the value
   *
   * @return element
   */
  public T pop() {
    T element = this.stack[top];
    this.stack[top] = null;
    top--;
    currentSize--;

    return element;
  }

  /**
   * Returns the top value
   *
   * @return element
   */
  public T peek() {
    return this.stack[top];
  }

  /**
   * Increases the maxSize of the stack by 2x when at max capacity
   */
  private void increaseMaxSize() {
    int newMaxSize = this.maxSize * 2;
    T[] newStack = (T[]) new Object[newMaxSize];
    // copy over stack contents into new, larger stack
    for (int i = 0; i < this.maxSize; i++) {
      newStack[i] = this.stack[i];
    }
    this.maxSize = newMaxSize;
    this.stack = newStack;
    return;
  }

  /**
   * Checks the stack to see if it is full
   *
   * @return boolean
   */
  private boolean isFull() {
    return ((top + 1) == this.maxSize);
  }

  /**
   * Checks the stack to see if it is empty
   *
   * @return boolean
   */
  public boolean isEmpty() {
    return (this.currentSize == 0);
  }

  /**
   * Provides stack size to other classes
   *
   * @return int size of stack
   */
  public int getCurrentSize() {
    return this.currentSize;
  }

  /**
   * Empties all stack contents
   *
   * @return void
   */
  public void empty() {
    this.stack = (T[]) new Object[this.maxSize];
    this.currentSize = 0;
    this.top = -1;
    return;
  }

  /**
   * Override to return a readable string interpretation of the stack's
   * data and metadata. Displays the elements delineated by commas and
   * the current size vs. max size
   *
   * @return String
   */
  public String toString() {
    // visual pointer to the top element
    String s = "STACK CONTENTS: TOP -> ";
    for (int i = top; i >= 0; i--) {
      s += this.stack[i];
      // add comma delineator except for last element
      if (i != 0) {
        s += ", ";
      }
    }
    return s + "\nSTACK SIZE: " + this.currentSize + " / " + this.maxSize;
  }

  /**
   * For printing purely the original input into the stack
   *
   * @return String
   */
  public String toReversedString() {
    // visual pointer to the top element
    String s = "\tPREFIX EXPRESSION: ";
    for (int i = 0; i <= top; i++) {
      s += this.stack[i];
    }
    return s;
  }

}
