/*
 * file: RunetimeMetric.java
 * class: EN.605.202.82.FA22
 */

/**
 * This class is a re-capitalization of the runtime metrics class provided in
 * EN.605.202.82.FA22 References Resources module. This class is instantiated at
 * the beginning of a single operation and then used to track the amount of time
 * it took for that operation to run. The resulting data is then stored in
 * Metrics.
 *
 * @author W.T. Door
 * @author Justin Law
 * @version 1.1
 * @see https://jhu.instructure.com/courses/18085/pages/programming-assignments-guidelines?module_item_id=1658592
 */
public class RuntimeMetric {

  private long size;
  private long start = 0;
  private long end = 0;

  /**
   * Constructor is used to create each metric. The metric
   * cannot be changed after creation.
   *
   * @param n The size of the problem.
   */
  public RuntimeMetric(long n) {
    this.size = n;
  }

  /**
   * Fetches the time it took to solve the problem.
   *
   * @return The time measured in nanoseconds.
   */
  public long getRuntime() {
    return this.end - this.start;
  }

  /**
   * Fetches the size of the problem.
   *
   * @return A size that is determined by the way the problem is stated.
   */
  public long getSize() {
    return this.size;
  }

  /**
   * Gets the start time
   *
   */
  public void start() {
    this.start = System.nanoTime();
    return;
  }

  /**
   * Gets the end time
   *
   */
  public void end() {
    this.end = System.nanoTime();
    return;
  }

}
