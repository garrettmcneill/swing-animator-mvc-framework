# MVC-PROJECT-PART1-v2

### Week 1 Deliverable for MVC Project

#### By Guy Bou Ladou & Garrett McNeill

##### Version 4.5.21

- - - - - - - - - - -

#### Preface

We can still spend a lot of time doing testing, and we still have some minor fixes that we needed to
do, but we are really out of time. So we consider this version to be a v0.5 rather than a v1.0. With
that said, it was a pleasure to develop the model as it made us really test what we learn in a
battlefield like situation. This is also made us realize how easy can it be to breach SOLID
principles when working under pressure.


- - - - - - - - - - -

## Interfaces

### & their implementations

- - - - - - - - - - -


= = = = = = = = =
**AnimatedShape.Java**
= = = = = = = = =

Represents a shape which is a component in an animation scene. Each shape contains an animationList
which is used to store transformations (movement, color change, and rescaling).

AnimatedShape the super-type of all shapes in our model.

It is implemented in the abstract base class **AnimatedShapeImpl**.

Currently our model has these shapes implemented as derived classes of AnimatedShapeImpl. To add new
shapes, a new value in our **ShapeType.java** enum should be added as well as a concrete
implementation.

Shapes supported in this version:

- **ARectangle.Java**
  -> Represents a rectangle with a height & width.

- **AElipse.Java**
  -> Represents a elipse with an a-axis and b-axis.

Shapes are created through a factory pattern which is located in the file **ShapeFactory.Java**

= = = = = = = = =
**Animation.Java**
= = = = = = = = =

Represents a supertype of animations used in our model. These animations are stored in a list with
the AnimatedShape.

**AnimationType.java** is an enum that contains the available animations.

The animations themselves are implemented in the following files:

- **MoveAnimation.Java**
  -> Represents an animation where a shape moves from a starting to an ending location within a
  specific time.

- **ScaleAnimation.Java**
  -> Represents an animation where a shape increases or decreases in size (by passing in a new
  height or width)

- **ColorAnimation.Java**
  -> Represents an animation where the shape color changes within a specific time frame.

= = = = = = = = =
**AnimationModel.Java**
= = = = = = = = =

Represents the contract for our model. Contains methods for registering and de-registering shapes to
the scene. It will become the face of our model for the controller.

- **AnimatorModelImpl.Java**
  -> Implementation of our model.

  Contains utility methods for:
    - registering & deregistering shapes
    - adding a shape animation

  Contains Animation methods for:
    - moving a shape to a new location in a given duration.
    - changing a shape's color in a given duration.
    - rescaling a shape's height &/or width in a given duration.

## Enums

- - - - - - - - - - -
= = = = = = = = =
**AnimationType.Java**
= = = = = = = = =

-> Has the three animation types: COLOR, MOVE, SCALE, & UNKNOWN.

= = = = = = = = =
**ShapeType.Java**
= = = = = = = = =

-> Has the two shape types currently supported by our model: RECTANGLE, ELLIPSE, & UNKNOWN.

## Utility Components

- - - - - - - - - - -
= = = = = = = = =
**Point2D.Java**
= = = = = = = = =

-> Is used by shapes & animations for storing a 2D reference location which contains an X & Y
coordinate.

= = = = = = = = =
**Velocity2D.Java**
= = = = = = = = =

-> Is used by shapes & animations for storing the deltaX and deltaY values for when a shape is
moving. (ie. How many pixels does the shape move in a given direction in a single tick)

= = = = = = = = =
**AnimationComparatorStartTime.Java**
= = = = = = = = =

-> Is used by the generateScript() method of **AnimatedModelImpl** to aid in the sorting of
animations by their start time.

## Data Storage Notes

- - - - - - - - - - -

**AnimatorModelImpl** has a map containing a:

-> key: string for shapeName

-> value: AnimatedShapeImpl instance of a shape

**AnimatedShapeImpl** has an ArrayList of animations stored in the field **animationList**