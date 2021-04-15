package cs5004.animator.view;

import java.io.IOException;

public interface StaticViewInterface {

  public void activateView(String outFile, int animationFrameRate) throws IOException;

  public void playAnimation();

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
