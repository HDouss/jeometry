# jeometry
<img align="left" alt="Logo" src="https://github.com/HDouss/jeometry/blob/gh-pages/images/path3479.png" width="100" />

[<img src="https://www.yegor256.com/images/award/2020/winner-hdouss.png"
  style="height:45px;" alt='winner'/>](https://www.yegor256.com/2019/11/03/award-2020.html)
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
final XyPoint<Double> ppoint = new RandomPoint<>();
final Line<Double> lline = new PtsLine<>(new RandomPoint<>(), new RandomPoint<>());
final Line<Double> mline = new ParallelLine<>(lline, ppoint);
final XyPoint<Double> qpoint = new InLinePoint<>(mline);
final Line<Double> nline = new PerpLine<>(lline, qpoint);
final XyPoint<Double> rpoint = new LineIntersectPoint<>(lline, nline);
final Circle<Double> circle = new DblCircle(rpoint, 2.);
final Figure figure = new Figure().add(ppoint)
    .add(lline).add(mline)
    .add(qpoint).add(
        new Shape(
            nline, new DefaultStyle(new FixedStroke(Color.BLUE, Dash.DASHED, 2f))
        )
    )
    .add(rpoint).add(
        new Shape(
            circle, new DefaultStyle(new FixedColorFill(Color.RED))
        )
    );
```
Note that this figure will output the Line N with a blue, dashed, 2px width line, and the circle C filled with red color.
The above code shows some of the jeometry API classes. Let's detail the purpose of these classes:
* `RandomPoint`: builds a random 2D point
* `PtsLine`: builds a line passing by the two points -passed in the constructor-. In our case, they are two random points
* `ParallelLine`: builds a line that is parallel to a line -passed in the constructor- (and optionally passing by a point -passed in the constructor-)
* `InLinePoint`: builds a random point belonging to the line passed in the constructor
* `PerpLine`: builds a line that is perpendicular to a line -passed in the constructor- (and optionally passing by a point -passed in the constructor-)
* `LineIntersectPoint`: builds the point formed by the intersection of the two lines passed in the constructor
* `DblCircle`: builds a circle with the passed point as its center and the passed `double` as its radius (note here that the prefix `Dbl` is to indicate that the passed radius is a `double`, more details will be given hereafter about that)
* `Shape`: builds a renderable with a style
* `DefaultStyle`: builds a default style, with the capability of overriding the filling style, or the stroking style
* `FixedStroke`: builds a stroking style with a color, a pattern and a width
* `FixedColorFill`: builds a filling style with the passed color


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

<img alt="Figure on screen" src="https://github.com/HDouss/jeometry/blob/gh-pages/images/sample1v2.png" />

# Usage and project structure
jeometry is a maven project divided into 3 sub modules:
* `aljebra`: `aljebra` is a standalone module that defines abstract [linear algebra](https://en.wikipedia.org/wiki/Linear_algebra) concepts such as [fields](https://en.wikipedia.org/wiki/Field_(mathematics)), [ordered fields](https://en.wikipedia.org/wiki/Ordered_field), [scalars](https://en.wikipedia.org/wiki/Scalar_(mathematics)), [vectors](https://en.wikipedia.org/wiki/Vector_(mathematics_and_physics)), [matrices](https://en.wikipedia.org/wiki/Matrix_(mathematics))...
* `jeometry-api`: an `aljebra` dependant module that defines common geometric shapes. The only (mathematic) assumption in these definitions is that we operate in a 2D [vector space](https://en.wikipedia.org/wiki/Vector_space). Scalars manipulated in this module are abstract and could theoretically be elements of any mathematical field (they are not necessarily real numbers)
* `jeometry-double`: a `jeometry-api` dependant module that defines convenient classes to build shapes with real number scalars (for now implemented as java double). In addition, `jeometry-double` defines the figure `Output` interface and offers an AWT drawing implementation of the geometric figure

Depending on what module you need, you should declare this dependency in your pom file in order to use jeometry:
```xml
<dependency>
  <groupId>com.github.hdouss</groupId>
  <artifactId>jeometry-double</artifactId>
</dependency>
```

# How to contribute
To contribute, just submit a pull request. The pull request should necessarily resolves an issue. Feel free to create an issue if your pull request does not solve an existing issue. Keep in mind that:
* The project uses [Qulice](https://www.qulice.com/) 0.16.5 (will upgrade [soon](https://github.com/HDouss/jeometry/milestone/2)) for static analysis quality control
* Pull requests has a [travis](https://github.com/HDouss/jeometry/blob/master/.travis.yml) build check, and a coveralls test coverage check
* Coveralls check succeeds if coverage is at least 90%, and if the coverage does not drop from the last check by more than ~~5%~~ 1%
* If the two checks succeeds and code review comments (if any) are resolved, the pull request will be labeled by [`tomerge`](https://github.com/HDouss/jeometry/labels/tomerge). This will trigger a GitHub [workflow](https://github.com/HDouss/jeometry/blob/master/.github/workflows/merge-pr.yml)
* The pull request merging GitHub workflow will:
  * Checkout your branch
  * Merge it locally (inside the container running the workflow) with master branch
  * Perform a build (`mvn clean install`)
  * If the build succeeds the PR is merged into master branch
  * This guarantees that the master branch is always in a succeeding build state

# Milestones and release management
Every issue in the project is assigned to a milestone. [Milestones](https://github.com/HDouss/jeometry/milestones) define the scope of each jeometry version. When all the issues of a milestone are resolved, we close this milestone.
Closing the milestone will automatically trigger the Release GitHub [workflow](https://github.com/HDouss/jeometry/blob/master/.github/workflows/release.yml). This workflow will:
* Checkout master branch
* Update version in pom: run `mvn versions:set -DnewVersion=` with the milestone title as parameter
* Commit the changes locally
* Test, package and deploy to [Sonatype](https://central.sonatype.org/) and [Maven Central](https://search.maven.org/)
* Upon deployment, that commit is tagged and the tag is pushed
* Generates a description containing the issues of the milestone and creates a GitHub release with it
* Uploads the package and the javadoc as assets of the GitHub release
 