package cs5004.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import cs5004.animator.model.AnimatedShapeImpl;
import cs5004.animator.model.ShapeType;

/**
 * This is a private JPanel for the Visual View that is created through Swing. It takes a list of
 * shapes at tick t and paints those shapes to the canvas in the order in which shape types are
 * created per the input file.
 */
class AnimationPanel extends JPanel {

  private List<AnimatedShapeImpl> shapeList;

  /**
   * Constructor for an AnimationPanel.
   *
   * @param shapes A list of shapes to create an animation for.
   */
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

    int xVal;
    int yVal;
    int hVal;
    int wVal;
    Color col;

    for (AnimatedShapeImpl tmpShape : shapeList) {
      xVal = (int) tmpShape.getLocation().getX();
      yVal = (int) tmpShape.getLocation().getY();
      hVal = (int) tmpShape.getShapeHeight();
      wVal = (int) tmpShape.getShapeWidth();
      col = tmpShape.getColor();
      g2d.setColor(col);

      if (tmpShape.getType().equals(ShapeType.ELLIPSE)) {
        g2d.fillOval(xVal, yVal, wVal, hVal);
        g2d.drawOval(xVal, yVal, wVal, hVal);
      } else if (tmpShape.getType().equals(ShapeType.RECTANGLE)) {
        g2d.fillRect(xVal, yVal, wVal, hVal);
        g2d.drawRect(xVal, yVal, wVal, hVal);
      }
    }
  }

  /**
   * Setter Method for changing the shapeList that is being consumed by the animation.
   *
   * @param shapeList A shapeList object to set to the view.
   */
  public void setShapes(List<AnimatedShapeImpl> shapeList) {
    this.shapeList = shapeList;
  }
}
