package priorityqueue;

import java.io.*;
import java.util.*;

/*
위상정렬
1. 각 노드들의 진입차수 계산
2. 진입 차수가 0인 노드들을 큐에 삽입
3. 큐에서 노드를 꺼내 연결된 간선 제거
4. 제거로 인해 진입 차수가 0된 노드 큐에 삽입
5.  3-4번 반복
 */

public class Prob1766_문제집 {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        cnt = new int[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        int a, b;
        for (int i = 0; i < M; i++) { // 쉬운 거 -> 어려운 거 그래프
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            graph[a].add(b);
            cnt[b]++;
        }
        StringBuilder res = new StringBuilder();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int curr;
        for (int i = 0; i < N; i++)
            if (cnt[i] == 0) q.offer(i);
        while (!q.isEmpty()) {
            curr = q.poll();
            res.append(curr + 1).append(' ');
            for (int x : graph[curr])
                if (--cnt[x] == 0) q.offer(x);
        }
        System.out.print(res);
    }


}
