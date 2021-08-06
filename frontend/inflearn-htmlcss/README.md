## [만들면서 배우는 HTML/CSS](https://www.inflearn.com/course/html-css-sucademy/dashboard)

### CSS Reset
- 각 브라우저마다 설정되어 있는 기본 스타일이 다르기 때문에 초기화 작업이 필요함
- 태그의 간격, 색상 등을 통일
http://html5doctor.com/html-5-reset-stylesheet/

<br>

### 배경이미지
- css의 background: url 속성

```html

<div class="slider">
    <div class="background_01"></div>
    <div class="background_02"></div>
    <div class="background_03"></div>
</div>

html, body {
    width: 100%;
    height: 100%;
}

<!-- wrapper, viewport: bxslider 사용 후 개발자도구를 통해 확인했을때 생기는 태그 -->
.bx-wrapper, .bx-viewport, .slider {
    width: 100%;
    height: 100%;
}

/* 부모 태그인 body, html에 width, height을 100%로 설정해야 자식 태그에도 설정됨 */
.background_01 {
    width: 100%;
    height: 100%;
    background: url("../images/a.jpg") no-repeat;
    background-size: cover;
    background-position: center;
}
```

<br>

### 이미지 slide => bxslider 사용
https://bxslider.com/

```html
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
        
<script>
<html>
<head>

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

  <script>
    $(document).ready(function(){
      $('.slider').bxSlider();
    });
  </script>

</head>
<body>

  <div class="slider">
    <div>I am a slide.</div>
    <div>I am another slide.</div>
  </div>

</body>
</html>
</script>
```

<br>

### position
- 웹 문서 안 요소들의 배치를 어떻게 할 지 정하는 속성
- text, image를 원하는 위치, 어떤 방식으로 놓을지 배치할 수 있음
- static, relative, absolute, fixed, sticky

```html
static: 요소를 문서 흐름에 맞춰 배치
relative: 이전 요소(보통 부모요소)에 자연스럽게 연결하여 위치 지정
absolute: 원하는 위치를 지정해 배치
fixed: 지정한 위치에 고정하여 배치
sticky: 위치에 따라 동작방식이 달라짐

https://engkimbs.tistory.com/922
```

<br>

### z-index
- position을 사용해 배치를 결정하고, 요소들의 수직 위치를 z-index 속성으로 결정
- 숫자가 클수록 위로, 작을수록 아래로 내려옴

![image](https://user-images.githubusercontent.com/50076031/127421710-e69bbf24-953d-4139-a95d-b60f6e9d090e.png)


<br>

### css inline, block
- inline: 줄을 바꾸지 않고 다른 요소와 함께 한 행에 위치하려는 속성(<a> 태그)
- block: inline과는 다르게 한 줄에 나열되지 않고 자체로 한 줄을 차지하는 속성(<p> 태그)
- inline-block: inline과 같이 한 줄에 표현하는 속성 + margin, width, height 속성 정의 가능

- display: inline-block 속성 없을 때

![image](https://user-images.githubusercontent.com/50076031/127422600-8b091633-ceca-4e6e-816e-bbc177b3caba.png)


- display: inline-block 속성 있을 때

![image](https://user-images.githubusercontent.com/50076031/127422619-6dfcfb04-349c-424b-96b8-be5f8d5d57d3.png)


<br>

### <li>태그 등 엔터치면서 나타나는 여백 제거하기
    
```html
<li>
    <a>
        <i>test</i>
    </a>
</li>

<li>
    <a>
        <i>test</i>
    </a>
</li>
```

- (1) 모든 공백 제거 => 가독성 안좋아짐 
- (2) 공백에 주석처리 => 가독성 안좋아짐
- (3) css 속성으로 처리(부모 속성에 지정) -> 주로 사용


```html

/* 엔터로 인해 나타나는 불필요한 여백 제거 */
.contents_list {
    font-size: 0;
}

contents_list 속성의 자식에는 다시 font-size 크기를 지정

```

<br>

### icon - [fontawesome](https://fontawesome.com/)

```html
<!-- cdn -->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

<i class="fab fa-github"></i>
<i class="fab fa-blogger-b"></i>
<i class="fab fa-linkedin-in"></i>
<i class="fab fa-facebook-k"></i> 
...
```


### contents 본문 위치 조정 - top, left

```html
.contents {
    position: absolute;
    top: 50%; 
    left: 50%;
    text-align: center;
    z-index: 15;
    font-family: "Malgun Gothic", sans-serif;
}
```

### 최종 결과물

![image](https://user-images.githubusercontent.com/50076031/127422619-6dfcfb04-349c-424b-96b8-be5f8d5d57d3.png)

