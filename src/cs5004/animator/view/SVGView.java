package cs5004.animator.view;

import cs5004.animator.model.AnimatedShape;
import cs5004.animator.model.AnimatedShapeImpl;
import cs5004.animator.model.Animation;
import cs5004.animator.model.AnimationComparatorStartTime;
import cs5004.animator.model.AnimatorModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SVGView extends AbstractView{

  public SVGView(AnimatorModel aModel, String aOutFileName, Long mSecsPTick){

    super(aModel, aOutFileName, mSecsPTick);

  }


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
  public void playAnimation() {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void makeVisible() {

  }

}
