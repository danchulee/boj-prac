package algo.boj;

import java.util.Arrays;
import java.util.Scanner;

public class Prob11399_ATM {
    static int N, ans = 0;
    static int[] times;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        times = new int[N];
        for (int i = 0; i < N; i++)
            times[i] = sc.nextInt();
        Arrays.sort(times);
        for (int i = 0; i < N; i++)
            ans += times[i] * (N - i);
        System.out.println(ans);
        sc.close();
    } // end of Main
} // end of Class
