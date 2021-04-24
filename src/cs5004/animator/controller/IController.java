package cs5004.animator.controller;

public interface IController {
  /**
   *
   */
  void togglePlay();

  /**
   *
   */
  void togglePause();

  /**
   *
   */
  void toggleLoop();

  /**
   *
   */
  void toggleDisableLoop();

  /**
   *
   */
  void restart();

  /**
   *
   */
  void decreaseSpeed();

  /**
   *
   */
  void increaseSpeed();

}
