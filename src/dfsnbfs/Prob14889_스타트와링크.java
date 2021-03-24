import java.io.*;
import java.util.*;

public class Prob14889_스타트와링크 {
    static int N;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0, 0);
        System.out.print(answer);
    }

    public static void dfs(int num, int index, int visit) {
        if (num == N / 2) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if ((visit & (1 << i)) != 0) {
                    for (int j = i + 1; j < N; j++)
                        if ((visit & (1 << j)) != 0)
                            sum -= (map[i][j] + map[j][i]);
                } else {
                    for (int j = i + 1; j < N; j++) {
                        if ((visit & (1 << j)) == 0)
                            sum += (map[i][j] + map[j][i]);
                    }
                }
            }
            answer = Math.min(answer, Math.abs(sum));
            return;
        }
        if(index == N) return;
        dfs(num + 1, index + 1, visit | (1 << index));
        dfs(num, index + 1, visit);
    }
}
