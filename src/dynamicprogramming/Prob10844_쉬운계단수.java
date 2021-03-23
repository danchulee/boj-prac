package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Prob10844_쉬운계단수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int mod = 1000000000;
        long[][] DP = new long[N + 1][10];
        Arrays.fill(DP[1], 1);
        DP[1][0] = 0;
        for (int i = 2; i <= N; i++) {
            DP[i][0] = DP[i - 1][1];
            DP[i][9] = DP[i - 1][8];
            for (int j = 1; j < 9; j++) {
                DP[i][j] = (DP[i - 1][j - 1] + DP[i - 1][j + 1]) % mod;
            }
        }
        long res = 0;
        for (long x : DP[N]) res += x;
        System.out.println(res % mod);
    }
}
