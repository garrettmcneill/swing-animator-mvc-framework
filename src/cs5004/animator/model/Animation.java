package cs5004.animator.model;

/** Animations interface. */
public interface Animation {

  /**
   * Getter method for the shape represented by the animation..
   * @return returns a pointer to the shape.
   */
  AnimatedShape getShape();

  /**
   * Mod that returns the type of the animation.
   * @return Enum that represents the animation type.
   */
  AnimationType getType();

  /**
   * Method that returns the starting tick of the animation.
   * @return Starting tick of animation.
   */
  int getStartTime();


  /**
   * Method that returns the ending tick of the animation.
   * @return Ending tick of animation.
   */
  int getEndTime();


  /**
   * An animation updates its shape appropriately given the elapsed time.
   * All constructors must include animation start and end times.
   * The updateShape will be used later in the project so that a shape can get it's
   * properties at time t.
   * todo: TO BE IMPLEMENTED IN WEEK 2 OF THE PROJECT.
   * @param currentTime Current tick to update the shape state to.
   */
  void updateShape(int currentTime);


  /**
   * Method that checks if animations have a time conflict.
   * @param previousAnimation or null.
   * @return True if consistent, false if not.
   */
  boolean checkConsistent(Animation previousAnimation);

  /**
   * Method that patches the starting state of an animation given the previous animation
   * end state.
   * @param previousAnimation or null.
   */
  void patchBeginningState(Animation previousAnimation);

  /**
   * A method that generates a string that shows what is the animation doing from t1 to t2.
   * @return A string representing the above.
   */
  String generateAnimationScript();



}
