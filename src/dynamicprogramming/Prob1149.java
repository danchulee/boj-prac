import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Prob1149 {
    static int N;
    static int[][] costs;
    static int[][] dps;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costs = new int[N][3];
        dps = new int[N][3];

        for (int i = 0; i < N; i++)
            costs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        System.out.println(dps[N]);
        br.close();
    }
}
