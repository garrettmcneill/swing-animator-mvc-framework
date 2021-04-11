package cs5004.animator.view;

import cs5004.animator.model.AnimatorModel;
import java.io.IOException;

public class VisualView extends AbstractView{

  public VisualView(AnimatorModel aModel, Long aTickMSecs){

    super(aModel, null, aTickMSecs);

  }


  @Override
  public void generateScript() throws IOException {
  }


}
