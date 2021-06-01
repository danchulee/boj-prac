package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob1074_Z2 {
    static int r, c, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        System.out.print(dfs((int) Math.pow(2, N - 1), 0, 0, 0));
    }

    public static int dfs(int len, int now, int nr, int nc) {
        if (nr == r && nc == c) return now;
        else {
            if (r >= nr + len && c >= nc + len) { // 3
                return dfs(len / 2, now + len * len * 3, nr + len, nc + len);
            } else if (r < nr + len && c >= nc + len) { // 1
                return dfs(len / 2, now + len * len, nr, nc + len);
            } else if (r >= nr + len && c < nc + len) { // 2
                return dfs(len / 2, now + len * len * 2, nr + len, nc);
            } else { // 0
                return dfs(len / 2, now, nr, nc);
            }
        }
    }

}
