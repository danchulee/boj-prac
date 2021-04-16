package graph;

import java.io.*;
import java.util.*;

public class Prob17471_게리맨더링 {
    static int N, answer = Integer.MAX_VALUE;
    static int[] population;
    static int[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        graph = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            population[i] = Integer.parseInt(st.nextToken());

        int area;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            while (st.hasMoreTokens()) {
                area = Integer.parseInt(st.nextToken());
                graph[i][area] = 1;
            }
        }
        go(1, 0);
        if (answer == Integer.MAX_VALUE)
            System.out.print(-1);
        else System.out.print(answer);
    }

    public static boolean go(int num, int divided) {
        if (num == N + 1) {
            int res = bothConnect(divided);
            if (res != -1) {
                answer = Math.min(answer, res);
                return res == 0;
            }
            return false;
        }
        if (go(num + 1, divided | (1 << num))) return true;
        if (go(num + 1, divided)) return true;

        return false;
    }

    public static int bothConnect(int divided) {
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();
        int resA, resB;
        for (int i = 1; i <= N; i++)
            if ((divided & (1 << i)) != 0)
                A.add(i);
            else B.add(i);
        if (A.isEmpty() || B.isEmpty())
            return -1;
        if ((resA = connect(A)) == -1 || (resB = connect(B)) == -1)
            return -1;
        return Math.abs(resA - resB);
    }

    public static int connect(ArrayList<Integer> team) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[N + 1];
        q.offer(team.get(0));

        int curr, res = 0, count = 0, size = team.size();
        while (!q.isEmpty()) {
            curr = q.poll();
            if (visit[curr])
                continue;
            visit[curr] = true;
            res += population[curr];
            for (int i = 1; i <= N; i++)
                if (graph[curr][i] == 1 && !visit[i] && team.contains(i))
                    q.offer(i);
            if (++count == size)
                return res;
        }
        return -1;
    }
}
