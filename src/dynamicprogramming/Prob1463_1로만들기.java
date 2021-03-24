package dynamicprogramming;

import java.io.*;
import java.util.Arrays;

public class Prob1463_1로만들기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] DP = new int[N + 1];
        Arrays.fill(DP, N);
        DP[N] = 0;
        for (int i = N; i > 1; i--) {
            if (i % 3 == 0)
                DP[i / 3] = Math.min(DP[i / 3], DP[i] + 1);
            if (i % 2 == 0)
                DP[i / 2] = Math.min(DP[i / 2], DP[i] + 1);
            DP[i - 1] = Math.min(DP[i - 1], DP[i] + 1);
        }
        //System.out.println(Arrays.toString(DP));
        System.out.println(DP[1]);
    }
}
