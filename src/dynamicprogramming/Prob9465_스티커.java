package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Prob9465_스티커 {
    static int N;
    static int[][] sticker;
    static int[][] DP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            sticker = new int[2][N];
            DP = new int[2][N];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    sticker[i][j] = Integer.parseInt(st.nextToken());
            }
            DP[0][0] = sticker[0][0];
            DP[1][0] = sticker[1][0];
            int tmp;
            for (int i = 1; i < N; i++) {
                DP[0][i] = DP[1][i - 1] + sticker[0][i];
                DP[1][i] = DP[0][i - 1] + sticker[1][i];
                if (i > 1) {
                    tmp = Math.max(DP[0][i - 2], DP[1][i - 2]);
                    DP[0][i] = Math.max(DP[0][i], tmp + sticker[0][i]);
                    DP[1][i] = Math.max(DP[1][i], tmp + sticker[1][i]);
                }
            }
            sb.append(Math.max(DP[0][N - 1], DP[1][N - 1])).append('\n');
        }
        System.out.print(sb);
    }
}
