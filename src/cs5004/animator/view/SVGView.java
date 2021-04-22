package cs5004.animator.view;

import cs5004.animator.model.AnimatorModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/** SVG View creates an SVG file from the model. */
public class SVGView extends AbstractView {

  /**
   * Constructor for an SVG View, generates XML and writes it to file.
   *
   * @param aModel The AnimatorModel object to animate in a view.
   * @param aOutFileName The output filename to write the view to.
   * @param mSecsPTick The number of frames per second (or ticks per second) for the animation.
   */
  public SVGView(AnimatorModel aModel, String aOutFileName, Long mSecsPTick) {

    super(aModel, aOutFileName, mSecsPTick);
  }

  /**
   * Activate View method initializes the view.
   *
   * @param aOutputFilename The output file name to write to.
   * @param animationFrameRate The number of ticks per second (or frame rate) for the animation.
   * @throws IOException Is thrown if write fails.
   */
  @Override
  public void activateView(String aOutputFilename, int animationFrameRate) throws IOException {

    FileWriter tmpFileWriter;
    BufferedWriter tmpWriter;

    try {
      // open output file for write
      tmpFileWriter = new FileWriter(this.outputFileName);
      tmpWriter = new BufferedWriter(tmpFileWriter);

      String tmpXML = this.model.generateXML(tickMSecs);

      tmpWriter.write(tmpXML);

      // close output file
      tmpWriter.flush();
      tmpWriter.close();

    } catch (IOException e) {
      throw e; // throw IO exception if write error
    }
  }

  @Override
  public void refresh() {
    // method not required for SVG view
  }

  @Override
  public void makeVisible() {
    // method not required for SVG view
  }
}
