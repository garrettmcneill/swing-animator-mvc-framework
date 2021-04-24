package cs5004.animator.view;

import cs5004.animator.EasyAnimator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.accessibility.Accessible;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs5004.animator.model.AnimatedShapeImpl;
import cs5004.animator.model.ReadOnlyAnimatorModel;

/**
 * Class represents a visual view which is powered by Java Swing.
 */
public class VisualView extends JFrame implements ViewInterface, ActionListener {

  private AnimationPanel animationPanel;
  private JPanel controlPanel;
  private JButton pauseButton;
  private JButton playButton;

  /**
   * Constructor for a Visual view type.
   *
   * @param aModel A read-only copy of a model to produce an animation for.
   */
  public VisualView(ReadOnlyAnimatorModel aModel) {

    super();

    // attrs

    int leftMostX = (int) aModel.getBoundingBoxLoc().getX();
    int topMostY = (int) aModel.getBoundingBoxLoc().getY();
    int height = aModel.getBoundingBoxHeight();
    int width = aModel.getBoundingBoxWidth();

    List<AnimatedShapeImpl> shapesAtTick = aModel.getShapesAtTick(aModel.getModelStartTime());

    this.setTitle("Animator Visual View - Bou Lahdou and McNeill");
    this.setSize(width, height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.controlPanel = new JPanel();
    controlPanel.setPreferredSize(new Dimension(width, (1*height)/8));
    this.add(controlPanel, BorderLayout.SOUTH);
    this.pauseButton = new JButton("Pause");
    this.controlPanel.add(pauseButton);

    this.pauseButton.addActionListener(this); //todo: pick up here
    this.pauseButton.setActionCommand("PAUSE"); //todo: pick up here

    this.animationPanel = new AnimationPanel(shapesAtTick);
    animationPanel.setPreferredSize(new Dimension(width, (7*height)/8));
    this.add(animationPanel, BorderLayout.NORTH);

    JScrollPane scroller = new JScrollPane(animationPanel);
    scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroller.setBounds(20, 25, 200, 50);

    this.add(scroller, BorderLayout.CENTER);
  }



  /**
   * Setter method for getting shapes at tick for updating the view.
   *
   * @param shapesAtTick The tick/frame to get shapes for.
   */
  public void setPanelShapes(List<AnimatedShapeImpl> shapesAtTick) {
    this.animationPanel.setShapes(shapesAtTick);
  }

  @Override
  public void activateView(String outFile, int animationFrameRate) throws IOException {
    // method is not required for a Visual View
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
  public void actionPerformed(ActionEvent e) {
    EasyAnimator.togglePause();
  }
}
