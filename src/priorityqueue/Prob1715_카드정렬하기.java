package priorityqueue;

import java.io.*;
import java.util.*;

public class Prob1715_카드정렬하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++)
            pq.add(Integer.parseInt(br.readLine()));
        int result, answer = 0;
        while (pq.size() > 1) {
            result = pq.poll() + pq.poll();
            answer += result;
            pq.offer(result);
        }
        System.out.print(answer);
    }
}
