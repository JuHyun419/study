package easy2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Relative_1122 {
    
    /*
    arr2와 arr1가 같도록 arr1의 요소를 정렬, arr2에 나타나지 않는 요소는 arr1의 끝에 오름차순으로 배치
    arr2를 순회하면서 arr1에 동일한 요소는 전부 lisT에 넣고 제거
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        List<Integer> list1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(arr2).boxed().collect(Collectors.toList());
        List<Integer> list3 = new ArrayList<>();

        for (int i = 0; i < list2.size(); i++) {
            for (int j = 0; j < list1.size(); j++) {
                if (list2.get(i).equals(list1.get(j))) {
                    list3.add(list2.get(i));
                    list1.remove(j);
                    j --;
                }
            }
        }

        Collections.sort(list1);
        list3.addAll(list1);

        return list3.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        int[] arr3 = relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(arr3));
    }

}
