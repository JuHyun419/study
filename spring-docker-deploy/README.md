## 📎  Spring Boot + Docker + AWS EC2 배포하기(Mac M1)

안녕하세요, 이번 시간에는 스프링 부트 애플리케이션을 AWS EC2에 도커를 통해 배포해보도록 하겠습니다.

※ 틀린 내용이 존재할 수 있으니 참고해서 봐주시면 감사하겠습니다 :)

저의 환경 설정은 다음과 같습니다.

- Mac OS(M1), 인텔리제이, Spring Boot, Java 8, Gradle

또한 이번에 M1에서 배포를 할 때, 기존 블로그 내용대로 작업을 하던 도중 에러가 발생했고 약간의 삽질이 있었는데요 .. 😭

간략히 정리해보도록 하겠습니다.

![image](https://user-images.githubusercontent.com/50076031/126147890-f97de3db-117b-4ba1-882c-96a3a5a189fc.png)
https://class101.net/products/5fc4a3b4fc231b000d85661b

대략적인 과정은 위와 같습니다.

- ``Dockerfile`` 을 build 해서 ``docker image`` 파일을 생성합니다.
- docker image 파일을 ``dockerhub``에 push 합니다.
- 서버(AWS EC2)에서 ``dockerhub``에 존재하는 docker image 파일을 pull로 받아옵니다.
- docker run 명령어를 통해 ``docker image`` 파일을 실행합니다.

<br>

### 🎯  스프링 부트 프로젝트 생성

![image](https://user-images.githubusercontent.com/50076031/126148062-97f96a43-6689-4711-845e-12296bd43806.png)

스프링 부트 프로젝트를 생성하고, 간단하게 Controller 생성한 뒤 위와 같이 작성합니다.  
(스프링 부트 프로젝트의 생성 방법에 대해서는 생략하겠습니다.)

<br>


### 🎯  Dockerfile 생성

![image](https://user-images.githubusercontent.com/50076031/126148134-27649640-1fa5-4ff7-8174-7872abe3580b.png)

위 사진과 같이 프로젝트 하위에 Dockerfile 이라는 파일을 생성하고 아래 내용을 작성합니다.

```java
FROM openjdk:8-jdk-alpine
// FROM amazoncorretto:11 ==> amazon corretto 11 사용할 경우
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
// ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]
// => 설정파일을 분리해서 사용할 때
// java -jar -Dspring.profiles.active=prod app.jar
위 코드에 대한 설명을 간략히 나타내면 다음과 같습니다.
```


**FROM openjdk:8-jdk-alpine**
- open jdk java8 버전의 환경을 구성합니다.
- Amazon의 corretto 버전을 사용할 경우, 주석처리 한 부분으로 사용하면 됩니다.

ARG JAR_FILE=build/libs/*.jar
- build가 되는 시점에 JAR_FILE 이라는 변수명에 build/libs/*.jar 표현식을 선언했다는 의미입니다.
- build/libs 경로는 gradle로 빌드했을 때 jar 파일이 생성되는 경로입니다.
- Maven의 경우 target/*.jar 로 설정해주시면 됩니다.

**COPY ${JAR_FILE} app.jar**
- 위에 선언한 JAR_FILE 을 app.jar 로 복사합니다.

**ENTRYPOINT ["java","-jar","/app.jar"]**
- jar 파일을 실행하는 명령어(java -jar jar파일) 입니다.

**ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]**
- 운영 및 개발에서 사용되는 환경 설정을 분리해서 사용할 경우, 위와 같이 ENTRYPOINT를 설정할 수 있습니다.

<br>

### 🎯  gradle 빌드

![image](https://user-images.githubusercontent.com/50076031/126148304-87e4a7e2-fedf-4b42-9d8a-2582c5f19805.png)

```java
./gradlew build -x test (-x test: 테스트 실행 X)
```

위 명령어를 통해 빌드하면 아래와 같이 build/libs 경로에 jar 파일이 생성됩니다.

![image](https://user-images.githubusercontent.com/50076031/126148310-9ad82666-0697-49b1-a37b-fea458c85b10.png)

<br>

### 🎯  Docker Hub
https://hub.docker.com/

위 사이트에 들어가서 회원가입을 하고 로그인을 합니다.

![image](https://user-images.githubusercontent.com/50076031/126148390-ac8726c4-4f6a-4a06-ab4d-3afd4710092e.png)
그 후 Repositories > Create Repository 로 들어가서 도커 레포지토리를 생성합니다.

![image](https://user-images.githubusercontent.com/50076031/126148405-e7eea57e-0166-49ce-91c9-8acddf4cbe9c.png)
Repository 이름과 Public으로 설정하고 Create 를 클릭합니다.

<br>

### 🎯  Docker Image build
호옥시 도커가 설치되어있지 않다면 아래 사이트를 통해 먼저 도커를 설치해주세요 :)

https://itkoo.tistory.com/10




위 명령어를 통해 dockerfile -> docker image 로 빌드를 진행합니다.

![image](https://user-images.githubusercontent.com/50076031/126148512-f13f7ca9-456a-4485-98e9-523e23f9ddb8.png)

```java
// gradle
docker build --build-arg DEPENDENCY=build/dependency -t 도커허브 ID/Repository --platform linux/amd64 .

// maven
docker build -t 도커허브 ID/Repository --platform linux/amd64 .
```

- 가장 마지막 점(.) 도 포함해야 합니다.
- 맥북 M1의 경우 --platform linux/amd64 옵션을 마지막에 추가해주어야 합니다.

만약 --platform linux/amd64 옵션을 추가하지 않고 빌드를 하면 아래와 같은 에러가 발생합니다. 😭

![image](https://user-images.githubusercontent.com/50076031/126148523-6ebe3f36-7f3c-406b-88a1-36a318e7f791.png)



위 문제는 [깃허브](https://github.com/google/cadvisor/issues/2763) 에 자세히 나와있습니다.
![image](https://user-images.githubusercontent.com/50076031/126148637-3912178d-b330-4664-b1dd-610e996be041.png)
애플 M1의 경우 도커 이미지로 빌드할 때 --platform 옵션을 사용해야 합니다.

<br>

### 🎯  Docker Image push
위에서 생성한 ``docker image`` 파일을 ``Docker Hub``에 push를 해줍니다.
![image](https://user-images.githubusercontent.com/50076031/126148642-63cd3f35-c59b-49c9-b0a9-3a4294d40039.png)
push 명령어는 docker hub의 Repository에 보면 확인할 수 있습니다.

![image](https://user-images.githubusercontent.com/50076031/126148739-646c7036-57fe-4284-a828-271802866e28.png)
만약 위와 같이 denied: requested access to the resource is denied 라는 문구가 나오면 로그인을 해주어야 합니다.

<br>

![image](https://user-images.githubusercontent.com/50076031/126148744-13539847-ba7d-4b1d-bb17-c70a530475c0.png)

![image](https://user-images.githubusercontent.com/50076031/126148750-2886b56d-ad59-4529-86f3-6adb443c26cf.png)

![image](https://user-images.githubusercontent.com/50076031/126148795-40f2e94a-5b08-423f-964b-0eafd4bf7b83.png)

docker login 명령어를 통해 로그인을 한 뒤 다시 push하면 정상적으로 push가 되고, docker hub의 Repository에

새로운 TAG(latest)가 생성된 걸 확인할 수 있습니다.

![image](https://user-images.githubusercontent.com/50076031/126148880-44afad1d-8980-40a3-b9cd-22b6625fa2f2.png)
현재 상태는 위와 같이 Dockerfile 을 통해 docker image로 빌드하고, docker hub에 push한 상태입니다.

이제 서버(AWS EC2)에서 도커를 설치하고, 애플리케이션을 배포하면 끝입니다 !

<br>

### 🎯  AWS EC2
아래 링크를 통해 AWS EC2 인스턴스를 생성하고, 도커를 설치한 후 애플리케이션을 배포해보도록 하겠습니다.  
[AWS EC2 인스턴스 생성하기](https://zzang9ha.tistory.com/329?category=954133)


**도커 설치**
- $ sudo yum install docker  

**도커 실행**
- $ sudo systemctl start docker

**도커 허브에 존재하는 이미지 파일 pull**
- $ sudo docker pull juhyun419/sample

**도커 이미지를 통해 스프링 부트 애플리케이션 배포(실행)**
- $ sudo docker run -p 8080:8080 juhyun419/sample


![image](https://user-images.githubusercontent.com/50076031/126149139-b68c5c97-4476-4d9b-8c40-a91bd749e7d6.png)
도커 이미지를 통해 스프링 부트 애플리케이션이 정상적으로 실행이 됩니다.

![image](https://user-images.githubusercontent.com/50076031/126149179-03ad968a-458d-40c8-99f0-030b1a3cbd8c.png)
최종적으로 AWS의 IP로 접속해보면 위와 같이 정상적으로 배포가 된 걸 확인할 수 있습니다.


<br><br>

![image](https://user-images.githubusercontent.com/50076031/126149198-d028b366-0adf-4ca1-82cc-43001dcf9126.png)

### **References**
- https://itkoo.tistory.com/10
- https://spring.io/guides/gs/spring-boot-docker/
- https://sas-study.tistory.com/399
- https://class101.net/products/5fc4a3b4fc231b000d85661b
- https://www.pinterest.co.kr/pin/984881012230730727/
- https://docs.aws.amazon.com/ko_kr/corretto/latest/corretto-11-ug/docker-install.html
 
