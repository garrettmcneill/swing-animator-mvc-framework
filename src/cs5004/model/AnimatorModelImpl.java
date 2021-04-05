package cs5004.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class is our animator engine. This may eventually execute in a separate thread, or remain single
 * threaded -- decision to be made.
 *
 * Our Model is a collection of shapes.
 *
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

  @Override
  public void registerObject(
      String name,
      ShapeType Shape,
      Point2D Loc,
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
        factory.createShape(name, Shape, Loc, r, g, b, length, width, appearTime, disappearTime);

    // add to shape map
    this.shapeMap.put(name, tmpShape);
  }

  @Override
  public void deregisterObject(String shapeName) {
    if (shapeName == null) {
      throw new NullPointerException("Invalid shape name. String cannot be null");
    }

    if (shapeMap.get(shapeName) == null) {
      throw new IllegalArgumentException("Shape does not exist in the model");
    }

    else {
      shapeMap.remove(shapeName);
    }

  }

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

  @Override
  public void moveTo(String shapeName, Point2D newLoc, int t1, int t2) {

    // find the shape
    AnimatedShapeImpl tmpShape = this.shapeMap.get(shapeName);
    if (tmpShape == null) {
      throw new IllegalArgumentException("ShapeName not found, animation cannot be added.");
    }


    // get list of animations
    List<Animation> tmpAnimationList = tmpShape.getAnimationList();

    // check whether there is a time conflict with another animation of the same type
    // todo: this is implemented in AnimatedShapeImpl but DOUBLE CHECK


    Animation newAnim=new MoveAnimation(tmpShape, newLoc, t1, t2);
    tmpAnimationList.add(newAnim);

  }

  @Override
  public void changeColor(String name, int t1, int t2, int r, int g, int b) {}

  @Override
  public void rescaleShape(String name, int newH, int newW, int t1, int t2) {}

  //////////////////////////////////////////////////////////
  //////////////////// CURRENT STATE ///////////////////////
  //////////////////////////////////////////////////////////

  /** */
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

  public String generateScript() {
    StringBuilder script = new StringBuilder();

    // add shapes title:
    script.append("Shapes: \n------------ \n");

    // generate shape info to string
    shapeMap.forEach((k,v) -> script.append(v.generateInfoScript()+"\n"));

    // add animation title:
    script.append("Animations: \n------------ \n");

    // validate animation list
    boolean allValid = true;
    for (AnimatedShape tempS : shapeMap.values()){
     // todo: I LEFT OFF HERE!!!!!!!!!!  allValid = allValid && tempS.
    }

    // generate animation list
    List<Animation> tmpList = new ArrayList<Animation>();
    shapeMap.forEach((k,v) -> tmpList.addAll(v.getAnimationList()));

    // sort list by start time
    Comparator animationCompare = new AnimationComparatorStartTime();
    tmpList.sort(animationCompare);

    // generate animation scripts
    tmpList.forEach((a) -> script.append(a.generateAnimationScript()));

    return script.toString();
  }


  public AnimatedShape findShape(String name){
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
}
