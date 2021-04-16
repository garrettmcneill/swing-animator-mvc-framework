package cs5004.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Abstract class represents a list of shapes that compose an animation scene. */
public abstract class AnimatedShapeImpl implements AnimatedShape {

  //todo: add to readme: appear&disappear times are no longer final

  // Class Attributes
  protected final String name;
  protected int appearTime;
  protected int disappearTime;
  protected Point2D reference;
  protected Color shapeColor;
  protected double shapeWidth;
  protected double shapeHeight;
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
      throw new IllegalArgumentException("String cannot be null and has to be a valid string");
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

  public AnimatedShapeImpl(AnimatedShapeImpl toCopy) {
    this.reference = new Point2D(toCopy.reference);
    this.name = toCopy.name;
    this.shapeColor = new Color (toCopy.shapeColor.getRed(), toCopy.shapeColor.getGreen(),
            toCopy.shapeColor.getBlue());
    this.animationList = toCopy.animationList; // todo: did a quick fix here but need to make sure
    // we do a deep copy of animationList as the copy constructor is invoked when passing
    // Shapes to view.
    this.appearTime = toCopy.appearTime;
    this.disappearTime = toCopy.disappearTime;
    this.velocity = toCopy.velocity;

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
  public void setAppearTime(int aAppearTime){
    this.appearTime = aAppearTime;
  }


  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

  @Override
  public void setDisappearTime(int aDisappearTime){
    this.disappearTime = aDisappearTime;
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
  ///////////////////// SHAPE SIZE /////////////////////////
  //////////////////////////////////////////////////////////

  @Override
  public abstract void setShapeSize(double aWidth, double aHeight);

  @Override
  public double getShapeWidth(){
    return this.shapeWidth;
  }

  @Override
  public double getShapeHeight(){
    return this.shapeHeight;
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

  /**
   * Getter method to return the list of animations.
   * @return A list of animations.
   */
  public List<Animation> getAnimationList() {
    return this.animationList;
  }

  /**
   * Method that adds an animation to the list of animations.
   */
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
            rVal = false;

          }
          lastAnimation = aAnimation;
        }
      }
    }

    return rVal;
  }

  //todo: do a quick review and test
  @Override
  public void updateState(int tickT) {

    // sort animation list
    Comparator animationCompare = new AnimationComparatorStartTime();
    this.animationList.sort(animationCompare);

    // iterate over AnimationType
    for (AnimationType aType : AnimationType.values()) {
      for (Animation aAnimation : this.animationList) {
        if (aAnimation.getType() == aType) {
          if (tickT >= aAnimation.getStartTime() && tickT <= aAnimation.getEndTime()) {
            aAnimation.updateShape(tickT);
            break;
          }
          else if (aAnimation.getEndTime() < tickT) {
            //todo: might be useful to add an ID for each anim to avoid reusing 1-line code below:
            aAnimation.updateShape(aAnimation.getEndTime());
          }
        }
      }
    }
  }





  //////////////////////////////////////////////////////////
  ////////////////////// TO STRING /////////////////////////
  //////////////////////////////////////////////////////////

  @Override
  public abstract String generateInfoScript();


}
