package cs5004.animator.model;

import java.awt.Color;

/** Interface represents shape which is a component in a animation scene. */
public interface AnimatedShape {

  //////////////////////////////////////////////////////////
  /////////////////// SHAPE NAME METHODS ///////////////////
  //////////////////////////////////////////////////////////

  /**
   * Getter method for the shape ID.
   *
   * @return The ID of this shape.
   */
  int getShapeId();

  /**
   * Getter method for retrieving the shape's name. Name is a final field.
   *
   * @return A string value for the name of the shape.
   */
  String getName();

  /**
   * Method that returns the shape type.
   *
   * @return the enum of the shape type.
   */
  ShapeType getType();

  //////////////////////////////////////////////////////////
  ///////////////// SHAPE LOCATION METHODS /////////////////
  //////////////////////////////////////////////////////////

  /**
   * Getter method for retrieving the location of the shape at tick t.
   *
   * @return A Point2D object containing the x,y coordinates of the shape at tick t.
   */
  Point2D getLocation();

  /**
   * Setter method for the starting X coordinate of the Shape.
   *
   * @param aX A double value for the x-coordinate.
   */
  void setX(double aX);

  /**
   * Setter method for the starting Y coordinate of the Shape.
   *
   * @param aY A double value for the Y-coordinate.
   */
  void setY(double aY);

  //////////////////////////////////////////////////////////
  ///////////////// SHAPE VELOCITY METHODS /////////////////
  //////////////////////////////////////////////////////////

  /**
   * Getter method for returning the velocity of the shape.
   *
   * @return A Velocity2D object for the shapes current velocity.
   */
  Velocity2D getVelocity();

  //////////////////////////////////////////////////////////
  ///////////////////// SHAPE SIZE /////////////////////////
  //////////////////////////////////////////////////////////

  void setShapeSize(double aWidth, double aHeight);

  double getShapeWidth();

  double getShapeHeight();

  //////////////////////////////////////////////////////////
  ///////////////////// SHAPE COLOR ////////////////////////
  //////////////////////////////////////////////////////////

  /**
   * Getter method for returning the shape's original color.
   *
   * @return A Java.awt value for the color.
   */
  Color getColor();

  /** Set the color of the original shape at it's default location. */
  void setColor(int r, int g, int b);

  /** Set the color of the original shape at it's default location. */
  void changeColor(Color color);

  /**
   * Get the appearance time of the shape.
   *
   * @return The tick at which the object appears.
   */
  int getAppearTime();

  /**
   * Setter method for changing the appearance time of a shape.
   *
   * @param aAppearTime An int value for the appearance time.
   */
  void setAppearTime(int aAppearTime);

  /**
   * Get the disappearance time of the shape.
   *
   * @return The tick at which the object disappears.
   */
  int getDisappearTime();

  /**
   * Setter for changing the disappearance time of a shape.
   *
   * @param aDisappearTime an int value for a shape to disappear.
   */
  void setDisappearTime(int aDisappearTime);


  //////////////////////////////////////////////////////////
  /////////////////// LIST UTILITIES ///////////////////////
  //////////////////////////////////////////////////////////

  /**
   * Method determines if an animation list is empty.
   * @return A boolean true value if the list is empty, false if it is not.
   */
  boolean emptyAnimationList();



  //////////////////////////////////////////////////////////
  /////////////////// SCRIPT GENERATION ////////////////////
  //////////////////////////////////////////////////////////
  /**
   * This method sorts by chronological order the animations (on the basis of start time) and then
   * iterates through all animations ( does a loop for each type) while checking for any conflict
   * and making sure the starting state of animations are updated given the animation that comes
   * before it.
   *
   * @return Boolean True for validatedAnimations and False if a conflict has been detected.
   */
  boolean validateAnimations();

  /**
   * Method that sorts by chronological order the animations( on the basis of start time) and then
   * iterates through all the animations of the same type while checking whether that tickT falls
   * during any of those animations. Once an animation is found, the state of the Shape at time t is
   * updated.
   *
   * @param tickT tick at which we want to update the state of the shape/
   */
  void updateState(int tickT);

  /**
   * It is a toSTring type of method that generates information about the shape and it's properties
   * ( location, color, width, height, type, etc.).
   *
   * @return A string of the aforementioned.
   */
  String generateInfoScript();

  /**
   * Method for generating XML for SVG display of a shape.
   *
   * @param mSecsPTick Number of ticks per second.
   * @return A string containing formatted xml for svg views.
   */
  String generateXML(Long mSecsPTick);
}
