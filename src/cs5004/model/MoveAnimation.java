package cs5004.model;

public class MoveAnimation extends AbstractAnimationImpl {

  // Attributes

  private double deltaX;
  private double deltaY;
  private Point2D startingLocation;
  private Point2D endLocation;

  public MoveAnimation(AnimatedShape aShape, int t1, int t2, Point2D endLoc) {
    super(aShape, t1, t2, AnimationType.MOVE);
    this.startingLocation = this.shape.getLocation(); // which location are we getting here
    this.endLocation = endLoc;
    recalculateVelocity();
  }

  @Override
  public void updateShape(int currentTime) {

    if (startTime <= currentTime && currentTime <= endTime) {

      double dT = currentTime - startTime;

      double newX = startingLocation.getX() + (dT * deltaX);
      double newY = startingLocation.getX() + (dT * deltaY);

      this.shape.getLocation().setX(newX);
      this.shape.getLocation().setY(newY);
    }
  }

  @Override  // might want to move back to abstract class
  public boolean checkConsistent(Animation previousAnimation) {

    boolean rVal = true;

    if (previousAnimation == null){
      return rVal;
    }

    if (previousAnimation instanceof MoveAnimation) {
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
    if (previousAnimation instanceof MoveAnimation) {
      MoveAnimation tmpMove = (MoveAnimation) previousAnimation;
      this.setStartingLocation(tmpMove.getEndingLocation());
    }

  }



  public Point2D getStartingLocation(){
    return this.startingLocation;
  }

  public void setStartingLocation(Point2D aLoc){
    this.startingLocation = aLoc;
    recalculateVelocity();
  }

  public Point2D getEndingLocation(){
    return this.endLocation;
  }

  public void setEndingLocation(Point2D aLoc){
    this.endLocation = aLoc;
    recalculateVelocity();
  }

  private void recalculateVelocity(){
    double deltaT = this.endTime - this.startTime;
    this.deltaX = (this.endLocation.getX() - this.startingLocation.getX()) / deltaT;
    this.deltaY = (this.endLocation.getY() - this.startingLocation.getY()) / deltaT;
  }

  @Override
  public String generateAnimationScript() {
    String tmpName = this.getShape().getName();

    return String.format("%s moves from(%.2f,%.2f) to (%.2f,%.2f) from t=%d to t=%d\n"
            ,tmpName ,this.startingLocation.getX(),this.startingLocation.getY(), this.endLocation.getX()
            ,this.endLocation.getY(), this.startTime, this.endTime);
  }



}