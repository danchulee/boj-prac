package greedy;

import java.io.*;
import java.util.*;

public class Prob1202_보석도둑 {
    static int N, K;
    static PriorityQueue<int[]> jewels = new PriorityQueue<>((o1, o2) -> { // 비싼 거 순, 그리고 가벼운 거 순
        if (o1[1] != o2[1]) return o2[1] - o1[1];
        return o1[0] - o2[0];
    });
    static int[] bags;
    static int[][] tmpJewels;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bags = new int[K];
        tmpJewels = new int[N][];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            tmpJewels[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        for (int i = 0; i < K; i++) bags[i] = Integer.parseInt(br.readLine());
        Arrays.sort(tmpJewels, ((o1, o2) -> {
            return o1[0] - o2[0];
        }));
        Arrays.sort(bags);

        long ans = 0;
        int lastIndex = 0;
        for (int i = 0; i < K; i++) {
            for (; lastIndex < N; lastIndex++)
                if (tmpJewels[lastIndex][0] <= bags[i]) jewels.offer(tmpJewels[lastIndex]);
                else break;
            if (!jewels.isEmpty())
                ans += jewels.poll()[1];
        }
        System.out.print(ans);
        // end of Input
    } // end of Main
} // end of Class
