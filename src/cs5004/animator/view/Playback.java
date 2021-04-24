package cs5004.animator.view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs5004.animator.EasyAnimator;
import cs5004.animator.controller.Controller;
import cs5004.animator.model.ReadOnlyAnimatorModel;

public class Playback extends  VisualView  {
  private JPanel controlPanel;
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

    this.controlPanel = new JPanel();
    controlPanel.setPreferredSize(new Dimension(aModel.getBoundingBoxWidth(), (1*aModel.getBoundingBoxHeight())/8));
    this.add(controlPanel, BorderLayout.SOUTH);

    this.pauseButton = new JButton("Pause");
    this.controlPanel.add(pauseButton);
    this.pauseButton.setActionCommand("PAUSE");

    this.playButton = new JButton("Play");
    this.controlPanel.add(playButton);
    this.playButton.setActionCommand("PLAY");

    this.incSpeed = new JButton("Increase Speed");
    this.controlPanel.add(incSpeed);
    this.incSpeed.setActionCommand("INCREASE SPEED");

    this.decSpeed = new JButton("Decrease Speed");
    this.controlPanel.add(decSpeed);
    this.decSpeed.setActionCommand("DECREASE SPEED");

    this.enLoop = new JButton("Loop");
    this.controlPanel.add(enLoop);
    this.enLoop.setActionCommand("ENABLE LOOP");


    this.disLoop = new JButton("Disable Loop");
    this.controlPanel.add(disLoop);
    this.disLoop.setActionCommand("DISABLE LOOP");

    this.rePlay = new JButton("Replay");
    this.controlPanel.add(rePlay);
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


