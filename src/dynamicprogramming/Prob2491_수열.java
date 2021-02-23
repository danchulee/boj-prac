package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Prob2491_수열 {
    static int N;
    //    static int[] dp, dp2;
    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        num = new int[N];
//        dp = new int[N];
//        dp2 = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            num[i] = Integer.parseInt(st.nextToken());

        int answer = 1;
//        dp[0] = dp2[0] = 1;
        int cnt = 1, cnt2 = 1;
        for (int i = 1; i < N; i++) {
            cnt = (num[i - 1] <= num[i] ? cnt + 1 : 1);
            cnt2 = (num[i - 1] >= num[i] ? cnt2 + 1 : 1);
            answer = Math.max(answer, Math.max(cnt, cnt2));
        }
        System.out.print(answer);
    }
}
