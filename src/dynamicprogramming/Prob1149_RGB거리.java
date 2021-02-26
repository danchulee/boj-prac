package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Prob1149_RGB거리 {
    static int N;
    static int[][] colors;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        dp = new int[N][N];
        colors = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                colors[i][j] = Integer.parseInt(st.nextToken());
        } // end of Input

//        for (int i = 0; i < N; i++)
//            dp[0][i] = colors[0][i];
//        for (int i = 1; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                if (j == i) continue;
//                dp[i][j] = Math.max(dp[i][j], dp[i-1][j] )
//            }
//        }

    }
}
