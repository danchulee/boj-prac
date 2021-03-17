package string;

import java.io.*;
import java.util.*;

public class Prob2002_추월 {
    static int N;
    static String[][] cars;
    static Queue<String> ccars = new LinkedList<>();
    static Set<String> passed = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cars = new String[N][2];
        for (int i = 0; i < N; i++) {
            cars[i][0] = br.readLine(); // 입구
            ccars.offer(cars[i][0]);
        }
        for (int i = 0; i < N; i++)
            cars[i][1] = br.readLine(); // 출구

        int answer = 0, index = 0;
        while (!ccars.isEmpty() && index < N) {
            String car = ccars.poll();
            if(passed.contains(car)) continue;
            while (!car.equals(cars[index][1])) {
                passed.add(cars[index++][1]);
                answer++;
            }
            index++;
        }
        System.out.print(answer);

    }
}
