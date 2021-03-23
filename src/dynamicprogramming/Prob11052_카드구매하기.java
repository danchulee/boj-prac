package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Prob11052_카드구매하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[N + 1];
        int[] DP = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            cards[i] = Integer.parseInt(st.nextToken());
        DP[1] = cards[1];
        for (int i = 2; i <= N; i++) {
            DP[i] = cards[i];
            for (int j = 1; j <= i / 2; j++)
                DP[i] = Math.max(DP[i], DP[j] + DP[i - j]);
        }
        System.out.println(DP[N]);
    }
}
