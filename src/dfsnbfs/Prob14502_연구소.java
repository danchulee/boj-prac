package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob14502_연구소 {
    static int N, M, answer;
    static int[][] map;
    static int[][] currMap;
    static ArrayList<int[]> viruses = new ArrayList<>();
    static ArrayList<int[]> blanks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        currMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) viruses.add(new int[]{i, j});
                else if (map[i][j] == 0) blanks.add(new int[]{i, j});
            }
        }
        buildWall(0, 0);

        System.out.print(answer);
    }

    public static void buildWall(int walls, int index) {
        if (walls == 3) {
            answer = Math.max(answer, spread());
            return;
        }

        int[] curr;
        for (int i = index; i < blanks.size(); i++) {
            curr = blanks.get(i);
            map[curr[0]][curr[1]] = 1;
            buildWall(walls + 1, i + 1);
            map[curr[0]][curr[1]] = 0;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<int[]> q = new LinkedList<>();

    public static int spread() {
        q.clear();
        for (int i = 0; i < N; i++)
            System.arraycopy(map[i], 0, currMap[i], 0, M);

        for (int[] virus : viruses)
            q.add(virus);

        int res = blanks.size() - 3;
        int nx, ny;
        int[] virus;
        while (!q.isEmpty()) {
            virus = q.poll();
            for (int i = 0; i < 4; i++) {
                nx = virus[0] + dx[i];
                ny = virus[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || currMap[nx][ny] != 0) continue;
                res--;
                currMap[nx][ny] = 2;
                q.offer(new int[]{nx, ny});
            }
        }
        return res;
    }
}
