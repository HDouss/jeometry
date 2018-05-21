# jeometry
<img align="left" alt="Logo" src="https://github.com/HDouss/jeometry/blob/gh-pages/images/path3479.png" width="100" />

[![Build Status](https://travis-ci.org/HDouss/jeometry.svg?branch=master)](https://travis-ci.org/HDouss/jeometry)
[![Coverage Status](https://coveralls.io/repos/github/HDouss/jeometry/badge.svg?branch=master)](https://coveralls.io/github/HDouss/jeometry?branch=master)<br/><br/>
jeometry is a Java 2D geometric figures construction library.
It provides a simple API to:
* Build a geometric figure based on renderable shapes and relations between these shapes
* Output the figure to the screen

jeometry is easily extensible for a developer who wants to enhance the library with custom shapes, as well as to add more geometric figure output medium (for example: svg file, but there is no limit to imagination).


# Quick start
Before digging into deeper details, let's begin with a quick start to clarify what could jeometry be about.
Supposing we have this geometric figure to draw:

```
Let P be a random point and L be a random line.
Let M be the parallel line to L passing by P.
Let Q be a random point of M.
Let N be the perpendicular line to L passing by Q.
Let R be the intersection point of L and N.
Let C be the circle of center R and with radius 2. 
```

This is a simple figure that have no purpose other than showing some of jeometry capabilities.

Below you find the way to build this figure using jeometry:

```java
final XyPoint ppoint = new RandomPoint();
final Line lline = new PtsLine(new RandomPoint(), new RandomPoint());
final Line mline = new ParallelLine(lline, ppoint);
final XyPoint qpoint = new InLinePoint(mline);
final Line nline = new PerpLine(lline, qpoint);
final XyPoint rpoint = new LineIntersectPoint(lline, nline);
final Circle circle = new DblCircle(rpoint, 2.);
final Figure figure = new Figure().add(ppoint)
    .add(lline).add(mline)
    .add(qpoint).add(nline)
    .add(rpoint);
    figure.add(circle, Color.BLUE);
```

The above code shows some of the jeometry API classes. Let's detail the purpose of these classes:
* `RandomPoint`: builds a random 2D point
* `PtsLine`: builds a line passing by the two points -passed in the constructor-. In our case, they are two random points
* `ParallelLine`: builds a line that is parallel to a line -passed in the constructor- (and optionally passing by a point -passed in the constructor-)
* `InLinePoint`: builds a random point belonging to the line passed in the constructor
* `PerpLine`: builds a line that is perpendicular to a line -passed in the constructor- (and optionally passing by a point -passed in the constructor-)
* `LineIntersectPoint`: builds the point formed by the intersection of the two lines passed in the constructor
* `DblCircle`: builds a circle with the passed point as its center and the passed `double` as its radius (note here that the prefix `Dbl` is to indicate that the passed radius is a `double`, more details will be given hereafter about that)

jeometry offers many classes to build geometric shapes for which you can check the javadoc to know how and what each class is for:
* [Points](https://github.com/HDouss/jeometry/tree/master/jeometry-api/src/main/java/com/jeometry/twod/point)
* [Lines](https://github.com/HDouss/jeometry/tree/master/jeometry-api/src/main/java/com/jeometry/twod/line)
* [Rays](https://github.com/HDouss/jeometry/tree/master/jeometry-api/src/main/java/com/jeometry/twod/ray)
* [Circle](https://github.com/HDouss/jeometry/tree/master/jeometry-api/src/main/java/com/jeometry/twod/circle)
* [Angles](https://github.com/HDouss/jeometry/tree/master/jeometry-api/src/main/java/com/jeometry/twod/angle)
* [Segments](https://github.com/HDouss/jeometry/tree/master/jeometry-api/src/main/java/com/jeometry/twod/segment)

Now that we built the `figure` object, we can pass it to an [Output](https://github.com/HDouss/jeometry/blob/master/jeometry-double/src/main/java/com/jeometry/render/Output.java) to render the figure.
For now, the only available `Output` in jeometry is the `Awt` output, that will produce an [AWT](https://en.wikipedia.org/wiki/Abstract_Window_Toolkit) window with the figure drawn on the screen (that is -by the way- pannable and zoomable).

This code:
```java
new Awt().render(figure);
```
will produce this window:
<img alt="Figure on screen" src="https://github.com/HDouss/jeometry/blob/gh-pages/images/sample1.png" />

# Project structure
jeometry is a maven project divided into 3 sub modules:
* `aljebra`: `aljebra` is a standalone module that defines abstract [linear algebra](https://en.wikipedia.org/wiki/Linear_algebra) concepts such as [fields](https://en.wikipedia.org/wiki/Field_(mathematics)), [ordered fields](https://en.wikipedia.org/wiki/Ordered_field), [scalars](https://en.wikipedia.org/wiki/Scalar_(mathematics)), [vectors](https://en.wikipedia.org/wiki/Vector_(mathematics_and_physics)), [matrices](https://en.wikipedia.org/wiki/Matrix_(mathematics))...
* `jeometry-api`: an `aljebra` dependent module that defines common geometric shapes. The only (mathematic) assumption in these definitions is that we operate in a 2D [vector space](https://en.wikipedia.org/wiki/Vector_space). Scalars manipulated in this module are abstract and could theoretically be elements of any mathematical field (they are not necessarily real numbers)
* `jeometry-double`: a `jeometry-api` dependent module that defines convenient classes to build shapes with real number scalars (for now implemented as java double). In addition, `jeometry-double` defines the figure `Output` interface and offers an AWT drawing implementation of the geometric figure
