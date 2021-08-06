const main = document.querySelector("#main"); 
const qna = document.querySelector("#qna");
const result = document.querySelector("#result");
const endPoint = 12; // 질문 갯수
const select = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; // 응답 버튼 선택할 때 담을 배열


/* 응답 결과 */
function calResult() {
    // let pointArray = [
    //     { name: 'mouse', value: 0, key: 0 },
    //     { name: 'cow', value: 0, key: 1 },
    //     { name: 'tiger', value: 0, key: 2 },
    //     { name: 'rabbit', value: 0, key: 3 },
    //     { name: 'dragon', value: 0, key: 4 },
    //     { name: 'snake', value: 0, key: 5 },
    //     { name: 'horse', value: 0, key: 6 },
    //     { name: 'sheep', value: 0, key: 7 },
    //     { name: 'monkey', value: 0, key: 8 },
    //     { name: 'chick', value: 0, key: 9 },
    //     { name: 'dog', value: 0, key: 10 },
    //     { name: 'pig', value: 0, key: 11 },
    // ]

    // for (let i = 0; i < endPoint; i++) {
    //     let target = qnaList[i].a[select[i]];
    //     for (let j = 0; j < target.type.length; j++) { // type 반복
    //         for (let k = 0; k < pointArray.length; k++) { // 동물의 name 카운트
    //             if (target.type[j] === pointArray[k].name) {
    //                 pointArray[k].value += 1;
    //             }
    //         }
    //     }
    // }

    // JS 배열 정렬(value 기준) -> compareFunction
    // let resultArray = pointArray.sort(function (a, b) {
    //     if (a.value > b.value) {
    //         return -1;
    //     }
    //     if (a.value < b.value) {
    //         return 1;
    //     }
    //     return 0;
    // });
    

    /* 위 주석 알고리즘 개선 */
    let result = select.indexOf(Math.max(...select)); // ...select -> 전개구문
    return result;
}

function setResult() {
    let point = calResult();
    const resultName = document.querySelector('.resultName');
    resultName.innerHTML = infoList[point].name;

    let resultImg = document.createElement('img');
    const imgDiv = document.querySelector('#resultImg');
    let imgUrl = 'img/image-' + point + '.png';
    resultImg.src = imgUrl;
    resultImg.alt = point;
    resultImg.classList.add('img-fluid');

    imgDiv.appendChild(resultImg);

    const resultDesc = document.querySelector('.resultDesc');
    resultDesc.innerHTML = infoList[point].desc;
}

/* 마지막 응답 후 결과화면 */
function goResult() {
    qna.style.WebkitAnimation = "fadeOut 1s";
    qna.style.animation = "fadeOut 1s";
    setTimeout(() => {
        result.style.WebkitAnimation = "fadeIn 1s";
        result.style.animation = "fadeIn 1s";
        setTimeout(() => {
            qna.style.display = "none";
            result.style.display = "block";
        }, 450)});
    setResult();
    calResult();
}


/* 응답 버튼 생성 함수 */
function addAnswer(answerText, qIndex, idx) {
    let a = document.querySelector('.answerBox');

    // createElement(): 지정한 tagName의 HTML 요소를 만들어서 반환
    let answerButton = document.createElement('button');
    answerButton.classList.add('answerList');
    answerButton.classList.add('my-3');
    answerButton.classList.add('py-3');
    answerButton.classList.add('mx-auto');
    answerButton.classList.add('fadeIn');

    // div > button
    a.appendChild(answerButton);

    answerButton.innerHTML = answerText;

    answerButton.addEventListener("click", function() {
        // 위에서 생성한 모든 버튼 선택
        let children = document.querySelectorAll('.answerList');
        for (let i = 0; i < children.length; i++) {
            children[i].disabled = true;
            // fadeOut 버튼 사라지기
            children[i].style.WebkitAnimation = "fadeOut 0.5s";
            children[i].style.animation = "fadeOut 0.5s";
        }
        setTimeout(() => {
            // 응답 결과 +1
            let target = qnaList[qIndex].a[idx].type;
            for (let i = 0; i < target.length; i++) {
                select[target[i]] += 1;
            }
            for (let i = 0; i < children.length; i++) {
                children[i].style.display = 'none';
            }
            goNext(++qIndex);
        }, 450)
    }, false);
}


/* 다음 질문 */
function goNext(qIndex) {

    // 질문이 끝났을때
    if (qIndex === endPoint) {
        goResult();
        return;
    }
    var q = document.querySelector('.qBox');
    q.innerHTML = qnaList[qIndex].q; // q: '1. 이성 사이에 진정한 친구는 있다, 없다?',

    /*
    a: [
      { answer: 'a. 이성 사이에 친구가 어딨어? 절대 없어', type: ['cow', 'tiger', 'dragon', 'chick'] },
      { answer: 'b. 친구 있지, 절대 이성으로만 안 보일뿐', type: ['mouse', 'rabbit', 'horse', 'snake', 'dog', 'monkey'] },
      { answer: 'c. 난 잘 모르겠어..', type: ['sheep', 'pig' ] },
    ]
    */
    for (let i in qnaList[qIndex].a) {
        addAnswer(qnaList[qIndex].a[i].answer, qIndex, i);
    }

    let status = document.querySelector('.statusBar');
    status.style.width = (100 / endPoint) * (qIndex + 1) + '%'; // 상태바 구현
}


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