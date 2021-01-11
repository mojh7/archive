/*
 * 2021-01-12
 * https://www.acmicpc.net/problem/1175
 * 백준 그래프 골드1
 * 
 * 푸는 중
 * 불가능 할 때 -1 출력 못 봐서 틀림
 * 한 번 방문 했던 곳 또 방문해도 되서 틀림(반례들 답이 달랐음)
 * 그래서 boolean 타입 isVisited 없앴더니 반례 테케는 맞았으나 메모리 초과(q에 너무 많이 담기고 탐색 됨.)
isVisited int형으로 바꿔서 1, 4, 5일 때 방문 안하게 했는데 틀림

3 5
C...C
#...#
.#S#.
출력 : 12

4 5
#####
#C#C#
#.S.#
##.##
출력 : 8

6 6
CC....
......
###...
......
....S.
.....#
출력 : 12

골드 1 치고 간단하고 쉬운 문제가타 보였으나
같은 방향 연속 두 번 연속으로 못가는 조건으로 인해서 어려움.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    private static final int[][] MOVE_POS = { {0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int answer = 0;
        char[][] map = new char[n][m];
        String input;
        int[] start = new int[]{0, 0, 0, -1};
        char c;
        for(int row = 0; row < n; row++) {
            input = br.readLine();
            for (int col = 0; col < m; col++) {
                c = input.charAt(col);
                if(c == 'S') {
                    start[0] = col;
                    start[1] = row;
                }
                map[row][col] = c;
            }
        }
        answer = bfs(map, start, n, m, 2);
        System.out.println(answer);
    }

    public static int bfs(char[][] map, int[] start, int n, int m, int cnt) {
        int extraTime = 0;
        // x, y, 시간, 이전 방향
        int[] cur;
        int[] next = new int[4];
        int[][] isVisited = new int[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            cur = q.poll();
            for(int idx = 0; idx < 4; idx++) {
                if(cur[3] == idx)
                    continue;
                next[0] = cur[0] + MOVE_POS[idx][0];
                next[1] = cur[1] + MOVE_POS[idx][1];
                if(next[0] < 0 || next[0] >= m || next[1] < 0 || next[1] >= n ||
                    map[next[1]][next[0]] == '#' || isVisited[next[1]][next[0]] == 5)
                    continue;
                if(map[next[1]][next[0]] == 'C') {
                    map[next[1]][next[0]] = '.';
                    if(cnt == 2) {
                        extraTime = bfs(map, new int[]{ next[0], next[1], 0, idx }, n, m, cnt - 1);
                        if(extraTime == -1) return -1;
                    }
                    return cur[2] + 1 + extraTime;
                }
                isVisited[next[1]][next[0]]++;
                q.add(new int[]{ next[0], next[1], cur[2] + 1, idx});
            }
        }
        return -1;
    }
}