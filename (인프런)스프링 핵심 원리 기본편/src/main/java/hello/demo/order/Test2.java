package hello.demo.order;

public class Test2 {
    public static int solution(int n) {
        int answer = 0;
        String three = tenToThree(n);   // 10진법 -> 3진법
        answer = threeToTen(three);     // 3진법 -> 10진법
        return answer;
    }

    public static String tenToThree(int n) {
        String result = "";
        
        while (n != 0) {
            result += n % 3;
            n /= 3;
        }
        return result;
    }

    public static int threeToTen(String str) {
        StringBuilder sb = new StringBuilder(str);
        int num = Integer.parseInt(sb.reverse().toString());
        int result = 0;
        int x = 1;

        while (num != 0) {
            result += (num%10) * x;
            x *= 3;
            num /= 10;
        }
        
        return result;
    }

    public static void main(String[] args) {
        int result;
        result = solution(125);
        System.out.println(result);
    }
}
