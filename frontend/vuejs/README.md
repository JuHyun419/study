# [Do it! Vue.js 입문](http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&barcode=9791188612789)

## node 설치
- https://nodejs.org/en/

## Vue.js devtools 설치
- https://chrome.google.com/webstore/detail/vuejs-devtools/nhdogjmejiglipccpnnnanhbledajbpd/related?hl=en

## 뷰 CLI 설치
- (sudo) npm install -g @vue/cli

## VS Code 플러그인
- Vetur: 문법 강조, vue.js 파일 코드의 하이라이팅 지원
- Vue VSCode Snippets: 코드 조작 지원
- vue-beautify: 코드 정렬

<br>

## 뷰 인스턴스
- 뷰 인스턴스(Instance)는 뷰로 화면을 개발하기 위해 필수적으로 생성해야 하는 기본 단위

```html
<!-- 뷰 인스턴스 형식, new Vue(): 생성자 -->
new Vue({ 
    ...
});
```

### 뷰 인스턴스 옵션 속성
- 인스턴스를 생성할 때 재정의할 data, el, template, methods 등의 속성을 의미함

### template
- 화면에 표시할 HTML, CSS 등의 마크업 요소를 정의하는 속성
- 뷰의 데이터 및 기타 속성들도 함께 화면에 그릴 수 있음

### methods
- 화면 로직 제어와 관계된 메서드를 정의하는 속성
- 마우스 클릭 이벤트 처리(v-on:click, methods)와 같이 화면의 전반적인 이벤트와 화면 동작과 관련된 로직 처리

### created
- 뷰 인스턴스가 생성되자마자 실행할 로직을 정의할 수 있는 속성
- 뷰 인스턴스 라이프 사이클
    - created, beforeCreate, beforeMount, mounted 등등이 존재함

<br>

## 컴포넌트
- 컴포넌트(Component)란 조합하여 화면을 구성할 수 있는 블록(화면의 특정 영역)
- 컴포넌트를 활용하여 화면을 빠르게 구조화하여 일괄적인 패턴으로 개발 할 수 있음
- 화면의 영역을 컴포넌트로 쪼개서 재활용 -> 재사용 편리, 유지보수 편리, 직관적 코드 이해 가능

### 전역(Global) 컴포넌트
- 여러 인스턴스에서 공통으로 사용할 수 있음
- 뷰 라이브러리를 로딩하고 나면 접근 가능한 Vue 변수를 이용하여 등록

```html
Vue.component('컴포넌트 이름', {
    <!-- 컴포넌트 내용 -->
});
```

### 지역(Local) 컴포넌트
- 특정 인스턴스에서만 유효한 범위를 가짐
- 인스턴스에 components 속성을 추가하고 등록

```html
new Vue({
    components: {
        <!-- '컴포넌트 이름': 컴포넌트 내용 -->
    }
});
```


<br>

## 라우팅
- 웹 페이지간의 이동 방법
- 싱글 페이지 애플리케이션(SPA, Single Page Application)에서 주로 사용
- 화면 간의 전환이 매끄럽고, UX 향상

<br>

## Vue 라우터
- 뷰에서 라우팅 기능을 구현할 수 있도록 지원하는 공식 라이브러리

```html
<router-link to="URL 값"> - 페이지 이동 태그, 화면에서는 `<a>` 태그로 표시되며 클릭하면 to에 지정한 URL로 이동
<router-view> - 페이지 표시 태그, 변경되는 URL에 따라 해당 컴포넌트를 뿌려주는 영역
```

<br>

## 뷰 HTTP 통신
- 뷰 리소스, 액시오스(axios)

### [액시오스(axios)](https://github.com/axios/axios)
- HTTP 통신 라이브러리
- Promise 기반의 API 형식이 다양하게 제공

`Promise 기반의 API 형식이란?`
> Promise란, 서버에 데이터를 요청하여 받아오는 동작과 같은 비동기 로직 처리에 유용한 자바스크립트 객체  
> 자바스크립트는 단일 스레드로 코드를 처리하기 때문에 특정 로직의 처리가 끝날때까지 기다리지 않음  
> 따라서 데이터를 요청하고 받아올 때까지 기다린 후 화면에 나타내는 로직을 실행해야 할 때 주로 `Promise`를 활용  
> 그리고 데이터를 받아왔을 때 `Promise`로 데이터를 화면에 표시하거나 연산을 수행하는 등 특정 로직을 수행

```html
<!-- axios cdn -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<!-- HTTP GET 요청 -->
axios.get('URL').then().catch()

<!-- HTTP POST 요청 -->
axios.post('URL').then().catch()

then(): 서버에서 보낸 데이터를 정상적으로 받아온 후 로직 수행
catch(): 데이터를 받아올 때 오류가 발생하면 해당 로직 수행
```

<br>

## 데이터 바인딩
- HTML 화면 요소를 뷰 인스턴스의 데이터와 연결하는 것

### {{ }} - 콧수염 괄호
- 뷰 인스턴스의 데이터를 HTML 태그에 연결하는 가장 기본적인 텍스트 삽입 방식

### v-bind
- 아이디, 클래스, 스타일 등의 HTML 속성(attributes)값에 뷰 데이터 값을 연결할 때 사용

```html
<!-- {{ }} -->
    <div id="app2">
        {{ message }}
    </div>
   
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
    <script>
        new Vue({
            el: '#app2',
            data: {
                message: 'Hello, Vue.js!'
            }
        });
    </script>


<!-- v-bind -->
    <div id="app">
        <p v-bind:id="A">아이디 바인딩</p>
        <p v-bind:class="B">클래스 바인딩</p>
        <p v-bind:style="C">스타일 바인딩</p>
    </div>

        <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
    <script>
        new Vue({
            el: '#app',
            data: {
                A: 10,
                B: 'container',
                C: 'color: blue'
            }
        });

        new Vue({
            el: '#app2',
            data: {
                message: 'Hello, Vue.js!'
            }
        });
    </script>
```

<br>

## 디렉티브
- HTML 태그 안에 `v-` 접두사를 가지는 모든 속성
- 화면의 요소를 더 쉽게 조작하기 위해 사용하는 기능

### v-if
- 지정한 뷰 데이터 값의 참, 거짓 여부에 따라 해당 HTML 태그를 화면에 표시하거나 표시하지 않음

### v-for
- 지정한 뷰 데이터의 개수만큼 해당 HTML 태그를 반복 출력

### v-show
- v-if와 유사하지만, v-if는 해당 태그를 완전히 삭제하지만 v-show는 display:none;으로 태그는 남아있음

### v-bind
- HTML 태그의 기본 속성과 뷰 데이터 속성을 여녁ㄹ

### v-on
- 화면 요소의 이벤트를 감지하여 처리할 때 사용
- ex) `v-on:click`은 해당 태그의 클릭 에빈트를 감지

### v-model
- 폼(form)에서 주로 사용되는 속성
- 폼에 입력한 값을 뷰 인스턴스의 데이터와 즉시 동기화
- input, select, textarea 태그에만 사용할 수 있음

<br>

## 뷰 프로젝트(싱글 파일 컴포넌트 체계)
### HTML 파일에서 뷰 코드 작성 시 한계점
- script 태그 안에서 HTML 코드는 구문 강조가 적용되지 않고, 오탈자를 찾기 어려움
- 가독성이 좋지 않음
- CSS 스타일 시트를 작성하거나 인라인 스타일을 적용하는 건 더 어려움

### 싱글 파일 컴포넌트 체계
- .vue 파일로 프로젝트 구조를 구성하는 방식
- 1개의 확장자 .vue 파일은 뷰 애플리케이션을 구성하는 1개의 컴포넌트와 동일
- .vue 파일의 구조는 다음과 같음

```html
<template>
    <!-- HTML 태그 내용 -->
</template>


<script>
    export default {
        // 자바스크립트 내용
    }
</script>


<style>
    /* CSS 스타일 내용 */
</style>
```

<br>

## 뷰 CLI 명령어
- vue init: 초기 프로젝트를 쉽게 구성해주는 명령어
    - vue init (webpack, webpack-simple, browserify, browserify-simple, simple, pwa)
- Vue 프로젝트 생성
```html
$ sudo npm i -g @vue/cli-init
$ vue init webpack-simple
    Project name
    Project description
    Author
    License
    Use sass? No
$ cd vue-cli
$ npm install
$ npm run dev

```

![image](https://user-images.githubusercontent.com/50076031/128977126-2e2727a3-3422-493a-84d0-80a18964b6f1.png)

- node_modules: npm install 명령어로 다운받은 라이브러리가 존재하는 위치
- src: .vue 파일을 포함한 애플리케이션이 동작하는데 필요한 로직이 들어갈 위치
- index.html: 뷰로 만든 웹 앱의 시작 파일. npm run dev 실행 시 로딩되는 파일
- package.json: npm 설정 파일. 뷰 애플리케이션이 동작하는 데 필요한 라이브러리 정의
- webpack.config.js: 웹팩 설정 파일. 웹팩 빌드를 위해 필요한 로직들을 정의하는 파일

### package.json 파일

![image](https://user-images.githubusercontent.com/50076031/128977630-52ce34ba-c30c-4bef-8c5f-64aae0d126e8.png)


### npm run dev 실행

![image](https://user-images.githubusercontent.com/50076031/128977801-3ed8d2ad-2a83-4085-8e4f-756c8a09ed77.png)

### 뷰 CLI로 프로젝트를 구성하는 과정
- 뷰 CLI 설치
    - (sudo) npm install -g @vue/cli
- 프로젝트 생성
    - vue init webpack-simple
- 관련 라이브러리 설치
    - npm install
- 프로젝트 구동
    - npm run dev
