package cs5004.animator.model;

import java.util.Comparator;

public class AnimationComparatorShapeID implements Comparator<AnimatedShape> {

  /**
   * An overloaded comparator for comparing sorting ShapeID between two shapes.
   * @param a1 Shape 1.
   * @param a2 Shape 2.
   * @return Returns -1, 0, 1 that represent the output of the comparison.
   */
  public int compare(AnimatedShape a1, AnimatedShape a2) {
    return Integer.compare(a1.getShapeId(), a2.getShapeId());
  }
}


