package graph;

import java.io.*;
import java.util.*;

public class Prob4256_트리 {
    static int N;
    static int[] pre, in;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());

            pre = new int[N];
            in = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                pre[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                in[i] = Integer.parseInt(st.nextToken());
            postorder(0, N, 0);
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static void postorder(int s, int e, int r) {
        for (int i = s; i < e; i++) {
            if (in[i] == pre[r]) { // 같으면 루트인가..?
                postorder(s, i, r + 1);
                postorder(i + 1, e, r + i - s + 1);
                sb.append(pre[r]).append(' ');
            }
        }
    }

}
