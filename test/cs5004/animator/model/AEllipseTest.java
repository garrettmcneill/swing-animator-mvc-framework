package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Test suite for Ellipse shape type. */
public class AEllipseTest {

  private AnimatedShapeImpl testEllDefCon; // default constructor
  private AnimatedShapeImpl testEllOverCon; // overloaded constructor

  /** Setup block for test methods. */
  @Before
  public void setUp() {

    testEllDefCon = new AEllipse("I am a test", new Point2D(5, 6), 10, 100);

    testEllOverCon = new AEllipse("Second Test", new Point2D(10, 15), 1, 2, 3, 50, 60, 20, 80);
  }

  /** Testing the default constructor. */
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

    assertEquals(
        "Name: I am a test\n"
            + "Type: AEllipse\n"
            + "Center: (5.00,6.00), X Radius: 1.00, Y Radius: 1.00, Color: [r=0,g=0,b=0]\n"
            + "Appears at t=10\n"
            + "Disappears at t=100\n",
        testEllDefCon.toString());
  }

  /** Test method for the second constructor. */
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

    assertEquals(
        "Name: Second Test\n"
            + "Type: AEllipse\n"
            + "Center: (10.00,15.00), X Radius: 50.00, Y Radius: 60.00, Color: [r=1,g=2,b=3]\n"
            + "Appears at t=20\n"
            + "Disappears at t=80\n",
        testEllOverCon.toString());
  }
}

