import java.io.*;
import java.util.*;

public class Prob1967_트리의지름 {
    static int N;
    static int[] cache;
    static ArrayList<Node>[] graph;
    static boolean[] visit;
    static int answer;

    static class Node {
        int link, dist;

        Node(int link, int dist) {
            this.link = link; // 연결 번호
            this.dist = dist; // 거리 수
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        int P, S, dist;
        int[] max = new int[2]; //0에 노드 번호, 1에 거리
        visit = new boolean[N];
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<Node>();
        cache = new int[N];
        cache[0] = 0;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            P = Integer.parseInt(st.nextToken()) - 1;
            S = Integer.parseInt(st.nextToken()) - 1;
            dist = Integer.parseInt(st.nextToken());
            graph[P].add(new Node(S, dist));
            graph[S].add(new Node(P, dist));
            cache[S] = cache[P] + dist;
            if (cache[S] > max[1]) { // 가장 먼 노드 찾는 과정 (1번 노드가 루트라고 가정)
                max[0] = S;
                max[1] = cache[S];
            }
        }

        visit[max[0]] = true;
        dfs(max[0], 0); // 가장 먼 노드로부터 dfs 출발
        System.out.print(answer);
    }

    public static void dfs(int point, int sum) {
        int cnt = 0;
        for (int i = 0; i < graph[point].size(); i++) {
            if (visit[graph[point].get(i).link]) { // 방문 완료인 곳
                cnt++;
                continue;
            }
            visit[graph[point].get(i).link] = true;
            // 현재 거리 + 노드사이 거리를 추가해서 매개변수로 보낸다.
            dfs(graph[point].get(i).link, sum + getDistance(point, graph[point].get(i).link));
            visit[graph[point].get(i).link] = false;
        }
        if (cnt == graph[point].size()) // graph[point].size()만큼 반복문에서 튕겨져 나왔다 = 더 이상 갈 곳이 없다. = 끝
            answer = Math.max(answer, sum);
    }

    public static int getDistance(int point, int i) {
        for (Node node : graph[point]) // 일치하는 노드 번호 찾아서 point, i 사이의 거리를 구하는 함
            if (node.link == i) return node.dist;
        return -1;
    }
}
