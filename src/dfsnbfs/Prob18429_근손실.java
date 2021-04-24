package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob18429_근손실 {
    static int[] nums;
    static int N, K, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        cases(0, 0, 500);
        System.out.print(answer);
    }

    public static void cases(int cnt, int visit, int kg) {
        if (kg < 500) return;
        if (cnt == N) {
            answer++;
            return;
        }
        for (int i = 0; i < N; i++)
            if ((visit & (1 << i)) == 0)
                cases(cnt + 1, visit | (1 << i), kg - K + nums[i]);
    }
}
