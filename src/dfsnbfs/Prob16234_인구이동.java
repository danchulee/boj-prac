package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob16234_인구이동 {
    static int N, L, R, moves;
    static int[][] map;
    static Queue<int[]> q = new LinkedList<>();
    static int[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visit = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        } // end of Input
        findArea();
        System.out.print(moves);
    }

    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};


    // for문으로 맵 싹 한 번 순회하면서 q에 넣는 거 잊지 말기
    public static void findArea() {
        int nx, ny, value, cnt;
        ArrayList<Integer> div = new ArrayList<>();
        do {
            cnt = 1;
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    for (int k = 0; k < 4; k++) {
                        nx = i + dx[k];
                        ny = j + dy[k];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny] != 0) continue;
                        value = Math.abs(map[i][j] - map[nx][ny]);
                        if (value >= L && value <= R) {
                            div.add(bfs(i, j, cnt++));
                        }
                    }
            // map 에 인구 덮어씌우는 과정
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (visit[i][j] != 0)
                        map[i][j] = div.get(visit[i][j] - 1);
            div.clear();
            for (int i = 0; i < N; i++)
                Arrays.fill(visit[i], 0);

            if (cnt != 1)
                moves++;
        } while (cnt != 1);
    }

    public static int bfs(int x, int y, int point) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visit[x][y] = point;

        int[] pos;
        int nx, ny, value, sum = map[x][y], cnt = 1;
        while (!q.isEmpty()) {
            pos = q.poll();
            for (int i = 0; i < 4; i++) {
                nx = pos[0] + dx[i];
                ny = pos[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny] != 0) continue;
                value = Math.abs(map[pos[0]][pos[1]] - map[nx][ny]);
                if (value >= L && value <= R) {
                    visit[nx][ny] = point;
                    q.offer(new int[]{nx, ny});
                    sum += map[nx][ny];
                    cnt++;
                }
            }
        }
        return sum / cnt;
    }
}
