/*
 * 2021-02-01
 * https://www.acmicpc.net/problem/5014
 * 백준 dfs bfs 골드 5
 *

풀이 중
37%에서 틀림

사용 반례
10 1 10 100 100
답 : use the stairs

10 5 5 234 543
답 : 0

1000 5 6 222 221
답 2

1000 5 50 222 221
답 90
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int answer;
    static int f; // 총 층수
    static int s; // 시작
    static int g; // 목적지
    static int u;
    static int d;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        visited = new int[f+1];

        answer = bfs();
        if(answer == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(answer);
        }
    }

    public static int bfs() {
        int start = s;
        int floor;
        int nextUpFloor = 0;
        int nextDownFloor = 0;
        int cnt = 0;
        
        if(s < g) {
            if(u == 0) return -1;
            cnt = (g - s) / u;
            start += u * cnt;
            if(cnt >= 1) {
                visited[start - u] = cnt - 1;
            }
        } else if(s > g){
            if(d == 0) return -1;
            cnt = (s - g) / d;
            start -= d * cnt;
            if(cnt >= 1) {
                visited[start + d] = cnt - 1;
            }
        } else {
            return 0;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = cnt;
        
        while (!q.isEmpty()) {
            floor = q.poll();
            cnt = visited[floor];
            nextUpFloor = floor + u;
            nextDownFloor = floor - d;
            if(u > 0 && nextUpFloor <= f && (visited[nextUpFloor] <= 0 ||
                (visited[nextUpFloor] > 0 && cnt + 1 < visited[nextUpFloor]))) {
                visited[nextUpFloor] = cnt + 1;
                q.add(nextUpFloor);
            }
            if(d > 0 && nextDownFloor >= 1 && (visited[nextDownFloor] <= 0 ||
                (visited[nextDownFloor] > 0 && cnt + 1 < visited[nextDownFloor]))) {
                visited[nextDownFloor] = cnt + 1;
                q.add(nextDownFloor);
            }

            if(nextUpFloor == g || nextDownFloor == g) return visited[g];
        }

        return -1;
    }
}