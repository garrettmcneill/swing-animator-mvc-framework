package cs5004.animator.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Test suite for top-level model functions. */
public class AnimatorModelImplTest {
  AnimatorModelImpl model1;

  @Before
  public void setUp() {
    model1 = new AnimatorModelImpl();
  }

  /** Test method for registering an object. */
  @Test
  public void registerObject() {

    model1.registerObject(
        "First Object", ShapeType.RECTANGLE, new Point2D(5, 20), 40, 20,
        150, 150, 100, 1, 10);

    model1.registerObject(
        "Second Object", ShapeType.ELLIPSE, new Point2D(50, 20), 40, 10,
        100, 80, 90, 20, 40);

    assertEquals(
        "Shapes: \n"
            + "------------ \n"
            + "Name: First Object\n"
            + "Type: ARectangle\n"
            + "Corner: (5.00,20.00), Length: 150.00, Width: 100.00, Color: "
            + "java.awt.Color[r=40,g=20,b=150]\n"
            + "Appears at t=1\n"
            + "Disappears at t=10\n"
            + "\n"
            + "Name: Second Object\n"
            + "Type: AEllipse\n"
            + "Center: (50.00,20.00), X Radius: 80.00, Y Radius: 90.00, Color:"
            + " [r=40,g=10,b=100]\n"
            + "Appears at t=20\n"
            + "Disappears at t=40\n"
            + "\n"
            + "Animations: \n"
            + "------------ \n",
        model1.generateScript());
  }

  /** Test method for de-registering an object. */
  @Test
  public void deregisterObject() {

    model1.registerObject(
        "First Object", ShapeType.RECTANGLE, new Point2D(5, 20), 40, 20, 150,
        150, 150, 1, 10);

    model1.registerObject(
        "Second Object", ShapeType.ELLIPSE, new Point2D(50, 20), 100, 100,
        100, 500, 20, 20, 40);

    model1.deregisterObject("First Object");

    assertEquals(
        "Shapes: \n"
            + "------------ \n"
            + "Name: Second Object\n"
            + "Type: AEllipse\n"
            + "Center: (50.00,20.00), X Radius: 500.00, Y Radius: 20.00, Color: "
            + "[r=100,g=100,b=100]\n"
            + "Appears at t=20\n"
            + "Disappears at t=40\n"
            + "\n"
            + "Animations: \n"
            + "------------ \n",
        model1.generateScript());
  }

  /** Test suite for move-type animation. */
  @Test
  public void moveTo() {
    model1.registerObject(
        "First Object", ShapeType.RECTANGLE, new Point2D(5, 20), 40, 20, 150,
        15, 30, 1, 10);

    model1.moveTo("First Object", new Point2D(10, 40), 2, 4);
    model1.moveTo("First Object", new Point2D(15, 45), 5, 8);

    AnimatedShape firstShape = model1.findShape("First Object");

    if (!firstShape.validateAnimations()) {
      Assert.fail("animations for shape 1 are invalid");
    }

    model1.registerObject(
        "Second Object", ShapeType.ELLIPSE, new Point2D(50, 20), 10, 20, 100,
        45, 90, 20, 40);

    model1.moveTo("Second Object", new Point2D(50, 90), 3, 9);

    AnimatedShape secondShape = model1.findShape("Second Object");

    if (!secondShape.validateAnimations()) {
      Assert.fail("animations for shape 2 are invalid");
    }

    assertEquals(
        "Shapes: \n"
            + "------------ \n"
            + "Name: First Object\n"
            + "Type: ARectangle\n"
            + "Corner: (5.00,20.00), Length: 15.00, Width: 30.00, Color: java.awt.Color[r=40,"
            + "g=20,b=150]\n"
            + "Appears at t=1\n"
            + "Disappears at t=10\n"
            + "\n"
            + "Name: Second Object\n"
            + "Type: AEllipse\n"
            + "Center: (50.00,20.00), X Radius: 45.00, Y Radius: 90.00, Color: [r=10,"
            + "g=20,b=100]\n"
            + "Appears at t=20\n"
            + "Disappears at t=40\n"
            + "\n"
            + "Animations: \n"
            + "------------ \n"
            + "First Object moves from(5.00,20.00) to (10.00,40.00) from t=2 to t=4\n"
            + "Second Object moves from(50.00,20.00) to (50.00,90.00) from t=3 to t=9\n"
            + "First Object moves from(10.00,40.00) to (15.00,45.00) from t=5 to t=8\n",
        model1.generateScript());
  }

  /** Test method for changing color animation. */
  @Test
  public void changeColor() {
    model1.registerObject(
        "First Object", ShapeType.RECTANGLE, new Point2D(5, 20), 40, 20, 150,
        40, 20, 1, 10);

    model1.changeColor("First Object", 2, 4, 200, 210, 220);
    model1.changeColor("First Object", 5, 6, 220, 230, 240);

    assertEquals(
        "Shapes: \n"
            + "------------ \n"
            + "Name: First Object\n"
            + "Type: ARectangle\n"
            + "Corner: (5.00,20.00), Length: 40.00, Width: 20.00, Color: java.awt.Color"
            + "[r=40,g=20,b=150]\n"
            + "Appears at t=1\n"
            + "Disappears at t=10\n"
            + "\n"
            + "Animations: \n"
            + "------------ \n"
            + "First Object changes color from [r=40,g=20,b=150] to [r=200,g=210,b=220]"
            + " from t=2 to t=4\n"
            + "First Object changes color from [r=200,g=210,b=220] to [r=220,g=230,b=240]"
            + " from t=5 to t=6\n",
        model1.generateScript());
  }

  /** Test method for rescaling a shape. */
  @Test
  public void rescaleShape() {
    model1.registerObject(
        "First Object", ShapeType.RECTANGLE, new Point2D(5, 20), 40, 20, 150,
        40, 20, 1, 10);

    model1.rescaleShape("First Object", 20, 10, 2, 7);
    model1.rescaleShape("First Object", 80, 100, 8, 9);

    assertEquals(
        "Shapes: \n"
            + "------------ \n"
            + "Name: First Object\n"
            + "Type: ARectangle\n"
            + "Corner: (5.00,20.00), Length: 40.00, Width: 20.00, Color: java.awt.Color"
            + "[r=40,g=20,b=150]\n"
            + "Appears at t=1\n"
            + "Disappears at t=10\n"
            + "\n"
            + "Animations: \n"
            + "------------ \n"
            + "First Object changes width from 20 to 10, and changes height from 40 to 20 "
            + "from t=2 to t=7\n"
            + "First Object changes width from 10 to 100, and changes height from 20 to 80 "
            + "from t=8 to t=9\n",
        model1.generateScript());
  }
}
