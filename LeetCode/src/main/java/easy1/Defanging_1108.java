package easy1;

public class Defanging_1108 {

    public static String defangIPaddr(String address) {
        return address.replaceAll("\\.", "[.]");
    }

    public static void main(String[] args) {
        String address = "1.1.1.1";
        System.out.println(defangIPaddr(address));
    }
}
