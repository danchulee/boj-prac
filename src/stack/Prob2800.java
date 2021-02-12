import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Prob2800 {
    static String expression;
    static Map<String, Integer> duplicate = new HashMap<>();
    static PriorityQueue<String> pq = new PriorityQueue<String>();
    static ArrayList<int[]> poses = new ArrayList<>();
    static int[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        expression = br.readLine();

        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                stack.add(i);
            } else if (expression.charAt(i) == ')') {
                poses.add(new int[]{stack.pop(), i});
            }
        }

        visit = new int[poses.size()];
        sets(0);
        while (!pq.isEmpty())
            sb.append(pq.poll()).append("\n");
        System.out.println(sb);
    }

    public static void sets(int pos) {
        if (pos == poses.size()) {
            //visit 처리해서 pq에 넣기
            StringBuilder newStr = new StringBuilder(expression);
            for (int i = 0; i < poses.size(); i++) {
                if (visit[i] == 1) {
                    newStr.setCharAt(poses.get(i)[0], 'X');
                    newStr.setCharAt(poses.get(i)[1], 'X');
                }
            }
            String tmp = newStr.toString().replaceAll("X", "");
            if (duplicate.containsKey(tmp)) return;
            else duplicate.put(tmp, 1);
            if (!tmp.equals(expression))
                pq.offer(tmp);
            return;
        }
        visit[pos] = 0;
        sets(pos + 1);
        visit[pos] = 1;
        sets(pos + 1);

    }
}
