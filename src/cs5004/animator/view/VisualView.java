package cs5004.animator.view;

import cs5004.animator.model.AnimatorModel;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class VisualView extends AbstractView {


  protected VisualView(AnimatorModel aModel) {
    super(aModel);
  }

  private class Visuals extends JFrame {

    private AnimationPanel animationPanel;

    // might need bounds for the JFrame : int x, int y, int width, int height

    public Visuals(AnimatorModel aModel, Long aTickMSecs) {
      super();

      this.setTitle("Animator Visual View - Bou Lahdou and McNeill");
      this.setSize(500, 500); // todo: need to replace that with the getters from our model
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      this.setLayout(new BorderLayout());

      this.pack();
    }

    public void refresh() {
      this.repaint();
    }


    public void makeVisible() {
      this.setVisible(true);
    }

  }




  @Override
  public void activateView(String aOutputFilename, int animationFrameRate) throws IOException {
  }

  @Override
  public void playAnimation() {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void makeVisible() {

  }


}
