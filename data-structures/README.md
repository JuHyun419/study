# 📎 Data-Structure(자료구조, 알고리즘)

## 재귀 함수
  - 자기 자신을 호출하는 함수
  - Base case
    - 간단히 결과를 반환하는 부분
  - Recursive case
    - 자기 자신을 호출하는 부분

### 문자열의 길이 계산
![image](https://user-images.githubusercontent.com/50076031/123888109-bd088380-d98d-11eb-8345-ce1b7461b0f6.png)

### 문자열 뒤집어 프린트
![image](https://user-images.githubusercontent.com/50076031/123888502-97c84500-d98e-11eb-8a5a-cc38790500f6.png)

### 2진수로 변환하여 출력
  - 음이 아닌 정수 n을 2진수로 변환하여 출력
  - n을 2로 나눈 몫을 먼저 2진수로 변환하여 인쇄(Recursive)
  - n을 2로 나눈 나머지를 인쇄

### Recursion VS Iteration
  - 모든 순환함수는 반복문으로 변경 가능
  - 그 역도 성립. 즉, **모든 반복문은 Recursion 으로 표현 가능**
  - 순환함수는 복잡한 알고리즘을 단순하고 알기쉽게 표현하는 것을 가능하게 함


### 미로찾기
Recursive Thinking
  - 현재 위치에서 출구까지 가는 경로가 있으려면
    - 현재 위치가 출구이거나 혹은
    - 이웃한 셀들 중 하나에서 현재 위치를 지나지 않고 출구까지 가는 경로가 있거나
![image](https://user-images.githubusercontent.com/50076031/123961104-f9b69800-d9ea-11eb-90cf-b53413901564.png)
      
<br>

### 순열(Permutation)
  - 선택 순서가 결과에 영향을 미치는 경우
  - nPr => n개의 숫자가 씌여진 공 중 r개를 뽑아 나열한 경우의 수

### 조합(Combination)
  - 선택 순서가 결과에 영향을 주지 않는 경우
  - nCr => n개의 숫자 중 r개를 뽑기만 하는 경우의 수

#### 예제
  - {1, 2, 3, 4} 숫자가 주어진 경우 
  - (순열) 만들 수 있는 가장 큰 두 자리 수를 구하라.
  - (조합) 두 수를 더했을 때 가장 큰 합을 구하라.



https://www.youtube.com/channel/UC-cOmaeWLm7Ii7erMQNatvA