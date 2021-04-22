# MVC-PROJECT-PART1-v2

### Week 2 Deliverable for MVC Project

#### By Guy Bou Lahdou & Garrett McNeill

##### Version 0.7 | Date 4.18.21

- - - - - - - - - - -

#### Preface

It was a pleasure to develop the model as it made us really test what we learn in a battlefield like
situation. This is also made us realize how easy can it be to breach SOLID principles when working
under pressure.

The product in this package represents mainly the Model, and View component of an MVC project that
is in progress.

The project represents a basic animator model that can be fed various shapes along with their
animations. Animations are created through liner-interpolation (tweening), based on two key frames
-- the start and end of an animation.


<br>

##### This application supports three types of views:

**-> Text View:** A script that describes the animation model, written to a .txt file.

**-> SVG View:** An export of the animation model as an SVG file.

**-> Visual View:** A view of the animation that plays in a window, powered by Java Swing.

Since that project doesn't still have a concrete controller, we do have a "temporary controller" in
the main() function located in EasyAnimator.java. EasyAnimator is the entry point to the program and
has the capability of being invoked through a command line that takes the following arguments:

1) **-in "name-of-animation-file.txt"** : the builder file to use in constructing an animation.
   <br>
2) **-view "type-of-view"** : must be (text, svg, or visual).
   <br>
3) **-out "name-of-outfile.ext"** : the name of the file to write to (.txt or .svg recommended).
   <br>
4) **-speed "integer-ticks-per-second"** : the desired frame-rate of the animation (30 recommended).

#### Characteristics of a valid input are:

- Each pair of arguments (-in "input-file", -out "output-file", etc.) may appear in any order
  (e.g. the -view pair can appear first, followed by -in and so on).
- Each pair of arguments are ordered. That is, if the user types -in then the next input must be the
  name of an input file, and so on.
- Providing an input file (the -in pair), and a view (the -view pair) are mandatory. If the output
  is not specified and the view needs it, the default should be System.out. If the speed is not
  specified and the view needs it, the default is 1 tick per second.

<br /><br />

### Changes since the last Version

We had to do a number of changes to our code to be able to adapt our code to the provided interface
and be able to build animations from the provided files.

**AnimationBuilder.java:** Is a new adapter to construct parsed files by the util package.

**AbstractAnimationImpl:**
<br />
Getters & Setters for:
<br />
-> start & end time, height & width of model, height & width of the bounding box.
<br />
-> getShapes() - returns a list of shapes
<br />
-> getShapesAtTick() - returns a list of shapes at a specific tick
<br />
-> generateXML

- AEllipse: generateXML

**AnimatedShape:**
<br />
added: setShapeSize, getShapeWidth, getShapeHeight, get & setAppearTime, get & setDisappearTime

**AnimatedShapeImpl:**
Added getLastShapeID() to be able to track the order of which shape types are added. GetShapeHeight,
GetShapeWidth

**ARectangle:**
- Added generateXML
- Added setShapeSize

**ColorAnimation.java**
- Added second constructor to accommodate AnimationBuilder.Java

**MoveAnimation.java**
- Added second constructor to accommodate AnimationBuilder.Java

**ScaleAnimation.java**
- Added second constructor to accommodate AnimationBuilder.Java

**Point2D.java**
- Removed validation  to allow accept negative coordinates.

- - - - - - - - - - -

## Model Interfaces

### & their implementations

- - - - - - - - - - -


= = = = = = = = =
**AnimatedShape.Java**
= = = = = = = = =

Represents a shape which is a component in a scene. Each shape contains an animationList which is
used to store transformations (movement, color change, and rescaling).

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

- - - - - - - - - - -

## View Interfaces

### & their implementations

- - - - - - - - - - -

= = = = = = = = =
**AbstractView.Java**
= = = = = = = = =
<br />
Represents an abstract class for SVGView and TextView. AbstractView class is the supertype of all
views. It holds a model which is implemented in the view.

= = = = = = = = =
**AnimationPanel.Java**
= = = = = = = = = 
<br />
This is a private JPanel for the Visual View that is created through Swing. It
takes a list of shapes at tick t and paints those shapes to the canvas in the order in which shape
types are created per the input file.

= = = = = = = = =
**SVGView.Java**
= = = = = = = = =

= = = = = = = = =
**TextView.Java**
= = = = = = = = =

= = = = = = = = =
**ViewFactory.Java**
= = = = = = = = = This is a factory for creating the three types of views.

Views supported in this version:

- **VisualView.Java** -> Represents a visual graphic view that is implemented through Java Swing.

- **SVGVIEW.Java**-> XXX.

- **TextView.Java**-> XXX.

= = = = = = = = =
**ViewInterface.Java**
= = = = = = = = = As the name says, the view interface is the contract that the three types of views
generally abide to.

= = = = = = = = =
**VisualView.Java**
= = = = = = = = = Visual view extends Java Swing's Java frame and sets all the required parameters (
height, width, scroll bar, leftmost and top most edge of the model, canvas layout, etc.)

It has a setter method for passing the latest list of shapes at a certain tick to Jpanel so it would
draw it to the canvas. This is used in the temporary place holder controller in the main method
located in **EasyAnimator.java**.

## Model Enums

- - - - - - - - - - -
= = = = = = = = =
**AnimationType.Java**
= = = = = = = = =

-> Has the three animation types: COLOR, MOVE, SCALE, & UNKNOWN.

= = = = = = = = =
**ShapeType.Java**
= = = = = = = = =

-> Has the two shape types currently supported by our model: RECTANGLE, ELLIPSE, & UNKNOWN.

## View Enums

- - - - - - - - - - -
= = = = = = = = =
**ViewType.Java**
= = = = = = = = =

-> Has the three view types:   TEXT, SVG, VISUAL, & UNKNOWN.

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