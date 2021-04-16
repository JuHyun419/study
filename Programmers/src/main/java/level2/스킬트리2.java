package level2;

public class 스킬트리2 {

    public static int solution(String skill, String[] skillTrees) {
        int answer = 0;

        removeNotContainSkills(skill, skillTrees);

        // 두 문자열은 같은 문자로만 이루어져 있음(순서, 갯수는 다를 수 있음)
        for (int i = 0; i < skillTrees.length; i++) {
            boolean possibleSkillTree = true;
            for (int j = 0; j < skillTrees[i].length(); j++) {
                if (skillTrees[i].charAt(j) != skill.charAt(j)) {
                    possibleSkillTree = false;
                    break;
                }
            }
            answer += possibleSkillTree ? 1 : 0;
        }

        return answer;
    }

    private static void removeNotContainSkills(String skill, String[] skillTrees) {

        // skill에 포함되지 않는 문자 제거
        for (int i = 0; i < skillTrees.length; i++) {
            StringBuilder sb = new StringBuilder(skillTrees[i]);
            for (int j = 0; j < sb.length(); j++) {
                char ch = sb.charAt(j);
                if (isNotContainSkill(skill, ch)) {
                    sb.deleteCharAt(j);
                    j --;
                }
            }
            skillTrees[i] = sb.toString();
        }
    }

    private static boolean isNotContainSkill(final String str, final char ch) {
        return str.indexOf(ch) == -1;
    }

    public static void main(String[] args) {
        String skill = "CBD";
        String[] skillTrees = {"BACDE", "CBADF", "AECB", "BDA"};

        solution(skill, skillTrees);
    }

}
