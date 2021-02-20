package etc;

import java.io.*;
import java.util.*;

public class Prob2559_수열 {
    static int N, K;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new int[N];

        int answer, sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < K; i++)
            sum += nums[i];
        answer = sum;
        for (int i = 1; i <= N - K; i++) {
            sum = sum - nums[i - 1] + nums[i + K - 1];
            answer = Math.max(answer, sum);
        }
        System.out.print(answer);
    }

}

