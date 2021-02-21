package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Prob15787_기차가어둠_미완성 {
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<String, Integer> pass = new HashMap<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] trains = new int[N][20];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int select = Integer.parseInt(st.nextToken());
            int train = Integer.parseInt(st.nextToken()) - 1;
            switch (select) {
                case 1:
                case 2:
                    int seat = Integer.parseInt(st.nextToken()) - 1;
                    trains[train][seat] = 2 - select;
                    break;
                case 3:
                    for (int j = 0; j < 19; j++)
                        trains[train][j + 1] = trains[train][j];
                    break;
                case 4:
                    for (int j = 0; j < 19; j++)
                        trains[train][j] = trains[train][j + 1];
            }
        }
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j : trains[i])
                sb.append(j);
            String tmp = sb.toString();
            if (!pass.containsKey(tmp) && tmp.contains("1"))
                pass.put(tmp, 1);
        }
        System.out.print(pass.size());
    }
}
