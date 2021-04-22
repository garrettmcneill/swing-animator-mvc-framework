package cs5004.animator.view;

import cs5004.animator.model.AnimatorModel;
import java.io.IOException;

/**
 * AbstractView class is the supertype of all views. It holds a model which is implemented in the
 * view.
 */
public abstract class AbstractView implements ViewInterface {

  // Attributes
  protected final AnimatorModel model;
  protected String outputFileName;
  protected Long tickMSecs;

  /**
   * Default constructor for an AbstractView.
   * @param aModel The AnimatorModel object to animate in a view.
   * @param aOutputFilename The output filename to write the view to.
   * @param aTickMSecs The number of frames per second (or ticks per second) for the animation.
   */
  protected AbstractView(AnimatorModel aModel, String aOutputFilename, Long aTickMSecs) {

    this.model = aModel;
    this.outputFileName = aOutputFilename;
    this.tickMSecs = aTickMSecs;
  }

  /**
   * 2nd Constructor for AbstractView. Accepts a model only.
   * @param aModel The AnimatorModel object to animate in a view.
   */
  protected AbstractView(AnimatorModel aModel) {
    this.model = aModel;
  }

  @Override
  public abstract void activateView(String aOutputFilename, int animationFrameRate)
      throws IOException;
}
