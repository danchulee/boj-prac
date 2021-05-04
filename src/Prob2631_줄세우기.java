import java.io.*;
import java.util.*;

// 증가하는 가장 긴 수열 lis
public class Prob2631_줄세우기 {
    static int N, max;
    static int[] tall;
    static int[] DP;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tall = new int[N];
        DP = new int[N];
        for (int i = 0; i < N; i++)
            tall[i] = Integer.parseInt(br.readLine());
        Arrays.fill(DP, 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++)
                if (tall[i] > tall[j])
                    DP[i] = Math.max(DP[i], DP[j] + 1);
            max = Math.max(max, DP[i]);
        }
        System.out.print(N - max);
    }
}
