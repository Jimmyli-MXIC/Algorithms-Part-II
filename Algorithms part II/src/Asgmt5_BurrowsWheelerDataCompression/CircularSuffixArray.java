package Asgmt5_BurrowsWheelerDataCompression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.HexDump;
import edu.princeton.cs.algs4.MSD;
import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {

    private String input;

    private int[] index;
    /**
     * Circular suffix array of s
     */
    public CircularSuffixArray(String s){
        input = s;
        index = new int[s.length()];
        char[] array = input.toCharArray();

    }

    /**
     * Length of s
     * @return
     */
    public int length(){
        return input.length();
    }

    /**
     * Return index of ith sorted suffix
     * @param i
     * @return
     */
    public int index(int i){
        return 0;
    }

    /**
     * Unit testing
     * @param args
     */

    public static void main(String[] args){
        int bytesPerLine = 16;
        if (args.length == 1) {
            bytesPerLine = Integer.parseInt(args[0]);
        }

        int i;
        for (i = 0; !BinaryStdIn.isEmpty(); i++) {
            if (bytesPerLine == 0) {
                BinaryStdIn.readChar();
                continue;
            }
            if (i == 0) StdOut.printf("");
            else if (i % bytesPerLine == 0) StdOut.printf("\n", i);
            else StdOut.print(" ");
            char c = BinaryStdIn.readChar();
            StdOut.printf("%02x", c & 0xff);
        }
        if (bytesPerLine != 0) StdOut.println();
        StdOut.println((i*8) + " bits");
    }
}
