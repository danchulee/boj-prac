package simulation;

import java.io.*;
import java.util.*;

public class Prob14891_톱니바퀴 {
    public static int[][] wheels = new int[4][8];
    public static int[][] moves;
    public static boolean[] visit = new boolean[4];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < 8; j++)
                wheels[i][j] = tmp.charAt(j) - '0';
        }
        int N = Integer.parseInt(br.readLine());
        moves = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            moves[i][0] = Integer.parseInt(st.nextToken()) - 1;
            moves[i][1] = Integer.parseInt(st.nextToken());
            Arrays.fill(visit, false);
            for (int j = 0; j < moves[i][0]; j++)
                if (wheels[j][2] == wheels[j + 1][6]) visit[j] = true;
            for (int j = 3; j > moves[i][0]; j--)
                if (wheels[j][6] == wheels[j - 1][2]) visit[j] = true;

            dfs(moves[i][0], moves[i][1]);
        } // end of input
        int answer = 0;
        for (int i = 0; i < 4; i++)
            if (wheels[i][0] == 1)
                answer += (int) Math.pow(2, i);
        System.out.print(answer);

    }


    public static void dfs(int num, int dir) { // 1시계방향 -1반시계방
        if (visit[num]) return;

        visit[num] = true;
        if (dir == 1) clock(num);
        else nonClock(num);

        if (num > 0)
            dfs(num - 1, -dir);
        if (num < 3)
            dfs(num + 1, -dir);
    }

    public static void clock(int num) {
        int tmp = wheels[num][7];
        for (int i = 7; i > 0; i--)
            wheels[num][i] = wheels[num][i - 1];
        wheels[num][0] = tmp;
    }

    public static void nonClock(int num) {
        int tmp = wheels[num][0];
        for (int i = 1; i < 8; i++)
            wheels[num][i - 1] = wheels[num][i];
        wheels[num][7] = tmp;
    }
}
