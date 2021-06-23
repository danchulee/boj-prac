package greedy;

import java.io.*;
import java.util.*;

public class Prob1052_물병 {
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int cnt, len, ans = 0, last = 0;
        int add;
        while (true) {
            cnt = 0;
            len = Integer.toBinaryString(N).length();
            for (int i = len - 1; i >= 0; i--)
                if ((N & (1 << i)) != 0) {
                    cnt++;
                    last = i;
                }
            if (cnt <= K) break;
            add = (int) Math.pow(2, last);
            ans += add;
            N += add;
        }
        System.out.print(ans);
    }
}
