package cs5004.animator.model;

/**
 * Abstract class implementing the animation interface. The animation class represents animations
 * that are assigned to Shapes that hold these animations.
 */
public abstract class AbstractAnimationImpl implements Animation {

  AnimationType type;
  AnimatedShape shape;
  int startTime;
  int endTime;

  /**
   * AbstractAnimationImpl constructor.
   *
   * @param aShape Takes an animated shape object ( Ellipse, Rectangle for this project).
   * @param t1 Appearance time of the object.
   * @param t2 Disappearance time of the object.
   * @param type Enum representing the AnimationType ( Scale, Move, ChangeColor).
   */
  public AbstractAnimationImpl(AnimatedShape aShape, int t1, int t2, AnimationType type) {
    if (t2 < t1) {
      throw new IllegalArgumentException(
          "Start of animation time has to be" + "smaller than the end of the animation time.");
    }

    this.type = type;
    this.startTime = t1;
    this.endTime = t2;

    if (!(aShape instanceof AnimatedShape)) {
      throw new IllegalArgumentException("Shape must be of type AnimatedShape");
    }

    this.shape = aShape;
  }

  @Override
  public AnimationType getType() {
    return this.type;
  }

  @Override
  public AnimatedShape getShape() {
    return this.shape;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public abstract String generateXML(Long mSecsPTick);
}
