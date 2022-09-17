package implementation;

import java.util.Arrays;
import java.util.Scanner;

public class 스위치_켜고_끄기 {

    private static Scanner scanner;
    private static int[] switches;
    private static int[][] students;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        final int switchCount = scanner.nextInt();
        setSwitchStatus(switchCount);
        final int student = scanner.nextInt(); // 학생 수
        setStudentStatus(student);

        changeSwitchStatus();

        printSwitchStatus();

        scanner.close();
    }

    /* 최초 스위치 상태 */
    private static void setSwitchStatus(int switchCount) {
        switches = new int[switchCount + 1];

        for (int i = 1; i < switches.length; i++) {
            switches[i] = scanner.nextInt();
        }
    }

    /* 학생 별 받은 수 */
    private static void setStudentStatus(int student) {
        students = new int[student][2];

        for (int i = 0; i < students.length; i++) {
            for (int j = 0; j < students[i].length; j++) {
                students[i][j] = scanner.nextInt();
            }
        }
    }

    /* 스위치 상태 변경 */
    private static void changeSwitchStatus() {
        for (int i = 0; i < students.length; i++) {
            if (students[i][0] == 1) {
                changeManSwitchStatus(students[i][1]);
            } else {
                changeWomanSwitchStatus(students[i][1]);
            }
        }

    }

    /* 남학생의 스위치 상태 변경: 자신이 받은 수의 배수인경우 스위치 상태 변경 */
    private static void changeManSwitchStatus(int number) {
        for (int i = 1; i < switches.length; i++) {
            if (i % number == 0) {
                reverseSwitchStatus(i);
            }
        }
    }

    /* 여학생의 스위치 상태 변경 */
    /* 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서, 구간에 속한 스위치 상태 모두 변경 */
    private static void changeWomanSwitchStatus(int index) {
        int left = 0;
        int right = 0;
        int distance = 1;

        // 좌우가 대칭이면서, 스위치 번호가 범위 내이면서 가장 많은 스위치를 포함하는 구간 찾기
        while (true) {
            int leftIndex = index - distance;
            int rightIndex = index + distance;
            if (leftIndex < 1 || rightIndex > switches.length - 1 || switches[leftIndex] != switches[rightIndex]) {
                break;
            }
            distance++;
            left = leftIndex;
            right = rightIndex;
        }

        // 좌우 대칭인 구간이 존재하는 경우
        if (distance != 1) {
            reverseSwitchStatus(left, right);
        } else {
            reverseSwitchStatus(index);
        }
    }

    /* 특정 번호의 스위치 상태 변경 */
    private static void reverseSwitchStatus(int index) {
        switches[index] = (switches[index] == 0) ? 1 : 0;
    }

    /* 스위치 상태 구간 변경 */
    private static void reverseSwitchStatus(int from, int to) {
        for (int i = from; i <= to; i++) {
            switches[i] = (switches[i] == 0) ? 1 : 0; // Math.abs(switches[i] - 1);
        }
    }

    /* 스위치의 최종 상태 출력 */
    private static void printSwitchStatus() {
        for (int i = 1; i < switches.length; i++) {
            System.out.print(switches[i] + " ");
            // 한 줄에 20개씩 출력
            if (i % 20 == 0) {
                System.out.println();
            }
        }
    }

}
