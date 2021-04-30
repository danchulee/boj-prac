package simulation;

import java.io.*;
import java.util.*;

public class Prob21608_상어초등학교 {
    static int N, answer;
    static int[] seq;
    static HashSet<Integer>[] like;
    static int[][] map;
    static final int[] dr = {0, 0, -1, 1};
    static final int[] dc = {-1, 1, 0, 0};
    static final int[] score = {0, 1, 10, 100, 1000};
    static PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> {
        if (o1.like != o2.like) return o2.like - o1.like;
        else if (o1.blank != o2.blank) return o2.blank - o1.blank;
        else if (o1.r != o2.r) return o1.r - o2.r;
        else return o1.c - o2.c;
    });

    static class Point {
        int r, c, blank, like;

        Point(int r, int c, int[] blankAndLike) {
            this.r = r;
            this.c = c;
            this.blank = blankAndLike[0];
            this.like = blankAndLike[1];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int num, len = N * N;
        seq = new int[len];
        like = new HashSet[len + 1];
        for (int i = 1; i <= len; i++)
            like[i] = new HashSet<>();
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            seq[i] = num;
            for (int j = 0; j < 4; j++)
                like[num].add(Integer.parseInt(st.nextToken()));
        }
        int student;
        Point best;
        for (int i = 0; i < len; i++) {
            pq.clear();
            student = seq[i];
            for (int r = 0; r < N; r++)
                for (int c = 0; c < N; c++)
                    if (map[r][c] == 0)
                        pq.offer(new Point(r, c, blankAndLike(student, r, c)));
            best = pq.poll();
            map[best.r][best.c] = student;
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                answer += score[blankAndLike(map[i][j], i, j)[1]];
        System.out.print(answer);
    }


    public static int[] blankAndLike(int num, int r, int c) {
        int nr, nc, blanks = 0, likes = 0;
        for (int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if (nr >= 0 && nc >= 0 && nr < N && nc < N)
                if (map[nr][nc] > 0 && like[num].contains(map[nr][nc]))
                    likes++;
                else if (map[nr][nc] == 0) blanks++;
        }
        return new int[]{blanks, likes};
    }
}
