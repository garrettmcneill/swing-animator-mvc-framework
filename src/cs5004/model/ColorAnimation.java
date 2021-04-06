package cs5004.model;

import java.awt.*;

public class ColorAnimation extends AbstractAnimationImpl {


  private double deltaR;
  private double deltaG;
  private double deltaB;
  private Color startingColor;
  private Color endColor;

  public ColorAnimation(AnimatedShape aShape, int t1, int t2, int r, int g, int b) {
    super(aShape, t1, t2, AnimationType.COLOR);


    if (r > 255 | r < 0 | g > 255 | g < 0 | b > 255 | b < 0) {
      throw new IllegalArgumentException("RGB Color Codes must be integers between 0 and 255 ");
    }

    this.startingColor = this.shape.getColor();
    this.endColor = new Color(r,g,b);
    recalculateVelocity();

  }

  @Override
  public void updateShape(int currentTime) {

    if (startTime <= currentTime && currentTime <= endTime) {

      double dT = currentTime - startTime;

      double newR = startingColor.getRed() + (dT * deltaR);
      double newG = startingColor.getGreen() + (dT * deltaG);
      double newB = startingColor.getBlue() + (dT * deltaB);

      this.shape.changeColor(new Color((int)newR, (int)newG, (int)newB));
    }
  }

  @Override  // might want to move back to abstract class
  public boolean checkConsistent(Animation previousAnimation) {

    boolean rVal = true;

    if (previousAnimation == null){
      return rVal;
    }

    if (previousAnimation instanceof ColorAnimation) {
      if ( previousAnimation.getEndTime() < this.endTime ) {
        rVal = true;
      }
      else {
        rVal = false;
      }
    }

    return rVal;
  }

  @Override // might want to move back to abstract class
  public void patchBeginningState(Animation previousAnimation){

    if (previousAnimation == null){
      return;
    }
    if (previousAnimation instanceof ColorAnimation) {
      ColorAnimation tmpMove = (ColorAnimation) previousAnimation;
      this.setStartingColor(tmpMove.getEndingColor());
    }

  }


  public Color getStartingColor(){
    return this.startingColor;
  }

  public void setStartingColor(Color color){
    this.startingColor = color;
    recalculateVelocity();
  }

  public Color getEndingColor(){
    return this.endColor;
  }

  public void setEndingColor(Color color){
    this.endColor = color;
    recalculateVelocity();
  }

  private void recalculateVelocity(){
    double deltaT = this.endTime - this.startTime;
    this.deltaR = (this.endColor.getRed() - this.startingColor.getRed()) / deltaT;
    this.deltaG = (this.endColor.getGreen() - this.startingColor.getGreen()) / deltaT;
    this.deltaB = (this.endColor.getBlue() - this.startingColor.getBlue()) / deltaT;
  }

  @Override
  public String generateAnimationScript() {
    String tmpName = this.getShape().getName();

    return String.format("%s changes color from %s to %s from t=%d to t=%d\n"
            ,tmpName ,this.startingColor.toString().substring(14)
            , this.endColor.toString().substring(14), this.startTime, this.endTime);
  }

}
