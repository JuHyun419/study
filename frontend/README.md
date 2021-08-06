## HTML, CSS 기초 예제
shift + option + f

### 설치한 플러그인
- Colorize
- Import cost(파일 크기 미리 보여줌)
- IntelliSense for CSS class names in HTML (자동완성 지원)
- Korean Language Pack for Visual Studio Code
- Liver Server(브라우저 창에서 결과 미리보기 제공)
- Material Icon Theme(폴더 구조화)
- Dracula Official(테마)
- Prettier: Code formatter
- htmltagwrap(태그 쉽게 감싸줌)
  - Alt + w(윈도우)
  - Option + w(맥)
- HTML Preview(결과물 미리보기)
  - command + Shift + v
- Beautify(태그 가독성)
- HTML CSS Support + CSS Peak
  - CSS 정의를 바로 확인할 수 있음
  - F12 & option + F12


### 기타
#### CSS 적용 순서
- Inline Style
- External and internal style sheets
  - Inline
  - ID
  - class
  - Tag
- Browser default

#### CSS 단위
- 추천단위: px, rem, %

![image](https://user-images.githubusercontent.com/50076031/126729083-3c79bb5c-c9c3-41db-88f4-4653a4fb6250.png)

![image](https://user-images.githubusercontent.com/50076031/126729135-0856f27d-9ed7-4440-96b2-fe62cd2971ff.png)

https://e-koreatech.step.or.kr/page/lms/?m1=home%25

<br>

#### CSS 색상

![image](https://user-images.githubusercontent.com/50076031/126729440-76f131b2-168e-4aa7-9939-842a98bfce49.png)

https://e-koreatech.step.or.kr/page/lms/?m1=home%25

<br>

#### CSS 폰트

```html
<!-- 구글 폰트: https://fonts.google.com/ -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=sofia">
```

<br><br>

![image](https://user-images.githubusercontent.com/50076031/126587312-3e6f0f4b-27b1-40a1-844e-57c701857bdd.png)


<br><br>


![image](https://user-images.githubusercontent.com/50076031/126515759-d5af9c63-abbd-4463-8658-865e97eed498.png)

- 스포이드(개발자도구로 화면상에 원하는 색 뽑아올 때 사용)

<br>

![image](https://user-images.githubusercontent.com/50076031/126586509-5f6a1dae-f53b-420f-bdfc-a58ccf019d8c.png)

<br><br>

### [Bootstrap icon](https://blog.getbootstrap.com/2021/01/07/bootstrap-icons-1-3-0/)

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    
</head>
<body>
    <i class="bi-alarm"></i> <br/><br/>
    <i class="bi-alarm-fill"></i>
    <i class="bi-cup"></i>
    <i class="bi-cup-straw"></i>
    <i class="bi-cup-fill"></i>
</body>
</html>
```

![image](https://user-images.githubusercontent.com/50076031/126859826-7aece7f5-c6b2-40a8-a68e-aa6c8d3666a4.png)

### [CSS icon](https://www.w3schools.com/css/css_icons.asp)

<br><br>

### CSS 레이아웃
- position
  - HTML 요소가 위치를 결정하는 방식을 설정

![image](https://user-images.githubusercontent.com/50076031/126860278-125cb47b-4383-4e7f-8c29-27c61675e085.png)

- float
  - css에서 정렬하기 위해 사용되는 속성으로 각 객체를 오른쪽이나 왼쪽으로 정렬하여 전체 문서를 배치(layout)할 때 사용

  ```html
  float: left
  float: right
  ```

- grid
  - 페이지를 여러 주요 영역으로 나누거나, 크기와 위치를 속성들을 빠르게 설정하는 방법

<br><br>

### [CSS Templates](https://www.w3schools.com/css/css_templates.asp)
- 반응형 웹 float, flexbox, grid 확인

<img width="430" alt="캡쳐1" src="https://user-images.githubusercontent.com/50076031/126861703-4338bcc7-03ad-4b9a-a70c-6af25bfd6628.png">

<br><br>

## [Emmet](https://docs.emmet.io/abbreviations/syntax/)

```html
div>ul>li
    <div>
        <ul>
            <li></li>
        </ul>
    </div>


div+p+span
    <div></div>
    <p></p>
    <span></span>


ul>li*5
<ul>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
</ul>


div.divClass
<div class="divClass"></div>

div#divClass
<div id="divClass">
    
```


<br><br>

### 참고 
- https://www.inflearn.com/course/%EC%9B%B9%EA%B0%9C%EB%B0%9C-%EA%B8%B0%EC%B4%88-html-css/dashboard

- https://youtu.be/_YrXKxY8PTY

- https://codepen.io/trending

- https://e-koreatech.step.or.kr/page/lms

- https://www.inflearn.com/course/html-css-sucademy/dashboard
