## [HTML/CSS/JS로 나만의 심리테스트 사이트 만들기](https://www.inflearn.com/course/%EC%8B%AC%EB%A6%AC%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%82%AC%EC%9D%B4%ED%8A%B8-%EC%A0%9C%EC%9E%91)

### [링크](https://juhyuns.netlify.app/)


### [Open Graph(오픈 그래프)](https://ogp.me/)
- HTML 문서의 메타정보를 쉽게 표시하기 위해 제목, 미리보기, URL, 이미지 등의 다양한 요소들을 사용할 수 있는 프로토콜
- 링크의 미리보기 제목, 설명, 이미지 등을 추가하는 페이스북의 프로토콜

```html
    <!-- sns 공유 meta tag -->
    <meta property="og:url" content="https://juhyuns-heart.netlify.app" />
    <meta property="og:title" content="주현쓰 십이간지 연애유형" />
    <meta property="og:type" content="website" />
    <meta property="og:image" content="img/share.png" />
    <meta property="og:description" content="주현쓰의 십이간지로 알아보는 연애유형" />
```

![image](https://user-images.githubusercontent.com/50076031/128179373-fea277ff-093b-41df-9136-3e29c23546ff.png)


<br>

### 파비콘(favicon) 적용
- 브라우저 좌측 상단 이미지

```hmtl
    <!-- favicon -->
    <link rel="shortcut icon" href="img/favicon-heart.ico">
    <link rel="apple-touch-icon-precomposed" href="img/favicon-heart.ico" />
```

<br>

### JS(ES6) setTimeOut
- ms만큼 시간 기다린 후 호출할 함수

```javascript
function begin() {
    main.style.WebkitAnimation = "fadeOut 1s";
    main.style.animation = "fadeOut 1s";
    setTimeout(() => {
        qna.style.WebkitAnimation = "fadeIn 1s";
        qna.style.animation = "fadeIn 1s";
        setTimeout(() => {
            main.style.display = "none";
            qna.style.display = "block";
        }, 450)
        let qIndex = 0;
        goNext(qIndex);
    }, 450);
}
```

<br>

### animation css

```html
@keyframes fadeIn {
    from { opacity: 0; }
    to { opacity: 1; }
}

@keyframes fadeOut {
    from { opacity: 1; }
    to { opacity: 0; }
}


@-webkit-keyframes fadeIn {
    from { opacity: 0; }
    to { opacity: 1; }
}

@-webkit-keyframes fadeOut {
    from { opacity: 1; }
    to { opacity: 0; }
}

.fadeIn {
    animation: fadeIn;
    animation-duration: 0.5s;
}

.fadeOut {
    animation: fadeOut;
    animation-duration: 0.5s;
}
```

<br>

### [Kakao Open API](https://developers.kakao.com/)

```html
    <!-- html --> 
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
        Kakao.init('JAVASCRIPT_KEY');
        Kakao.isInitialized();
    </script>

    <!-- js --> 
    function kakaoShare() {
    Kakao.Link.sendDefault({
        objectType: 'feed',
        content: {
          title: '십이간지 연애유형 결과',
          description: '뭘까용',
          imageUrl:
            'http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg',
          link: {
            mobileWebUrl: 'https://developers.kakao.com',
            androidExecutionParams: 'test',
          },
        },
        social: {
          likeCount: 10,
          commentCount: 20,
          sharedCount: 30,
        },
        buttons: [
          {
            title: '웹으로 이동',
            link: {
              mobileWebUrl: 'https://developers.kakao.com',
            },
          },
          {
            title: '앱으로 이동',
            link: {
              mobileWebUrl: 'https://developers.kakao.com',
            },
          },
        ]
      });
}
```

<br>

### [netlify(배포)](https://netlify.app/)

![image](https://user-images.githubusercontent.com/50076031/128186037-4b99ff9f-f9a7-4f31-b5c0-8305ba3c9f12.png)

<br>

### Javascript

```html
const main = document.querySelector("#main"); 

let result = select.indexOf(Math.max(...select)); // 전개구문

const resultName = document.querySelector('.resultName');
resultName.innerHTML = infoList[point].name;

let resultImg = document.createElement('img');
const imgDiv = document.querySelector('#resultImg');
let imgUrl = 'img/image-' + point + '.png';
resultImg.src = imgUrl;
resultImg.alt = point;
resultImg.classList.add('img-fluid');

imgDiv.appendChild(resultImg);

let answerButton = document.createElement('button');
answerButton.classList.add('answerList');
answerButton.classList.add('my-3');
answerButton.classList.add('py-3');
answerButton.classList.add('mx-auto');
answerButton.classList.add('fadeIn');

<!-- Event Listener -->
answerButton.addEventListener("click", function() {
    ...

let status = document.querySelector('.statusBar');
status.style.width = (100 / endPoint) * (qIndex + 1) + '%'; // 상태바 구현
```

<br>
