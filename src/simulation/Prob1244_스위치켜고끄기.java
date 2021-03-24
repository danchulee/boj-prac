import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob1244_스위치켜고끄기 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int students_N;
	static int[] switches;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		switches = new int[N];

		int i = 0;
		for (String st : br.readLine().split(" "))
			switches[i++] = Integer.parseInt(st);

		students_N = Integer.parseInt(br.readLine());
		for (i = 0; i < students_N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (sex == 1) {
				for (int j = num; j <= N; j += num)
					switches[j - 1] = Math.abs(1 - switches[j - 1]);
			} else {
				switches[num - 1] = Math.abs(1 - switches[num - 1]);
				for (int j = 1; num + j <= N && num - j > 0; j++) {
					int l = num - j;
					int r = num + j;
					if (switches[l - 1] != switches[r - 1])
						break;
					switches[l - 1] = switches[r - 1] = Math.abs(1 - switches[l - 1]);
				}
			}
		}
		for (i = 0; i < switches.length; i++) {
			sb.append(switches[i]).append(" ");
			if ((i + 1) % 20 == 0)
				sb.append("\n");
		}
		System.out.println(sb);

	} // end of Main

} // end of Class
