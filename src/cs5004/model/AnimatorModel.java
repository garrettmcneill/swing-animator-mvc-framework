package cs5004.model;

import java.util.List;

/** Animator interface represents a list of AnimatedShapes that compose an animation scene. */
public interface AnimatorModel {

  //////////////////////////////////////////////////////////
  /////////////////// REGISTER OBJECTS /////////////////////
  //////////////////////////////////////////////////////////
  /**
   * Method for registering objects to the animation scene.
   *
   * @param name The name of the shape.
   * @param Shape The type of shape (selected from ShapeType Enum)
   * @param Loc The location of the shape as a Point2D reference object. Contains x & y values.
   * @param length The Length of the shape.
   * @param width The width of the shape.
   * @param r Red (0 -> 255) for rgb.
   * @param g Green (0 -> 255) for rgb.
   * @param b Blue (0 -> 255) for rgb.
   * @param appearTime Starting time of object.
   * @param disappearTime Ending time of object.
   */
  void registerObject(
      String name,
      ShapeType Shape,
      Point2D Loc,
      int length,
      int width,
      int r,
      int g,
      int b,
      int appearTime,
      int disappearTime);
  /*
  Basically we handle all exceptions for the elements we're passing as this is the way the user
  communicated with the model.

  Need make sure that the name of the object is unique so that we can use that name later
  as a an identifier.

  We then use the factory method we created to create a new object. We then append the object
  to the shape list
   */

  /**
   * Method for de-registering a shape from the model.
   *
   * @param name The name of the shape.
   */
  void deregisterObject(String name);

  /**
   * Add an animation to the list of animations for a shape. One object can have many animations.
   *
   * @param shapeName A string name of the shape to add an animation to.
   * @param aAnimation An Animation object to add to the shape's animationList.
   */
  void addShapeAnimation(String shapeName, Animation aAnimation);
  /*
  Use this method to add an animation object to the shape starting  t1 (time it appears) and ending
  at t2( time it disappears). This would append an animation to the animation list member
  the shape object has. It would need to check that there is no conflict with the t1 and t2 of
  any other animations.
   */

  //////////////////////////////////////////////////////////
  /////////////////// ANIMATE OBJECTS //////////////////////
  //////////////////////////////////////////////////////////

  /**
   * Method for moving the location an object on the window.
   *
   * @param shapeName The name of the shape.
   * @param newLoc The location of the shape as a Point2D reference object. Contains x & y values.
   * @param t1 Starting time.
   * @param t2 Ending time.
   */
  void moveTo(String shapeName, Point2D newLoc, int t1, int t2);

  /**
   * Method for changing the color of a shape.
   *
   * @param shapeName The name of the shape.
   * @param t1 Starting time.
   * @param t2 Ending time.
   * @param r Red (0 -> 255) for rgb.
   * @param g Green (0 -> 255) for rgb.
   * @param b Blue (0 -> 255) for rgb.
   */
  void changeColor(String shapeName, int t1, int t2, int r, int g, int b);

  /**
   * Method for changing the shape and size of an object.
   *
   * @param shapeName The name of the shape.
   * @param newH The new height for the shape.
   * @param newW The new width for the shape.
   * @param t1 Starting time.
   * @param t2 Ending time.
   */
  void rescaleShape(String shapeName, int newH, int newW, int t1, int t2);

  //////////////////////////////////////////////////////////
  ///////////////////// CURRENT STATE //////////////////////
  //////////////////////////////////////////////////////////

  /**
   * Getter method for returning the state of the animation at a given frame (tick.)
   *
   * @param tick A frame on the timeline of the animation.
   * @return A deep copy of the animation's objects with values for the requested frame.
   */
  List<AnimatedShapeImpl> getShapesAtTick(int tick);

  /**
   * Returns a "script", as a String representation of all shapes and their animations.
   *
   * @return a string representation of an animation's script: containing a list of all shapes and a
   *     list of all animations sorted by starting time.
   */
  String generateScript();
}
