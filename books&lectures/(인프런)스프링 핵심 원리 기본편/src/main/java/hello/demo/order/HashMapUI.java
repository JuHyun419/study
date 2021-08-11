package hello.demo.order;

import java.util.*;

public class HashMapUI {
    public static void main(String[] args) {
        int nMenu = 0;
        boolean bFlag = true;
        String strName;
        int nScore = 0;

        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        while (bFlag) {
            System.out.println("--------------------------");
            System.out.println("1. 성적입력");
            System.out.println("2. 성적삭제");
            System.out.println("3. 성적검색");
            System.out.println("4. 전체성적출력");
            System.out.println("5. 전체성적 출력(이름순)");
            System.out.println("6. 전체성적 출력(성적순)");
            System.out.println("0. 종료");
            System.out.println("--------------------------");

            System.out.println("0 ~ 4 사이의 숫자를 입력하세요.");
            nMenu = Integer.parseInt(scan.nextLine());

            switch (nMenu) {
                case 0: // 종료
                    System.out.println("종료");
                    bFlag = false;
                    break;
                case 1: // 성적 입력
                    System.out.print("이름 : >> ");
                    strName = scan.nextLine();

                    System.out.print("성적 : >> ");
                    nScore = Integer.parseInt(scan.nextLine());

                    // HashMap에 이름과 성적 입력
                    map.put(strName, nScore);
                    break;
                case 2: // 성적 삭제
                    System.out.print("이름 : >> ");
                    strName = scan.nextLine();
                    map.remove(strName);
                    break;

                case 3: // 성적 검색
                    System.out.print("이름 : >> ");
                    strName = scan.nextLine();
                    nScore = map.get(strName);
                    System.out.println("이름 : " + strName + ", 점수 : " + nScore);
                    break;

                case 4: // 전체성적 조회
                    // HashMap에 있는 모든키들을 가져와 배열에 저장한다.
                    Set<String> keys = map.keySet();
                    Iterator it = keys.iterator();

                    while(it.hasNext()) {
                        String name = (String) it.next();
                        Integer score = map.get(name);
                        System.out.println("이름 : " + name + ", 성적 : " + score);
                    }
                    break;

                case 5: // 전체성적 출력(이름순)
                    TreeMap<String, Integer> tMap = new TreeMap<String, Integer>(map);
                    Set<String> keySet = map.keySet();
                    Iterator<String> keyIter = tMap.keySet().iterator();    // 키값 오름차순 정렬

                    while (keyIter.hasNext()) {
                        String key = (String)keyIter.next();
                        Integer value = tMap.get(key);
                        System.out.println("이름 : " + key + ", 성적 : " + value);
                    }

                    break;

                case 6:
                    List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());

                    // Comparator를 사용하여 성적 내림차순 정렬
                    Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
                        @Override
                        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {  // 값 비교
                            return o2.getValue().compareTo(o1.getValue());
                        }
                    });

                    for (Map.Entry<String, Integer> entry : entryList) {
                        System.out.println("이름 : " + entry.getKey() + ", 성적 : " + entry.getValue());
                    }
                    break;

                default:
                    System.out.println("0 ~ 4사이의 숫자를 입력해주세요.");
            }
        } // end while
    }
}




