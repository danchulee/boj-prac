import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob1260 {
    static int N, M, V;
    static boolean[] visit;
    static int[][] graph;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = 1;
        } // end of Input
        visit = new boolean[N + 1];
        dfs(V);
        sb.append("\n");

        visit = new boolean[N + 1];
        bfs(V);
        System.out.print(sb);
    } // end of Main

    public static void dfs(int point) {
        sb.append(point).append(" ");
        visit[point] = true;
        for (int i = 1; i <= N; i++)
            if (!visit[i] && graph[point][i] == 1)
                dfs(i);
    }

    public static void bfs(int startPoint) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(startPoint);
        visit[startPoint] = true;
        while (!q.isEmpty()) {
            int curr = q.poll();
            sb.append(curr).append(" ");
            for (int i = 1; i <= N; i++) {
                if (!visit[i] && graph[curr][i] == 1) {
                    visit[i] = true;
                    q.offer(i);
                }
            }
        }
    }
} // end of Class

