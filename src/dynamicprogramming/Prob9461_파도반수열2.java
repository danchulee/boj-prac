package dynamicprogramming;

import java.io.*;

// 1 1 1 2 2 / 예외 / 3 4 5 7 9 12
public class Prob9461_파도반수열2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long[] DP;
        int N, T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            DP = new long[N + 1];
            DP[1] = 1;
            if (N > 1) {
                DP[2] = 1;
                if (N > 2) {
                    DP[3] = 1;
                    if (N > 3) {
                        DP[4] = 2;
                        if (N > 4)
                            DP[5] = 2;
                    }
                }
            }
            if (N > 5)
                for (int i = 6; i <= N; i++)
                    DP[i] = DP[i - 1] + DP[i - 5];
            sb.append(DP[N]).append("\n");
        }
        System.out.print(sb);
    }
}
