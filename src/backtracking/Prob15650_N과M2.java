package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob15650_N과M2 {
    static int N, M;
    static int[] visit;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new int[N + 1];

        recursion(0, 1);

        System.out.print(sb);
    }

    public static void recursion(int cnt, int num) {
        if (cnt == M) {
            for (int i = 1; i <= N; i++) {
                if (visit[i] == 1)
                    sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = num; i <= N; i++) {
            visit[i] = 1;
            recursion(cnt + 1, i + 1);
            visit[i] = 0;
        }
    }
}
