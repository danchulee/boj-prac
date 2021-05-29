package greedy;

import java.io.*;
import java.util.*;

public class Prob11497_통나무건너뛰기 {
    static int answer, N;
    static int[] num;
    static Deque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            answer = 0;
            deque.clear();

            N = Integer.parseInt(br.readLine());
            num = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                num[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(num);
            for (int i = 0; i < N; i++) {
                if (i % 2 == 0)
                    deque.addFirst(num[i]);
                else deque.addLast(num[i]);
            }
            int out, cmp = deque.peekLast();
            while (!deque.isEmpty()) {
                out = deque.pollFirst();
                answer = Math.max(answer, Math.abs(cmp - out));
                cmp = out;
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

}
