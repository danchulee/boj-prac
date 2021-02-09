import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 * 반례 5 5
 * 1 2 1 4 5
 */

/**
 * 스택 사용하기.
 * 처음에 넣고 max값을 찾음.. 만약 max가 새로 발견되면 그 사이의 것들 계산 (반복)
 */
public class Prob14719 {
    static int W, answer, max;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        W = Integer.parseInt(br.readLine().split(" ")[1]);

        StringTokenizer st = new StringTokenizer(br.readLine());
        stack.add(Integer.parseInt(st.nextToken()));
        max = stack.peek();
        while (st.hasMoreTokens()) {
            int height = Integer.parseInt(st.nextToken());
            if (height >= max) {

            } else {

            }
        }
//            ArrayList<Integer> middle = new ArrayList<>();
//            do {
//                next_height = Integer.parseInt(st.nextToken());
//            } while (st.hasMoreTokens() && next_height < height);


    } // end of Main
} // end of Class
