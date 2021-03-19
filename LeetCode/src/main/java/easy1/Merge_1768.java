package easy1;

public class Merge_1768 {

    public static String mergeAlternately(String word1, String word2) {
        int minLength = Math.min(word1.length(), word2.length());
        StringBuilder sb = new StringBuilder();
        int i;

        for (i = 0; i < minLength; i++) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }

        sb = (word1.length() < word2.length())
                ? sb.append(word2.substring(i))
                : sb.append(word1.substring(i));

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(mergeAlternately("ab", "pqrs"));
    }

}
