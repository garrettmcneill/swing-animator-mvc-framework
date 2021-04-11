package cs5004.animator.model;

import cs5004.animator.util.AnimationBuilder;

//todo: add to readme
public final class AnimationModelBuilder implements AnimationBuilder<AnimatorModel> {


  @Override
  public AnimatorModel build() {
    return null;
  }

  @Override
  public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
    return null;
  }

  @Override
  public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
    return null;
  }

  @Override
  public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
      int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
      int b2) {
    return null;
  }

}
