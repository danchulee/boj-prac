package graph;

import java.io.*;
import java.util.*;

// 오르막길-내리막길 정해져있음 -> 방향이 정해져있는 그래프이다.
// 입구 정해져있음 -> 다익스트라 0번부터 시작
// 피로도는 최초 진입 기준이다. (돌아오는 길은 생각 안 하는 뜻임 - 그니까 그냥 일반적으로 풀면 된다.)
// 최악 최선 구해서 차이 구하기 (한 시작점 기준 모든 경우의 수)
// 우선순위 큐를 두 개 만드는데 각각 다른 comparator를 가진다.
// 하나는 오르막길 순, 하나는 내리막길 순 해서 두 번 다익스트라를 돌린다.

public class Prob13418_학교탐방하기_prim {
    static int[] K = new int[2];
    static int N, M;
    static ArrayList<Node>[] graph;
    static PriorityQueue<int[]> pq;

    static class Node {
        int vertex;
        int upDown; // 0 오르막길 1 내리막길

        Node(int vertex, int upDown) {
            this.vertex = vertex;
            this.upDown = upDown;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        int A, B, C;
        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }
        pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        dijkstra(0);

        pq.clear();
        pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        dijkstra(1);

        System.out.print(K[0] - K[1]);

    }

    public static void dijkstra(int sel) {
        boolean[] visit = new boolean[N + 1];

        int res = 0, cnt = 0;
        int[] curr;

        pq.offer(new int[]{0, -1});
        while (!pq.isEmpty()) {
            curr = pq.poll();
            if (visit[curr[0]])
                continue;
            if (curr[1] == 0)
                res++;
            visit[curr[0]] = true;
            for (Node node : graph[curr[0]])
                if (!visit[node.vertex])
                    pq.offer(new int[]{node.vertex, node.upDown});
            if (++cnt == N + 1) break;
        }
        K[sel] = res * res;
    }
}
