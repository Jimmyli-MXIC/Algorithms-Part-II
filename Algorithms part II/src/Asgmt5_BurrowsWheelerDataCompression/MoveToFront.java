package Asgmt5_BurrowsWheelerDataCompression;

import edu.princeton.cs.algs4.*;

public class MoveToFront {

    private static final int R = 256;

    /**
     * Apply move-to-front encoding, reading from standard input and writing a standard output.
     */
    public static void transform() {
        char[] array = new char[R];
        for (int i = 0; i < R; i++) {
            array[i] = (char) i;
        }

        while (!StdIn.isEmpty()) {
            char c = StdIn.readChar();
            int out = 0;
            for (int i = 0; i < R; i++) {
                if (array[i] == c) {
                    out = i;
                    break;
                }
            }

            for (int i = out; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = c;
            StdOut.println(out);
        }
//        ST<Character, Integer> st = new ST<>();
//        for (int i = 0; i < R; i++){
//            st.put((char) i, i);
//        }
//
//        while (!BinaryStdIn.isEmpty()){
//            char c = BinaryStdIn.readChar();
//            int out = st.get(c);
//            StdOut.printf("%03x", out);
//            st.put(c, 0);
//            for (char tmp = 0; tmp < c;)
//        }
    }

    /**
     * Apply move-to-front decoding, reading from standard input and writing a standard output.
     */
    public static void inverseTransform() {
    }

    /**
     * if args[0] is '-', apply move-to-front encoding
     * if args[0] is '+', apply move-to-front decoding
     *
     * @param args
     */
    public static void main(String[] args) {
        transform();
    }
}
