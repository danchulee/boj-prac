package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Prob11660_구간합구하기5 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];
        int[][] DP = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                DP[i][j] = DP[i - 1][j] + DP[i][j - 1] - DP[i - 1][j - 1] + map[i][j];
            }
        }
//
//        for (int i = 0; i <= N; i++)
//            System.out.println(Arrays.toString(DP[i]));

        int x1, x2, y1, y2, out;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            out = DP[x2][y2] - DP[x1 - 1][y2] - DP[x2][y1 - 1] + DP[x1 - 1][y1 - 1];
            sb.append(out).append("\n");
        }
        System.out.print(sb);


    }
}
