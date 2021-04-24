package cs5004.animator.view;

import cs5004.animator.EasyAnimator;
import cs5004.animator.model.AnimatedShapeImpl;
import cs5004.animator.model.Animation;
import cs5004.animator.model.AnimationComparatorStartTime;
import cs5004.animator.model.AnimatorModel;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Text View creates a TXT file from the model. Is a sub-type of AbstractView. It's supertype is
 * ViewInterface
 */
public class TextView extends AbstractView {

  /**
   * Constructor for TextView populating values to super-type AbstractView.
   *
   * @param aModel An AnimatorModel object to produce a text view for.
   * @param aOutFileName The output filename for the view to be written to.
   */
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
      tmpWriter.flush();
      tmpWriter.close();

    } catch (IOException e) {
      throw e; // throw IO exception if write error
    }

    if (aOutputFilename.equals(EasyAnimator.DEFAULT_OUT_FILE)) {
      BufferedReader reader = new BufferedReader(new FileReader(EasyAnimator.DEFAULT_OUT_FILE));
      String line = null;
      String ls = System.getProperty("line.separator");
      StringBuilder stringBuilder = new StringBuilder();

      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line);
        stringBuilder.append(ls);
      }

      stringBuilder.deleteCharAt(stringBuilder.length() - 1);
      reader.close();
      System.out.print(stringBuilder.toString());
    }
  }

  @Override
  public void refresh() {
    // method not required for text view
  }

  @Override
  public void makeVisible() {
    // method not required for text view

  }

  @Override
  public void setPanelShapes(List<AnimatedShapeImpl> shapesAtTick) {
    throw new UnsupportedOperationException("Unsupported Method For TextView");
  }

  @Override
  public void setListener(ActionListener listener) {
    throw new UnsupportedOperationException("Unsupported Method For SVG View");
  }
}
