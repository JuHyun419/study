package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 학급_회장 {

    private char solution(int n, String str) {
        Map<Character, Integer> rank = new HashMap<>();

        for (char c : str.toCharArray()) {
            rank.put(c, rank.getOrDefault(c, 0) + 1);
        }

        return findKeyByValue(rank, getMaxRank(rank));
    }

    private Integer getMaxRank(Map<Character, Integer> rank) {
        int max = 0;

        for (Integer value : rank.values()) {
            max = Math.max(max, value);
        }

        return max;
    }

    private Character findKeyByValue(Map<Character, Integer> rank, Integer value) {
        for (Character character : rank.keySet()) {
            Integer compare = rank.get(character);
            if (compare.equals(value)) {
                return character;
            }
        }
        throw new IllegalStateException("can't find key by value(" + value + ")");
    }

    public static void main(String[] args) {
        학급_회장 T = new 학급_회장();
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        String str = kb.next();
        System.out.println(T.solution(n, str));
    }

}
