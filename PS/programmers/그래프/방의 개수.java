/*
 * 2020-12-18
 * https://programmers.co.kr/learn/courses/30/lessons/49190
 * 프로그래머스 코딩테스트 고득점 Kit 그래프
테스트 1 〉	통과 (0.59ms, 52.1MB)
테스트 2 〉	통과 (1.48ms, 52.7MB)
테스트 3 〉	실패 (1.61ms, 52.7MB)
테스트 4 〉	실패 (2.89ms, 53MB)
테스트 5 〉	실패 (12.59ms, 54.6MB)
테스트 6 〉	실패 (60.67ms, 62.5MB)
테스트 7 〉	실패 (9.13ms, 54.8MB)
테스트 8 〉	실패 (53.86ms, 64MB)
테스트 9 〉	실패 (61.63ms, 79MB)
 */

import java.util.HashMap;

class Solution {
    private static final int[][] MOVE_POS = {
        {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}
    };
    private static final int X = 1;
    private static final int Y = 0;
    private static final Long Y_CORRECTION_VALUE = 1000000L;
    
    public int solution(int[] arrows) {
        int answer = 0;
        Long[] curPos = {new Long(0), new Long(0)};
        Point curPoint = null;
        Point nextPoint = null;
        Long compressedPos = 0L;
        HashMap<Long, Point> coordinates2D = new HashMap<>();
        compressedPos = curPos[Y] * Y_CORRECTION_VALUE + curPos[X];
        coordinates2D.put(compressedPos, new Point());
        
        for(int arrow : arrows) {
            curPoint = coordinates2D.get(compressedPos);
            curPos[X] += MOVE_POS[arrow][X];
            curPos[Y] += MOVE_POS[arrow][Y];
            compressedPos = curPos[Y] * Y_CORRECTION_VALUE + curPos[X];
            if(coordinates2D.containsKey(compressedPos)) {
                if(!curPoint.visited8Dir[arrow]) {
                    answer++;
                    nextPoint = coordinates2D.get(compressedPos);
                }
            } else {
                nextPoint = new Point();
                coordinates2D.put(compressedPos, nextPoint);
            }
            curPoint.visited8Dir[arrow] = true;
            nextPoint.visited8Dir[(arrow + 4) % 8] = true;
        }
        return answer;
    }
}

class Point {
    boolean[] visited8Dir;
    public Point() {
        visited8Dir = new boolean[8];
    }
}