package dynamicprogramming;

import java.io.*;

public class Prob2156_포도주시식 {
    static int N;
    static int[] cups;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cups = new int[N];
        dp = new int[N];
        for (int i = 0; i < N; i++)
            cups[i] = Integer.parseInt(br.readLine());
        dp[0] = cups[0];
        if (N > 1) {
            dp[1] = dp[0] + cups[1];
            if (N > 2)
                dp[2] = Math.max(Math.max(cups[0], cups[1]) + cups[2], dp[1]);
        }
        for (int i = 3; i < N; i++)
            dp[i] = Math.max(Math.max(cups[i - 1] + dp[i - 3], dp[i - 2]) + cups[i], dp[i - 1]);

        System.out.print(dp[N - 1]);
    }
}
