package kakao.blind_2018;

import java.util.ArrayList;
import java.util.List;

public class 뉴스_클러스터링 {
    public int solution(String str1, String str2) {
        List<String> list1 = getMultiSet(str1);
        List<String> list2 = getMultiSet(str2);

        if (list1.size() == 0 && list2.size() == 0) {
            return 65536;
        }
        final int intersection = getIntersection(list1, list2);
        // 합집합은 다음과 같이 구할수도 있음
        // 집합1 사이즈 + 집합2 사이즈 - 교집합
        final int union = getUnion(list1, list2);
        return (int) getJakard(intersection, union);
    }

    /* 문자열을 두 글자씩 끊어서 다중집합 원소로 만들기 */
    private List<String> getMultiSet(String str) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < str.length() - 1; i++) {
            String set = str.substring(i, i + 2).toLowerCase();
            if (isValidWords(set)) {
                list.add(set);
            }
        }
        return list;
    }

    /* 다중집합의 원소가 영문자로만 되어있는지 판단 */
    private boolean isValidWords(String set) {
        for (int i = 0; i < set.length(); i++) {
            if (!Character.isLetter(set.charAt(i))) {
                return false;
            }
            /* 위에와 동일한 로직
            if (set.charAt(i) < 'a' || set.charAt(i) > 'z') {
                return false;
            } */
        }
        return true;
    }

    /* 두 집합에서 교집합 갯수 */
    private int getIntersection(List<String> list1, List<String> list2) {
        int result = 0;
        List<String> temp = new ArrayList<>(list2);

        for (String s : list1) {
            if (temp.contains(s)) {
                result += 1;
                temp.remove((Object) s);
            }
        }
        return result;
    }

    /* 두 집합에서 합집합 갯수 */
    private int getUnion(List<String> list1, List<String> list2) {
        int result = list1.size();

        for (String s : list2) {
            if (list1.contains(s)) {
                list1.remove((Object) s);
                continue;
            }
            result += 1;
        }
        return result;
    }

    /**
     * @param var1: 교집합 수
     * @param var2: 합집합 수
     * @return 자카드 유사도
     */
    private double getJakard(int var1, int var2) {
        return Math.floor((double) var1 / var2 * 65536);
    }

    public static void main(String[] args) {
        뉴스_클러스터링 d = new 뉴스_클러스터링();
        String str1 = "aa1+aa2";
        String str2 = "AAAA12";
        System.out.println(d.solution(str1, str2));
    }
}
