package cs5004.animator.model;

/** Subclass for the Ellipse Shape. */
public class AEllipse extends AnimatedShapeImpl {

  // Constants
  private static final int DEFAULT_HEIGHT_AXIS = 1;
  private static final int DEFAULT_WIDTH_AXIS = 1;

  // Attributes
  private double aWidthAxis;
  private double bHeightAxis;

  /**
   * Minimal Constructor for an ellipse. Requires a location to be created & instantiates with
   * default values.
   *
   * @param name Name of the shape represented by a string.
   * @param aReference Point2D Object referring to the location of the shape.
   */
  public AEllipse(String name, Point2D aReference, int aAppearTime, int aDisappearTime) {
    super(aReference, name, DEFAULT_HEIGHT_AXIS, DEFAULT_WIDTH_AXIS, aAppearTime, aDisappearTime);
    this.aWidthAxis = DEFAULT_WIDTH_AXIS;
    this.bHeightAxis = DEFAULT_HEIGHT_AXIS;
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
      int aAxis,
      int bAxis,
      int aAppearTime,
      int aDisappearTime) {
    super(name, ref, r, g, b, aAxis, bAxis, aAppearTime, aDisappearTime);

    if (aAxis <= 0 | bAxis <= 0 && aAxis != -1 && bAxis != -1) {
      throw new IllegalArgumentException("Ellipse axes must be positive non zero entries");
    }

    this.aWidthAxis = aAxis;
    this.bHeightAxis = bAxis;
  }

  // Copy Constructor
  public AEllipse(AEllipse toCopy) {
    super(toCopy);
    this.aWidthAxis = toCopy.aWidthAxis;
    this.bHeightAxis = toCopy.bHeightAxis;
  }

  /**
   * Setter for Elipse axis.
   *
   * @param aAxis The a-axis of the elipse.
   * @param bAxis The b-axis of the elipse.
   */
  public void setAxis(double aAxis, double bAxis) {
    if (aAxis > 0 && bAxis > 0) {
      this.aWidthAxis = aAxis;
      this.bHeightAxis = bAxis;
    } else {
      throw new IllegalArgumentException("Axes must be positive non zero entries.");
    }
  }

  /**
   * Getter Method for a-Axis.
   *
   * @return The double value of the a-Axis.
   */
  public double getaWidthAxis() {
    return this.aWidthAxis;
  }

  /**
   * Getter Method for b-Axis.
   *
   * @return The double value of the b-Axis.
   */
  public double getbHeightAxis() {
    return this.bHeightAxis;
  }

  @Override
  public ShapeType getType() {
    return ShapeType.ELLIPSE;
  }

  @Override
  public void setShapeSize(double aWidth, double aHeight) {
    this.shapeWidth = aWidth;
    this.shapeHeight = aHeight;
    this.aWidthAxis = aWidth;
    this.bHeightAxis = aHeight;
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
            getLocation().getX(), getLocation().getY(), this.aWidthAxis, this.bHeightAxis, rgb);
    info += String.format("Appears at t=%d\n", this.appearTime);
    info += String.format("Disappears at t=%d\n", this.disappearTime);

    return info;
  }

  @Override
  public String generateXML(Long mSecsPTick) {
    String rgb =
        "rgb("
            + Integer.toString(this.getColor().getRed())
            + ","
            + Integer.toString(this.getColor().getGreen())
            + ","
            + Integer.toString(this.getColor().getBlue())
            + ")";

    StringBuilder xml = new StringBuilder("<ellipse id=\"");
    xml.append(this.getName());
    xml.append("\" cx=\"");
    xml.append(Integer.toString((int) this.getLocation().getX()));
    xml.append("\" cy=\"");
    xml.append(Integer.toString((int) this.getLocation().getY()));
    xml.append("\" rx=\"");
    xml.append(Integer.toString((int) this.getShapeWidth()));
    xml.append("\" ry=\"");
    xml.append(Integer.toString((int) this.getShapeHeight()));
    xml.append("\" fill=\"");
    xml.append("rgb(");
    xml.append(Integer.toString(this.getColor().getRed()));
    xml.append(",");
    xml.append(Integer.toString(this.getColor().getGreen()));
    xml.append(",");
    xml.append(Integer.toString(this.getColor().getBlue()));
    xml.append(")\">");

    for (Animation tmpAnimation : animationList) {
      xml.append(tmpAnimation.generateXML(mSecsPTick));
    }
    xml.append("</ellipse>\n");

    return xml.toString();
  }
}
