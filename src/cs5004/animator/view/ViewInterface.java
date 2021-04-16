package cs5004.animator.view;

import java.io.IOException;

public interface ViewInterface {

  void activateView(String outFile, int animationFrameRate) throws IOException;

  void playAnimation();

  /**
   * Signal the view to draw itself
   */
  void refresh();

  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();



}
