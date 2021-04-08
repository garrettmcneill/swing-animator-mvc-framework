package cs5004.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class is our animator engine. This may eventually execute in a separate thread, or remain single
 * threaded -- decision to be made.
 *
 * <p>Our Model is a collection of shapes.
 */
public class AnimatorModelImpl implements AnimatorModel {

  private Map<String, AnimatedShapeImpl> shapeMap;
  private ShapeFactory factory;

  public AnimatorModelImpl() {
    this.shapeMap = new HashMap<String, AnimatedShapeImpl>();
    this.factory = new ShapeFactory();
  }

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
  @Override
  public void registerObject(
      String name,
      ShapeType shape,
      Point2D aLoc,
      int length,
      int width,
      int r,
      int g,
      int b,
      int appearTime,
      int disappearTime) {

    // validate values
    if (name == null || shapeMap.get(name) != null) {
      throw new IllegalArgumentException("Invalid shape name, must be unique and not null.");
    }

    // call ShapeFactory to create object
    AnimatedShapeImpl tmpShape =
        factory.createShape(name, shape, aLoc, r, g, b, length, width, appearTime, disappearTime);

    // add to shape map
    this.shapeMap.put(name, tmpShape);
  }

  /**
   * Method for de-registering a shape from the model.
   *
   * @param shapeName The name of the shape.
   */
  @Override
  public void deregisterObject(String shapeName) {
    if (shapeName == null) {
      throw new IllegalArgumentException("Invalid shape name. String cannot be null");
    }

    if (shapeMap.get(shapeName) == null) {
      throw new IllegalArgumentException("Shape does not exist in the model");
    } else {
      shapeMap.remove(shapeName);
    }
  }

  /**
   * Add an animation to the list of animations for a shape. One object can have many animations.
   *
   * @param shapeName A string name of the shape to add an animation to.
   * @param aAnimation An Animation object to add to the shape's animationList.
   */
  @Override
  public void addShapeAnimation(String shapeName, Animation aAnimation) {

    // find the shape
    AnimatedShapeImpl tmpShape = this.shapeMap.get(shapeName);

    if (tmpShape == null) {
      throw new IllegalArgumentException("ShapeName not found, animation cannot be added.");
    }

    // add this animation to the list
    tmpShape.addAnimation(aAnimation);
  }

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
   * @throws IllegalStateException Is thrown if the shape cannot be found by the string name given.
   */
  @Override
  public void moveTo(String shapeName, Point2D newLoc, int t1, int t2)
      throws IllegalStateException {

    // todo: CODE TO BE REFACTORED IN THE NEXT ITERATION

    // find the shape
    AnimatedShapeImpl tmpShape = this.shapeMap.get(shapeName);
    if (tmpShape == null) {
      throw new IllegalArgumentException("ShapeName not found, animation cannot be added.");
    }

    // get list of animations
    List<Animation> tmpAnimationList = tmpShape.getAnimationList();

    // check whether there is a time conflict with another animation of the same type
    // todo: this is implemented in AnimatedShapeImpl but DOUBLE CHECK

    Animation newAnim = new MoveAnimation(tmpShape, t1, t2, newLoc);
    tmpAnimationList.add(newAnim);

    if (!tmpShape.validateAnimations()) {
      throw new IllegalStateException("Animation conflicts with other animations");
      // no method to remove animations todo: implement in next iteration
    }
  }

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
  @Override
  public void changeColor(String shapeName, int t1, int t2, int r, int g, int b) {

    // todo: CODE TO BE REFACTORED IN THE NEXT ITERATION

    // find the shape
    AnimatedShapeImpl tmpShape = this.shapeMap.get(shapeName);
    if (tmpShape == null) {
      throw new IllegalArgumentException("ShapeName not found, animation cannot be added.");
    }

    // get list of animations
    List<Animation> tmpAnimationList = tmpShape.getAnimationList();

    // check whether there is a time conflict with another animation of the same type
    // todo: this is implemented in AnimatedShapeImpl but DOUBLE CHECK

    Animation newAnim = new ColorAnimation(tmpShape, t1, t2, r, g, b);
    tmpAnimationList.add(newAnim);

    if (!tmpShape.validateAnimations()) {
      throw new IllegalStateException("Animation conflicts with other animations");
      // no method to remove animations todo: implement in next iteration
    }
  }

  /**
   * Method for changing the shape and size of an object.
   *
   * @param shapeName The name of the shape.
   * @param newH The new height for the shape.
   * @param newW The new width for the shape.
   * @param t1 Starting time.
   * @param t2 Ending time.
   */
  @Override
  public void rescaleShape(String shapeName, int newH, int newW, int t1, int t2) {

    // todo: CODE TO BE REFACTORED IN THE NEXT ITERATION

    AnimatedShapeImpl tmpShape = this.shapeMap.get(shapeName);

    if (tmpShape == null) {
      throw new IllegalArgumentException("ShapeName not found, animation cannot be added.");
    }

    // get list of animations
    List<Animation> tmpAnimationList = tmpShape.getAnimationList();

    // check whether there is a time conflict with another animation of the same type
    // todo: this is implemented in AnimatedShapeImpl but DOUBLE CHECK

    if (tmpShape instanceof ARectangle) {
      Animation newAnim = new ScaleAnimation((ARectangle) tmpShape, t1, t2, newH, newW);
      tmpAnimationList.add(newAnim);
    } else if (tmpShape instanceof AEllipse) {
      Animation newAnim = new ScaleAnimation((AEllipse) tmpShape, t1, t2, newH, newW);
      tmpAnimationList.add(newAnim);
    } else {
      throw new IllegalArgumentException("Shape has to be an instance of ARectangle or AEllipse");
    }

    if (!tmpShape.validateAnimations()) {
      throw new IllegalStateException("Animation conflicts with other animations");
      // no method to remove animations todo: implement in next iteration
    }
  }

  //////////////////////////////////////////////////////////
  //////////////////// CURRENT STATE ///////////////////////
  //////////////////////////////////////////////////////////

  /** Stubbed method for running an animation. Todo in the next pass. */
  public void runAnimation() {

    // todo: init clock

    // todo: setNextClockTic()
    // --> take the current clock and add a number of seconds to it to create the next
    // tic

    // todo: for each object, handleClockTic(0)

    // todo: loop until finished
    // --> wait until next tic
    // --> for each object, handleClockTic(n)

  }

  /**
   * Returns a "script", as a String representation of all shapes and their animations.
   *
   * @return a string representation of an animation's script: containing a list of all shapes and a
   *     list of all animations sorted by starting time.
   */
  public String generateScript() {
    StringBuilder script = new StringBuilder();

    // add shapes title:
    script.append("Shapes: \n------------ \n");

    // generate shape info to string
    shapeMap.forEach((k, v) -> script.append(v.generateInfoScript() + "\n"));

    // add animation title:
    script.append("Animations: \n------------ \n");

    // validate animation list
    boolean allValid = true;
    for (AnimatedShape tempS : shapeMap.values()) {
      // todo: I LEFT OFF HERE!!!!!!!!!!  allValid = allValid && tempS.
    }

    // generate animation list
    List<Animation> tmpList = new ArrayList<Animation>();
    shapeMap.forEach((k, v) -> tmpList.addAll(v.getAnimationList()));

    // sort list by start time
    Comparator animationCompare = new AnimationComparatorStartTime();
    tmpList.sort(animationCompare);

    // generate animation scripts
    tmpList.forEach((a) -> script.append(a.generateAnimationScript()));

    return script.toString();
  }

  /**
   * Find shape method for getting a shape object by name.
   *
   * @param name A string name of the shape.
   * @return An AnimatedShape object that matches the name passed in.
   */
  public AnimatedShape findShape(String name) {
    // find the shape, if not found -- return null
    AnimatedShapeImpl tmpShape = this.shapeMap.get(name);
    if (tmpShape == null) {
      return null;
    }
    return tmpShape;
  }

  @Override
  public List<AnimatedShapeImpl> getShapesAtTick(int tick) {
    return null;
  }

  // TODO: ADD THIS TO README CHANGED IN WK 2
  @Override
  public List<AnimatedShapeImpl> getShapes() {

    List<AnimatedShapeImpl> rVal = new ArrayList<AnimatedShapeImpl>(shapeMap.values());

    return rVal;
  }
}
