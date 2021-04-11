package cs5004.animator.model;

/**
 * This class represents a 2D velocity. The deltas are how far the shape moves in one clock tick.
 */
public class Velocity2D {
  private double deltaX;
  private double deltaY;

  /**
   * Construct a 2d velocity with the given values.
   *
   * @param aDeltaX The horizontal velocity component.
   * @param aDeltaY The vertical velocity component.
   */
  public Velocity2D(double aDeltaX, double aDeltaY) {
    this.deltaX = aDeltaX;
    this.deltaY = aDeltaY;
  }

  /**
   * Return the horizontal component.
   *
   * @return deltaX of this object.
   */
  public double getDeltaX() {
    return deltaX;
  }

  /**
   * Return the vertical component.
   *
   * @return deltaY of this object.
   */
  public double getDeltaY() {
    return deltaY;
  }

  /**
   * Set the horizontal component.
   *
   * @param aDeltaX of this object.
   */
  public void setDeltaX(double aDeltaX) {
    // todo: maybe validate? Can't be bigger than the screen size?
    this.deltaX = aDeltaX;
  }

  /**
   * Set the vertical component.
   *
   * @param aDeltaY of this object.
   */
  public void setDeltaY(double aDeltaY) {
    this.deltaY = aDeltaY;
  }
}
