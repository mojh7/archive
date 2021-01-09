/*
 * 틀려서 푸는 중
 * 2021-01-09
 * https://www.acmicpc.net/problem/15997
 * 백준 브루트포스 골드 3
 * 카카오 코드 페스티벌 2018 A번
 *
 * 예제는 값 나오는데 1%에서 바로 틀림
 * 그런데 아래 예제에서 나온 결과 값이 합이 2가 안되는 것 보면 중간에 로직 틀린 듯
KOREA CCC BBB AAA
KOREA CCC 0.3 0.5 0.2
AAA BBB 0.2 0.8 0.0
AAA KOREA 0.1 0.4 0.5
CCC BBB 0.3 0.6 0.1
KOREA BBB 0.55 0.25 0.2
CCC AAA 0.0 1.0 0.0
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.*;

class Main {
    static final int WIN = 0;
    static final int DRAW = 1;
    static final int LOSE = 2;
    static Team[] leagueResult;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        leagueResult = new Team[4];
        LinkedHashMap<String, Team> teams = new LinkedHashMap<>();
        Game[] games = new Game[6];
        for(int idx = 0; idx < 4; idx++) {
            leagueResult[idx] = new Team();
            teams.put(st.nextToken(), leagueResult[idx]);
        }
        for(int idx = 0; idx < 6; idx++) {
            st = new StringTokenizer(br.readLine());
            games[idx] = new Game(teams.get(st.nextToken()), teams.get(st.nextToken()),
                    Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()));
        }

        dfs(teams, games, 1, 0);
        for(Map.Entry<String, Team> entry : teams.entrySet()) {
            bw.write(String.format("%.10f\n", entry.getValue().percentage));
        }
        bw.flush();
        bw.close();
    }

    public static void dfs(LinkedHashMap<String, Team> teams, Game[] games, float odds, int round) {
        if(round == 6) {
            finishLeague(teams, odds);
            return;
        }
        float nextOdds;
        for(int matchResult = 0; matchResult < 3; matchResult++) {
            nextOdds = odds * games[round].odds[matchResult];
            if(nextOdds == 0)
                continue;
            runGame(games[round], matchResult,false);
            dfs(teams, games, nextOdds, round + 1);
            runGame(games[round], matchResult, true);
        }
    }

    public static void finishLeague(LinkedHashMap<String, Team> teams, float odds) {
        Arrays.sort(leagueResult, (t1, t2) -> t2.score - t1.score);
        if(leagueResult[0].score > leagueResult[1].score) {
            leagueResult[0].percentage += odds;
            if(leagueResult[1].score > leagueResult[2].score) {
                leagueResult[1].percentage += odds;
            } else if(leagueResult[2].score > leagueResult[3].score){
                leagueResult[1].percentage += odds * 0.5f;
                leagueResult[2].percentage += odds * 0.5f;
            } else {
                leagueResult[1].percentage += odds * (2 / 3f);
                leagueResult[2].percentage += odds * (2 / 3f);
                leagueResult[3].percentage += odds * (2 / 3f);
            }
        } else if(leagueResult[1].score > leagueResult[2].score) {
            leagueResult[0].percentage += odds;
            leagueResult[1].percentage += odds;
        } else if(leagueResult[2].score > leagueResult[3].score) {
            leagueResult[0].percentage += odds * (2 / 3f);
            leagueResult[1].percentage += odds * (2 / 3f);
            leagueResult[2].percentage += odds * (2 / 3f);
        } else {
            leagueResult[0].percentage += odds * 0.5f;
            leagueResult[1].percentage += odds * 0.5f;
            leagueResult[2].percentage += odds * 0.5f;
            leagueResult[3].percentage += odds * 0.5f;
        }
    }

    public static void runGame(Game game, int matchResult, boolean isReversed) {
        int correction = isReversed ? -1 : 1;
        switch (matchResult) {
            case WIN:
                game.leftTeam.score += 3 * correction;
                break;
            case DRAW:
                game.leftTeam.score += 1 * correction;
                game.rightTeam.score += 1 * correction;
                break;
            case LOSE:
                game.rightTeam.score += 3 * correction;
                break;
            default:
                break;
        }
    }
}

class Team {
    int score;
    float percentage;
}

class Game {
    Team leftTeam;
    Team rightTeam;
    // win, draw, lose
    float[] odds;

    public Game(Team leftTeam, Team rightTeam, float win, float draw, float lose) {
        this.leftTeam = leftTeam;
        this.rightTeam = rightTeam;
        odds = new float[]{ win, draw, lose };
    }
}