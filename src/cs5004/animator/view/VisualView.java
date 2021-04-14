package cs5004.animator.view;

import cs5004.animator.model.AnimatorModel;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class VisualView extends JFrame implements ViewInterface {

  private AnimationPanel animationPanel;


  // might need bounds for the JFrame : int x, int y, int width, int height

  public VisualView(AnimatorModel aModel, Long aTickMSecs){
    super();

    this.setTitle("Animator Visual View - Bou Lahdou and Mcneill");
    this.setSize(500,500); // todo: need to replace that with the getters from our model
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());


    this.pack();




  }

  @Override
  public void refresh() {
    this.repaint();
  }


  @Override
  public void makeVisible() {
    this.setVisible(true);
  }


  @Override
  public void activateView(String aOutputFilename, int animationFrameRate) throws IOException {
  }

  @Override
  public void playAnimation() {

  }


}
