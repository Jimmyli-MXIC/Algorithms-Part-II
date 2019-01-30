package Asgmt4_Boggle;

import Asgmt4_Boggle.testfile.BoggleBoard;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.HashSet;

public class BoggleSolver {

    private PrefixTrieST<Integer> dictionary;

    /**
     * Initializes the data structure using the given array of stings as the dictionary.
     *
     * @param dictionary
     */
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null)
            throw new IllegalArgumentException();

        String[] copyOfDict = Arrays.copyOf(dictionary, dictionary.length);

        this.dictionary = new PrefixTrieST<>();
        int[] points = {0, 0, 0, 1, 1, 2, 3, 5, 11};

        for (String s : copyOfDict) {
            if (s.length() >= points.length - 1)
                this.dictionary.put(s, points[points.length - 1]);
            else this.dictionary.put(s, points[s.length()]);
        }
    }

    /**
     * Returns the set of all valid words in the given Boggle board, as an Iterable.
     *
     * @param board
     * @return
     */
    public Iterable<String> getAllValidWords(BoggleBoard board) {

        HashSet<String> validWords = new HashSet<>();
        boolean[][] marked = new boolean[board.rows()][board.cols()];

        for (int row = 0; row < board.rows(); row++) {
            for (int col = 0; col < board.cols(); col++) {

                marked[row][col] = true;
                dfs(row, col, marked, board, validWords, addLetter(new StringBuilder(), board.getLetter(row, col)));
                marked[row][col] = false;
            }
        }
        return validWords;
    }

    private void dfs(int row, int col, boolean[][] marked, BoggleBoard board,
                     HashSet<String> validWords, StringBuilder letterSeq) {

        if (isValid(letterSeq.toString()))
            validWords.add(letterSeq.toString());

        for (int i = Math.max(0, row - 1); i <= Math.min(board.rows() - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(board.cols() - 1, col + 1); j++) {
                if (!marked[i][j]) {

                    if (!dictionary.hasPrefix(addLetter(letterSeq, board.getLetter(i, j)).toString())) {
                        deleteLetter(letterSeq);
                        continue;
                    }
                    marked[i][j] = true;
                    dfs(i, j, marked, board, validWords, letterSeq);
                    deleteLetter(letterSeq);
                    marked[i][j] = false;
                }
            }
        }

    }

    private boolean isValid(String letterSequence) {
        if (letterSequence.length() < 3)
            return false;
        return dictionary.contains(letterSequence);
    }

    private StringBuilder addLetter(StringBuilder sequence, char letter) {
        if (letter == 'Q') {
            return sequence.append("QU");
        } else
            return sequence.append(letter);
    }

    private StringBuilder deleteLetter(StringBuilder sequence) {
        if (sequence.charAt(sequence.length() - 2) == 'Q')
            return sequence.deleteCharAt(sequence.length() - 1).deleteCharAt(sequence.length() - 1);
        else
            return sequence.deleteCharAt(sequence.length() - 1);
    }

    /**
     * Returns the score of the given word if it is in the dictionary, zero otherwise.
     *
     * @param word
     * @return
     */
    public int scoreOf(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        if (!dictionary.contains(word))
            return 0;
        else return dictionary.get(word);
    }

    public static void main(String[] args) {

//        In in = new In(args[0]);
//        String[] dictionary = in.readAllStrings();
//        BoggleSolver solver = new BoggleSolver(dictionary);
//        BoggleBoard board = new BoggleBoard(args[1]);
//        int score = 0;
//        for (String word : solver.getAllValidWords(board)) {
//            StdOut.println(word);
//            score += solver.scoreOf(word);
//        }
//        StdOut.println("Score = " + score);

        double timeOfConstruct = 0.0;
        double timeOfSolve = 0.0;

        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();

        Stopwatch timer;
//        timer = new Stopwatch();

        for (int i = 0; i < 10; i++){
            timer = new Stopwatch();
            BoggleSolver solver = new BoggleSolver(dictionary);
            timeOfConstruct += timer.elapsedTime();
        }

        StdOut.println("time cost of construct(10times)  : " + timeOfConstruct);

//
//    for (int i = 0; i < 2000; i++){
//        BoggleBoard board = new BoggleBoard();
//        timer = new Stopwatch();
//        solver.getAllValidWords(board);
//        timeOfSolve += timer.elapsedTime();
//    }
//    StdOut.println("time cost of solver(1000times)  : " + timeOfSolve);
//
//    }

    }
}
