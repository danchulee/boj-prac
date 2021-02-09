import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Prob15663 {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static ArrayList<Integer> numbers = new ArrayList<>();
    static HashMap<String, Boolean> dones = new HashMap<>();
    static int[] visit, picked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new int[N];
        picked = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            numbers.add(Integer.parseInt(st.nextToken()));

        Collections.sort(numbers);

        recursion(0);

        System.out.print(sb);
    }

    public static void recursion(int count) {
        if (count == M) {
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < M; i++)
                tmp.append(picked[i]).append(" ");
            if(dones.containsKey(tmp.toString())) return;
            else dones.put(tmp.toString(), true);
            sb.append(tmp).append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visit[i] == 1) continue;
            visit[i] = 1;
            picked[count] = numbers.get(i);
            recursion(count + 1);
            visit[i] = 0;
        }
    }
}
