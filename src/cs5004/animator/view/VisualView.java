package cs5004.animator.view;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import cs5004.animator.model.AnimatedShapeImpl;
import cs5004.animator.model.ReadOnlyAnimatorModel;

public class VisualView extends JFrame implements ViewInterface {

  private AnimationPanel animationPanel;
  private ReadOnlyAnimatorModel model;
  private int leftMostX;
  private int topMostY;
  private int height;
  private int width;
  private List<AnimatedShapeImpl> shapesAtTick;

  // might need bounds for the JFrame : int x, int y, int width, int height

  public VisualView(ReadOnlyAnimatorModel aModel) {
    super();

    this.model = aModel;
    this.leftMostX = (int) aModel.getBoundingBoxLoc().getX();
    this.topMostY = (int) aModel.getBoundingBoxLoc().getY();
    this.height = aModel.getModelHeight();
    this.width = aModel.getModelWidth();
    this.shapesAtTick = aModel.getShapesAtTick(0);

    this.setTitle("Animator Visual View - Bou Lahdou and McNeill");
    this.setSize(this.width, this.height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    this.setLayout(new BorderLayout());

    this.animationPanel = new AnimationPanel(this.shapesAtTick);


    animationPanel.setPreferredSize(new Dimension(this.width, this.height));
    this.add(animationPanel, BorderLayout.CENTER);

//    JScrollPane scroller = new JScrollPane(animationPanel);
//    scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//    scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//    scroller.setBounds(50, 30, 300, 50);

//    this.add(scroller, BorderLayout.CENTER);




  }

  //todo: NEED TO DECIDE WHETHER this goes to interface ( SOLID : Interface segregation)
  public void setPanelShapes(List<AnimatedShapeImpl> shapesAtTick) {
    this.animationPanel.setShapes(shapesAtTick);
  }

  @Override
  public void activateView(String outFile, int animationFrameRate) throws IOException {
  }

  @Override
  public void playAnimation() {
  }


  @Override
  public void refresh() {
    this.repaint();
  }



  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

}