package simulation;

import java.io.*;
import java.util.*;

public class Prob2999_비밀이메일 {
    static int R, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        for (int i = (int) Math.sqrt(input.length()); i >= 0; i--) {
            if (input.length() % i == 0) {
                R = i;
                break;
            }
        }
        C = input.length() / R;

        int cnt = 0;
        char[][] map = new char[R][C];
        for (int i = 0; i < C; i++)
            for (int j = 0; j < R; j++)
                map[j][i] = input.charAt(cnt++);
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                sb.append(map[i][j]);

        System.out.print(sb);
    }

}
