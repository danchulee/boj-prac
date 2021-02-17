import java.io.*;
import java.util.*;

public class Prob1167_트리의지름 {
    static int V;
    static int[][] graph;
    static boolean[] visit;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        graph = new int[V][V];
        visit = new boolean[V];

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken(); // 무시
            int count = (st.countTokens() - 1) / 2;
            for (int j = 0; j < count; j++)
                graph[i][Integer.parseInt(st.nextToken()) - 1] = Integer.parseInt(st.nextToken());
        } // end of Input
        for (int i = 0; i < V; i++) {
            visit[i] = true;
            dfs(i, 0);
            visit[i] = false;
        }
        System.out.print(answer);
    }

    public static void dfs(int point, int sum) {
        int cnt = 0;
        for (int i = 0; i < V; i++) {
            if (visit[i] || (!visit[i] && graph[point][i] == 0)) {
                cnt++;
                continue;
            }
            visit[i] = true;
            dfs(i, sum + graph[point][i]);
            visit[i] = false;
        }
        if (cnt == V)
            answer = Math.max(answer, sum);
    }
}
