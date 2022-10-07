/*
 * file: Metrics.java
 * class: EN.605.202.82.FA22
 */

/**
 * This class is a parent of RuntimeMetric - adding storage, retrieval,
 * and formatting methods in an array. This allows the manipulation
 * and organization of the output for each operation of prefix to
 * postfix expression conversion.
 *
 * @author Justin Law
 * @version 1.0
 */

public class Metrics {

  private RuntimeMetric[] metrics;
  private int index;
  private int maxSize;

  /**
   * Constructor to create the metric storage array
   *
   * @param size
   */
  public Metrics(int size) {
    this.maxSize = size;
    this.metrics = new RuntimeMetric[this.maxSize];
    this.index = 0;
  }

  /**
   * Stores a new metric in the array and increments index
   *
   * @param metric
   */
  public void storeMetric(RuntimeMetric metric) {
    if (index > this.metrics.length) {
      this.increaseMaxSize();
    }
    metrics[index] = metric;
    index++;
    return;
  }

  /**
   * Increases the maxSize of the array by 2x when at max capacity
   */
  private void increaseMaxSize() {
    int newMaxSize = this.maxSize * 2;
    RuntimeMetric[] newMetrics = new RuntimeMetric[newMaxSize];
    // copy over stack contents into new, larger stack
    for (int i = 0; i < this.maxSize; i++) {
      newMetrics[i] = this.metrics[i];
    }
    this.maxSize = newMaxSize;
    this.metrics = newMetrics;
    return;
  }

  /**
   * Override method to get stored metrics as CSV format
   *
   * @return string that contains each metric on a separate line
   */
  public String toString() {
    String results = "SIZE,TIME,SUCCESSFUL\n";
    for (int i = 0; i < index; i++) {
      results += metrics[i].getSize() + "," +
          metrics[i].getRuntime();
      // append FALSE when a conversion shows sign of failure
      // or empty expression
      if (metrics[i].getSize() == 0) {
        results += "," + "FALSE\n";
      } else {
        results += "," + "TRUE\n";
      }
    }
    return results;
  }

}
