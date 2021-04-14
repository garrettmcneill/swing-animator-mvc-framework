package cs5004.animator.view;

import java.awt.*;

import javax.swing.*;

import cs5004.animator.model.AnimatorModel;

class AnimationPanel extends JPanel {

  private final AnimatorModel model; // todo: refactor interface to create a readonly one
  private long tempo;

  AnimationPanel(AnimatorModel m, Long aTickMSecs) {
    this.model = m;
    this.tempo = aTickMSecs;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2d.setColor(Color.BLACK);



    /*
    Thinking:
    tempo would be in ticks per second : translates to how many loops we need to do per second

    Assume we're going from 1 to 100 ticks at a 20/tick per second rate


    Notes:
     - User Space coords ==> Automatic during rendering ==> Device space coords
     - When a component is to be displayed it's paint OR update method is autoamtically invoked
     -  Java 2D API includes the java.awt.Graphics2D class, which extends the Graphics
        class to provide access to the enhanced graphics and rendering features of the Java 2D API.
     -  Draw method, Fill Method


     */

  }
}
