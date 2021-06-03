import java.util.List;

public class PlayResult {
    private int ballCount;
    private int strikeCount;

    public PlayResult() {
        ballCount = 0;
        strikeCount = 0;
    }

    public int getBallCount() {
        return ballCount;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int countBall(List<Integer> playerLists, List<Integer> computerLists) {
        for (int i = 0; i < playerLists.size(); i++) {
            for (int j = 0; j < computerLists.size(); j++) {
                if (i != j && computerLists.get(j).equals(playerLists.get(i))) {
                    ballCount++;
                }
            }
        }
        return ballCount;
    }

    public int countStrike(List<Integer> playerLists, List<Integer> computerLists) {
        for (int i = 0; i < playerLists.size(); i++) {
            if (isStrike(playerLists.get(i), computerLists.get(i))) {
                strikeCount++;
            }
        }
        return strikeCount;
    }

    public boolean isStrike(int playerNumber, int computerNumber) {
        return playerNumber == computerNumber;
    }

}
