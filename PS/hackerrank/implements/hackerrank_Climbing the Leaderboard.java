/*
 * 2021-01-17
 * https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem?isFullScreen=true
 * Medium, Implementation
 * 
정수 list ranked가 주어지면 또 다른 정수 list player의 각 인덱스마다 순회하면서 알맞은 순위 찾고 List<Integer> 타입으로 리턴하기

ranked는 내림차순으로 정렬된 상태로 입력이 들어오고 등수를 1등 부터 매김.
player.get(idx)에 해당하는 점수를 ranked내에서 알맞은 위치를 찾아야 하는데
ranked에 중복된 숫자가 들어가 있을 수도 있으므로 (ex : index 0 : 100점 - 1등, index 1 : 100점 - 1등)
이분 탐색 하기 전에 ranked 안에 들어가 있는 중복된 숫자를 따로 처리를 해줘야 됨.
아래 코드에서는 ranked의 중복된 숫자를 remove 해주고 나서 이분 탐색 했다.
 */

class Result {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        initializeRank(ranked);
        
        for(int idx = 0; idx < player.size(); idx++) {
            player.set(idx, binarySearch(ranked, player.get(idx)));
        }
    
        return player;
    }
    
    public static void initializeRank(List<Integer> ranked) {
        int recentScore = -1;
        int idx = 0;
        int cnt = ranked.size();
        while(cnt-- > 0) {
            if(recentScore != ranked.get(idx)) {
                recentScore = ranked.get(idx);
                idx++;
            } else {
                ranked.remove(idx);
            }
        }
    }
    
    public static int binarySearch(List<Integer> ranked, int playerScore) {
        int start = 0;
        int end = ranked.size();
        int mid;
        int rankScore;
        while(start < end) {
            mid = (start + end) / 2;
            rankScore = ranked.get(mid); 
            if(rankScore > playerScore) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        
        return start + 1;
    }
}