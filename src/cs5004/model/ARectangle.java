package cs5004.model;

import java.awt.Color;

/**
 * This class represents a rectangle shape type. It is a derived from AnimatedShapeImpl. Named
 * ARectangle to prevent conflict with Java.awt.
 *
 * <p>Instantiates with default values, 1x1 width, color: black.
 */
public class ARectangle extends AnimatedShapeImpl {

  // Constants - Default Values
  private static final int DEFAULT_WIDTH = 1;
  private static final int DEFAULT_LENGTH = 1;
  private static final Color DEFAULT_COLOR = Color.black;
  // private static final Velocity2D DEFAULT_VELOCITY = new Velocity2D(0.0, 0.0);

  // Attributes
  private double width;
  private double length;

  /**
   * Minimal Constructor for a Rectangle. Requires a location to be created & instantiates with
   * default values.
   *
   * @param name Name of the shape represented by a string.
   * @param aReference Point2D Object referring to the location of the shape.
   */
  public ARectangle(String name, Point2D aReference, int aAppearTime, int aDisappearTime) {
    super(aReference, name, aAppearTime, aDisappearTime);
    this.width = DEFAULT_WIDTH;
    this.length = DEFAULT_LENGTH;
    this.shapeColor = DEFAULT_COLOR;
  }

  /**
   * Constructor for a Rectangle. Requires a location to be created & instantiates passed arguments.
   *
   * @param name The name of the shape.
   * @param ref Point2D Object referring to the location of the sha
   * @param r Red (0 -> 255) for rgb.
   * @param g Green (0 -> 255) for rgb.
   * @param b Blue (0 -> 255) for rgb.
   * @param length The Length of the shape.
   * @param width The width of the shape. width
   */
  public ARectangle(
      String name,
      Point2D ref,
      int r,
      int g,
      int b,
      int length,
      int width,
      int aAppearTime,
      int aDisappearTime) {
    super(name, ref, r, g, b, aAppearTime, aDisappearTime);
    this.width = width;
    this.length = length;
  }

  /**
   * Getter Method for returning the width of the rectangle.
   *
   * @return A double value representing the width of the rectangle.
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Setter method for mutating the width of a rectangle.
   *
   * @param aWidth A double value representing the width of the rectangle.
   */
  public void setWidth(double aWidth) {
    if (aWidth > 0) {
      this.width = aWidth;
    } else {
      throw new IllegalArgumentException("Width must have a value greater than zero");
    }
  }

  /**
   * Getter method for returning the length of a rectangle.
   *
   * @return A double value representing the length of a rectangle.
   */
  public double getLength() {
    return this.length;
  }

  /**
   * Setter method for mutating the length of a rectangle.
   *
   * @param aLength A double value representing the length of a rectangle.
   */
  public void setLength(double aLength) {
    if (aLength > 0) {
      this.length = aLength;
    } else {
      throw new IllegalArgumentException("Width must have a value greater than zero");
    }
  }

  /**
   * To string method for returning the state of the rectangle.
   *
   * @return A string representation of the rectangle.
   */
  @Override
  public String generateInfoScript() {
    String info;
    String rgb = this.getColor().toString();

    info = String.format("Name: %s\n", this.getName());
    info += String.format("Type: %s\n", this.getClass().getSimpleName());
    info +=
        String.format(
            "Corner: (%.2f,%.2f), Length: %.2f, Width: %.2f, Color: %s\n",
            getLocation().getX(), getLocation().getY(), this.length, this.width, rgb);

    return info;
  }
}
