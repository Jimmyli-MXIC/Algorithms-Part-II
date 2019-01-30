package Week3_RadixSorts;

public class KeyIndexedCounting {

    public static void main(String[] args){
        int R = 5;
        int[] a = new int[10];
        int N = a.length;
        int[] count = new int[R+1];
        int[] aux = new int[10];

        for (int i = 0; i < N; i++)
            count[a[i]+1]++;

        for (int r = 0; r < R; r++)
            count[r+1] += count[r];

        for (int i = 0; i < N; i++)
            aux[count[a[i]]++] = a[i];

        for (int i = 0; i < N; i++)
            a[i] = aux[i];
    }
}
