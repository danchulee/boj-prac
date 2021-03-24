package dfsnbfs;

import java.io.*;
import java.util.*;

/**
 * 부분집합으로 해서 푸는 방법도 있다.
 * 부분집합으로 푸는 게 더 빠르다.
 * 배열은 일차원 배열 여러개로 사용하는 것이 조금 더 객체지향적이다.
 * 가지치기가 없으면 재귀는 손해다.
 *
 */
public class Prob2961_도영이가만든맛있는음식 {
    static int N;
    static int answer = Integer.MAX_VALUE;
    static int[][] tastes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        tastes = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            tastes[i][0] = Integer.parseInt(st.nextToken());
            tastes[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            select(i, 0, 0, 1, 0);
        }
        System.out.print(answer);
    }

    public static void select(int using, int cnt, int index, int S, int B) {
        // 부분집합 이옹하면 매개변수 index, sVal, bVal로만 해서 구현할 수 있다.
        if (cnt == using) {
            answer = Math.min(answer, Math.abs(S - B));
            return;
        }
        for (int i = index; i < N; i++)
            select(using, cnt + 1, i + 1, S * tastes[i][0], B + tastes[i][1]);
    }

}
