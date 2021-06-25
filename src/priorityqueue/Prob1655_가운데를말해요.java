package priorityqueue;

import java.io.*;
import java.util.*;

public class Prob1655_가운데를말해요 {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> left = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        PriorityQueue<Integer> right = new PriorityQueue<>();
        N = Integer.parseInt(br.readLine());

        int lSize, rSize, l = 0, r = 0;
        for (int i = 1; i <= N; i++) {
            lSize = left.size();
            rSize = right.size();
            if (lSize <= rSize) {
                left.offer(Integer.parseInt(br.readLine()));
                lSize++;
            } else {
                right.offer(Integer.parseInt(br.readLine()));
                rSize++;
            }
            if (!left.isEmpty()) l = left.peek();
            if (!right.isEmpty()) r = right.peek();
            if (lSize > rSize) sb.append(l).append('\n');
            else if (lSize == rSize)
                if (l < r) sb.append(l).append('\n');
                else sb.append(r).append('\n');
        }
        System.out.print(sb);
    }
}
