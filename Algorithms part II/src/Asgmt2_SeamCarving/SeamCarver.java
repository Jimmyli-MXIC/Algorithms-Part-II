package Asgmt2_SeamCarving;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private Picture picture;
    private double[][] energy;
    private double[][] distTo;
    private int[][] pathTo;
    private int width, height;

    /**
     * Create a seam carver object based on the given picture
     *
     * @param picture
     */
    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
        this.distTo = new double[width][height];
        this.pathTo = new int[width][height];

        this.width = this.picture.width();
        this.height = this.picture.height();


        energy = new double[this.width][this.height];

        /* Compute the energy of every pixel */
        for (int x = 0; x < this.width; x++)
            for (int y = 0; y < this.height; y++)
                energy[x][y] = computeEnergy(x, y);
    }

    /**
     * Current picture
     *
     * @return
     */
    public Picture picture() {
        return new Picture(this.picture);
    }

    /**
     * Width of current picture
     *
     * @return
     */
    public int width() {
        return width;
    }

    /**
     * Height of current picture
     *
     * @return
     */
    public int height() {
        return height;
    }

    /**
     * Energy of pixel at column <em>x</em> and row <em>y</em>
     *
     * @param x
     * @param y
     * @return
     */
    public double energy(int x, int y) {
        if ((x < 0 && x > width - 1) || (y < 0 && y > height - 1))
            throw new IllegalArgumentException("x or y is outside its prescribed range");
        return energy[x][y];

    }

    private double computeEnergy(int x, int y) {
        if ((x == 0 || x == width - 1) || ((y == 0 || y == height - 1)))
            return 1000.00;

        int[] rgbL = extractRGB(picture.getRGB(x - 1, y));
        int[] rgbR = extractRGB(picture.getRGB(x + 1, y));
        int[] rgbU = extractRGB(picture.getRGB(x, y - 1));
        int[] rgbD = extractRGB(picture.getRGB(x, y + 1));

        double xSquare = 0.0, ySquare = 0.0;
        for (int i = 0; i < 3; i++) {
            xSquare += Math.pow(rgbL[i] - rgbR[i], 2);
            ySquare += Math.pow(rgbU[i] - rgbD[i], 2);
        }
        return Math.sqrt(xSquare + ySquare);
    }

    private int[] extractRGB(int rgb) {

        int[] arrayOfRGB = new int[3];
        arrayOfRGB[0] = (rgb >> 16) & 0xFF;
        arrayOfRGB[1] = (rgb >> 8) & 0xFF;
        arrayOfRGB[2] = (rgb >> 0) & 0xFF;

        return arrayOfRGB;
    }


    /**
     * Sequence of indices for horizontal seam
     *
     * @return
     */
    public int[] findHorizontalSeam() {
        //  TODO
        double transposeEnergy = new
        return null;
    }

    /**
     * Sequence of indices for vertical seam
     *
     * @return
     */
    public int[] findVerticalSeam() {


        /* Initialize the distance array */
        for (int i = 0; i < width; i++) {
            distTo[i][0] = 1000.00;
        }
        for (int y = 1; y < height; y++)
            for (int x = 0; x < width; x++)
                distTo[x][y] = Double.POSITIVE_INFINITY;


        for (int y = 0; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                relax(x, y, x - 1, y + 1);
                relax(x, y, x, y + 1);
                relax(x, y, x + 1, y + 1);
            }
        }
        double minimumEnergy = Double.POSITIVE_INFINITY;
        int bottomSeam = 0;
        for (int i = 0; i < width(); i++){
            if (distTo[i][height()-1] < minimumEnergy){
                minimumEnergy = distTo[i][height()-1];
                bottomSeam = i;
            }
        }
        int[] seamPath = new int[height()];
        seamPath[height()-1] = bottomSeam;
        for (int y = height()-1; y > 0; y--){
           seamPath[y-1] = pathTo[seamPath[y]][y];
        }
        return seamPath;


    }

    private void relax(int xFrom, int yFrom, int xTo, int yTo) {
        if (distTo[xTo][yTo] > distTo[xFrom][yFrom] + energy[xTo][yTo]){
            distTo[xTo][yTo] = distTo[xFrom][yFrom] + energy[xTo][yTo];
            pathTo[xTo][yTo] = xFrom;
        }
    }


    /**
     * Remove horizontal seam from current picture
     *
     * @param seam
     */
    public void removeHorizontalSeam(int[] seam) {
        //  TODO
    }

    /**
     * Remove vertical seam from current picture
     *
     * @param seam
     */
    public void removeVerticalSeam(int[] seam) {
        //  TODO
    }
}
