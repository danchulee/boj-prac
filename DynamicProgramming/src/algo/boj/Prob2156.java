package algo.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob2156 {
    static int N;
    static int[] cups;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cups = new int[N];
        for (int i = 0; i < N; i++)
            cups[i] = Integer.parseInt(br.readLine());
        dp[0] = cups[0];
        dp[2] = Math.max(dp[0] + cups[2], cups[1] + cups[2]);
        dp[3] = Math.max(dp[2] + cups[3], dp[0]);
        for (int i = 0; i < N; i++) {

        }
    }
}
