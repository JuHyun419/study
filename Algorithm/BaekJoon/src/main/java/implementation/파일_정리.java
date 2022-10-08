package implementation;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class 파일_정리 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> extension = new TreeMap<>();

        int N = scanner.nextInt();
        for (int i = 0; i < N; i++) {
            String file = scanner.next();
            String fileExtension = file.substring(file.indexOf(".") + 1);
            extension.put(fileExtension, extension.getOrDefault(fileExtension, 0) + 1);
        }

        for (Map.Entry<String, Integer> e : extension.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }

        scanner.close();
    }
}
