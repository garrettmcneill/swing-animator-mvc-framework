package cs5004.animator.model;

import java.awt.Color;

/**
 * Subclass for the Ellipse Shape.
 */
public class AEllipse extends AnimatedShapeImpl {

  // Constants
  private static final int DEFAULT_A_AXIS = 1;
  private static final int DEFAULT_B_AXIS = 1;
  private static final Color DEFAULT_COLOR = Color.black;

  // Attributes
  private double aAxis;
  private double bAxis;

  /**
   * Minimal Constructor for an ellipse. Requires a location to be created & instantiates with
   * default values.
   *
   * @param name Name of the shape represented by a string.
   * @param aReference Point2D Object referring to the location of the shape.
   */
  public AEllipse(String name, Point2D aReference, int aAppearTime, int aDisappearTime) {
    super(aReference, name, aAppearTime, aDisappearTime);
    this.aAxis = DEFAULT_A_AXIS;
    this.bAxis = DEFAULT_B_AXIS;
    this.shapeColor = DEFAULT_COLOR;
  }


  /**
   * Constructor for an ellipse. Requires a location to be created & instantiates passed arguments.
   *
   * @param name The name of the shape.
   * @param ref Point2D Object referring to the location of the sha
   * @param r Red (0 -> 255) for rgb.
   * @param g Green (0 -> 255) for rgb.
   * @param b Blue (0 -> 255) for rgb.
   * @param aAxis A axis of the ellipse.
   * @param bAxis B axis of the ellipse.
   */
  public AEllipse(
      String name,
      Point2D ref,
      int r,
      int g,
      int b,
      double aAxis,
      double bAxis,
      int aAppearTime,
      int aDisappearTime) {
    super(name, ref, r, g, b, aAppearTime, aDisappearTime);

    if (aAxis <= 0 | bAxis <= 0) {
      throw new IllegalArgumentException("Ellipse axes must be positive non zero entries");
    }

    this.aAxis = aAxis;
    this.bAxis = bAxis;
  }

  //Copy Constructor
  public AEllipse (AEllipse toCopy) {
    super(toCopy);
    this.aAxis = toCopy.aAxis;
    this.bAxis = toCopy.bAxis;
  }

  /**
   * Setter for Elipse axis.
   * @param aAxis The a-axis of the elipse.
   * @param bAxis The b-axis of the elipse.
   */
  public void setAxis(double aAxis, double bAxis) {
    if (aAxis > 0 && bAxis > 0) {
      this.aAxis = aAxis;
      this.bAxis = bAxis;
    } else {
      throw new IllegalArgumentException("Axes must be positive non zero entries.");
    }
  }


  /**
   * Getter Method for a-Axis.
   * @return The double value of the a-Axis.
   */
  public double getaAxis() {
    return this.aAxis;
  }

  /**
   * Getter Method for b-Axis.
   * @return The double value of the b-Axis.
   */
  public double getbAxis() {
    return this.bAxis;
  }


  @Override
  public ShapeType getType() {
    return ShapeType.ELLIPSE;
  }

  @Override
  public void setShapeSize(double aWidth, double aHeight){
    this.shapeWidth = aWidth;
    this.shapeHeight = aHeight;
    this.aAxis = shapeWidth;
    this.bAxis = shapeHeight;
  }


  @Override
  public String generateInfoScript() {
    String info;
    String rgb = this.getColor().toString().substring(14);

    info = String.format("Name: %s\n", this.getName());
    info += String.format("Type: %s\n", this.getClass().getSimpleName());
    info +=
        String.format(
            "Center: (%.2f,%.2f), X Radius: %.2f, Y Radius: %.2f, Color: %s\n",
            getLocation().getX(),
            getLocation().getY(),
            this.aAxis,
            this.bAxis,
            rgb);
    info += String.format("Appears at t=%d\n", this.appearTime);
    info += String.format("Disappears at t=%d\n", this.disappearTime);

    return info;
  }


}
