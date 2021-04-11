package cs5004.animator.view;

import cs5004.animator.model.AnimatorModel;
import java.io.IOException;

public class SVGView extends AbstractView{

  public SVGView(AnimatorModel aModel, String aOutFileName){

    super(aModel, aOutFileName, null);

  }


  @Override
  public void generateScript() throws IOException {

  }

}
