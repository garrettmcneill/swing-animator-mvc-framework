package cs5004.animator.model;

import cs5004.animator.util.AnimationBuilder;

// todo: add to readme
public final class AnimationModelBuilder implements AnimationBuilder<AnimatorModel> {

  // Constants
  private static final Point2D SHAPE_DEFAULT_LOC = new Point2D(-1.0, -1.0);
  private static final int SHAPE_DEFAULT_LENGTH = -1;
  private static final int SHAPE_DEFAULT_WIDTH = -1;
  private static final int SHAPE_DEFAULT_RED = 0;
  private static final int SHAPE_DEFAULT_GREEN = 0;
  private static final int SHAPE_DEFAULT_BLUE = 0;
  private static final int SHAPE_DEFAULT_APPEAR = 0;
  private static final int SHAPE_DEFAULT_DISAPPEAR = 1;

  // Attributes
  AnimatorModelImpl theModel;

  public AnimationModelBuilder() {
    theModel = new AnimatorModelImpl();
  }

  @Override
  public AnimatorModel build() {
    return theModel;
  }

  @Override
  public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {

    theModel.setBoundingBoxLoc(x, y);
    theModel.setBoundingBoxWidth(width);
    theModel.setBoundingBoxHeight(height);

    return this;
  }

  @Override
  public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {

    ShapeType tmpType = ShapeType.fromString(type);

    theModel.registerObject(
        name,
        tmpType,
        SHAPE_DEFAULT_LOC,
        SHAPE_DEFAULT_RED,
        SHAPE_DEFAULT_GREEN,
        SHAPE_DEFAULT_BLUE,
        SHAPE_DEFAULT_LENGTH,
        SHAPE_DEFAULT_WIDTH,
        SHAPE_DEFAULT_APPEAR,
        SHAPE_DEFAULT_DISAPPEAR);

    return this;
  }

  @Override
  public AnimationBuilder<AnimatorModel> addMotion(
      String name,
      int t1,
      int x1,
      int y1,
      int w1,
      int h1,
      int r1,
      int g1,
      int b1,
      int t2,
      int x2,
      int y2,
      int w2,
      int h2,
      int r2,
      int g2,
      int b2) {

    AnimatedShapeImpl tmpShape = theModel.findShape(name);

    if (tmpShape == null) {
      throw new IllegalArgumentException("Shape does not exist.");
    }

    // set initial time
    int tmpAppear = tmpShape.getAppearTime();
    if ( tmpShape.emptyAnimationList() || t1 < tmpAppear) {
      tmpShape.setAppearTime(t1);
    }

    int tmpDisappear = tmpShape.getDisappearTime();
    if ( tmpShape.emptyAnimationList() || t2 > tmpDisappear) {
      tmpShape.setDisappearTime(t2);
    }

    if ((tmpShape.getLocation().getX() < 0 || tmpShape.getLocation().getY() < 0)) {

      // set initial position
      tmpShape.setX(x1);
      tmpShape.setY(y1);

      // set initial shape size
      tmpShape.setShapeSize((double) w1, (double) h1);

      // set initial color
      tmpShape.setColor(r1, g1, b1);
    }

    // (if necessary) build move animation & add it
    if (x1 != x2 || y1 != y2) {
      Point2D tmpStartLoc = new Point2D((double) x1, (double) y1);
      Point2D tmpEndLoc = new Point2D((double) x2, (double) y2);
      MoveAnimation tmpMove = new MoveAnimation(tmpShape, t1, t2, tmpStartLoc, tmpEndLoc);
      tmpShape.addAnimation(tmpMove);
    }

    // (if necessary) build color animation & add it
    if (r1 != r2 || g1 != g2 || b1 != b2) {

      ColorAnimation tmpColor = new ColorAnimation(tmpShape, t1, r1, g1, b1, t2, r2, g2, b2);
      tmpShape.addAnimation(tmpColor);
    }

    // (if necessary) build scale animation & add it
    if (w1 != w2 || h1 != h2){

      ScaleAnimation tmpScale = new ScaleAnimation(tmpShape, t1, t2, w1, h1, w2, h2);
      tmpShape.addAnimation(tmpScale);
    }


    return this;
  }
}
