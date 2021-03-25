package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Prob2565_전깃줄 {
    static int N;
    static int[][] lines;
    static int[] DP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        DP = new int[N];
        lines = new int[N][];

        // 교차조건 앞2 > 앞1 && 뒤2 < 뒤1
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(lines, (o1, o2) -> o1[0] - o2[0]);

        // 0 제거 1 제거 x
        int max = 0;
        for (int i = 0; i < N; i++) {
            DP[i] = 1;
            for (int j = 0; j < i; j++)
                if (lines[j][1] < lines[i][1])
                    DP[i] = Math.max(DP[i], DP[j] + 1);
            max = Math.max(max, DP[i]);
        }
        System.out.print(N - max);
    }

}
