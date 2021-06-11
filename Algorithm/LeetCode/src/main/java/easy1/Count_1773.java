package easy1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Count_1773 {

    public static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int result = 0;

        for (List<String> lists : items) {
            int index = getRuleKeyIndex(ruleKey);
            if (isMatchedItem(lists.get(index), ruleValue)) {
                result ++;
            }
        }
        return result;
    }

    private static int getRuleKeyIndex(final String ruleKey) {
        return ("type".equals(ruleKey)) ? 0
                : ("color".equals(ruleKey)) ? 1
                    : 2;
    }

    private static boolean isMatchedItem(final String str1, final String str2) {
        return str1.equals(str2);
    }

    public static void main(String[] args) {
        List<List<String>> items = new ArrayList<>();
        List<String> list1 = Arrays.asList("phone", "blue", "pixel");
        List<String> list2 = Arrays.asList("computer", "silver", "lenovo");
        List<String> list3 = Arrays.asList("phone", "gold", "iphone");

        items.add(list1);
        items.add(list2);
        items.add(list3);

        System.out.println(countMatches(items, "color", "silver"));
    }
}
