package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.AnimatedShapeImpl;
import cs5004.animator.model.ShapeType;

class AnimationPanel extends JPanel {

  private List<AnimatedShapeImpl> shapeList;

  AnimationPanel(List<AnimatedShapeImpl> shapes) {

    super();
    this.shapeList = shapes;

    this.setBackground(Color.WHITE);


  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);

    int x;
    int y;
    int H;
    int W;
    Color col;


    for (AnimatedShapeImpl S: shapeList) {
      x= (int)S.getLocation().getX();
      y= (int)S.getLocation().getY();
      H = (int) S.getShapeHeight();
      W = (int) S.getShapeWidth();
      col = S.getColor();

      g2d.setColor(col);


      if (S.getType().equals(ShapeType.ELLIPSE)) {
        System.out.println("Drew Ellipse"+S.getName());

        System.out.println(x);
        System.out.println(y);
        System.out.println(H);
        System.out.println(W);
        g2d.fillOval(x,y, W, H);
        g2d.drawOval(x,y,W,H);
      }
      else if(S.getType().equals(ShapeType.RECTANGLE)) {
        System.out.println("Drew Rectangle"+S.getName());
        System.out.println(x);
        System.out.println(y);
        System.out.println(H);
        System.out.println(W);
        g2d.fillRect(x,y, W, H);
        g2d.drawRect(x,y,W,H);
      }

    }




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

  public void setShapes(List<AnimatedShapeImpl> shapeList) {
    this.shapeList = shapeList;
  }
}
