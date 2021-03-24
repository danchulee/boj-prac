package dfsnbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob17478_재귀함수가뭔가요 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static String question = "\"재귀함수가 뭔가요?\"\n";
	static String talk1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
	static String talk2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
	static String talk3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
	static String line = "____";
	static String answered = "라고 답변하였지.\n";

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		reply(0);
		System.out.println(sb);

	}

	private static void reply(int num) {
		String lines = new String("");
		for (int i = 0; i < num; i++)
			lines += line;
		sb.append(lines).append(question);
		if (num == N) {
			sb.append(lines).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n").append(lines).append(answered);
			return;
		}
		sb.append(lines).append(talk1).append(lines).append(talk2).append(lines).append(talk3);
		reply(num + 1);
		sb.append(lines).append(answered);

	}
}
