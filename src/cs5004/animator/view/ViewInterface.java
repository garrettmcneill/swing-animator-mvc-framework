package cs5004.animator.view;

import java.io.IOException;

public interface ViewInterface {

  public void activateView(String outFile, int animationFrameRate) throws IOException;

  public void playAnimation();

}
