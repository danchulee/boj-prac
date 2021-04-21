package dynamicprogramming;

import java.io.*;
import java.util.*;

public class Prob5557_1학년 {
    static int N;
    static int[] nums;
    static long[][] DP;

    // DP 배열한 0< < 20 범위에 가능한 경우의 수를 넣는다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        DP = new long[N + 1][21];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) // n-1ㄲㅏ지 숫자, n은 결과
            nums[i] = Integer.parseInt(st.nextToken());

        DP[1][nums[1]] = 1;
        int num1, num2;
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 20; j++) {
                num1 = j + nums[i];
                num2 = j - nums[i];
                if (num1 <= 20 && num1 >= 0) DP[i][num1] += DP[i - 1][j];
                if (num2 <= 20 && num2 >= 0) DP[i][num2] += DP[i - 1][j];
            }
        }
        System.out.print(DP[N - 1][nums[N]]);
    }
}