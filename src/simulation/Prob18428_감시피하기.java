package simulation;

import java.io.*;
import java.util.*;

public class Prob18428_감시피하기 {
    static int N, size, tSize;
    static char[][] map;
    static ArrayList<int[]> blank = new ArrayList<>();
    static ArrayList<int[]> teachers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        char tmp;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                tmp = st.nextToken().charAt(0);
                map[i][j] = tmp;
                if (tmp == 'T') teachers.add(new int[]{i, j});
                else if (tmp == 'X') blank.add(new int[]{i, j});
            }
        }
        size = blank.size();
        tSize = teachers.size();

        System.out.print(buildWall(0, 0, new int[]{-1, -1, -1}) ? "YES" : "NO");
    }

    public static boolean buildWall(int index, int cnt, int[] picked) {
        if (cnt == 3) {
            boolean fail = true;
            setWall(picked, 'O');
            for (int i = 0; i < tSize; i++)
                if ((fail = findStud(teachers.get(i)))) break;
            setWall(picked, 'X');
            return !fail;
        }
        for (int i = index; i < size; i++) {
            picked[cnt] = i;
            if (buildWall(i + 1, cnt + 1, picked)) return true;
        }
        return false;
    }

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static boolean findStud(int[] pos) {
        int nr, nc, r = pos[0], c = pos[1];
        for (int i = 0; i < 4; i++) {
            nr = r;
            nc = c;
            while (true) {
                nr += dr[i];
                nc += dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 'O') break;
                if (map[nr][nc] == 'S') return true;
            }
        }
        return false;
    }

    public static void setWall(int[] picked, char ch) {
        int[] curr;
        for (int i = 0; i < 3; i++) {
            curr = blank.get(picked[i]);
            map[curr[0]][curr[1]] = ch;
        }
    }
}
