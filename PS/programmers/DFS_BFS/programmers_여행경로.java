/*
 * 2021-01-21
 * https://programmers.co.kr/learn/courses/30/lessons/43164
 * 코딩테스트 고득점 Kit dfs/bfs level 3
 * 
 * 푸는 중
 * "ICN" 항공 도시 시작 기준으로 kruskal 알고리즘 이용해서 mst 만들고 dfs해서 경로 리턴할 생각중
 */

import java.util.*;

class Solution {
    private static final ICN = "ICN";
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        HashMap<String, Integer> strToNodeIdx = new HashMap<>();
        int nodeIdx = 0;
        PriorityQueue<Edge> edgesPQ = new PriorityQueue<>();
        Edgep[] edges = new Edge[10001];
        
        for(Strin[] ticket : tickets) {
            for(int idx = 0; idx < 2; idx++) {
                if(!strToNodeIdx.contains(ticket[idx])) {
                    strToNodeIdx.put(ticket[idx], nodeIdx++);
                }    
            }
            
        }
        // airport
        return answer;
    }
    
    public void union() {
        
    }
    
    public int find() {
        
    }
}

class Edge implements Comparable<Edge> {
    String s;
    String e;
    
    @override
    public int compareTo(Edge o) {
        if(this.s.equals(ICN)) {
            if(!o.equals(ICN)) {
                return -1;
            } else {
                return s.compare(e)
            }
        } else {
            if(!o.equals(ICN)) {
                return 1;
            } else {
                return s.compare(e)
            }
        }
    }
}