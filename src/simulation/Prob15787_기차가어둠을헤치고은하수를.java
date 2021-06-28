package simulation;

import java.io.*;
import java.util.*;

public class Prob15787_기차가어둠을헤치고은하수를 {
    static int N, M;
    static int[] trains;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trains = new int[N + 1];

        int order, i, x;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            order = Integer.parseInt(st.nextToken());
            i = Integer.parseInt(st.nextToken());
            switch (order) {
                case 1:
                    x = Integer.parseInt(st.nextToken()) - 1;
                    trains[i] |= (1 << x);
                    break;
                case 2:
                    x = Integer.parseInt(st.nextToken()) - 1;
                    trains[i] &= ~(1 << x); // x 자리 bit 0으로
                    break;
                case 3:
                    trains[i] <<= 1;
                    trains[i] &= ~(1 << 20);
                    break;
                case 4:
                    trains[i] >>= 1;
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for (int j = 1; j <= N; j++) set.add(trains[j]);
        System.out.print(set.size());
    }
}
