package easy2;

import java.util.ArrayList;
import java.util.List;

public class String_Matching_in_an_Array_1408 {
    public List<String> stringMatching(String[] words) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;

                if (words[i].contains(words[j])) {
                    if (!list.contains(words[j])) {
                        list.add(words[j]);
                    }
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {
        String str = "superhero";
        String str2 = "sup";
        String str3 = "hero";

        System.out.println(str.contains(str2));
        System.out.println(str.contains(str3));
    }
}
