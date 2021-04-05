package cs5004.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimatorModelImplTest {
  AnimatorModelImpl model1;

  @Before
  public void setUp() {

    model1 = new AnimatorModelImpl();

  }

  @Test
  public void registerObject() {

    model1.registerObject("First Object", ShapeType.RECTANGLE, new Point2D(5,20),
            40,20, 150,150,150,1,10);

    model1.registerObject("Second Object", ShapeType.ELLIPSE, new Point2D(50,20),
            500,20, 100,100,100,20,40);

    assertEquals("Name: First Object\n" +
            "Type: ARectangle\n" +
            "Center: (5.00,20.00), Length: 40.00, Width: 20.00, Color: java.awt.Color[r=150,g=150,b=150]\n" +
            "\n" +
            "Name: Second Object\n" +
            "Type: AEllipse\n" +
            "Center: (50.00,20.00), X Radius: 500.00, Y Radius: 20.00, Color: [r=100,g=100,b=100]\n" +
            "Appears at t=20\n" +
            "Disappears at t=40\n\n", model1.generateScript());

  }

  @Test
  public void deregisterObject() {

    model1.registerObject("First Object", ShapeType.RECTANGLE, new Point2D(5,20),
            40,20, 150,150,150,1,10);

    model1.registerObject("Second Object", ShapeType.ELLIPSE, new Point2D(50,20),
            500,20, 100,100,100,20,40);

    model1.deregisterObject("First Object");

    assertEquals("Name: Second Object\n" +
            "Type: AEllipse\n" +
            "Center: (50.00,20.00), X Radius: 500.00, Y Radius: 20.00, Color: [r=100,g=100,b=100]\n" +
            "Appears at t=20\n" +
            "Disappears at t=40\n\n", model1.generateScript());
  }

  @Test
  public void addShapeAnimation() {
  }

  @Test
  public void moveTo() {
    model1.registerObject("First Object", ShapeType.RECTANGLE, new Point2D(5,20),
            40,20, 150,150,150,1,10);

    model1.moveTo("First Object", new Point2D(10,40),2,4);
    model1.moveTo("First Object", new Point2D(15,45),4,8);

    AnimatedShape firstShape = model1.findShape("First Object");
    if (!firstShape.validateAnimations()){
      Assert.fail("animations for shape 1 are invalid");
    }

    model1.registerObject("Second Object", ShapeType.ELLIPSE, new Point2D(50,20),
            500,20, 100,100,100,20,40);

    model1.moveTo("Second Object", new Point2D(50,90),3,9);

    AnimatedShape secondShape = model1.findShape("Second Object");
    if (!secondShape.validateAnimations()){
      Assert.fail("animations for shape 2 are invalid");
    }

    assertEquals(
        "Shapes: \n"
            + "------------ \n"
            + "Name: First Object\n"
            + "Type: ARectangle\n"
            + "Center: (5.00,20.00), Length: 40.00, Width: 20.00, Color: java.awt.Color[r=150,g=150,b=150]\n"
            + "\n"
            + "Name: Second Object\n"
            + "Type: AEllipse\n"
            + "Center: (50.00,20.00), X Radius: 500.00, Y Radius: 20.00, Color: [r=100,g=100,b=100]\n"
            + "Appears at t=20\n"
            + "Disappears at t=40\n"
            + "\n"
            + "Animations: \n"
            + "------------ \n"
            + "First Object moves from(5.00,20.00) to (10.00,40.00) from t=2 to t=4\n"
            + "Second Object moves from(50.00,20.00) to (50.00,90.00) from t=3 to t=9\n"
            + "First Object moves from(10.00,40.00) to (15.00,45.00) from t=4 to t=8\n",
        model1.generateScript());
  }

  @Test
  public void changeColor() {
  }

  @Test
  public void rescaleShape() {
  }

  @Test
  public void runAnimation() {
  }

  @Test
  public void generateScript() {
  }

  @Test
  public void printScript() {
  }

  @Test
  public void getShapesAtTick() {
  }
}