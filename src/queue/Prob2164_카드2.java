package queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Prob2164_카드2 {
    static Queue<Integer> q = new LinkedList<Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++)
            q.offer(i);

        while (q.size() != 1) {
            q.poll();
            if(q.size() == 1) break;
            q.offer(q.poll());
        }

        System.out.println(q.poll());
    }
}
