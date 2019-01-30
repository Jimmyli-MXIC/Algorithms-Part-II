package Week3_RadixSorts;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class LongestRepeatedSubstring {

    public static String lrs(String s){
        int N = s.length();

        String[] suffixes = new String[N];
        for (int i = 0; i < N; i++)
            suffixes[i] = s.substring(i, N);

        Arrays.sort(suffixes);

        String lrs = "";
        for (int i = 0; i < N-1; i++)
        {
            int len = lcp(suffixes[i], suffixes[i+1]);
            if (len > lrs.length())
                lrs = suffixes[i].substring(0, len);
        }
        return lrs;
    }

    private static int lcp(String a, String b){
        int len = 0;
        for (int i = 0; i < Math.min(a.length(), b.length()); i++){
            if (a.charAt(i) == b.charAt(i))
                len += 1;
            else
                break;
        }
        return len;
    }

    public static void main(String[] args){
        String s = "asdfsfslafjlksjflaskjdflkasglsdfhls";
        StdOut.println(lrs(s));
    }
}
