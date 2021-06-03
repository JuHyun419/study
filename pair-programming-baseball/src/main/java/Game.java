import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {

    public List<Integer> generateRandomNumber() {
        return new Random().ints(3, 1, 9).boxed().collect(Collectors.toList());
    }

    public String play(List<Integer> playerLists, List<Integer> computerLists) {
        //strike 체크
        // index, number, computerLists
//        int strikeCount = countStrike(playerLists, computerLists);
//
//        //ball 체크
//        countBall(playerLists, computerLists);

        //아웃
        return null;
    }

    // 2 => [4, 2, 5]
    public boolean checkStrike(int input, List<Integer> computerLists) {
        return true;
    }

    public boolean checkBall(int input) {
        return true;
    }

    public boolean isStrike(int playerNumber, int computerNumber) {
        return playerNumber == computerNumber;
    }


    // [1,2,3] , [1,3,5] => 2
    // [2,2,3] , [2,2,6] => 2
    // [1,2,3] , [1,1,6] => 2
    // [1,2,3] , [1,1,6] => 2
    public int countMatchNumbers(List<Integer> computerNumbers, List<Integer> playerNumbers) {
//        int count = 0;
//        for (Integer i : computerNumbers) {
//            if (playerNumbers.contains(i)) count ++;
//        }
//
//        Set<Integer> computerSet = new HashSet<>(computerNumbers);
//        Set<Integer> playerSet = new HashSet<>(playerNumbers);
//
        return 0;
    }
}
