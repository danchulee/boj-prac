package dynamicprogramming;

import java.io.*;

public class Prob11726_2xn타일링 {
    static int N;
    static int[] DP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        DP = new int[N + 1];
        DP[0] = DP[1] = 1;
        for (int i = 2; i <= N; i++) {
            DP[i] += DP[i - 1];
            if (i - 2 >= 0) DP[i] += DP[i - 2];
            DP[i] %= 10007;
        }
        System.out.print(DP[N]);
    }
}
