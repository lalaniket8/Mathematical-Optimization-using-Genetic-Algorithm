# Mathematical-Optimization-using-Genetic-Algorithm
Linear and non-linear 2-variable optimization

This project aims at developing a Genetic algo library which supports mathematical functionality 
and its graphical representation using jfreechart library.

This approach is not limited to linear equations.
It supports linear and non linear constraints and objective functions.

Launcher.java: contains main method <br>
XYLineChart_AWT.java: deals with graphical representation of population<br>
Generation.java: abstract class which provides the base for genetic algorithm<br>
LinAlgebra.java: extends Generation class and implements all necessary methods of genetic algo pertaining to 
our problem statement<br>
Point.java: Point object is an individual organism in the population<br>
Exp.java: Expression class for easy working with constraints and objective function<br>
SimplePoint.java: A tuple of x and y coords<br><br>
JFreeChart library is used for graphical representation of solution<br>
http://www.jfree.org/jfreechart/

Scope for imporvement:
1) Establish a proper interface for the library(mathmatical parts)
2) Rewrite expression class and write different inherited classes for objective function and constraints
3) Use regex in expression class
4) Properly scale graph output
5) UI modification

Video : [Take me to youtube](https://youtu.be/07VRD8BHcP8)
