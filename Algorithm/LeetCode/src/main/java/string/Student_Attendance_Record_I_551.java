package string;

public class Student_Attendance_Record_I_551 {
    public static boolean checkRecord(String s) {
        /* The student was absent ('A') for strictly fewer than 2 days total */
        int absentDays = 0;
        char[] array = s.toCharArray();
        for (char ch : array) {
            if (ch == 'A') {
                absentDays++;
            }
            if (absentDays == 2) {
                return false;
            }
        }

        /* The student was never late ('L') for 3 or more consecutive days */
        for (int i = 0; i < array.length - 2; i++) {
            if (array[i] == array[i + 1] && array[i + 1] == array[i + 2] && array[i + 2] == 'L') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "PPALLL";
        System.out.println(checkRecord(s));
    }
}
