package cs5004.animator.model;

/** Scale Animation class represents a concrete implementation of a SCALE animation type. */
public class ScaleAnimation extends AbstractAnimationImpl {

  // Attributes
  private double deltaH;
  private double deltaW;
  private double startingHeight;
  private double startingWidth;
  private double endingHeight;
  private double endingWidth;

  /**
   * Constructor for ARectangle objects that want to add a scale animation.
   *
   * @param aShape An ARectangle AnimatedShapeImpl object.
   * @param t1 The start time of the scale animation.
   * @param t2 The end time of the scale animation.
   * @param endingHeight The new ending height of the shape after the scale animation.
   * @param endingWidth The new ending width of the shape after the scale animation.
   */
  public ScaleAnimation(
      ARectangle aShape, int t1, int t2, double endingHeight, double endingWidth) {
    super(aShape, t1, t2, AnimationType.SCALE);

    if (endingHeight <= 0 || endingWidth <= 0) {
      throw new IllegalArgumentException("Proposed new height or width cannot be zero or negative");
    }

    this.startingHeight = aShape.getLength();
    this.startingWidth = aShape.getWidth();
    this.endingHeight = endingHeight;
    this.endingWidth = endingWidth;
    this.shape = (ARectangle) aShape;

    recalculateVelocity();
  }

  public ScaleAnimation(
      AnimatedShapeImpl aShape,
      int t1,
      int t2,
      double startingHeight,
      double endingHeight,
      double startingWidth,
      double endingWidth) {
    super(aShape, t1, t2, AnimationType.SCALE);

    if (endingHeight <= 0 || endingWidth <= 0) {
      throw new IllegalArgumentException("Proposed new height or width cannot be zero or negative");
    }

    this.startingHeight = startingHeight;
    this.startingWidth = startingWidth;
    this.endingHeight = endingHeight;
    this.endingWidth = endingWidth;
    this.shape = aShape;

  }

  /**
   * Constructor for AEllipse objects that want to add a scale animation.
   *
   * @param aShape An AEllipse AnimatedShapeImpl object.
   * @param t1 The start time of the scale animation.
   * @param t2 The end time of the scale animation.
   * @param endingHeight The new ending height of the shape after the scale animation.
   * @param endingWidth The new ending width of the shape after the scale animation.
   */
  @Deprecated
  public ScaleAnimation(AEllipse aShape, int t1, int t2, double endingHeight, double endingWidth) {
    super(aShape, t1, t2, AnimationType.SCALE);

    if (endingHeight <= 0 || endingWidth <= 0) {
      throw new IllegalArgumentException("Proposed new height or width cannot be zero or negative");
    }

    this.startingHeight = aShape.getaWidthAxis();
    this.startingWidth = aShape.getbHeightAxis();
    this.endingHeight = endingHeight;
    this.endingWidth = endingWidth;
    this.shape = (AEllipse) aShape;

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

      double newH = startingHeight + (dT * deltaH);
      double newW = startingWidth + (dT * deltaW);

      if (this.shape instanceof ARectangle) {
        ((ARectangle) this.shape).setLength(newH);
        ((ARectangle) this.shape).setWidth(newW);
      } else if (this.shape instanceof AEllipse) {
        ((AEllipse) this.shape).setAxis(newH, newW);
      }
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

    if (previousAnimation instanceof ScaleAnimation) {
      if (previousAnimation.getEndTime() < this.startTime) {
        rVal = true;
      } else {
        rVal = false;
      }
    }

    return rVal;
  }

  /**
   * Helper method for patching the beginning state of an animation list. It will update the
   * starting height as the ending height after an animation is run.
   *
   * @param previousAnimation or null.
   */
  @Override // todo: might want to move back to abstract class
  public void patchBeginningState(Animation previousAnimation) {

    if (previousAnimation == null) {
      return;
    }
    if (previousAnimation instanceof ScaleAnimation) {
      ScaleAnimation tmpMove = (ScaleAnimation) previousAnimation;

      this.setStartingHeightWidth(tmpMove.getEndingHeight(), tmpMove.getEndingWidth());
    }
  }

  /**
   * Getter method for returning the starting height of a scale animation.
   *
   * @return a double value for the starting height.
   */
  public double getStartingHeight() {
    return this.startingHeight;
  }

  /**
   * Getter method for returning the starting width of a scale animation.
   *
   * @return a double value for the starting width.
   */
  public double getStartingWidth() {
    return this.startingWidth;
  }

  /**
   * Setter method for the starting height of a scale animation.
   *
   * @param newHeight A double value for the new height of the shape.
   * @param newWidth A double value for the new width of the shape.
   */
  public void setStartingHeightWidth(double newHeight, double newWidth) {
    this.startingHeight = newHeight;
    this.startingWidth = newWidth;
    recalculateVelocity();
  }

  /**
   * Getter method for returning the ending height of the shape after the scale animation has been
   * applied.
   *
   * @return A double value for the ending height of the shape after the scale animation.
   */
  public double getEndingHeight() {
    return this.endingHeight;
  }

  /**
   * Getter method for returning the ending with of the shape after the scale animation.
   *
   * @return
   */
  public double getEndingWidth() {
    return this.endingWidth;
  }

  /**
   * Setter method for changing the ending height of the shape after the scale animation.
   *
   * @param newHeight A double value for the new height of the shape.
   * @param newWidth A double value for the new width of the shape.
   */
  public void setEndingHeightWidth(double newHeight, double newWidth) {
    this.endingWidth = newWidth;
    this.endingHeight = newHeight;
    recalculateVelocity();
  }

  /** Method for recalculating the velocity of the scale animation. */
  private void recalculateVelocity() {
    double deltaT = this.endTime - this.startTime;
    this.deltaH = (this.endingHeight - this.startingHeight) / deltaT;
    this.deltaW = (this.endingWidth - this.startingWidth) / deltaT;
  }

  /**
   * Method generates an animation script for a scale animation.
   *
   * @return A formatted string that contains the printout of a scale animation.
   */
  @Override
  public String generateAnimationScript() {
    String tmpName = this.getShape().getName();

    return String.format(
        "%s changes width from %d to %d, and changes height from %d to %d from t=%d to t=%d\n",
        tmpName,
        (int) this.startingWidth,
        (int) this.endingWidth,
        (int) this.startingHeight,
        (int) this.endingHeight,
        (int) this.startTime,
        (int) this.endTime);
  }

  @Override
  public String generateXML(int msecsPtick) {
    return null;
  }
}
