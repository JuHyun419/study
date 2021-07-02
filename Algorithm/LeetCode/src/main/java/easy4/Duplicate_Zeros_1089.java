package easy4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Duplicate_Zeros_1089 {
    public void duplicateZeros(int[] arr) {
        if (isNotContainsZero(arr)) {
            return;
        }

        List<Integer> list = toList(arr);

        for (int i = 0; i < list.size(); i++) {
            int number = list.get(i);
            if (number == 0) {
                list.add((i + 1), number);
                list.remove(list.size() - 1);
                i++;
            }
        }

        arr = toArray(list);
    }

    private boolean isNotContainsZero(int[] array) {
        for (int num : array) {
            if (num == 0) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> toList(int[] arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }

    private int[] toArray(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public void duplicateZeros2(int[] arr) {
        int[] newArr = new int[arr.length];
        int p2 = 0;
        for (int i = 0; i < arr.length && p2 < arr.length; i++) {
            if (arr[i] == 0) {
                p2++;
            } else {
                newArr[p2] = arr[i];
            }
            p2++;
        }
        System.arraycopy(newArr, 0, arr, 0, arr.length);
    }

    public void duplicateZeros3(int[] arr) {
        int[] temp = new int[arr.length]; // default: 0
        int index = 0;

        for (int i = 0; i < arr.length && index < temp.length; i++) {
            if (arr[i] == 0) {
                index += 2;
            } else {
                temp[index++] = arr[i];
            }
        }
        System.arraycopy(temp, 0, arr, 0, arr.length);
    }

    public static void main(String[] args) {
        Duplicate_Zeros_1089 d = new Duplicate_Zeros_1089();
        int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
        d.duplicateZeros2(arr);
    }
}
