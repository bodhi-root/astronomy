# Astronomical Algorithms

This package implements select algorithms from Astronomical Algorithms
(v2) by Meeus.  I decided to put these algorithms in their own package and
make them self-sufficient so they do not depend on any of the other
classes or object-oriented decisions made in the rest of this library.
This also leaves the main library free to supplement or even switch
to different algorithms when desired.

The code is organized into a different class for each chapter of the
book.  Functions are static so that their inputs and outputs are
clearly defined.

At a high-level, the chapters are:

7. Julian Day
10. Dynamical Time and Universal Time
12. Sidereal Time at Greenwich
13. Transformation of Coordinates
21. Precession
22. Nutation and Obliquity of the Ecliptic
27. Equinoxes and Solstices

 
