package Asgmt2_SeamCarving;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private Picture picture;
    private double[][] energy;
    private double[] distTo;
    private int[] pathTo;

    /**
     * Create a seam carver object based on the given picture
     *
     * @param picture
     */
    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
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
        return picture.width();
    }

    /**
     * Height of current picture
     *
     * @return
     */
    public int height() {
        return picture.height();
    }

    /**
     * Energy of pixel at column <em>x</em> and row <em>y</em>
     *
     * @param x
     * @param y
     * @return
     */
    public double energy(int x, int y) {
        if ((x < 0 && x > width() - 1) || (y < 0 && y > height() - 1))
            throw new IllegalArgumentException("x or y is outside its prescribed range");
        return computeEnergy(x, y, false);

    }

    private double computeEnergy(int x, int y, boolean transposed) {
        if (transposed)
            return computeEnergy(y, x, false);

        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1)
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

        energy = new double[height()][width()];
        for (int row = 0; row < width(); row++)
            for (int col = 0; col < height(); col++)
                energy[row][col] = computeEnergy(row, col, true);

        return getSeam();

    }

    private int position(int col, int row) {
        return row * energy[0].length + col;
    }

    /**
     * Sequence of indices for vertical seam
     *
     * @return
     */
    public int[] findVerticalSeam() {

        energy = new double[height()][width()];
        for (int row = 0; row < height(); row++)
            for (int col = 0; col < width(); col++)
                energy[row][col] = computeEnergy(col, row, false);

        return getSeam();


    }

    private int[] getSeam() {
        int tmpHeight = energy.length;
        int tmpWidth = energy[0].length;
        int size = tmpHeight * tmpWidth;

//        energy = new double[size];
        distTo = new double[size];
        pathTo = new int[size];

        for (int row = 0; row < tmpHeight; row++) {
            for (int col = 0; col < tmpWidth; col++) {
                int p = position(col, row);

                if (row == 0)
                    distTo[p] = 1000.00;
                else
                    distTo[p] = Double.POSITIVE_INFINITY;
//                energy[p] = computeEnergy(col, row, transpose);
                pathTo[p] = -1;
            }
        }

        for (int row = 0; row < tmpHeight - 1; row++) {
            for (int col = 1; col < tmpWidth - 1; col++) {
                int p = position(col, row);
                relax(p, col - 1, row + 1);
                relax(p, col, row + 1);
                relax(p, col + 1, row + 1);
            }
        }

        double minimumEnergy = Double.POSITIVE_INFINITY;
        int bottomSeam = 0;
        for (int col = 0; col < tmpWidth; col++) {
            if (distTo[position(col, tmpHeight - 1)] < minimumEnergy) {
                minimumEnergy = distTo[position(col, tmpHeight - 1)];
                bottomSeam = col;
            }
        }

        return verticalSeam(bottomSeam);
    }

    private int[] verticalSeam(int bottomSeam) {
        int tmpHeight = energy.length;
        int tmpWidth = energy[0].length;
        int[] result = new int[tmpHeight];
        result[tmpHeight - 1] = bottomSeam;

        for (int y = tmpHeight - 1; y > 0; y--) {
            result[y - 1] = pathTo[position(result[y], y)] % tmpWidth;
        }
        return result;
    }

    private void relax(int from, int colTo, int rowTo) {
        if (distTo[position(colTo, rowTo)] > distTo[from] + energy[colTo][rowTo]) {
            distTo[position(colTo, rowTo)] = distTo[from] + energy[colTo][rowTo];
            pathTo[position(colTo, rowTo)] = from;
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
