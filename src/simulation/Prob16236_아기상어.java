package simulation;

import java.io.*;
import java.util.*;

public class Prob16236_아기상어 {
    // 물고기 배열 (거리 저장)
    // 맵
    static int N, answer;
    static int[][] map;
    static boolean[][] visit;
    static PriorityQueue<Position> shorts;
    static Position shark;

    static class Position {
        int x, y, size, eatOrDist;

        Position(int x, int y, int size, int eatOrDist) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.eatOrDist = eatOrDist;
        }
    }

    static class myComp implements Comparator<Position>{
        @Override
        public int compare(Position o1, Position o2) {
            int res;
            if (o1.x != o2.x) res = o1.x - o2.x;
            else res = o1.y - o2.y;
            return res;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        shorts = new PriorityQueue<>(new myComp());
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9)
                    shark = new Position(i, j, 2, 0);
            }
        } // end of Input

        bfs();
        System.out.print(answer);

    }

    public static void bfs() {
        while (true) {
            shorts.clear();
            shortest(shark);
            if (shorts.size() == 0)
                break;
            Position shortone = shorts.poll();
            answer += shortone.eatOrDist;
            shark.eatOrDist++;
            if (shark.eatOrDist == shark.size) {
                shark.size++;
                shark.eatOrDist = 0;
            }
            map[shark.x][shark.y] = 0;
            map[shortone.x][shortone.y] = 9;
            shark.x = shortone.x;
            shark.y = shortone.y;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void shortest(Position shark) {
        int dist = 0, nx, ny, size;
        Queue<Position> q = new LinkedList<>();
        for (int i = 0; i < N; i++)
            Arrays.fill(visit[i], false);
        visit[shark.x][shark.y] = true;
        q.add(shark);

        while (!q.isEmpty()) {
            size = q.size();
            dist++;
            while (size-- > 0) {
                Position curr = q.poll();
                for (int i = 0; i < 4; i++) {
                    nx = curr.x + dx[i];
                    ny = curr.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny]) continue;
                    visit[nx][ny] = true;
                    if (map[nx][ny] != 0 && map[nx][ny] < curr.size) {
                        shorts.add(new Position(nx, ny, map[nx][ny], dist));
                    } else if (map[nx][ny] == 0 || (map[nx][ny] != 0 && map[nx][ny] == curr.size)) {
                        q.add(new Position(nx, ny, curr.size, dist));
                    }
                }
            }
            if (shorts.size() > 0) break;
        }
    }
}
