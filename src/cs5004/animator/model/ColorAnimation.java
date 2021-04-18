package cs5004.animator.model;

import java.awt.Color;

/** Color Animation class represents a concrete implementation of a COLOR animation type. */
public class ColorAnimation extends AbstractAnimationImpl {

  // Attributes
  private double deltaR;
  private double deltaG;
  private double deltaB;
  private Color startingColor;
  private Color endColor;

  /**
   * Default constructor for creating a Color animation.
   *
   * @param aShape An AnimatedShapeImpl object to apply a color animation to.
   * @param t1 The start time of the scale animation.
   * @param t2 The end time of the scale animation.
   * @param r The red color value as an int (0->255 are valid values).
   * @param g The green color value as an int (0->255 are valid values).
   * @param b The blue color value as an int (0->255 are valid values).
   */
  public ColorAnimation(AnimatedShape aShape, int t1, int t2, int r, int g, int b) {
    super(aShape, t1, t2, AnimationType.COLOR);

    if (r > 255 | r < 0 | g > 255 | g < 0 | b > 255 | b < 0) {
      throw new IllegalArgumentException("RGB Color Codes must be integers between 0 and 255 ");
    }

    this.startingColor = this.shape.getColor();
    this.endColor = new Color(r, g, b);
    recalculateVelocity();
  }

  public ColorAnimation(
      AnimatedShape aShape, int t1, int r1, int g1, int b1, int t2, int r2, int g2, int b2) {
    super(aShape, t1, t2, AnimationType.COLOR);
    if (r1 > 255 | r1 < 0 | g1 > 255 | g1 < 0 | b1 > 255 | b1 < 0) {
      throw new IllegalArgumentException("RGB Color Codes must be integers between 0 and 255 ");
    }
    if (r2 > 255 | r2 < 0 | g2 > 255 | g2 < 0 | b2 > 255 | b2 < 0) {
      throw new IllegalArgumentException("RGB Color Codes must be integers between 0 and 255 ");
    }

    this.startingColor = new Color(r1, g1, b1);
    this.endColor = new Color(r2, g2, b2);
    recalculateVelocity();
  }

  /**
   * Method for updating a shape after an animation has been added. Will be used for running
   * animations to update the shape's properties.
   *
   * @param currentTime The current tick to update the shape's properties to.
   */
  @Override
  public void updateShape(int currentTime) {

    if (startTime <= currentTime && currentTime <= endTime) {

      double dT = currentTime - startTime;

      double newR = startingColor.getRed() + (dT * deltaR);
      double newG = startingColor.getGreen() + (dT * deltaG);
      double newB = startingColor.getBlue() + (dT * deltaB);

      this.shape.changeColor(new Color((int) newR, (int) newG, (int) newB));
    }
  }

  /**
   * Check Consistent is a helper method to ensure that animations of the same type are not added on
   * top of each other. The previous animation of the same type must end before a new animation of
   * the same type can begin.
   *
   * @param previousAnimation or null.
   * @return True if the animations are consistent and do not overlap, false if they do not.
   */
  @Override // todo: might want to move back to abstract class
  public boolean checkConsistent(Animation previousAnimation) {

    boolean rVal = true;

    if (previousAnimation == null) {
      return rVal;
    }

    if (previousAnimation instanceof ColorAnimation) {
      if (previousAnimation.getEndTime() <= this.startTime) {
        rVal = true;
      } else {
        rVal = false;
      }
    }

    return rVal;
  }

  /**
   * Helper method for patching the beginning state of an animation list. It will update the
   * starting color as the ending color after an animation is run.
   *
   * @param previousAnimation or null.
   */
  @Override // might want to move back to abstract class
  public void patchBeginningState(Animation previousAnimation) {

    if (previousAnimation == null) {
      return;
    }
    if (previousAnimation instanceof ColorAnimation) {
      ColorAnimation tmpMove = (ColorAnimation) previousAnimation;
      this.setStartingColor(tmpMove.getEndingColor());
    }
  }

  /**
   * Getter method for returning the starting color.
   *
   * @return A Java.awt.Color object of the color.
   */
  public Color getStartingColor() {
    return this.startingColor;
  }

  /**
   * Setter method for changing the starting color in an animation.
   *
   * @param color A Java.awt.Color object of the color.
   */
  public void setStartingColor(Color color) {
    this.startingColor = color;
    recalculateVelocity();
  }

  /**
   * Getter method for returning the ending color of a shape in an animation.
   *
   * @return A Java.awt.Color object of the color.
   */
  public Color getEndingColor() {
    return this.endColor;
  }

  /**
   * Setter method for changing the ending color in an animation.
   *
   * @param color A Java.awt.Color object of the color.
   */
  public void setEndingColor(Color color) {
    this.endColor = color;
    recalculateVelocity();
  }

  /** Method for recalculating the velocity of a color animation. */
  private void recalculateVelocity() {
    double deltaT = this.endTime - this.startTime;
    this.deltaR = (this.endColor.getRed() - this.startingColor.getRed()) / deltaT;
    this.deltaG = (this.endColor.getGreen() - this.startingColor.getGreen()) / deltaT;
    this.deltaB = (this.endColor.getBlue() - this.startingColor.getBlue()) / deltaT;
  }

  /**
   * Method generates an animation script for a color animation.
   *
   * @return A formatted string that contains the printout of a color animation.
   */
  @Override
  public String generateAnimationScript() {
    String tmpName = this.getShape().getName();

    return String.format(
        "%s changes color from %s to %s from t=%d to t=%d\n",
        tmpName,
        this.startingColor.toString().substring(14),
        this.endColor.toString().substring(14),
        this.startTime,
        this.endTime);
  }

  @Override
  public String generateXML(Long mSecsPTick) {

    //  boolean loopback = false;
    String loopbackStr = "";

    String xml = "";
    Long duration = (this.endTime - this.startTime) * mSecsPTick;

    // if loopback true
    //  if (loopback){
    //    loopbackStr = "base.begin+";
    //  }

    if (this.startingColor != this.endColor) {
      xml =
          "<animate attributeType=\"xml\" begin=\""
              + loopbackStr
              + Long.toString(this.startTime * mSecsPTick)
              + "ms\""
              + " dur=\""
              + Long.toString(duration)
              + "ms\" attributeName=\"fill\" from=\"rgb("
              + this.startingColor.getRed()
              + ","
              + this.startingColor.getGreen()
              + ","
              + this.startingColor.getBlue()
              + ")\""
              + " to =\"rgb("
              + this.endColor.getRed()
              + ","
              + this.endColor.getGreen()
              + ","
              + this.endColor.getBlue()
              + ")\""
              + " fill=\"freeze\" />\n";
    }

    return xml;
  }
} // end class
