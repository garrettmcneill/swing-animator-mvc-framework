package cs5004.animator.view;

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

public class TextView extends AbstractView {


  public TextView(AnimatorModel aModel, String aOutFileName) {

    super(aModel, aOutFileName, null);
  }

  @Override
  public void activateView(String aOutputFilename, int animationFrameRate) throws IOException {

    FileWriter tmpFileWriter;
    BufferedWriter tmpWriter;

    try {
      // open output file for write
      tmpFileWriter = new FileWriter(this.outputFileName);
      tmpWriter = new BufferedWriter(tmpFileWriter);

      // add shapes title:
      tmpWriter.write("Shapes: \n------------ \n");

      // generate shape info to string
      List<AnimatedShapeImpl> tmpShapeList = this.model.getShapes();

      for (AnimatedShapeImpl tmpShape : tmpShapeList) {
        tmpWriter.write(tmpShape.generateInfoScript() + "\n");
      }

      // add animation title:
      tmpWriter.write("Animations: \n------------ \n");

      // generate animation list
      List<Animation> tmpAnimationList = new ArrayList<Animation>();

      for (AnimatedShapeImpl tmpShape : tmpShapeList) {
        tmpAnimationList.addAll(tmpShape.getAnimationList());
      }

      // sort list by start time
      Comparator animationCompare = new AnimationComparatorStartTime();
      tmpAnimationList.sort(animationCompare);

      // generate animation scripts
      for (Animation tmpAnimation : tmpAnimationList) {
        tmpWriter.write(tmpAnimation.generateAnimationScript());
      }

      // close output file
      tmpFileWriter.close();

    } catch (IOException e) {
      throw e; // throw IO exception if write error
    }
  }

  @Override
  public void playAnimation() {

  }
}
