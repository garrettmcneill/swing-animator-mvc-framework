package cs5004.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Abstract class represents a list of shapes that compose an animation scene. */
public abstract class AnimatedShapeImpl implements AnimatedShape {

  // Class Attributes
  protected final String name;
  protected final int appearTime;
  protected final int disappearTime;
  protected Point2D reference;
  protected Point2D startingLocation;
  protected Color shapeColor;
  protected List<Animation> animationList; // we should probably declare as array list
  protected Velocity2D velocity;

  /**
   * Default Constructor for creating a shape with a list of animations.
   *
   * @param reference The location of the shape as a Point2D reference object. Contains x & y
   *     values.
   * @param name The name of the shape.
   */
  public AnimatedShapeImpl(Point2D reference, String name, int aAppearTime, int aDisappearTime) {
    this.reference = reference;
    this.name = name;
    this.animationList = new ArrayList<>();
    this.appearTime = aAppearTime;
    this.disappearTime = aDisappearTime;
    this.velocity = new Velocity2D(0.0, 0.0);
  }

  /**
   * Second constructor for creating a shape with a list of animations.
   *
   * @param name The name of the shape.
   * @param ref The location of the shape as a Point2D reference object. Contains x & y * values.
   * @param r Red (0 -> 255) for rgb.
   * @param g Green (0 -> 255) for rgb.
   * @param b Blue (0 -> 255) for rgb.
   */
  public AnimatedShapeImpl(
      String name, Point2D ref, int r, int g, int b, int aAppearTime, int aDisappearTime) {

    if (name == null) {
      throw new NullPointerException("String cannot be null and has to be a valid string");
    }

    if (r > 255 | r < 0 | g > 255 | g < 0 | b > 255 | b < 0) {
      throw new IllegalArgumentException("RGB Color Codes must be integers between 0 and 255 ");
    }

    if (aAppearTime < 0 | aDisappearTime < 0) {
      throw new IllegalArgumentException(
          "Appearance and Disappearance times must be positive integers");
    }

    if (aAppearTime > aDisappearTime) {
      throw new IllegalArgumentException("Appear time must be less than the disappear time");
    }

    this.reference = ref;
    this.name = name;
    this.shapeColor = new Color(r, g, b);
    this.animationList = new ArrayList<>();
    this.appearTime = aAppearTime;
    this.disappearTime = aDisappearTime;
    this.velocity = new Velocity2D(0.0, 0.0);
  }

  //////////////////////////////////////////////////////////
  /////////////////// SHAPE NAME METHODS ///////////////////
  //////////////////////////////////////////////////////////

  /**
   * Getter method for retrieving the shape's name.
   *
   * @return A string value for the name of the shape.
   */
  public String getName() {
    return this.name;
  }

  //////////////////////////////////////////////////////////
  /////////////////// SHAPE VISIBILITY /////////////////////
  //////////////////////////////////////////////////////////

  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

  //////////////////////////////////////////////////////////
  ///////////////// SHAPE LOCATION METHODS /////////////////
  //////////////////////////////////////////////////////////

  public Point2D getLocation() {
    // todo: need to implement that after figuring the tick related stuff
    return this.reference;
  }

  @Override
  public void setX(double aX) {
    reference.setX(aX);
  }

  @Override
  public void setY(double aY) {
    reference.setY(aY);
  }

  //  @Override
  //  public double distanceFromOrigin() {
  //    return reference.distToOrigin();
  //  }

  @Override
  public Velocity2D getVelocity() {
    // todo: need to implement once we get animations/transformations implemented
    return this.velocity;
  }

  //////////////////////////////////////////////////////////
  //////////////////// COLOR METHODS ///////////////////////
  //////////////////////////////////////////////////////////

  @Override
  public Color getColor() {
    return this.shapeColor;
  }

  @Override
  public void changeColor(Color color) {
    this.shapeColor = color;
  }

  @Override
  public void setColor(int r, int g, int b) {
    this.shapeColor = new Color(r, g, b);
  }

  //
  //  @Override
  //  public void rotate(double degClock) {
  //    this.angleOfRotation = degClock % 360;
  //  }

  //  @Override
  //  public int compare(AnimatedShape s) {
  //    double areaThis = this.area();
  //    double areaOther = s.area();
  //
  //    if (areaThis < areaOther) {
  //      return -1;
  //    } else if (areaOther < areaThis) {
  //      return 1;
  //    } else {
  //      return 0;
  //    }
  //  }

  //////////////////////////////////////////////////////////
  /////////////////// LIST UTILITIES ///////////////////////
  //////////////////////////////////////////////////////////

  public List<Animation> getAnimationList() {
    return this.animationList;
  }

  public void addAnimation(Animation aAnimation) {

    if (aAnimation.getStartTime() < this.appearTime
        | aAnimation.getEndTime() > this.disappearTime) {
      throw new IllegalArgumentException(
          "Animation start and end time are not"
              + "within the shape's appearance and disappearance times");
    }

    this.animationList.add(aAnimation);
  }

  @Override
  public boolean validateAnimations() {
    boolean rVal = true;
    // sort animation list
    Comparator animationCompare = new AnimationComparatorStartTime();
    this.animationList.sort(animationCompare);

    // iterate over AnimationType
    Animation lastAnimation;
    for (AnimationType aType : AnimationType.values()) {
      lastAnimation = null;
      for (Animation aAnimation : this.animationList) {
        if (aAnimation.getType() == aType) {
          if (aAnimation.checkConsistent(lastAnimation)) {
            aAnimation.patchBeginningState(lastAnimation);
          } else {
            // todo: might want to log inconsistency
            rVal = false;
          }
          lastAnimation = aAnimation;
        }
      }
    }

    return rVal;
  }

  //////////////////////////////////////////////////////////
  ////////////////////// TO STRING /////////////////////////
  //////////////////////////////////////////////////////////

  @Override
  public abstract String generateInfoScript();
}
