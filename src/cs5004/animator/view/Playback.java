package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import cs5004.animator.model.ReadOnlyAnimatorModel;

/**
 * Class represents a playback view, which is an extension of the visual view that includes playback
 * controls.
 */
public class Playback extends VisualView {
  // private JPanel controlPanel;
  private JButton pauseButton;
  private JButton playButton;
  private JButton rePlay;
  private JButton incSpeed;
  private JButton decSpeed;
  private JButton enLoop;
  private JButton disLoop;
  private JButton eXit;

  /**
   * Constructor for a Visual view type.
   *
   * @param aModel A read-only copy of a model to produce an animation for.
   */
  public Playback(ReadOnlyAnimatorModel aModel) {
    super(aModel);

    JPanel controlPanel;

    controlPanel = new JPanel();
    controlPanel.setPreferredSize(
        new Dimension(aModel.getBoundingBoxWidth(), (1 * aModel.getBoundingBoxHeight()) / 8));
    this.add(controlPanel, BorderLayout.SOUTH);

    this.pauseButton = new JButton("Pause");
    controlPanel.add(pauseButton);
    this.pauseButton.setActionCommand("PAUSE");

    this.playButton = new JButton("Play");
    controlPanel.add(playButton);
    this.playButton.setActionCommand("PLAY");

    this.incSpeed = new JButton("Increase Speed");
    controlPanel.add(incSpeed);
    this.incSpeed.setActionCommand("INCREASE SPEED");

    this.decSpeed = new JButton("Decrease Speed");
    controlPanel.add(decSpeed);
    this.decSpeed.setActionCommand("DECREASE SPEED");

    this.enLoop = new JButton("Loop");
    controlPanel.add(enLoop);
    this.enLoop.setActionCommand("ENABLE LOOP");

    this.disLoop = new JButton("Disable Loop");
    controlPanel.add(disLoop);
    this.disLoop.setActionCommand("DISABLE LOOP");

    this.rePlay = new JButton("Replay");
    controlPanel.add(rePlay);
    this.rePlay.setActionCommand("REPLAY");
  }

  @Override
  public void setListener(ActionListener list) {
    pauseButton.addActionListener(list);
    playButton.addActionListener(list);
    rePlay.addActionListener(list);
    incSpeed.addActionListener(list);
    decSpeed.addActionListener(list);
    enLoop.addActionListener(list);
    disLoop.addActionListener(list);
  }
}
