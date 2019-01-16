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
        if (picture == null) throw new IllegalArgumentException("Expected non-null picture");
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
        if (x < 0 || x > width() - 1 || y < 0 || y > height() - 1)
            throw new IllegalArgumentException("Expected x in [0, width - 1] and y in [0, height - 1]");
        return calculateEnergy(x, y, false);

    }

    private double calculateEnergy(int x, int y, boolean transposed) {
        if (transposed) {
            return calculateEnergy(y, x, false);
        }

        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1)
            return 1000.00;

        double dx = 0, dy = 0;
        for (int i = 16; i >= 0; i -= 8) {
            dx += Math.pow(((picture.getRGB(x - 1, y) >> i) & 0xFF) - ((picture.getRGB(x + 1, y) >> i) & 0xFF), 2);
            dy += Math.pow(((picture.getRGB(x, y - 1) >> i) & 0xFF) - ((picture.getRGB(x, y + 1) >> i) & 0xFF), 2);
        }
        return Math.sqrt(dx + dy);
    }

    /**
     * Sequence of indices for horizontal seam
     *
     * @return
     */
    public int[] findHorizontalSeam() {

        energy = new double[width()][height()];
        for (int row = 0; row < width(); row++)
            for (int col = 0; col < height(); col++)
                energy[row][col] = calculateEnergy(col, row, true);

        return getPath();

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
                energy[row][col] = calculateEnergy(col, row, false);

        return getPath();


    }

    private int[] getPath() {
        int height = energy.length;
        int width = energy[0].length;
        int size = height * width;

        distTo = new double[size];
        pathTo = new int[size];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int p = position(col, row);

                if (row == 0)
                    distTo[p] = 1000.00;
                else
                    distTo[p] = Double.POSITIVE_INFINITY;

                pathTo[p] = -1;
            }
        }

        for (int row = 0; row < height - 1; row++) {
            for (int col = 1; col < width - 1; col++) {
                int p = position(col, row);
                relax(p, col - 1, row + 1);
                relax(p, col, row + 1);
                relax(p, col + 1, row + 1);
            }
        }

        int bottomSeam = 0;
        double minimumEnergy = Double.POSITIVE_INFINITY;

        for (int col = 0; col < width; col++) {
            if (distTo[position(col, height - 1)] < minimumEnergy) {
                minimumEnergy = distTo[position(col, height - 1)];
                bottomSeam = col;
            }
        }

        return getSeam(height, width, bottomSeam);
    }

    private void relax(int from, int colTo, int rowTo) {
        if (distTo[position(colTo, rowTo)] > distTo[from] + energy[rowTo][colTo]) {
            distTo[position(colTo, rowTo)] = distTo[from] + energy[rowTo][colTo];
            pathTo[position(colTo, rowTo)] = from;
        }
    }

    private int[] getSeam(int height, int width, int bottomSeam) {
        int[] result = new int[height];
        result[height - 1] = bottomSeam;

        for (int y = height - 1; y > 0; y--) {
            result[y - 1] = pathTo[position(result[y], y)] % width;
        }
        return result;
    }

    private int position(int col, int row) {
        return row * energy[0].length + col;
    }


    /**
     * Remove horizontal seam from current picture
     *
     * @param seam
     */
    public void removeHorizontalSeam(int[] seam) {
        //  TODO
        if (seam == null) throw new IllegalArgumentException("Expected non-null seam");
        if (seam.length != width()) throw new IllegalArgumentException("Expected seam with length " + height());

        for (int i = 1; i < width(); i++)
            if (Math.abs(seam[i] - seam[i - 1]) > 1)
                throw new IllegalArgumentException(
                        "Expected adjacent elements of seam with have a an absolute difference of most 1");

        if (height() <= 1) throw new IllegalArgumentException("Cannot remove vertical seam on width <= 1");

        Picture result = new Picture(width(), height() - 1);
        for (int col = 0; col < width(); col++) {
            for (int row = 0, k = 0; row < height(); row++) {
                if (row != seam[col])
                    result.setRGB(col, k++, picture.getRGB(col, row));
            }
        }
        this.picture = result;

        distTo = null;
        energy = null;
        pathTo = null;

    }

    /**
     * Remove vertical seam from current picture
     *
     * @param seam
     */
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("Expected non-null seam");
        if (seam.length != height()) throw new IllegalArgumentException("Expected seam with length " + height());

        for (int i = 1; i < height(); i++)
            if (Math.abs(seam[i] - seam[i - 1]) > 1)
                throw new IllegalArgumentException(
                        "Expected adjacent elements of seam with have a an absolute difference of most 1");

        if (width() <= 1) throw new IllegalArgumentException("Cannot remove vertical seam on width <= 1");

        Picture result = new Picture(width() - 1, height());
        for (int row = 0; row < height(); row++) {
            for (int col = 0, k = 0; col < width(); col++) {
                if (col != seam[row])
                    result.setRGB(k++, row, picture.getRGB(col, row));
            }
        }
        this.picture = result;

        distTo = null;
        energy = null;
        pathTo = null;

    }
}
