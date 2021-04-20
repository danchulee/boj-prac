package etc;

import java.io.*;
import java.util.*;

public class Prob5557_1학년 {
    static int N, answer;
    static int[] nums;
    static int[][] DP;

    // DP 배열한 0< < 20 범위에 가능한 경우의 수를 넣는다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        //DP = new int[][][2]

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        //dfs(0, 0);
        System.out.print(answer);
    }
}
