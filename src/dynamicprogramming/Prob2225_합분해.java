package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Prob2225_합분해 {
    static final int MOD = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 합이 N이어야 한다.
        long[][] DP = new long[K + 1][N + 1]; // K 개의 수로 N만들기
        Arrays.fill(DP[0], 0);
        Arrays.fill(DP[1], 1);
        for (int i = 2; i <= K; i++)
            for (int j = 0; j <= N; j++)
                for (int m = 0; m <= j; m++)
                    DP[i][j] = ((DP[i][j] + DP[i - 1][j - m]) % MOD);
        System.out.print(DP[K][N]);
    }
}
