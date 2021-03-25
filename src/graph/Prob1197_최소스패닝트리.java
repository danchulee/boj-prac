package graph;

import java.io.*;
import java.util.*;

public class Prob1197_최소스패닝트리 {
    static int V, E, answer;
    static ArrayList<Node>[] graph;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[1] - o2[1]));
    // minEdge 관리
    static boolean[] visit;

    static class Node {
        int vertex, distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[V];
        visit = new boolean[V];
        for (int i = 0; i < V; i++) graph[i] = new ArrayList();

        int from, to, dist;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken()) - 1;
            to = Integer.parseInt(st.nextToken()) - 1;
            dist = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, dist));
            graph[to].add(new Node(from, dist));
        }

        pq.add(new int[]{0, 0});

        int cnt = 0;
        int[] min;
        while (!pq.isEmpty()) {
            min = pq.poll();
            if(visit[min[0]]) // 이거 안 해줬더니 한 번 틀림
                continue;
            visit[min[0]] = true;
            answer += min[1];
            for (int i = 0; i < graph[min[0]].size(); i++)
                if (!visit[graph[min[0]].get(i).vertex])
                    pq.add(new int[]{graph[min[0]].get(i).vertex, graph[min[0]].get(i).distance});
            if (++cnt == V)
                break; // 이거 안 해주면 중복 계산됨

        }
        System.out.print(answer);
    }
}
