package cs5004.model;

public class ScaleAnimation extends AbstractAnimationImpl {

  // Attributes

  private double deltaH;
  private double deltaW;
  private double startingHeight;
  private double startingWidth;
  private double endingHeight;
  private double endingWidth;

  public ScaleAnimation(ARectangle aShape, int t1, int t2, double endingHeight
          , double endingWidth) {
    super(aShape, t1, t2, AnimationType.SCALE);

    if ( endingHeight <= 0 || endingWidth <= 0) {
      throw new IllegalArgumentException("Proposed new height or width cannot be zero or negative");
    }

    this.startingHeight = aShape.getLength();
    this.startingWidth = aShape.getWidth();
    this.endingHeight = endingHeight;
    this.endingWidth = endingWidth;
    this.shape = (ARectangle)aShape;

    recalculateVelocity();
  }

  public ScaleAnimation(AEllipse aShape, int t1, int t2, double endingHeight
          , double endingWidth) {
    super(aShape, t1, t2, AnimationType.SCALE);

    if ( endingHeight <= 0 || endingWidth <= 0) {
      throw new IllegalArgumentException("Proposed new height or width cannot be zero or negative");
    }

    this.startingHeight = aShape.getaAxis();
    this.startingWidth = aShape.getbAxis();
    this.endingHeight = endingHeight;
    this.endingWidth = endingWidth;
    this.shape = (AEllipse)aShape;

    recalculateVelocity();
  }


  @Override
  public void updateShape(int currentTime) {

    if (startTime <= currentTime && currentTime <= endTime) {

      double dT = currentTime - startTime;

      double newH = startingHeight + (dT * deltaH);
      double newW = startingWidth + (dT * deltaW);

      if ( this.shape instanceof ARectangle) {
        ((ARectangle) this.shape).setLength(newH);
        ((ARectangle) this.shape).setWidth(newW);
      }

      else if ( this.shape instanceof AEllipse) {
        ((AEllipse) this.shape).setAxis(newH, newW);
      }

    }
  }




  @Override  // might want to move back to abstract class
  public boolean checkConsistent(Animation previousAnimation) {

    boolean rVal = true;

    if (previousAnimation == null){
      return rVal;
    }

    if (previousAnimation instanceof ScaleAnimation) {
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
    if (previousAnimation instanceof ScaleAnimation) {
      ScaleAnimation tmpMove = (ScaleAnimation) previousAnimation;

      this.setStartingHeightWidth(tmpMove.getEndingHeight(), tmpMove.getEndingWidth());
    }

  }



  public double getStartingHeight(){
    return this.startingHeight;
  }

  public double getStartingWidth(){
    return this.startingWidth;
  }

  public void setStartingHeightWidth(double newHeight, double newWidth){
    this.startingHeight = newHeight;
    this.startingWidth = newWidth;
    recalculateVelocity();
  }

  public double getEndingHeight(){
    return this.endingHeight;
  }

  public double getEndingWidth(){
    return this.endingWidth;
  }

  public void setEndingHeightWidth(double newHeight, double newWidth){
    this.endingWidth = newWidth;
    this.endingHeight = newHeight;
    recalculateVelocity();
  }

  private void recalculateVelocity(){
    double deltaT = this.endTime - this.startTime;
    this.deltaH= (this.endingHeight - this.startingHeight) / deltaT;
    this.deltaW = (this.endingWidth - this.startingWidth) / deltaT;
  }

  @Override
  public String generateAnimationScript() {
    String tmpName = this.getShape().getName();

    return String.format("%s changes width from %d to %d, and changes height from %d to %d from t=%d to t=%d\n"
            ,tmpName ,(int)this.startingWidth,(int)this.endingWidth, (int)this.startingHeight
            ,(int)this.endingHeight, (int)this.startTime, (int)this.endTime);
  }



}