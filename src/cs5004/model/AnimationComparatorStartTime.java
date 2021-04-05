package cs5004.model;

import java.util.Comparator;

public class AnimationComparatorStartTime implements Comparator<Animation> {

  public int compare(Animation a1, Animation a2){
    return Integer.compare(a1.getStartTime(), a2.getStartTime());
  }

}
