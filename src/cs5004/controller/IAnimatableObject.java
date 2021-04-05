package cs5004.controller;

/**
 * Interface represents the collections of shape objects to be animated and provides methods for
 * performing animations.
 */
public interface IAnimatableObject {


  /**
   * Method will set the clock tic of this object.
   *
   * @param aClockTic
   */
  public void handleClockTic(int aClockTic);

  // todo: set the current clock to the param

  // todo: update the rotation (if any)

  // todo: update the position (if changed)

  // todo: update the color (if needed)

  // todo: redraw object

  /////// NOTE FROM CLARK
  // todo: getShapesAtTic()

}
