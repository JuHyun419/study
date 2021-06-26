package easy5;

public class Maximum_69_Number_1323 {
    public int maximum69Number (int num) {
        return Integer.parseInt(
                String.valueOf(num).replaceFirst("6", "9")
        );
    }
}
