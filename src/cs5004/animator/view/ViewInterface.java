package cs5004.animator.view;

import java.io.IOException;
import java.util.List;

import cs5004.animator.model.AnimatedShapeImpl;

/**
 * View interface is the contract that the three types of views generally
 * abide to.
 */
public interface ViewInterface {

  /**
   * Activate view function is used to initialize a view.
   *
   * @param outFile The output file to write to.
   * @param animationFrameRate The frame rate or number of ticks per second for an animation.
   * @throws IOException Is thrown if writing to the file fails.
   */
  void activateView(String outFile, int animationFrameRate) throws IOException;

  /** Signal the view to draw itself. */
  void refresh();

  /** Make the view visible. This is usually called after the view is constructed. */
  void makeVisible();


  /**
   * Setter method for getting shapes at tick for updating the Visual View.
   *
   * @param shapesAtTick The tick/frame to get shapes for.
   */
  void setPanelShapes(List<AnimatedShapeImpl> shapesAtTick);
}
