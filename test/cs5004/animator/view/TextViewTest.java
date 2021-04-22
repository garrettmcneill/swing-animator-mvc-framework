package cs5004.animator.view;

import cs5004.animator.model.AnimationModelBuilder;
import cs5004.animator.model.AnimatorModel;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for text view class, compares a model input to output.
 */
public class TextViewTest {
  AnimatorModel theModel;
  StringBuilder stringBuilder = new StringBuilder();


  /**
   * setUp method builds a model and reads from a view file that is written from file.
   * @throws IOException if read/write fails.
   */
  @Before
  public void setUp() throws IOException {
    FileReader tmpFileReader = new FileReader("smalldemo.txt");
    BufferedReader tmpReader = new BufferedReader(tmpFileReader);
    AnimationBuilder<AnimatorModel> tmpBuilder = new AnimationModelBuilder();
    this.theModel = AnimationReader.parseFile(tmpReader, tmpBuilder);
    ViewInterface theView =
        ViewFactory.createView(this.theModel, ViewType.TEXT,
            "TEXTviewTestResult.txt", 20L);
    theView.activateView("TEXTviewTestResult.txt", 2);
    BufferedReader reader = new BufferedReader(new FileReader("TEXTviewTestResult.txt"));
    String line = null;
    String ls = System.getProperty("line.separator");

    while ((line = reader.readLine()) != null) {
      this.stringBuilder.append(line);
      this.stringBuilder.append(ls);
    }

    this.stringBuilder.deleteCharAt(this.stringBuilder.length() - 1);
    reader.close();
  }

  /**
   * Method tests the activateView function, checks the string builder output of the view.
   */
  @Test
  public void activateView() {
    Assert.assertEquals(
        "Shapes: \n------------ \nName: R\nType: ARectangle\nCorner: (200.00,200.00),"
            + " Length: 100.00, Width: 50.00, Color: java.awt.Color[r=255,g=0,b=0]\nAppears at "
            + "t=1\nDisappears at t=100\n\nName: C\nType: AEllipse\nCenter: (440.00,70.00), "
            + "X Radius: 120.00, Y Radius: 60.00, Color: [r=0,g=0,b=255]\nAppears at t=6\n"
            + "Disappears at t=100\n\nAnimations: \n------------ \nR moves from(200.00,200.00) "
            + "to (200.00,200.00) from t=1 to t=10\nC moves from(440.00,70.00) to (440.00,70.00) "
            + "from t=6 to t=20\nR moves from(200.00,200.00) to (300.00,300.00) from t=10 to t=50"
            + "\nC moves from(440.00,70.00) to (440.00,250.00) from t=20 to t=50\nR moves "
            + "from(300.00,300.00) to (300.00,300.00) from t=50 to t=51\nC moves"
            + " from(440.00,250.00)"
            + " to (440.00,370.00) from t=50 to t=70\nC changes color from [r=0,g=0,b=255] to "
            + "[r=0,g=170,b=85] from t=50 to t=70\nR changes width from 25 to 100, and changes "
            + "height from 50 to 100 from t=51 to t=70\nR moves from(300.00,300.00) to "
            + "(200.00,200.00) from t=70 to t=100\nC changes color from [r=0,g=170,b=85] "
            + "to [r=0,g=255,b=0] from t=70 to t=80\nC moves from(440.00,370.00) to "
            + "(440.00,370.00) from t=80 to t=100",
        this.stringBuilder.toString());
  }
}
