package cs5004.model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class AEllipseTest {

  private AnimatedShapeImpl testEllDefCon; // default constructor
  private AnimatedShapeImpl testEllOverCon; // overloaded constructor

  @Before
  public void setUp() {

    testEllDefCon = new AEllipse("I am a test", new Point2D(5, 6)
            , 10, 100);

    testEllOverCon = new AEllipse("Second Test", new Point2D(10, 15)
            , 1, 2, 3, 50, 60, 20, 80);

  }


  @Test
  public void testDefConstructor() {

    assertEquals("I am a test", testEllDefCon.getName());
    assertEquals(5.0, testEllDefCon.getLocation().getX(), 0.001);
    assertEquals(6.0, testEllDefCon.getLocation().getY(), 0.001);
    assertEquals(10, testEllDefCon.getAppearTime());
    assertEquals(100, testEllDefCon.getDisappearTime());

    assertEquals(0, testEllDefCon.getColor().getRed());
    assertEquals(0, testEllDefCon.getColor().getGreen());
    assertEquals(0, testEllDefCon.getColor().getBlue());

    assertEquals("Name: I am a test\n" +
            "Type: AEllipse\n" +
            "Center: (5.00,6.00), X Radius: 1.00, Y Radius: 1.00, Color: [r=0,g=0,b=0]\n" +
            "Appears at t=10\n" +
            "Disappears at t=100\n", testEllDefCon.toString());

  }

  @Test
  public void testOverConstructor() {

    assertEquals("Second Test", testEllOverCon.getName());
    assertEquals(10.0, testEllOverCon.getLocation().getX(), 0.001);
    assertEquals(15.0, testEllOverCon.getLocation().getY(), 0.001);
    assertEquals(20, testEllOverCon.getAppearTime());
    assertEquals(80, testEllOverCon.getDisappearTime());

    assertEquals(1, testEllOverCon.getColor().getRed());
    assertEquals(2, testEllOverCon.getColor().getGreen());
    assertEquals(3, testEllOverCon.getColor().getBlue());

    assertEquals("Name: Second Test\n" +
            "Type: AEllipse\n" +
            "Center: (10.00,15.00), X Radius: 50.00, Y Radius: 60.00, Color: [r=1,g=2,b=3]\n" +
            "Appears at t=20\n" +
            "Disappears at t=80\n", testEllOverCon.toString());

  }

  }

  // todo: need to figure a more complete testing of ellipse
  // todo: + figure whether we need to set Getters and test them

  // todo: and we need to figure a way to test these methods that we wrote ( set Axis / get Axis)...

  // todo: need to also test the points of failure where we have exceptions.
  // todo: please see the exceptions I added to AnimatedShapeImpl and AEllipse
  // todo: add similar exceptions for rectangle

  // todo: set up a similar test for RECTANGLE
  // todo: follow the toString method of Ellipse so that we set the same for the RECTANGLE toSTRING
  // todo: run some testing for point 2D.




//    /* todo: test getters for each field set above
//    assertEquals(
//        "Name: Temp Circ!\n"
//            + "Type: ACircle\n"
//            + "Center: (10.00,20.00), Radius: 1.00, Color: java.awt.Color[r=0,g=0,b=0]\n",)
//  }
//     */
//
//  }
//}