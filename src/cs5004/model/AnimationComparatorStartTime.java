package cs5004.model;

import java.util.Comparator;

/**
 * Class that holds the comparator method that does comparisons between the starting and
 * end time of two Animations.
 */
public class AnimationComparatorStartTime implements Comparator<Animation> {

  /**
   * An overloaded comparator for comparing the starting time between two animations.
   * @param a1 Animation 1.
   * @param a2 Animation 2.
   * @return Returns -1, 0, 1 that represent the output of the comparison.
   */
  public int compare(Animation a1, Animation a2) {
    return Integer.compare(a1.getStartTime(), a2.getStartTime());
  }

}
