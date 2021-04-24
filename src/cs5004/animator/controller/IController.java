package cs5004.animator.controller;

/**
 * Interface for the EasyAnimator controller.
 */
public interface IController {
  /** Method will cause the animation to start playing if it is stopped or paused. */
  void togglePlay();

  /** Method will pause the animation when called. */
  void togglePause();

  /** Method will enable looping of the animation if it is called. */
  void toggleLoop();

  /** Method will disable looping of the animation if it is called. */
  void toggleDisableLoop();

  /** Method will restart the animation to frame 0 when called. */
  void restart();

  /** Method will increase the FPS of the animation. */
  void decreaseSpeed();

  /** Method will decrease the FPS of the animation. */
  void increaseSpeed();
}
