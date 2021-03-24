import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Prob2493_탑1 {
	static Stack<Integer> towers = new Stack<>();
	static Stack<Integer> pos = new Stack<>();
	static int N;
	static int[] ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());

			while (!towers.empty() && towers.peek() < tmp) {
				towers.pop();
				pos.pop();
			}
			if (towers.empty())
				sb.append("0 ");
			else
				sb.append(pos.peek()).append(" ");
			towers.add(tmp);
			pos.add(i + 1);
		}
		System.out.println(sb);

	} // end of Main
} // end of Class
