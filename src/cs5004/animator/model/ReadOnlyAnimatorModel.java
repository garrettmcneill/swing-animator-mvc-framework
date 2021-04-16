package cs5004.animator.model;

import java.util.List;

public interface ReadOnlyAnimatorModel {

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

  // TODO: ADD THIS TO README CHANGED IN WK 2
  List<AnimatedShapeImpl> getShapes();

}
