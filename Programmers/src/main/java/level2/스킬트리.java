package level2;

public class 스킬트리 {

    public static int solution(String skill, String[] skillTrees) {
        int answer = 0;
        int[] check;

        for (int i = 0; i < skillTrees.length; i++) {
            check = new int[skill.length()];
            boolean isPossibleSkillTree = true;

            for (int j = 0; j < skillTrees[i].length(); j++) {
                int learnSkillIndex = skill.indexOf(skillTrees[i].charAt(j));
                if (learnSkillIndex < 0) // 현재 배우는 스킬이, 선행 스킬의 요소에 포함되지 않을 경우
                    continue;
                check[learnSkillIndex] = 1; // 현재 스킬 배웠다고 표시

                for (int k = 0; k < learnSkillIndex; k++) { // 선행 스킬 학습 여부 체크
                    if (check[k] == 0) { // 선행 스킬을 학습안한 경우 => 불가능
                        isPossibleSkillTree = false;
                        break;
                    }
                }
            }

            if (isPossibleSkillTree) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String skill = "CBD";
        String[] skillTrees = {"BACDE", "CBADF", "AECB", "BDA"};
    }
}
