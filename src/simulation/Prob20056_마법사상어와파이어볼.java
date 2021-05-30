package simulation;

import java.io.*;
import java.util.*;

public class Prob20056_마법사상어와파이어볼 {
    static int N, M, K;
    static ArrayList<Ball>[][] map;
    static ArrayList<Ball>[][] newmap;

    static int[] dr, dc;

    static class Ball {
        int m, s, d;

        Ball(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        newmap = new ArrayList[N][N];
        dr = new int[]{N - 1, N - 1, 0, 1, 1, 1, 0, N - 1};
        dc = new int[]{0, 1, 1, 1, 0, N - 1, N - 1, N - 1};
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList();
                newmap[i][j] = new ArrayList<>();
            }

        int r, c, m, s, d;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            map[r][c].add(new Ball(m, s, d));
        }
        for (int i = 0; i < K; i++)
            move();
        int ans = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (Ball x : map[i][j])
                    ans += x.m;
        System.out.print(ans);
    }

    public static void move() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                newmap[i][j].clear();
        int nr, nc;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (Ball x : map[i][j]) {
                    nr = (i + (dr[x.d] * x.s)) % N;
                    nc = (j + (dc[x.d] * x.s)) % N;
                    newmap[nr][nc].add(new Ball(x.m, x.s, x.d));
                }
            }
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                map[i][j].clear();
                map[i][j].addAll(newmap[i][j]);
            }
        divide();
    }

    public static void divide() {
        int len, msum, dsum, ssum, dtype;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((len = map[i][j].size()) > 1) {
                    msum = ssum = dsum = 0;
                    dtype = map[i][j].get(0).d % 2;
                    for (Ball x : map[i][j]) {
                        msum += x.m;
                        ssum += x.s;
                        if (dtype != x.d % 2) dsum = 1;
                    }
                    map[i][j].clear();
                    ssum /= len;
                    msum /= 5;
                    dsum %= 2;
                    if (msum != 0)
                        for (int k = 0; k < 4; k++)
                            map[i][j].add(new Ball(msum, ssum, k * 2 + dsum));
                }
            }
        }
    }
}
