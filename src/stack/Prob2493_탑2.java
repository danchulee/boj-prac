import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Prob2493_탑2 {
	static Stack<int[]> towers = new Stack<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int tower = Integer.parseInt(st.nextToken());
			while (!towers.empty() && towers.peek()[0] < tower)
				towers.pop();
			if (towers.empty())
				sb.append("0 ");
			else
				sb.append(towers.peek()[1]).append(" ");
			towers.push(new int[] { tower, i + 1 });
		}
		System.out.println(sb);

	} // end of Main
} // end of Class
