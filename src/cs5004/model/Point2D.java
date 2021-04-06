package cs5004.model;

/**
 * This class represents a 2D point. This point is denoted in Cartesian coordinates (x,y) within the
 * first quadrant of the coordinate plane where both x and y have non negative values.
 */
public class Point2D {
  private double x;
  private double y;

  /**
   * Construct a 2d point with the given coordinates
   *
   * @param x the x-coordinate of this point
   * @param y the y-coordinate of this point
   */
  public Point2D(double x, double y) throws IllegalArgumentException {

    if (x < 0 | y < 0) {
      throw new IllegalArgumentException("X and Y Coordinates cannot be negative");
    }

    this.x = x;
    this.y = y;
  }

  /**
   * Compute and return the Euclidean distance of this point to the origin
   *
   * @return the euclidean distance
   */
  public double distToOrigin() {
    return Math.sqrt(x * x + y * y);
  }

  /**
   * Return the x-coordinate of this point
   *
   * @return x-coordinate of this point
   */
  public double getX() {
    return x;
  }

  /**
   * Return the y-coordinate of this point
   *
   * @return y-coordinate of this point
   */
  public double getY() {
    return y;
  }

  /**
   * Set a new x coordinate for the point.
   *
   * @param x new x-axis coordinate.
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Set a new y coordinate for the point.
   *
   * @param y new y-axis coordinate.
   */
  public void setY(double y) {
    this.y = y;
  }
}
