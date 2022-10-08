package implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 지우개 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(i + 1);
        }

        while (list.size() != 1) {
            for (int i = 0; i < list.size(); i++) {
                list.remove(i);
            }
        }

        System.out.println(list.get(0));

        scanner.close();
    }

}
