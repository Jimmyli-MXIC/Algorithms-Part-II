package Asgmt2_SeamCarving;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private Picture picture;
    /**
     * Create a seam carver object based on the given picture
     * @param picture
     */
    public SeamCarver(Picture picture){
        this.picture = picture;
    }

    /**
     * Current picture
     * @return
     */
    public Picture picture(){}

    /**
     * Width of current picture
     * @return
     */
    public int width(){}

    /**
     * Height of current picture
     * @return
     */
    public int height(){}

    /**
     * Energy of pixel at column <em>x</em> and row <em>y</em>
     * @param x
     * @param y
     * @return
     */
    public double energy(int x, int y){}

    /**
     * Sequence of indices for horizontal seam
     * @return
     */
    public int[] findHorizontalSeam(){}

    /**
     * Sequence of indices for vertical seam
     * @return
     */
    public int[] findVerticalSeam(){}

    /**
     * Remove horizontal seam from current picture
     * @param seam
     */
    public void removeHorizontalSeam(int[] seam){}

    /**
     * Remove vertical seam from current picture
     * @param seam
     */
    public void removeVerticalSeam(int[] seam){}
}
