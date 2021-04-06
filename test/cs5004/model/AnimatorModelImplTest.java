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

  // todo: We can still spend a lot of time doing testing and we still have some minor
  // fixes t we needed to do but we are really out of time. So we consider this version
  // to be a v0.5 rather than a v1.0. With that said, it was a pleasure to develop the model
  // as it made us really test what we learn in a battlefield like situation. This is also
  // made realize how easy can it be to breach SOLID principles when working under pressure.

  @Test
  public void registerObject() {

    model1.registerObject("First Object", ShapeType.RECTANGLE, new Point2D(5,20),
            40,20, 150,150,150,1,10);

    model1.registerObject("Second Object", ShapeType.ELLIPSE, new Point2D(50,20),
            500,20, 100,100,100,20,40);

    assertEquals("Shapes: \n" +
            "------------ \n" +
            "Name: First Object\n" +
            "Type: ARectangle\n" +
            "Corner: (5.00,20.00), Length: 40.00, Width: 20.00, Color: java.awt.Color[r=150,g=150,b=150]\n" +
            "\n" +
            "Name: Second Object\n" +
            "Type: AEllipse\n" +
            "Center: (50.00,20.00), X Radius: 500.00, Y Radius: 20.00, Color: [r=100,g=100,b=100]\n" +
            "Appears at t=20\n" +
            "Disappears at t=40\n" +
            "\n" +
            "Animations: \n" +
            "------------ \n", model1.generateScript());

  }

  @Test
  public void deregisterObject() {

    model1.registerObject("First Object", ShapeType.RECTANGLE, new Point2D(5,20),
            40,20, 150,150,150,1,10);

    model1.registerObject("Second Object", ShapeType.ELLIPSE, new Point2D(50,20),
            500,20, 100,100,100,20,40);

    model1.deregisterObject("First Object");

    assertEquals("Shapes: \n" +
            "------------ \n" +
            "Name: Second Object\n" +
            "Type: AEllipse\n" +
            "Center: (50.00,20.00), X Radius: 500.00, Y Radius: 20.00, Color: [r=100,g=100,b=100]\n" +
            "Appears at t=20\n" +
            "Disappears at t=40\n" +
            "\n" +
            "Animations: \n" +
            "------------ \n", model1.generateScript());
  }

  @Test
  public void addShapeAnimation() {
  }

  @Test
  public void moveTo() {
    model1.registerObject("First Object", ShapeType.RECTANGLE, new Point2D(5,20),
            40,20, 150,150,150,1,10);

    model1.moveTo("First Object", new Point2D(10,40),2,4);
    model1.moveTo("First Object", new Point2D(15,45),5,8);

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
        "Shapes: \n" +
                "------------ \n" +
                "Name: First Object\n" +
                "Type: ARectangle\n" +
                "Corner: (5.00,20.00), Length: 40.00, Width: 20.00, Color: java.awt.Color[r=150,g=150,b=150]\n" +
                "\n" +
                "Name: Second Object\n" +
                "Type: AEllipse\n" +
                "Center: (50.00,20.00), X Radius: 500.00, Y Radius: 20.00, Color: [r=100,g=100,b=100]\n" +
                "Appears at t=20\n" +
                "Disappears at t=40\n" +
                "\n" +
                "Animations: \n" +
                "------------ \n" +
                "First Object moves from(5.00,20.00) to (10.00,40.00) from t=2 to t=4\n" +
                "Second Object moves from(50.00,20.00) to (50.00,90.00) from t=3 to t=9\n" +
                "First Object moves from(10.00,40.00) to (15.00,45.00) from t=5 to t=8\n",
        model1.generateScript());
  }


  // NEED TO FIX ALL THE FAILURE TESTING FOR MOVE TO , CHANGE COLOR, RESCALE
//  @Test
//  public void moveToFails() {
//
//    model1.registerObject("First Object", ShapeType.RECTANGLE, new Point2D(5,20),
//            40,20, 150,150,150,1,10);
//
//    model1.moveTo("First Object", new Point2D(10,40),2,4);
//    model1.moveTo("First Object", new Point2D(15,45),3,8);
//  }

  @Test
  public void changeColor() {
    model1.registerObject("First Object", ShapeType.RECTANGLE, new Point2D(5,20),
            40,20, 150,150,150,1,10);

    model1.changeColor("First Object",2,4,200,210,220);
    model1.changeColor("First Object",5,6,220,230,240);


    assertEquals(
            "Shapes: \n" +
                    "------------ \n" +
                    "Name: First Object\n" +
                    "Type: ARectangle\n" +
                    "Corner: (5.00,20.00), Length: 40.00, Width: 20.00, Color: java.awt.Color[r=150,g=150,b=150]\n" +
                    "\n" +
                    "Animations: \n" +
                    "------------ \n" +
                    "First Object changes color from [r=150,g=150,b=150] to [r=200,g=210,b=220] from t=2 to t=4\n" +
                    "First Object changes color from [r=200,g=210,b=220] to [r=220,g=230,b=240] from t=5 to t=6\n",
            model1.generateScript());
  }

  @Test
  public void rescaleShape() {
    model1.registerObject("First Object", ShapeType.RECTANGLE, new Point2D(5,20),
            40,20, 150,150,150,1,10);

    model1.rescaleShape("First Object",20,10,2,7);
    model1.rescaleShape("First Object",80,100,8,9);


    assertEquals(
            "Shapes: \n" +
                    "------------ \n" +
                    "Name: First Object\n" +
                    "Type: ARectangle\n" +
                    "Corner: (5.00,20.00), Length: 40.00, Width: 20.00, Color: java.awt.Color[r=150,g=150,b=150]\n" +
                    "\n" +
                    "Animations: \n" +
                    "------------ \n" +
                    "First Object changes width from 20 to 10, and changes height from 40 to 20 from t=2 to t=7\n" +
                    "First Object changes width from 10 to 100, and changes height from 20 to 80 from t=8 to t=9\n",
            model1.generateScript());
  }


  @Test
  public void getShapesAtTick() {
  }
}