package cs5004.model;

/** Animations interface. */
public interface Animation {


  AnimatedShape getShape();

  AnimationType getType();

  int getStartTime();


  int getEndTime();


  /*
  An animation updates its shape appropriately given the elapsed time.
  All constructors must include animation start and end times.
   */
  // todo: implement me (only in derived classes)
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
   * Method remains abstract in base class.
   * @return
   */
  String generateAnimationScript();

}
