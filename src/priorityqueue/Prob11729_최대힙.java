package priorityqueue;

import java.io.*;
import java.util.*;

public class Prob11729_최대힙 {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        N = Integer.parseInt(br.readLine());

        int input;
        for (int i = 0; i < N; i++) {
            input = Integer.parseInt(br.readLine());
            if (input == 0) sb.append(pq.isEmpty() ? 0 : pq.poll()).append('\n');
            else pq.offer(input);
        }
        System.out.print(sb);
    }
}
