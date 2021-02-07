# Programming Assignment: Percolation

Here is the programming assignment [specification](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php) that describes the assignment requirements.

## Percolation

Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.

## The model

We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)

### Etc

Be sure that your code conforms to the prescribed APIs: each program must be in the "default" package (i.e., no package statements) and include only the public methods and constructors specified (extra private methods are fine). Note that algs4.jar uses a "named" package, so you must use an import statement to access a class in algs4.jar.

### Web Submission

Submit a zip file named percolation.zip that contains only the two source files Percolation.java and PercolationStats.java.

### Assessment Report

Here is some information to help you interpret the assessment report. See the Assessment Guide for more details.

- Compilation: we compile your .java files using a Java 8 compiler. Any error or warning messages are displayed and usually signify a major defect in your code. If your program does not compile, no further tests are performed.
- API: we check that your code exactly matches the prescribed API (no extra methods and no missing methods). If it does not, no further tests are performed.
- Bugs: we run SpotBugs to check for common bug patterns in Java programs. A warning message strongly suggests a bug in your code but occasionally there are false positives. Here is a summary of bug descriptions, which you can use to help decode warning messages.
- Style: we run Checkstyle to automatically checks the style of your Java programs. Here is a list of available Checkstyle checks, which you can use to help decode any warning messages.
- Correctness: we perform a battery of unit tests to check that your code meets the specifications.
- Memory: we determine the amount of memory according to the 64-bit memory cost model from lecture.
- Timing: we measure the running time and count the number of elementary operations.
