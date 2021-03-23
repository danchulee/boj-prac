package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Prob11053_가장긴증가하는부분수열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int[] DP = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());

        int answer = 1;
        Arrays.fill(DP, 1);
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[i] > numbers[j])
                    DP[i] = Math.max(DP[i], DP[j] + 1);
            }
            answer = Math.max(answer, DP[i]);
        }
        System.out.println(answer);
    }
}
