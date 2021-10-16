package refactoring;

public class Before {
    public static int splitAndSum(String text) {
        int result = 0;
        if (text == null || text.isEmpty()) {
            result = 0;
        } else {
            String[] values = text.split(",|:");
            for (String value : values) {
                result += Integer.parseInt(value);
            }
        }
        return result;
    }
}
