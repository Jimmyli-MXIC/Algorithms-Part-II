package Asgmt2_SeamCarving.testfile; /******************************************************************************
 *  Compilation:  javac PrintEnergy.java
 *  Execution:    java PrintEnergy input.png
 *  Dependencies: SeamCarver.java
 *
 *
 *  Read image from file specified as command line argument. Print energy
 *  of each pixel as calculated by SeamCarver object. 
 *
 ******************************************************************************/

import Asgmt2_SeamCarving.SeamCarver;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PrintEnergy {

    public static void main(String[] args) {
//        double timeOfEnergy = 0.0;
//        Stopwatch timer;
//        Picture picture = new Picture(args[0]);
//        StdOut.printf("image is %d pixels wide by %d pixels high.\n", picture.width(), picture.height());
//
//        SeamCarver sc = new SeamCarver(picture);
//
//        StdOut.printf("Printing energy calculated for each pixel.\n");
//
//            timer = new Stopwatch();
//            for (int row = 0; row < sc.height(); row++) {
//                for (int col = 0; col < sc.width(); col++) {
//                    sc.energy(col, row);
//                }
//            }
//            timeOfEnergy += timer.elapsedTime();
//
//        StdOut.println("time cost of calculate energy: ");
//        StdOut.print(timeOfEnergy);
        Picture picture = new Picture(args[0]);
        StdOut.printf("image is %d pixels wide by %d pixels high.\n", picture.width(), picture.height());

        SeamCarver sc = new SeamCarver(picture);

        StdOut.printf("Printing energy calculated for each pixel.\n");

        for (int row = 0; row < sc.height(); row++) {
            for (int col = 0; col < sc.width(); col++)
                StdOut.printf("%9.0f ", sc.energy(col, row));
            StdOut.println();
        }
        for (int i : sc.findVerticalSeam())
            StdOut.print(i + " ");
        StdOut.print("/n");
        for (int i : sc.findHorizontalSeam())
            StdOut.print(i + " ");
    }

}
