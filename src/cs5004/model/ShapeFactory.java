package cs5004.model;

/**
 * Shapefactory class that mainly contains a method to create shapes. ShapeFactory makes use of the
 * factory design pattern.
 */
public class ShapeFactory {

  /**
   * Factory Pattern. Creates a Circle or Rectangle with only a location parameter.
   *
   * @param aShapeType
   * @param aLoc
   * @return
   */
  public AnimatedShapeImpl createShape(
      String name,
      ShapeType aShapeType,
      Point2D aLoc,
      int r,
      int g,
      int b,
      int length,
      int width,
      int aAppearTime,
      int aDisappearTime) {

    AnimatedShapeImpl rVal = null; //  might want to rethink why we have null

    switch (aShapeType) {
      case ELLIPSE:
        rVal = new AEllipse(name, aLoc, r, g, b, length, width, aAppearTime, aDisappearTime);
        break;
      case RECTANGLE:
        rVal = new ARectangle(name, aLoc, r, g, b, length, width, aAppearTime, aDisappearTime);
        break;
      case UNKNOWN:
      default:
        throw new IllegalArgumentException("Shape must be an Ellipse or Rectangle");
    }
    return rVal;
  }
}
