package simulation;

import java.io.*;
import java.util.*;

// 목적지 같은 고객 처리해줘야 함
public class Prob19238_스타트택시 {
    static int N, M, oil;
    static int[][] map;
    static int[] taxistart = new int[2];
    static ArrayList<int[]> customers = new ArrayList<>();
    static HashSet<Integer>[][] dest;
    static boolean[][] visit;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static int success;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        oil = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visit = new boolean[N][N];
        dest = new HashSet[N][N];
        for (int i = 0; i < N; i++) { // 0 빈칸 1 빈 벽
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dest[i][j] = new HashSet<>();
            }
        }
        st = new StringTokenizer(br.readLine());
        taxistart[0] = Integer.parseInt(st.nextToken()) - 1;
        taxistart[1] = Integer.parseInt(st.nextToken()) - 1;

        int start_x, start_y, end_x, end_y;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            start_x = Integer.parseInt(st.nextToken()) - 1;
            start_y = Integer.parseInt(st.nextToken()) - 1;
            end_x = Integer.parseInt(st.nextToken()) - 1;
            end_y = Integer.parseInt(st.nextToken()) - 1;
            map[start_x][start_y] = -(i + 1);
            dest[end_x][end_y].add(i + 1);
        }
        int res;
        for (int i = 0; i < M; i++) {
            res = takeup();
            if (res == -1) {
                System.out.print(-1);
                break;
            } else oil += res;
        }
        if (success == M)
            System.out.print(oil);
    }

    // 가장 가까운 승객부터 운행
    // 그 중 행 번호가 가장 작고, 열 번호가 가장 작은 사람부터
    public static int takeup() {
        customers.clear();
        for (int i = 0; i < N; i++)
            Arrays.fill(visit[i], false);

        Queue<int[]> taxi = new LinkedList<>();
        taxi.offer(taxistart);

        boolean found = false;
        int nx, ny, size, tmp_oil = oil;
        int[] curr;
        // 음수 번호 : 시작점
        // 음수 번호 + INF : 도착점
        while (!taxi.isEmpty() && !found && tmp_oil > 0) { // 태울 수 있는 손님 찾기
            tmp_oil--;
            size = taxi.size();
            while (size-- > 0) {
                curr = taxi.poll();
                for (int i = 0; i < 5; i++) {
                    nx = curr[0] + dx[i];
                    ny = curr[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1 || visit[nx][ny])
                        continue;
                    visit[nx][ny] = true;
                    if (map[nx][ny] < 0) { // 시작점
                        found = true;
                        customers.add(new int[]{nx, ny});
                        if (i == 0) {
                            tmp_oil++;
                            break;
                        }
                    } else taxi.offer(new int[]{nx, ny});
                }
            }
        }

        if (!customers.isEmpty()) {
            customers.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] != o2[0]) return o1[0] - o2[0];
                    else return o1[1] - o2[1];
                }
            });

            int res;
            int[] first = customers.get(0);
            int num = -(map[first[0]][first[1]]);
            taxistart[0] = first[0];
            taxistart[1] = first[1];
            // 손님 태움
            map[first[0]][first[1]] = 0;
            // 데려다 줄 수 있는가?
            oil = tmp_oil;
            if ((res = takedown(oil, num)) != -1) {
                success++;
                return oil - res;
            }
        }
        return -1; // 태울 사람 없으면 바로 return
    }

    public static int takedown(int tmp_oil, int num) { // 남은 오일 리턴, 못 가면 -1
        for (int i = 0; i < N; i++)
            Arrays.fill(visit[i], false);

        Queue<int[]> taxi = new LinkedList<>();
        taxi.offer(taxistart);

        int nx, ny, size;
        int[] curr;
        while (!taxi.isEmpty() && tmp_oil > 0) {
            tmp_oil--;
            size = taxi.size();
            while (size-- > 0) {
                curr = taxi.poll();
                for (int i = 0; i < 5; i++) {
                    nx = curr[0] + dx[i];
                    ny = curr[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1 || visit[nx][ny])
                        continue;
                    visit[nx][ny] = true;
                    if (dest[nx][ny].contains(num)) { // 도착점
                        if (i == 0)
                            tmp_oil++;
                        taxistart[0] = nx;
                        taxistart[1] = ny;
                        return tmp_oil;
                    } else taxi.offer(new int[]{nx, ny});
                }
            }
        }
        return -1;
    }
}
