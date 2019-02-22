package Asgmt5_BurrowsWheelerDataCompression;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SuffixArray;

public class MoveToFront {

    private static final int R = 256;

    /**
     * Apply move-to-front encoding, reading from standard input and writing a standard output.
     */
    public static void encode() {
        char[] array = new char[R];
        for (int i = 0; i < R; i++) {
            array[i] = (char) i;
        }

        while (!StdIn.isEmpty()) {
            char in = StdIn.readChar();
            int out = 0;
            for (int i = 0; i < R; i++) {
                if (array[i] == in) {
                    out = i;
                    break;
                }
            }

            for (int i = out; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = in;
            StdOut.printf("%d ", out);
        }

    }

    /**
     * Apply move-to-front decoding, reading from standard input and writing a standard output.
     */
    public static void decode() {
        char[] array = new char[R];
        for (int i = 0; i < R; i++) {
            array[i] = (char) i;
        }

        while(!StdIn.isEmpty()){
            int in = StdIn.readInt();
            char out = array[in];

            for (int i = in; i > 0; i--){
                array[i] = array[i - 1];
            }
            array[0] = out;
            StdOut.printf("%c", out);
        }


    }

    /**
     * if args[0] is '-', apply move-to-front encoding
     * if args[0] is '+', apply move-to-front decoding
     *
     * @param args
     */
    public static void main(String[] args) {
//        if (args[0] == "-")
//            encode();
//        else if (args[0] == "+")
//            decode();
//        else
//            return;
        String s = "ABRACADABRA!";
        SuffixArray suffixArray = new SuffixArray(s);
        for (int i = 0; i < s.length(); i++){
            StdOut.println(suffixArray.index(i));
        }
    }
}
