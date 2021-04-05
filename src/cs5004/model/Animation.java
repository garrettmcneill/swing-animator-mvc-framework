package cs5004.model;

/** Animations interface. */
public interface Animation {

  /**
   * Method to be implemented in base class.
   * @return
   */
  AnimatedShape getShape();

  AnimationType getType();

  /**
   * Method to be implemented in base class.
   * @return
   */
  int getStartTime();

  /**
   * Method to be implemented in base class.
   * @return
   */
  int getEndTime();


  /*
  An animation updates its shape appropriately given the elapsed time.
  All constructors must include animation start and end times.
   */
  // todo: implement me (only in derived classes)
  void updateShape(int currentTime);


  /**
   * Method remains abstract in base class.
   * @param previousAnimation or null.
   * @return True if consistent, false if not.
   */
  boolean checkConsistent(Animation previousAnimation);

  /**
   * Method remains abstract in base class.
   * @param previousAnimation or null.
   */
  void patchBeginningState(Animation previousAnimation);

  /**
   * Method remains abstract in base class.
   * @return
   */
  String generateAnimationScript();

}
