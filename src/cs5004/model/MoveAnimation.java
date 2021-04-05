package cs5004.model;

public class MoveAnimation implements Animation {

  // Attributes
  //todo: move me to base class
  final AnimationType type;
  final AnimatedShape shape;
  final int startTime;
  final int endTime;

  double deltaX;
  double deltaY;
  Point2D startingLocation;
  Point2D endLocation;

  public MoveAnimation(AnimatedShape aShape, Point2D endLoc, int t1, int t2) {

    if (t2 < t1) {
      throw new IllegalArgumentException("Start of animation time has to be" +
              "smaller than the end of the animation time.");
    }
    this.type = AnimationType.MOVE;
    this.startTime = t1;
    this.endTime = t2;

    if ( !(aShape instanceof AnimatedShape)) {
      throw new IllegalArgumentException("Shape must be of type AnimatedShape");
    }

    this.shape = aShape;

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

  @Override
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

  @Override
  public void patchBeginningState(Animation previousAnimation){

    if (previousAnimation == null){
      return;
    }
    if (previousAnimation instanceof MoveAnimation) {
      MoveAnimation tmpMove = (MoveAnimation) previousAnimation;
      this.setStartingLocation(tmpMove.getEndingLocation());
    }

  }


  @Override
  public AnimatedShape getShape(){
    return this.shape;
  }

  @Override
  public int getStartTime(){
    return this.startTime;
  }

  @Override
  public int getEndTime(){
    return this.endTime;
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

  @Override
  public AnimationType getType(){
    return this.type;
  }

}
