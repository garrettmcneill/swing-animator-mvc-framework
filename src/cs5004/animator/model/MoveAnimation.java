package cs5004.animator.model;

/** Scale Animation class represents a concrete implementation of a SCALE animation type. */
public class MoveAnimation extends AbstractAnimationImpl {

  // Attributes
  private double deltaX;
  private double deltaY;
  private Point2D startingLocation;
  private Point2D endLocation;

  /**
   * Default constructor for a move-type animation.
   *
   * @param aShape An AnimatedShape object to be moved in this animation.
   * @param t1 The starting time of the move animation.
   * @param t2 The ending time of the move animation.
   * @param endLoc The ending location as a Point2D reference object (containing new x & y
   *     coordinates)
   */
  public MoveAnimation(AnimatedShape aShape, int t1, int t2, Point2D endLoc) {
    super(aShape, t1, t2, AnimationType.MOVE);
    this.startingLocation = this.shape.getLocation(); // which location are we getting here
    this.endLocation = endLoc;
    recalculateVelocity();
  }

  /**
   * Second constructor for a move-type animation. Primarily used by AnimationBuilder.java.
   * @param aShape An AnimatedShape object to be moved in this animation.
   * @param t1 The starting time of the move animation.
   * @param t2 The ending time of the move animation.
   * @param startLoc THe starting location as a Point2D reference
   * @param endLoc The ending location as a Point2D reference object (containing new x & y *
   *     coordinates)
   */
  public MoveAnimation(AnimatedShape aShape, int t1, int t2, Point2D startLoc, Point2D endLoc) {
    super(aShape, t1, t2, AnimationType.MOVE);
    this.startingLocation = startLoc;
    this.endLocation = endLoc;
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

      double newX = startingLocation.getX() + (dT * deltaX);
      double newY = startingLocation.getY() + (dT * deltaY);

      this.shape.setX(newX);
      this.shape.setY(newY);
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

    if (previousAnimation instanceof MoveAnimation) {
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
   * starting height as the ending height after an animation is run.
   *
   * @param previousAnimation or null.
   */
  @Override // todo: might want to move back to abstract class
  public void patchBeginningState(Animation previousAnimation) {

    if (previousAnimation == null) {
      return;
    }
    if (previousAnimation instanceof MoveAnimation) {
      MoveAnimation tmpMove = (MoveAnimation) previousAnimation;
      this.setStartingLocation(tmpMove.getEndingLocation());
    }
  }

  /**
   * Getter method for returning the starting location of the shape during the move animation.
   *
   * @return A Point2D reference object that contains the starting location's x&y coordinates.
   */
  public Point2D getStartingLocation() {
    return this.startingLocation;
  }

  /**
   * Setter method for updating the starting location.
   *
   * @param aLoc A Point2D reference object that represents the starting location.
   */
  public void setStartingLocation(Point2D aLoc) {
    this.startingLocation = aLoc;
    recalculateVelocity();
  }

  /**
   * Getter method for returning the ending location of the shape in the move animation.
   *
   * @return A Point2D reference object that contains the ending location's x&y coordinates.
   */
  public Point2D getEndingLocation() {
    return this.endLocation;
  }

  /**
   * Setter method for changing the ending location.
   *
   * @param aLoc A Point2D reference object that represents the ending location's x&y coordinates.
   */
  public void setEndingLocation(Point2D aLoc) {
    this.endLocation = aLoc;
    recalculateVelocity();
  }

  /** Method for recalculating the velocity of the move animation. */
  private void recalculateVelocity() {
    double deltaT = this.endTime - this.startTime;
    this.deltaX = (this.endLocation.getX() - this.startingLocation.getX()) / deltaT;
    this.deltaY = (this.endLocation.getY() - this.startingLocation.getY()) / deltaT;
  }

  /**
   * Method generates an animation script for a move animation.
   *
   * @return A formatted string that contains the printout of a move animation.
   */
  @Override
  public String generateAnimationScript() {
    String tmpName = this.getShape().getName();

    return String.format(
        "%s moves from(%.2f,%.2f) to (%.2f,%.2f) from t=%d to t=%d\n",
        tmpName,
        this.startingLocation.getX(),
        this.startingLocation.getY(),
        this.endLocation.getX(),
        this.endLocation.getY(),
        this.startTime,
        this.endTime);
  }

  @Override
  public String generateXML(Long mSecsPTick) {

    //  boolean loopback = false;
    String loopbackStr = "";

    String xml = "";
    Long duration = (this.endTime - this.startTime) * mSecsPTick;

    if (this.startingLocation.getX() != this.getEndingLocation().getX()) {

      xml =
          "<animate attributeType=\"xml\" begin=\""
              + loopbackStr
              + Long.toString(this.startTime * mSecsPTick)
              + "ms\""
              + " dur=\""
              + Long.toString(duration)
              + "ms\" attributeName=\"x\" from=\""
              + this.startingLocation.getX()
              + "\" to =\""
              + this.getEndingLocation().getX()
              + "\" fill=\"freeze\" />\n";
    }

    // if y move animation
    if (this.startingLocation.getY() != this.getEndingLocation().getY()) {
      xml =
          "<animate attributeType=\"xml\" begin=\""
              + loopbackStr
              + Long.toString(this.startTime * mSecsPTick)
              + "ms\""
              + " dur=\""
              + Long.toString(duration)
              + "ms\" attributeName=\"y\" from=\""
              + this.startingLocation.getY()
              + "\" to =\""
              + this.getEndingLocation().getY()
              + "\" fill=\"freeze\" />\n";
    }

    return xml;
  }
}
