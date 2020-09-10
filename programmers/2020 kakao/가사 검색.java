/*
 * 2020-09-11
 * https://programmers.co.kr/learn/courses/30/lessons/60060
 * 프로그래머스 2020 KAKAO BLIND RECRUITMENT 가사 검색

푸는 중
Set<String>을 두어 words의 중복 체크

query에 ? 영역을 찾을 때 o(n)으로 찾아서 words랑 비교하여 결과 출력
 - 효율성 틀림

query에 ? 영역을 찾을 때 이분 탐색으로 o(logn)으로 찾아서 words랑 비교하여 결과 출력
 - 효율성 틀림

words를 1차원 배열에 두었는데 2차원 배열로 두어 List<String>[]
각 가사 단어의 문자열 길이가 같은 것끼리 모아두어 queries와 검사
 - 효율성 틀림

trie? 쪽 써야 될 것 같다.
*/


/*
query beginIndex, endIndex o(n)으로 찾을 때

통과 (0.22ms, 68.7MB)
테스트 2 〉	통과 (0.14ms, 69.4MB)
테스트 3 〉	통과 (0.13ms, 70.1MB)
테스트 4 〉	통과 (0.12ms, 69.1MB)
테스트 5 〉	통과 (0.15ms, 69.7MB)
테스트 6 〉	통과 (0.12ms, 68.8MB)
테스트 7 〉	통과 (3.56ms, 68.9MB)
테스트 8 〉	통과 (5.57ms, 69.5MB)
테스트 9 〉	통과 (5.53ms, 70.6MB)
테스트 10 〉	통과 (3.53ms, 70.4MB)
테스트 11 〉	통과 (3.40ms, 69.4MB)
테스트 12 〉	통과 (3.95ms, 70.9MB)
테스트 13 〉	통과 (37.62ms, 70.2MB)
테스트 14 〉	통과 (38.83ms, 70.8MB)
테스트 15 〉	통과 (35.90ms, 70.7MB)
테스트 16 〉	통과 (38.38ms, 71.1MB)
테스트 17 〉	통과 (37.95ms, 70.9MB)
테스트 18 〉	통과 (38.01ms, 70.2MB)
효율성  테스트
테스트 1 〉	실패 (시간 초과)
테스트 2 〉	실패 (시간 초과)
테스트 3 〉	실패 (8.64ms, 95.8MB)
테스트 4 〉	통과 (41.76ms, 79.3MB)
테스트 5 〉	통과 (42.56ms, 80.1MB)
*/

/*
query beginIndex, endIndex 이분 탐색으로 찾을 때 - o(logn)

테스트 1 〉	통과 (0.20ms, 70.6MB)
테스트 2 〉	통과 (0.08ms, 70.1MB)
테스트 3 〉	통과 (0.14ms, 70.4MB)
테스트 4 〉	통과 (0.14ms, 69.2MB)
테스트 5 〉	통과 (0.10ms, 69.6MB)
테스트 6 〉	통과 (0.09ms, 70.1MB)
테스트 7 〉	통과 (3.66ms, 69.2MB)
테스트 8 〉	통과 (4.49ms, 69.6MB)
테스트 9 〉	통과 (5.49ms, 70.6MB)
테스트 10 〉	통과 (3.17ms, 69.7MB)
테스트 11 〉	통과 (3.86ms, 69.4MB)
테스트 12 〉	통과 (4.27ms, 68.8MB)
테스트 13 〉	통과 (32.48ms, 70.1MB)
테스트 14 〉	통과 (47.73ms, 70.4MB)
테스트 15 〉	통과 (37.84ms, 70.6MB)
테스트 16 〉	통과 (34.04ms, 71.6MB)
테스트 17 〉	통과 (47.91ms, 71.5MB)
테스트 18 〉	통과 (34.18ms, 70.1MB)
효율성  테스트
테스트 1 〉	실패 (시간 초과)
테스트 2 〉	실패 (시간 초과)
테스트 3 〉	실패 (8.58ms, 95.8MB)
테스트 4 〉	통과 (48.37ms, 79.2MB)
테스트 5 〉	통과 (27.37ms, 78.4MB)
*/

/*
words를

List<String>[] 으로 각 가사 단어의 길이를 인덱스로 취해서
query랑 word랑 비교할 때 동일 문자열 길이만 비교할 수 있도록 하려 했으나
역시 효율성 실패 

테스트 1 〉	통과 (3.23ms, 69.1MB)
테스트 2 〉	통과 (1.85ms, 69.2MB)
테스트 3 〉	통과 (2.79ms, 70.3MB)
테스트 4 〉	통과 (2.82ms, 69.7MB)
테스트 5 〉	통과 (2.39ms, 68.7MB)
테스트 6 〉	통과 (2.63ms, 70.2MB)
테스트 7 〉	통과 (7.03ms, 71.9MB)
테스트 8 〉	통과 (8.61ms, 69.6MB)
테스트 9 〉	통과 (10.26ms, 71.4MB)
테스트 10 〉	통과 (4.60ms, 70.7MB)
테스트 11 〉	통과 (10.62ms, 70.1MB)
테스트 12 〉	통과 (7.51ms, 69.3MB)
테스트 13 〉	통과 (41.50ms, 72.7MB)
테스트 14 〉	통과 (54.02ms, 71.3MB)
테스트 15 〉	통과 (40.49ms, 70.3MB)
테스트 16 〉	통과 (41.97ms, 71MB)
테스트 17 〉	통과 (51.27ms, 72.1MB)
테스트 18 〉	통과 (46.60ms, 71.1MB)
효율성  테스트
테스트 1 〉	실패 (시간 초과)
테스트 2 〉	실패 (시간 초과)
테스트 3 〉	실패 (13.58ms, 100MB)
테스트 4 〉	통과 (39.45ms, 79.4MB)
테스트 5 〉	통과 (29.14ms, 79.9MB)

import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        HashSet<String> wordsSet = new HashSet<>();
        List<String>[] wordsList = new LinkedList[10001];
        String word;
        int matchedWordsCnt = 0;
        int beginIndex = 0;
        int endIndex = 0;
        int s, e, m;
        
        for(int i = 0; i <= 10000; i++) {
            wordsList[i] = new LinkedList<String>();
        }
        
        for(int i = 0; i < words.length; i++) {
            if(!wordsSet.contains(words[i])) {
                wordsList[words[i].length()].add(words[i]);
            }
        }
        
        for(int i = 0; i < queries.length; i++) {
            beginIndex = 0;
            endIndex = queries[i].length(); 
            s = 0;
            e = queries[i].length() - 1;
            if(queries[i].charAt(e) == '?') {
                if(queries[i].charAt(0) == '?') {
                    answer[i] = 0;
                    break;
                }
                while(s < e) {
                    m = (s + e) / 2;
                    if(queries[i].charAt(m) != '?') {
                        s = m + 1;
                    } else {
                        e = m;
                    }
                }
                endIndex = s;
            } else {
                while(s < e) {
                    m = (s + e) / 2;
                    if(queries[i].charAt(m) != '?') {
                        e = m;
                    } else {
                        s = m + 1;
                    }
                }
                beginIndex = s;
            }
            
            matchedWordsCnt = 0;
            for(int j = 0; j < wordsList[queries[i].length()].size(); j++) {
                word = wordsList[queries[i].length()].get(j);
                matchedWordsCnt++;
                for(int k = beginIndex; k < endIndex; k++) {
                    if(queries[i].charAt(k) !=  word.charAt(k)) {
                        matchedWordsCnt--;
                        break;
                    }
                }
            }
            answer[i] = matchedWordsCnt;
        }
        return answer;
    }
}
*/

/*
List<String>[] 사용한 거랑 로직은 똑같은데 이전 것은 
"각 가사 단어의 길이는 1 이상 10,000 이하로 빈 문자열인 경우는 없습니다."
으로 배열 index 0부터 10000까지 new LinkedList<String>(); 초기화 해줬는데
필요 이상으로 자원을 써서 words 갯수가 적을 때 느렸음.

for(int i = 0; i < words.length; i++) {
    if(!wordsSet.contains(words[i])) {
        if(null == wordsList[words[i].length()]) {
            wordsList[words[i].length()] = new LinkedList<String>();
        }
        wordsList[words[i].length()].add(words[i]);
    }
}

List<String>[] 에서 각 가사의 길이에 해당하는 index의 값이 null 일 때만
new LinkedList<String>(); 초기화 하고 사용했더니 정확성 쪽에서 시간은 다시 줄음.
근데 효율성쪽에서 정답은 아님.

아예 다른 알고리즘 써서 풀어야 될 듯.

정확성 테스트
테스트 1 〉	통과 (0.47ms, 71.5MB)
테스트 2 〉	통과 (0.41ms, 70.4MB)
테스트 3 〉	통과 (0.41ms, 70.2MB)
테스트 4 〉	통과 (0.38ms, 69.8MB)
테스트 5 〉	통과 (0.37ms, 70.6MB)
테스트 6 〉	통과 (0.38ms, 69.9MB)
테스트 7 〉	통과 (8.24ms, 70.1MB)
테스트 8 〉	통과 (10.21ms, 69.6MB)
테스트 9 〉	통과 (6.32ms, 69.3MB)
테스트 10 〉	통과 (4.98ms, 69.7MB)
테스트 11 〉	통과 (5.99ms, 69.8MB)
테스트 12 〉	통과 (6.03ms, 71.7MB)
테스트 13 〉	통과 (36.79ms, 71.5MB)
테스트 14 〉	통과 (51.97ms, 71.9MB)
테스트 15 〉	통과 (31.89ms, 70.7MB)
테스트 16 〉	통과 (42.38ms, 71.4MB)
테스트 17 〉	통과 (60.06ms, 70.2MB)
테스트 18 〉	통과 (44.80ms, 71.1MB)
효율성  테스트
테스트 1 〉	실패 (시간 초과)
테스트 2 〉	실패 (시간 초과)
테스트 3 〉	실패 (21.70ms, 99.1MB)
테스트 4 〉	통과 (39.86ms, 78.7MB)
테스트 5 〉	통과 (26.26ms, 79.7MB)

*/