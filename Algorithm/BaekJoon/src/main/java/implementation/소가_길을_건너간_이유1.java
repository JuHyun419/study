package implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class 소가_길을_건너간_이유1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Cow> list = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < N; i++) {
            int number = scanner.nextInt();
            int position = scanner.nextInt();
            Cow cow = new Cow(number, position);

            if (isFirstCow(list, number) || list.isEmpty()) {
                list.add(cow);
                continue;
            }

            for (Cow compare : list) {
                if (compare.getNumber() == number && compare.getPosition() != position) {
                    count++;
                    compare.setPosition(position);
                }
            }
        }

        System.out.println(count);

        scanner.close();
    }

    private static boolean isFirstCow(List<Cow> list, int number) {
        for (Cow cow : list) {
            if (number == cow.getNumber()) {
                return false;
            }
        }
        return true;
    }

}

class Cow {

    private final int number;

    private int position;

    public Cow(int number, int position) {
        this.number = number;
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cow cow = (Cow) o;
        return number == cow.number && position == cow.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, position);
    }
}
