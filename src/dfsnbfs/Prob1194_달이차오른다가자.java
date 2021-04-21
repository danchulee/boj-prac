package dfsnbfs;

import java.io.*;
import java.util.*;

public class Prob1194_달이차오른다가자 {
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};
    static int N, M;
    static char[][] map;
    static int[] start;
    static HashSet<Dest> dest = new HashSet<>();
    static boolean[][][] visit;

    static class Dest {
        int r, c;

        Dest(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            Dest dest = (Dest) o;
            return r == dest.r && c == dest.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[65][N][M];

        String tmp;
        for (int i = 0; i < N; i++) {
            tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == '0')
                    start = new int[]{i, j};
                else if (map[i][j] == '1') dest.add(new Dest(i, j));
            }
        }
        System.out.print(maze());
    }

    public static int maze() {
        Queue<int[]> q = new LinkedList<>();

        visit[0][start[0]][start[1]] = true;
        q.offer(new int[]{start[0], start[1], 0});

        int moves = 0;
        int nr, nc, size, nkeys;
        int[] curr;
        while (!q.isEmpty()) {
            moves++;
            size = q.size();
            while (size-- > 0) {
                curr = q.poll();
                for (int i = 0; i < 4; i++) {
                    nr = curr[0] + dr[i];
                    nc = curr[1] + dc[i];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == '#' || visit[curr[2]][nr][nc]) continue;
                    if (dest.contains(new Dest(nr, nc))) return moves;
                    nkeys = curr[2];
                    if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
                        nkeys = (nkeys | (1 << map[nr][nc] - 'a'));
                    } else if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
                        if ((curr[2] & (1 << map[nr][nc] - 'A')) == 0) continue;
                    }
                    visit[curr[2]][nr][nc] = true;
                    q.offer(new int[]{nr, nc, nkeys});
                }
            }
        }
        return -1;
    }
}
