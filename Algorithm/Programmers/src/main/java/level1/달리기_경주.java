package level1;

import java.util.HashMap;
import java.util.Map;

public class 달리기_경주 {

    private Map<String, Integer> rank = new HashMap<>();
    private Map<Integer, String> player = new HashMap<>();

    public String[] solution(String[] players, String[] callings) {
        initPlayers(players);
        runPlayers(callings);

        return rank(players.length);
    }

    private void initPlayers(String[] players) {
        for (int i = 0; i < players.length; i++) {
            rank.put(players[i], i);
            player.put(i, players[i]);
        }
    }

    private void runPlayers(String[] callings) {
        for (String player : callings) {
            final int currentRank = rank.get(player);
            final String beforePlayer = this.player.get(currentRank - 1);

            rank.put(player, currentRank - 1);
            rank.put(beforePlayer, currentRank);

            this.player.put(currentRank - 1, player);
            this.player.put(currentRank, beforePlayer);
        }
    }

    private String[] rank(int size) {
        String[] answer = new String[size];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = player.get(i);
        }

        return answer;
    }
}
