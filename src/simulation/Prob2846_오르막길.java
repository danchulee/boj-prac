package simulation;

import java.io.*;
import java.util.StringTokenizer;

public class Prob2846_오르막길 {
    static int N, answer;
    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            num[i] = Integer.parseInt(st.nextToken());
        int start = 0;
        for (int i = 1; i < N; i++) {
            if (i == N - 1) answer = Math.max(answer, num[i] - num[start]);
            if (num[i] <= num[i - 1]) {
                answer = Math.max(answer, num[i - 1] - num[start]);
                start = i;
            }
        }
        System.out.print(answer);
    }
}
