import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Prob2579 {
    static int N;
    static int[] stairs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        stairs = new int[N];
        for (int i = 0; i < N; i++)
            stairs[i] = Integer.parseInt(br.readLine());
    }
}
