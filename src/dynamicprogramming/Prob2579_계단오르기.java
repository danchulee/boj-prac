package dynamicprogramming;
import java.io.*;

public class Prob2579_계단오르기 {
    static int N;
    static int[] dp, stairs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        stairs = new int[N + 1];
        for (int i = 1; i <= N; i++)
            stairs[i] = Integer.parseInt(br.readLine());
        dp[1] = stairs[1];
        if (N > 1)
            dp[2] = dp[1] + stairs[2];
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2], stairs[i - 1] + dp[i - 3]) + stairs[i];
        }
        System.out.print(dp[N]);
    }
}
