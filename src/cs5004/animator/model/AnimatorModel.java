package cs5004.animator.model;

import java.util.List;

/** Animator interface represents a list of AnimatedShapes that compose an animation scene. */
public interface AnimatorModel extends ReadOnlyAnimatorModel {

  void setBoundingBoxWidth(int boundingBoxWidth);

  void setBoundingBoxHeight(int boundingBoxHeight);

  void setBoundingBoxLoc(double aX, double aY);

  //////////////////////////////////////////////////////////
  /////////////////// REGISTER OBJECTS /////////////////////
  //////////////////////////////////////////////////////////
  /**
   * Method for registering objects to the animation scene.
   *
   * @param name The name of the shape.
   * @param shape The type of shape (selected from ShapeType Enum)
   * @param aLoc The location of the shape as a Point2D reference object. Contains x & y values.
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
      ShapeType shape,
      Point2D aLoc,
      int length,
      int width,
      int r,
      int g,
      int b,
      int appearTime,
      int disappearTime);

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
   * Getter method for returning the state of the shape at a given time frame (tick.)
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

  /**
   * Generates String formatted XML (SVG format) for being written to file.
   * @param msecsPTick The number of frames per second (or ticks per second) for the animation.
   * @return String formatted XML (SVG format).
   */
  String generateXML(Long msecsPTick);

  /**
   * Getter method for returning the shapes that belong to the model.
   * @return A list of shapes in the animation.
   */
  List<AnimatedShapeImpl> getShapes();

  /**
   * Setter method for the Model's start time.
   *
   * @param x Starting stick.
   */
  void setModelStartTime(int x);

  /**
   * Setter method for the Model's end time.
   *
   * @param x Ending stick.
   */
  void setModelEndTime(int x);
}
