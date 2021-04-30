package graph;

import java.io.*;
import java.util.*;

public class Prob1005_ACMCraft_미완성 {
    static int N, K, W;
    static int[] times;
    static int[] childs;
    static ArrayList<Integer>[] graph;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N + 1];
            times = new int[N + 1];
            childs = new int[N + 1];
            for (int i = 1; i <= N; i++)
                graph[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++)
                times[i] = Integer.parseInt(st.nextToken());
            int from, to;
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
                childs[to]++;
            }
            W = Integer.parseInt(br.readLine());

            sb.append(solve()).append("\n");
        }
        System.out.print(sb);
    }

    public static int solve() {
        int[] res = new int[N + 1];

        q.clear();
        for (int i = 1; i <= N; i++) {
            if (childs[i] == 0)
                q.offer(new int[]{0, i});
            res[i] = times[i];
        }
        int[] curr;
        while (!q.isEmpty()) {
            curr = q.poll(); // 현재 정점

            res[curr[1]] = Math.max(res[curr[1]], times[curr[1]] + res[curr[0]]); // 시간 갱신
            for (int i : graph[curr[1]])
                if (childs[i]-- > 0)
                    q.offer(new int[]{curr[1], i});
        } // end of while
        return res[W];
    }
}
