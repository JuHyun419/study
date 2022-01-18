# 📝 DBCP(DataBase Connection Pool)

클라이언트와 서버 사이드인 웹 어플리케이션에서, 사용자의 요청에 따라 Connection이 생성된다면 수 많은 사용자가 요청을 했을 때 서버에 과부하가 걸리게 됩니다.  
이러한 상황을 예방하기 위해  
- **미리 일정 갯수의 Connection을 만들어 Pool에 저장**을 하고  
- **사용자의 요청이 발생하면 Connection을 제공**하고  
- **사용자와의 연결이 종료된다면 Pool에 다시 반환**하여 보관하는 것을 의미합니다.


#### 즉, Connection Pool에 대해 정리하자면 다음과 같습니다.
- 여러 개의 DB Connection을 하나의 Pool에 모아놓고 관리
- DB 커넥션 객체를 여러 개 생성한 뒤 Pool에 담아놓고 필요할 때 불러와서 사용
- 만약, 빌려줄 수 있는 Connection이 없다면 Connection 객체가 반환할 때 까지 클라이언트는 대기 상태로 전환
- 사용이 끝난 커넥션 객체는 다른 작업에서 다시 사용할 수 있도록 Pool에 반환

<img width="820" alt="캡쳐1" src="https://user-images.githubusercontent.com/50076031/128815183-82d567be-21f7-4953-9160-35b3a048995b.png">

<br>

## 🤔 그럼, 이러한 DBCP(DataBase Connection Pool)은 왜 필요한 걸까요?
웹 어플리케이션은 CRUD와 같은 작업을 처리할 때 주로 데이터베이스를 이용합니다.  
DBMS나 기타 외부와의 접속이 빈번하게 필요한 시스템에는 반드시 존재해야 하는 기능인데 데이터베이스와 Connection을 맺는 작업은 매우 느리며 자원을 많이 소모하는 작업입니다.  
이와 같이 **서버가 물리적으로 데이터베이스 서버에 연결되어 Connection을 맺는 작업은 비용이 큰 부분** 입니다.  
만약, 다수의 사용자들이 동시다발적으로 데이터베이스의 Connection 연결을 요청한다면 최악의 경우 서버가 다운이 될 수 있습니다.
따라서 이러한 큰 비용의 문제를 해결하기 위해 DBCP를 사용할 수 있습니다.  

<img width="820" alt="캡쳐1" src="https://user-images.githubusercontent.com/50076031/128815187-8dcd8fa0-f8a2-4e0a-92d0-1b6770b7a8f6.png">

위 사진과 같이 Pool을 만들어서 미리 생성한 여러 Connection 객체를 담아두고 클라이언트가 요청할 때 마다 하나씩 빌려줍니다.  
클라이언트가 사용을 마친 후에는 Pool에 다시 Connection 객체를 반환하기 때문에 데이터베이스 접근 요청이 존재하더라도  
새로운 Connection 객체를 생성할 필요가 없습니다.

앞에서 말씀드린 대로 다수의 사용자들이 동시다발적으로 요청해서 Pool에 빌려줄 수 있는 커넥션이 존재하지 않는 경우에는 에러가 발생하지 않고 
다른 클라이언트가 커넥션을 Pool에 반환할 때까지 요청한 클라이언트는 대기상태로 전환이 됩니다.  
그 후 다른 클라이언트가 커넥션을 반환하면, 대기상태인 클라이언트가 해당 커넥션을 사용하는 구조로 되어있습니다.

<br>

## 🤔 DBCP(DataBase Connection Pool)의 옵션
이제까지 DBCP의 개념과 구조, 사용 이유에 대해 살펴보았습니다.  
[Apache의 Commons DBCP](https://commons.apache.org/proper/commons-dbcp/) 의 경우 설정할 수 있는 옵션은 다음과 같습니다.(Commons DBCP 1.x 기준입니다.)  
> Commons DBCP는, 커넥션 풀 구현체중 가장 많이 쓰이는 오픈소스 입니다.  
> [Spring Boot 2.0부터는 HikariCP](https://www.concretepage.com/spring-boot/spring-boot-hikaricp#:~:text=HikariCP%20is%20fast%2C%20simple%2C%20reliable,because%20HikariCP%20offers%20superior%20performance.)를 채택해서 커넥션 풀을 관리합니다.(Tomcat Pool -> HikariCP)

<img width="640" alt="캡쳐1" src="https://user-images.githubusercontent.com/50076031/128815728-c30abd0e-c6b1-466c-aac1-b827a18febe4.png">

위와 같은 설정 외에도 validationQuery, testOnCreate, testOnBorrow, testOnReturn, defaultAutoCommit, defaultReadOnly 등등 다양한 설정이 존재합니다.  
다양한 설정이 궁금하시다면 [Apache의 공식문서](https://commons.apache.org/proper/commons-dbcp/configuration.html) 를 참고해주세요 :)

<br>

## 🎯 커넥션 갯수와 관련된 속성 및 조건

### maxActive >= initialSize
- 동시에 사용할 수 있는 최대 커넥션의 갯수(maxActive)가 10이고, 최초 커넥션을 맺을 때 Pool에 생성되는 커넥션의 갯수(initialSize)가 20개라면, 
  initialSize 값이 최대 커넥션 갯수인 maxActive 값보다 커서 논리적으로 오류가 있는 설정입니다.

### maxIdle >= minIdle
- 당연하지만 최대로 유지할 커넥션의 갯수가 최소한으로 유지될 커넥션의 갯수보다 많아야 합니다.

### maxActive = maxIdle
- maxActive와 maxIdle 값이 동일한 것이 좋습니다. 동시에 사용할 수 있는 최대 커넥션의 갯수(maxActive)가 20이고, Pool에 반납할 때 최대로 유지될 수 있는 커넥션의 갯수(maxIdle)가 10이라고 가정해 보겠습니다. 
  커넥션을 동시에 10개를 사용하고 있는상황에서 1개의 커넥션이 추가로 요청된다면 maxActive = 20이므로 1개의 추가 커넥션을 데이터베이스에 연결한 후 Pool은 비즈니스 로직으로 커넥션을 전달합니다. 
  이후 비즈니스 로직이 커넥션을 사용한 후 Pool에 반납할 경우, maxIdle = 10이므로 커넥션을 실제로 닫아버리므로, 일부 커넥션을 매번 생성했다 닫는 비용이 발생할 수 있습니다.

### initialSize = maxActive = maxIdle = minIdle
- 위 네 가지의 설정은 동일한 값으로 통일해도 무방합니다. 커넥션 갯수와 관련된 가장 중요한 성능 요소는 일반적으로 커넥션의 최대 갯수 입니다. 따라서 위 항목의 설정 값 차이는 성능을 좌우하는 중요 변수는 아닙니다.

### maxActive
- maxActive의 값은 DBMS의 설정과 애플리케이션 서버 갯수, Apache, Tomcat 등에서 동시에 처리할 수 있는 사용자 수 등을 고려해서 설정해야 합니다. 
  DBMS가 수용할 수 있는 커넥션의 갯수를 확인한 후 애플리케이션 서버 인스턴스 1개가 사용하기에 적절한 갯수를 설정합니다. 
  만약 다수의 사용자가 요청을 하는 상황에서 커넥션을 많이 사용할 때 maxActive의 값이 충분히 크지 않다면 병목 지점이 될 수 있습니다. 
  반대로 사용자가 적어서 사용중인 커넥션이 많지 않은 시스템에서는 maxActive 값을 지나치게 작게 설정하지 않는 한 성능에는 큰 영향이 없습니다.
  
<br>

### 마무리
이상으로 간단하게 DBCP에 대해 알아보았습니다.  
이론적으로만 정리를 하였으며 실제로는 DBMS와 애플리케이션 서버의 자원, 기타 예외 상황, Tomcat 설정, 사용자의 요청, TPS 등을 고려하여 설정을 해야 합니다.   
특히 Commons DBCP와 같은 커넥션 풀 라이브러리는 전체 애플리케이션의 성능과 안정성에 중요한 역할을 하기 때문에 충분한 시간과 노력을 투자해야 한다고 합니다.  
만약 실무에서 업무를 진행하며 직접적으로 DBCP의 설정을 손대야 할 일이 발생한다면, 좀 더 자세히 정리해보도록 하겠습니다.

Commons DBCP에 대한 자세한 정리는 다음 포스팅을 추천드립니다.
- https://d2.naver.com/helloworld/5102792

<br><br>


### References
- https://beaniejoy.tistory.com/24
- https://brownbears.tistory.com/289
- https://commons.apache.org/proper/commons-dbcp/configuration.html
- https://d2.naver.com/helloworld/5102792
