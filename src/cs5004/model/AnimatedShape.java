package cs5004.model;

import java.awt.Color;
import java.util.List;

/**
 * Interface represents shape which is a component in a animation scene.
 */
public interface AnimatedShape {

  //////////////////////////////////////////////////////////
  /////////////////// SHAPE NAME METHODS ///////////////////
  //////////////////////////////////////////////////////////

  /**
   * Getter method for retrieving the shape's name. Name is a final field.
   *
   * @return A string value for the name of the shape.
   */
  String getName();


  //////////////////////////////////////////////////////////
  /////////////////// SHAPE VISIBILITY /////////////////////
  //////////////////////////////////////////////////////////


  /**
   * Getter Method for determining a shape visibility at tick r.
   * @return isVisible A boolean value for the shape's visibility. Visible if true, false if not.

  boolean isVisible();
   */

  //////////////////////////////////////////////////////////
  ///////////////// SHAPE LOCATION METHODS /////////////////
  //////////////////////////////////////////////////////////

  /**
   * Getter method for retrieving the location of the shape at tick t.
   * @return A Point2D object containing the x,y coordinates of the shape at tick t.
   */
  Point2D getLocation();


  /**
   * Setter method for the starting X coordinate of the Shape.
   *
   * @param aX A double value for the x-coordinate.
   */
  public void setX(double aX);

  /**
   * Setter method for the starting Y coordinate of the Shape.
   *
   * @param aY A double value for the Y-coordinate.
   */
  public void setY(double aY);




//  // todo: do we need this?
//  double distanceFromOrigin(int t);

  //////////////////////////////////////////////////////////
  ///////////////// SHAPE VELOCITY METHODS /////////////////
  //////////////////////////////////////////////////////////

  /**
   * Getter method for returning the velocity of the shape.
   * @return A Velocity2D object for the shapes current velocity.
   */
  public Velocity2D getVelocity();


  //////////////////////////////////////////////////////////
  ///////////////////// SHAPE COLOR ////////////////////////
  //////////////////////////////////////////////////////////


  /**
   * Getter method for returning the shape's original color.
   * @return A Java.awt value for the color.
   */
  Color getColor();

  /**
   * Set the color of the original shape at it's default location.
   */
  void setColor(int r, int g, int b);

  /**
   * Set the color of the original shape at it's default location.
   */
  void changeColor(Color color);

  /**
   * Get the appearance time of the shape.
   * @return The tick at which the object appears.
   */
  int getAppearTime();

  /**
   * Get the disappearance time of the shape.
   * @return The tick at which the object disappears.
   */
  int getDisappearTime();

  //////////////////////////////////////////////////////////
  /////////////////// SCRIPT GENERATION ////////////////////
  //////////////////////////////////////////////////////////

  public boolean validateAnimations();

  String generateInfoScript();

}
