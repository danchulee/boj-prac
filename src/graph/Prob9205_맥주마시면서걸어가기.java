package graph;

import java.io.*;
import java.util.*;

public class Prob9205_맥주마시면서걸어가기 {
    static int N;
    static Queue<int[]> q = new LinkedList<>();
    static int[][] market;
    static int[] dst;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) { // 1000칸 이내로 걷기 목적지가 1000칸 이내면 무조건 직
            q.clear();
            N = Integer.parseInt(br.readLine());
            market = new int[N][];
            visit = new boolean[N];

            st = new StringTokenizer(br.readLine());
            q.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                market[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            }
            st = new StringTokenizer(br.readLine());
            dst = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            // end of Input
            if (bfs()) sb.append("happy").append("\n");
            else sb.append("sad").append("\n");
        }
        System.out.print(sb);
    }

    public static boolean bfs() {
        int[] curr;
        while (!q.isEmpty()) {
            curr = q.poll();
            if (distance(curr, dst) <= 1000) return true;
            for (int i = 0; i < N; i++) {
                if (visit[i] || distance(curr, market[i]) > 1000) continue;
                q.add(market[i]);
                visit[i] = true;
            }
        }
        return false;
    }

    public static int distance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }


}
