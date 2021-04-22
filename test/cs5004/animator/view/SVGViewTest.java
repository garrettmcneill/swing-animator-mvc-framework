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

/** Test suite for the SVG view-type in the Easy Animator application. */
public class SVGViewTest {
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
        ViewFactory.createView(this.theModel, ViewType.SVG, "SVGviewTestResult.svg",
            20L);
    theView.activateView("SVGviewTestResult.svg", 2);
    BufferedReader reader = new BufferedReader(new FileReader("SVGviewTestResult.svg"));
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
        "<svg width=\"2000\" height=\"1000\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"200ms\" dur=\"800ms\" "
            + "attributeName=\"y\" from=\"200.0\" to =\"300.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1020ms\" dur=\"380ms\" "
            + "attributeName=\"width\" from=\"25.0\" to =\"100.0\" fill=\"freeze\" />\n"
            + "\n"
            + "<animate attributeType=\"xml\" begin=\"1020ms\" dur=\"380ms\" "
            + "attributeName=\"height\" from=\"50.0\" to =\"100.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1400ms\" dur=\"600ms\" "
            + "attributeName=\"y\" from=\"300.0\" to =\"200.0\" fill=\"freeze\" />\n"
            + "</rect> \n"
            + "<ellipse id=\"C\" cx=\"440\" cy=\"70\" rx=\"120\" ry=\"60\" "
            + "fill=\"rgb(0,0,255)\"><animate attributeType=\"xml\" begin=\"400ms\" "
            + "dur=\"600ms\" attributeName=\"y\" from=\"70.0\" to =\"250.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"400ms\" "
            + "attributeName=\"y\" from=\"250.0\" to =\"370.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000ms\" dur=\"400ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to =\"rgb(0,170,85)\" "
            + "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1400ms\" dur=\"200ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,170,85)\" to =\"rgb(0,255,0)\" "
            + "fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>",
        this.stringBuilder.toString());
  }
}
