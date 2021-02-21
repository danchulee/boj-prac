package simulation;

import java.io.*;
import java.util.*;

public class Prob2116_주사위쌓기 {
	static int N, answer;
	static int[][] dice;
	static int[][] upDown;

	static final int[] aside = { 5, 3, 4, 1, 2, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dice = new int[N][6];
		upDown = new int[N][];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++)
				dice[i][j] = Integer.parseInt(st.nextToken());
		}

		int sum;
		for (int i = 0; i < 6; i++) {
			sum = 0;
			upDown[0] = new int[] { i, aside[i] };
			for (int j = 1; j < N; j++)
				findUpDown(j, dice[j - 1][upDown[j - 1][1]]); // 위 아래면인덱 찾아둠
//			mix(0, 0);
			for (int j = 0; j < N; j++) {
				int max = 0;
				for (int k = 0; k < 6; k++) {
					if (upDown[j][0] == k || upDown[j][1] == k)
						continue;
					max = (dice[j][k] > max ? dice[j][k] : max);
				}
				sum += max;
			}
			answer = Math.max(answer, sum);
		}
		System.out.print(answer);
	}

	static void findUpDown(int index, int num) {
		int numIndex = 0;
		for (int i = 0; i < 6; i++)
			if (dice[index][i] == num)
				numIndex = i;
		upDown[index] = new int[] { numIndex, aside[numIndex] };
	}

//	static void mix(int diceIndex, int sum) {
//		if (sum + (N - diceIndex) * 6 < answer)
//			return;
//		if (diceIndex == N) {
//			answer = Math.max(answer, sum);
//			return;
//		}
//		for (int i = 0; i < 6; i++) {
//			if (upDown[diceIndex][0] == i || upDown[diceIndex][1] == i)
//				continue;
//			mix(diceIndex + 1, sum + dice[diceIndex][i]);
//		}
//	}

}
