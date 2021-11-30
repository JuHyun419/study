## 📎 웹서버와 웹 애플리케이션 서버(apache, tomcat ...)

### 웹서버(Web Server)
> 웹서버: 웹 브라우저 클라이언트로부터 HTTP 요청을 받아들이고 HTML 문서와 같은 웹 페이지를 반환하는 컴퓨터 프로그램  
> [위키백과](https://ko.wikipedia.org/wiki/%EC%9B%B9_%EC%84%9C%EB%B2%84#:~:text=%EC%9B%B9%20%EC%84%9C%EB%B2%84(Web%20Server)%EB%8A%94%20HTTP%EB%A5%BC%20%ED%86%B5%ED%95%B4%20%EC%9B%B9%20%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80,%ED%95%98%EB%8A%94%20%EA%B2%BD%EC%9A%B0%EA%B0%80%20%EA%B0%84%ED%98%B9%20%EC%9E%88%EB%8B%A4.)
- 웹 서버의 주 기능은 **웹 페이지를 클라이언트로 전달하는 것**
  - 즉, 정적 컨텐츠(HTML, CSS, JavaScript, 이미지 등)을 포함한 HTML 문서
- 또한 웹 서버가 동적 컨텐츠를 요청 받으면 웹 애플리케이션 서버(WAS)에게 요청을 넘겨주고, WAS에서 처리한 결과를 클라이언트에게 전달해주는 역할도 함

![image](https://user-images.githubusercontent.com/50076031/143876144-796d0ab3-8403-4ef5-8af2-1ee90e42b20f.png)

#### 대표적인 웹서버가 Apache(아파치)
- 아파치는 오픈 소스 프로젝트를 운영하는 비영리 단체의 이름 & 프로젝트 이름
  - Hadoop, Kafka, commons, Maven, Spark, Struts, Tomcat ...

![image](https://user-images.githubusercontent.com/50076031/143873656-03acf6eb-fb70-4200-aeca-579de7ba516c.png)

<br>

### 웹 애플리케이션 서버(WAS, Web Application Server)
> - 웹 애플리케이션과 서버 환경을 만들어 동작시키는 기능을 제공하는 소프트웨어 프레임워크    
> - 인터넷 상에서 HTTP를 통해 사용자 컴퓨터나 장치에 애플리케이션을 수행해 주는 미들웨어로 볼 수 있음  
> - 웹 애플리케이션 서버는 동적 서버 콘텐츠를 수행하는 것으로 일반적인 웹서버와 구분되며, 주로 데이터베이스와 같이 수행  
> [위키백과](https://ko.wikipedia.org/wiki/%EC%9B%B9_%EC%95%A0%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98_%EC%84%9C%EB%B2%84#:~:text=%EC%9B%B9%20%EC%95%A0%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98%20%EC%84%9C%EB%B2%84(Web%20Application,%ED%95%98%EB%8A%94%20%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4%20%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC%EC%9D%B4%EB%8B%A4.))
- 웹 서버와 *웹 컨테이너가 합쳐진 형태로, 웹서버 단독으로는 처리할 수 없는 동적 콘텐츠(데이터베이스 처리가 필요한 로직)를 제공
  - *웹 컨테이너(서블릿 컨테이너): 웹 서버의 컴포넌트 중 하나로 자바 서블릿과 상호작용 함
  - 서블릿의 생명주기를 관리하고, URL과 특정 서블릿을 매핑하며 URL 요청이 올바른 접근 권한을 갖도록 보장
    - 서블릿: 자바를 사용하여 웹페이지를 동적으로 생성하는 서버 프로그램
    - [서블릿](https://velog.io/@han_been/Servlet), [서블릿 컨테이너](https://velog.io/@han_been/%EC%84%9C%EB%B8%94%EB%A6%BF-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88Servlet-Container-%EB%9E%80), [서블릿 생명주기](https://velog.io/@han_been/%EC%84%9C%EB%B8%94%EB%A6%BF-%EC%83%9D%EB%AA%85%EC%A3%BC%EA%B8%B0Servlet-Life-Cycle)
    
#### 대표적인 WAS 종류가 톰캣(아파치 톰캣)

![image](https://user-images.githubusercontent.com/50076031/143875165-eaf2cf71-5f64-45d2-8963-f07763e79810.png)

<br>

### 웹서버는 왜 필요할까?

### 웹 애플리케이션 서버는 왜 필요할까?

### 웹서버와 웹 애플리케이션 서버를 함께 사용하는 이유는 뭘까?

<br>

### Web Service Architecture
- 클라이언트(사용자) -> Web Server
- 클라이언트(사용자) -> WAS -> DB
- 클라이언트(사용자) -> Web Server -> WAS -> DB

### References
- https://www.apache.org/
- https://gap85.tistory.com/45
- https://codechasseur.tistory.com/25