package dynamicprogramming;

import java.io.*;

public class Prob2156_포도주시식2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] juice = new int[N + 1];
        int[] DP = new int[N + 1];

        for (int i = 1; i <= N; i++) juice[i] = Integer.parseInt(br.readLine());

        int answer = 0;
        DP[1] = juice[1];
        answer = DP[1];
        if (N > 1) {
            DP[2] = DP[1] + juice[2];
            answer = DP[2];
        }
        for (int i = 3; i <= N; i++) {
            DP[i] = Math.max(DP[i - 1], Math.max(DP[i - 2], DP[i - 3] + juice[i - 1]) + juice[i]);
            answer = Math.max(answer, DP[i]);
        }
        System.out.print(answer);
    }
}
