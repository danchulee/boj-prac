package queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Prob1966_프린터큐 {
    static int N, M, num, target;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int TC = 0; TC < T; TC++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            Queue<Integer> q = new LinkedList<Integer>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());


            int tmp = st.countTokens();
            for (int i = 0; i < tmp; i++) {
                int token = Integer.parseInt(st.nextToken());
                if (i == M) {
                    target = token;
                    num = i;
                }
                pq.offer(token);
                q.offer(token);
            }
            int out, count = 0;
            while (true) {
                out = q.poll();
                num--;
                if (pq.peek() != out) {
                    q.offer(out);
                    if (num == -1)
                        num = q.size() - 1;
                } else {
                    pq.poll();
                    count++;
                    if (num == -1 && out == target)
                        break;
                }
            }
            sb.append(count).append("\n");
        } // end of case
        System.out.println(sb);
    } // end of main
} // end of class
