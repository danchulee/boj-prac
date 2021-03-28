package dynamicprogramming;

import java.io.*;
import java.util.*;

/*
10
1 1
1 1
1 1
1 1
6 100
1 1
1 1
1 1
1 1
1 1
 */

public class Prob15486_퇴사2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[][] days = new int[N + 1][]; // 0 : 기간 1 : 금액
        days[0] = new int[]{0, 0};
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            days[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        int answer = 0;
        int[] DP = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (i + 1 <= N) // 일 하기 전 상태 다음 날에 저장해둠
                DP[i + 1] = Math.max(DP[i + 1], DP[i]);
            if (i + days[i][0] - 1 <= N) { // 오늘 일 기간안에 끝낼 수 있는지
                DP[i] = Math.max(DP[i], days[i][1] + DP[i]);
                if (i + days[i][0] <= N) // 다음 날 일 가능한지
                    DP[i + days[i][0]] = Math.max(DP[i + days[i][0]], DP[i]);
            }
            answer = Math.max(answer, DP[i]);
        }
        System.out.println(answer);
    }


}
