import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob15649_N과M1 {
    static int N, M;
    static int[] arr;
    static int[] visit;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        visit = new int[N + 1];

        recursion(1, 0);

        System.out.println(sb);
    }

    public static void recursion(int number, int count) {
        if (count == M) {
            for (int x : arr)
                sb.append(x).append(" ");
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if(visit[i] == 1) continue;
            arr[count] = i;
            visit[i] = 1;
            recursion(i + 1, count + 1);
            visit[i] = 0;
        }
    }
}
