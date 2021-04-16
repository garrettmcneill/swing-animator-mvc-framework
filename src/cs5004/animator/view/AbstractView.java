package cs5004.animator.view;

import cs5004.animator.model.AnimatorModel;
import java.io.IOException;

public abstract class AbstractView implements ViewInterface {

  // Attributes
  protected final AnimatorModel model;
  protected String outputFileName;
  protected Long tickMSecs;

  protected AbstractView(AnimatorModel aModel, String aOutputFilename, Long aTickMSecs) {

    this.model = aModel;
    this.outputFileName = aOutputFilename;
    this.tickMSecs = aTickMSecs;
  }

  protected AbstractView(AnimatorModel aModel){
    this.model = aModel;
  }

  @Override
  public abstract void activateView(String aOutputFilename, int animationFrameRate)
      throws IOException;

  @Override
  public abstract void playAnimation();

}
