package simulation;
import java.io.*;
import java.util.*;

public class Prob2564_경비원 {
    static int N, M;
    static int[] dong;
    static int[][] store;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int r = 0, c = 0, answer = 0;
        int size = Integer.parseInt(br.readLine());
        store = new int[size][];
        for (int i = 0; i <= size; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            switch (dir) {
                case 1:
                    r = 0;
                    c = dist;
                    break;
                case 2:
                    r = N;
                    c = dist;
                    break;
                case 3:
                    r = dist;
                    c = 0;
                    break;
                case 4:
                    r = dist;
                    c = M;
            }
            if (i == size)
                dong = new int[]{r, c};
            else store[i] = new int[]{r, c};
        }
        for (int i = 0; i < size; i++)
            answer += minDist(i);
        System.out.print(answer);
    }

    public static int minDist(int index) {
        int calc;
        int allLen = 2 * (N + M);
        if ((dong[0] == 0 && store[index][0] == N) || (dong[0] == N && store[index][0] == 0)) {
            calc = (dong[1] + store[index][1] + N);
        } else if ((dong[1] == 0 && store[index][1] == M) || (dong[1] == M && store[index][1] == 0)) {
            calc = (dong[0] + store[index][0] + M);
        } else {
            calc = (Math.abs(dong[0] - store[index][0]) + Math.abs(dong[1] - store[index][1]));
        }
        return Math.min(calc, allLen - calc);
    }
}

