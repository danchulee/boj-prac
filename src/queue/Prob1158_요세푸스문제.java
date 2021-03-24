package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob1158_요세푸스문제 {
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("<");
        StringTokenizer st = new StringTokenizer(br.readLine());

        Queue<Integer> people = new LinkedList<Integer>();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++)
            people.offer(i);
        while (!people.isEmpty()) {
            int cnt = 0;
            while (cnt++ != K - 1) {
                people.offer(people.poll());
            }
            sb.append(people.poll()).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(">");

        System.out.print(sb);
    }
}
