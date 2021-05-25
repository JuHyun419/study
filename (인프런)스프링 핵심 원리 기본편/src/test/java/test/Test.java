package test;

public class Test {

    private boolean isVowel(String str){
        str = str.toLowerCase(); // 소문자로 변경
        return ("a".equals(str) || "e".equals(str) || "i".equals(str) || "o".equals(str) || "u".equals(str));
    }

    private String convertToAltCaps(String str){
        String letter = "";
        StringBuilder sb = new StringBuilder(str);
        int index = 0;

        for(int i=0; i<sb.length(); i++){
            if(sb.charAt(i) == ' '){
                letter += ' ';
                sb.delete(i, i+1);
                i --;
                continue;
            }

            char c = sb.charAt(i);
            if(i%2 == 0){
                c = Character.toLowerCase(c);
            } else{
                c = Character.toUpperCase(c);
            }
            letter += c;
        }
        return letter;
    }


    private String reverseWordOrder(String s){
        String[] arr = s.split(" ");    // 문자열 split은 문자열 배열로 받아야함.
        String tmp = "";
        for(int i=arr.length-1; i>=0; i--){
            tmp += arr[i];
            if(i != 0){
                tmp += " ";
            }
        }
        return tmp;
    }

    public static void main(String[] args) {
        Test test = new Test();

        boolean test1 = test.isVowel("A");
        boolean test2 = test.isVowel("a");
        boolean test3 = test.isVowel("b");

        String str1 = test.convertToAltCaps("Summer is here!");
        String str2 = test.reverseWordOrder("Hello what is your name?");

        System.out.println(test1 + " " + test2 + " " + test3);
        System.out.println("convertToAltCaps : " + str1);
        System.out.println("reverseWordOrder : " + str2);
    }
}
