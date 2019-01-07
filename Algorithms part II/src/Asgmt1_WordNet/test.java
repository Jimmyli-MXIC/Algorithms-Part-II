package Asgmt1_WordNet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class test {
    public static void verifyRoot(Digraph digraph){
        for (int i = 0, sz = digraph.V(); i < sz; i++){
            if (!digraph.adj(i).iterator().hasNext()){
                StdOut.println("1");
            }
            if (digraph.adj(i) == null)
                StdOut.println("2");
        }

    }
    public static void main(String[] args){
//
//        String[] lines = new String[8];
//        Integer[] indexes = new Integer[8];
//        String[] synsets = new String[8];
//
//        In in = new In(args[0]);
//        int i = 0;
//        while (in.hasNextLine()){
//            lines[i] = in.readLine();
//            indexes[i] = Integer.parseInt(lines[i].split(",")[0]);
//            synsets[i] = lines[i].split(",")[1];
//            i++;
//        }
//
////        for (String line : lines)
////            StdOut.println(line);
////        for (Integer index : indexes)
////            StdOut.println(index);
//        for (String synset : synsets)
//            StdOut.print(synset);
        In in = new In(args[0]);
        Digraph digraph = new Digraph(in);
        verifyRoot(digraph);




    }
}
