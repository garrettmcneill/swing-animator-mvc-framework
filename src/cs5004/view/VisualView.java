package cs5004.view;

import cs5004.model.AnimatorModel;
import java.io.IOException;

public class VisualView extends AbstractView{

  public VisualView(AnimatorModel aModel, Long aTickMSecs){

    super(aModel, null, aTickMSecs);

  }


  @Override
  public void generateScript() throws IOException {
  }


}
