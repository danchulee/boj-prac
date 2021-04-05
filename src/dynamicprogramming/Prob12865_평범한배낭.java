package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Prob12865_평범한배낭 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] costs = new int[N + 1][2];
        int[][] DP = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            costs[i][0] = Integer.parseInt(st.nextToken());
            costs[i][1] = Integer.parseInt(st.nextToken());
        }

        int weight, worth;
        for (int i = 1; i <= N; i++) {
            weight = costs[i][0];
            worth = costs[i][1];
            //DP[i][0] = DP[i - 1][0];
            System.arraycopy(DP[i - 1], 0, DP[i], 0, K + 1);
            for (int j = 1; j <= K; j++) {
                if (j >= weight) {
                    DP[i][j] = Math.max(DP[i][j - 1],
                            Math.max(DP[i][j], DP[i - 1][j - weight] + worth));
                }
            }
        }
        System.out.println(DP[N][K]);
    }
}
/*
4 8
6 13
4 8
4 6
5 12

14 100000
61803 5
62863 0
41632 3
12794 2
71324 8
55358 2
34870 8
41590 7
17928 0
24218 3
18426 0
65130 2
16478 2
93173 9

4 2
1 1
5 1
5 1
1 1

4 8
6 13
4 8
4 6
5 12
 */