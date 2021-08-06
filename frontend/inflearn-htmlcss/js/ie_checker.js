$(document).ready(function(){
      checkBroswerHtml();
});
 

// 브라우저를 판별합니다.
function checkBroswerHtml(){

      // UserAgent를 이용해서 IE인지를 체크합니다.
      var ua = navigator.userAgent.toLowerCase();

      // IE7엔 브라우저 엔진명인 Trident가 없고 IE11엔 MSIE란 문자열이 없으므로 두 가지 경우를 다 체크합니다. 
      if(ua.indexOf ('msie') != -1 || ua.indexOf ('trident') != -1 ) {

         var version = 11 ;
          
         ua = /msie ([0-9]{1,}[\.0-9]{0,})/.exec( ua );

         if(ua)
         {
             version = parseInt(ua[1]);
         }

         var classNames = '';
          
         // 기존의 방식에 is-ie 라는 클래스도 추가해봅니다.
         classNames += ' is-ie';

         // 마찬가지로 기존의 방식에 현재 버전 표시를 추가해봅니다.
         classNames += ' ie' + version;

         for( var i = version + 1 ; i <= 11 ; i ++ ) {
             classNames +=  ' lt-ie' + i;
         }
          
         // html 태그에 클래스를 추가합니다.
         document.getElementsByTagName ('html')[0].className += classNames;
      }
}