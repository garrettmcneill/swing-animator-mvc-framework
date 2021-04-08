package cs5004.view;

import cs5004.model.AnimatorModel;
import java.io.IOException;

public class SVGView extends AbstractView{

  public SVGView(AnimatorModel aModel, String aOutFileName){

    super(aModel, aOutFileName, null);

  }


  @Override
  public void generateScript() throws IOException {

  }

}
