package string;

public class Detect_Capital_520 {
    public boolean detectCapitalUse(String word) {

        /* All letters in this word are capitals, like "USA" */
        if (word.equals(word.toUpperCase())) {
            return true;
        }

        /* All letters in this word are not capitals, like "leetcode" */
        if (word.equals(word.toLowerCase())) {
            return true;
        }

        /* Only the first letter in this word is capital, like "Google" */
        String word2 = word.substring(1);
        if (Character.isUpperCase(word.charAt(0)) && word2.equals(word2.toLowerCase())) {
            return true;
        }
        return false;
    }
}
