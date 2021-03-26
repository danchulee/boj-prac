import java.io.*;
import java.util.*;

public class Prob17472_다리만들기2 {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int cnt;
    static ArrayList<Node>[] graph;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));

    static class Node {
        int vertex, dist;

        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] == 1 && !visit[i][j]) numberLand(++cnt, i, j);

        graph = new ArrayList[cnt];
        for (int i = 0; i < cnt; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] != 0) makeGraph(i, j, map[i][j]);

        System.out.print(prim());
    }

    public static int prim() {
        int[] curr;
        boolean[] visit = new boolean[cnt];
        pq.offer(new int[]{0, 0});

        int answer = 0, turn = 0;
        while (!pq.isEmpty()) {
            curr = pq.poll();
            if (visit[curr[0]])
                continue;
            visit[curr[0]] = true;
            answer += curr[1];
            for (Node node : graph[curr[0]]) {
                if (!visit[node.vertex]) pq.add(new int[]{node.vertex, node.dist});
            }
            if (++turn == cnt) return answer;
        }
        return -1;
    }


    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void numberLand(int number, int x, int y) {
        int nx, ny;
        visit[x][y] = true;
        map[x][y] = number;

        Queue<int[]> q = new LinkedList<>();
        int[] curr;

        q.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            curr = q.poll();
            for (int i = 0; i < 4; i++) {
                nx = curr[0] + dx[i];
                ny = curr[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny] || map[nx][ny] == 0)
                    continue;
                visit[nx][ny] = true;
                map[nx][ny] = number;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    public static void makeGraph(int x, int y, int number) {
        int nx, ny, cnt;
        for (int i = 0; i < 4; i++) {
            cnt = -1;
            nx = x;
            ny = y;
            while (++cnt >= 0) {
                nx += dx[i];
                ny += dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == number) break;
                if (map[nx][ny] != 0 && map[nx][ny] != number) {
                    if (cnt > 1)
                        graph[number - 1].add(new Node(map[nx][ny] - 1, cnt));
                    break;
                }
            }
        }
    }
}
