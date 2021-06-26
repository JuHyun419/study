# 📝 더 자바, 코드를 조작하는 다양한 방법
https://www.inflearn.com/course/the-java-code-manipulation/dashboard

  - JVM 구조를 이해할 수 있습니다.
  - 바이트코드 조작은 무엇인지 어떻게 사용할 수 있는지 학습합니다.
  - 리플렉션이 무엇인지 어떻게 사용할 수 있는지 학습합니다.
  - 프록시가 무엇이며 어떻게 사용할 수 있는지 학습합니다.
  - 애노테이션 프로세서가 무엇인지 어떻게 사용할 수 있는지 학습합니다.
  
  ![image](https://user-images.githubusercontent.com/50076031/123371598-09824680-d5bd-11eb-94d7-43a1a8fdaeef.png)
  
  - **이번 강좌는 자바 개발자라면 한 번쯤은 사용해보거나 들어봤을 스프링, 스프링 데이터 JPA, 하이버네이트, 롬복 등의 기반이 되는 자바 기술에 대해 학습합니다.**
  

<br><br><br>

## JVM 이해하기

### JVM(Java Virtual Machine)
  - 자바 가상 머신, 자바 바이트코드(.class 파일)을 OS에 특화된 코드로 변환(인터프리터 + JIT 컴파일러)하여 실행

### JRE(Java Runtime Environment)
  - JVM + 라이브러리
  - 자바 애플리케이션을 실행할 수 있도록 구성된 배포판
  - 개발 관련 도구는 포함하지 않음

### JDK(Java Development Kit)
  - JRE + 개발 툴
  - 소스 코드를 작성할 때 사용하는 자바 언어는 플랫폼에 독립적
  - 오라클은 자바 11부터는 JDK만 제공하며 JRE를 따로 제공하지는 않는다.
  - WORA(Wirte Once Run Anywhere)

### JVM 구조

<img width="630" alt="캡쳐1" src="https://user-images.githubusercontent.com/50076031/123371923-9927f500-d5bd-11eb-8df7-bc46d250ed6b.png">

  - 클래스 로더
    - .class에서 바이트코드를 읽고 메모리에 저장
  - 메모리
    - 메모리 영역에는 클래스 수준의 정보(클래스 이름, 부모 클래스, 메소드, 변수 등) 저장
    - 힙: 객체를 저장
    - 스택: 쓰레드 마다 런타임 스택을 만들고, 그 안에 메소드 호출을 스택
      - 예외가 발생할 때 나오는 로그들은 이 스택에 쌓인 후 호출되는 과정
  - 실행 엔진
    - 인터프리터: 바이트 코드를 한 줄 단위로 읽어서 실행
    - JIT(Just In Time) 컴파일러: 인터프리터의 효율을 높이기 위해 인터프리터가 반복되는 코드를 발견하면, JIT 컴파일러로 반복되는 코드를 모두 네이티브 코드로 바꿈
    - 그 후 인터프리터는 네이티브 코드로 컴파일된 코드를 바로 사용(캐시)
  - GC(Garbage Collector): 더 이상 참조되지 않는 객체를 모아서 정리
  - JNI(Java Native Interface)
    - 자바 애플리케이션에서 C, C++, 어셈블리어 등으로 작성된 함수를 사용할 수 있는 방법
    - ex) Thread의 메소드들

<img width="630" alt="캡쳐1" src="https://user-images.githubusercontent.com/50076031/123372517-aa253600-d5be-11eb-8a8e-0ebc7762b3ce.png">\

추가 자료
  - https://catch-me-java.tistory.com/12?category=438116
  - https://yadon079.github.io/2020/java%20study%20halle/week-01

### 클래스 로더
  - 로딩, 링크, 초기화 순으로 진행됨
  - 로딩
    - 클래스 로더가 바이트코드(.class 파일)을 읽고 그 내용에 따라 적절한 바이너리 데이터를 만들고 "메소드" 영역에 저장
    - Bootstrap -> Extension -> Application 순으로 위임하여 클래스를 찾아옴
    - 즉, 부모 로더에서 읽어오고 없으면 그 다음 부모 로더, 마지막으론 본인이 읽어오고 해당 클래스를 찾을 수 없으면 **ClassNotFoundException** 예외가 발생
    - 부트 스트랩 클래스로더(Bootstrap ClassLoader)
      - JAVA_HOME\lib에 있는 코어 자바 API를 제공
      - 최상위 우선순위를 가진 클래스 로더
    - 플랫폼 클래스로더
      - JAVA_HOME/lib/ext 폴더 또는 java.ext.dirs 시스템 변수에 해당하는 위치에 있는 클래스를 읽음
    - 애플리케이션 클래스로더
      - 애플리케이션 클래스 패스(애플리케이션 실행할 때 주는 -classpath 옵션 또는 java.class.path 환경 변수의 값에 해당하는 위치)에서 클래스를 읽음


<br><br>

## 바이트코드 조작

### 코드 커버리지 측정
  - 코드 커버리지? 테스트 코드가 확인할 수 있는 소스 코드 %
  - Jacoco 사용 - TODO: 추후 업데이트

### 모자에서 토끼를 꺼내는 마술(바이트코드 조작)
  - ASM: https://asm.ow2.io/
  - Javassist: https://www.javassist.org/
  - ByteBuddy(추천): https://bytebuddy.net/#/

### 바이트코드 조작 정리(활용)
  - 프로그램 분석
    - 코드에서 버그 찾는 툴
    - 코드 복잡도 계산
  - 클래스 파일 생성
    - 프록시
    - 특정 API 호출 접근 제한
    - Scala 같은 언어의 컴파일러
    - 프로파일러, 최적화, 로깅 등등 ....
  - 스프링이 컴포넌트 스캔을 하는 방법(ASM 사용) : 코드 분석
    - 컴포넌트 스캔으로 빈으로 등록할 후보 클래스 정보를 찾는데 사용
      - 특정 어노테이션(@Service, @Repository, @Configuration 등)을 찾는 과정
    - ClassPathScanningCandidateComponentProvider -> SimpleMetadataReader
    - ClassReader와 Visitor를 사용해서 클래스에 있는 메타 정보를 읽어옴

<br><br>


## 리플렉션

### 스프링의 DI(Depedency Injection)은 어떻게 동작할까?


```java

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

}

@Repository
public class BookRepository {

}


@Autowired
BookService bookService;

@Test
void di_주입받은_객체는_NotNull이어야_한다() {
    assertNotNull(bookService);
    assertNotNull(bookService.bookRepository);
}


// 클래스 정보 얻는 방법 1
Class<Book> bookClass = Book.class;

// 클래스 정보 얻는 방법 2
Book book = new Book();
Class<? extends Book> aClass = book.getClass();

// 클래스 정보 얻는 방법 3(jdbc)
Class<?> aClass1 = Class.forName("com.example.reflection.Book");
```

### 애노테이션과 리플렉션
  - 중요 애노테이션
    - @Retention: 해당 애노테이션을 언제까지 유지할 것인가? 소스, 클래스, 런타임
    - @Inherit: 해당 애노테이션을 하위 클래스까지 전달할 것인가?
    - @Target: 어디에 사용할 수 있는가?
  - 리플렉션
    - getAnnotations(): 상속받은(@Inherit) 애노테이션까지 조회
    - getDeclaredAnnotations(): 자기 자신에만 붙어있는 애노테이션 조회

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface MyAnnotation {

    // default를 선언하지 않으면 어노테이션 선언할 때 값을 반드시 지정해줘야 함
    String value() default "JuHyun";

    int number() default 100;
}


public class Book {

    @MyAnnotation
    private String a;

    private static String B = "BOOK";

    private static final String C = "BOOK";

    @MyAnnotation(value = "String d", number = 150)
    public String d = "d";
    
    ...

public static void main(String[] args) throws ClassNotFoundException {
        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                if (a instanceof MyAnnotation) {
                    MyAnnotation my = (MyAnnotation) a;
                    System.out.println(my.value() + " " + my.number());
                }
            });
        });
}


JuHyun 100
String d 150
```

<br>

### 리플렉션 API 1부: 클래스 정보 수정 또는 실행

```java
package com.example.reflection2;

public class Books {

    public static String A = "A";

    private String B = "B";

    public Books() {
    }

    public Books(String b) {
        B = b;
    }

    private void c() {
        System.out.println("C");
    }

    public int d(int left, int right) {
        return left + right;
    }
}



package com.example.reflection2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        // 클래스 정보 얻는 방법 1
        Class<Books> bookClass = Books.class;

        // 클래스 정보 얻는 방법 2
        Books books = new Books();
        Class<? extends Books> aClass = books.getClass();

        // 클래스 정보 얻는 방법 3(jdbc)
        Class<?> aClass1 = Class.forName("com.example.reflection2.Books");

        Constructor<?> constructor = aClass1.getConstructor(String.class);
        Books newBooks = (Books) constructor.newInstance("JuHyun");

        System.out.println(newBooks);

        // static => 인스턴스에 해당하지 않으므로 null
        Field a = Books.class.getDeclaredField("A");
        System.out.println(a.get(null));  // A
        a.set(null, "AAAAAA");
        System.out.println(a.get(null)); // AAAAAA

        Field b = Books.class.getDeclaredField("B");
        b.setAccessible(true); // private access
        System.out.println(b.get(books)); // B - books이라는 인스턴스에 존재하는 필드를 가져옴
        b.set(books, "BBBBBB");
        System.out.println(b.get(books)); // BBBBBB

        Method c = Books.class.getDeclaredMethod("c");
        c.setAccessible(true);
        c.invoke(books); // C - books 인스턴스, c 메서드는 파라미터가 없기 때문에 인자 1개만 설정

        Method d = Books.class.getDeclaredMethod("d", int.class, int.class); // class 타입
        int invoke = (int) d.invoke(books, 10, 20);
        System.out.println(invoke); // 30

    }
}


com.example.reflection2.Books@edf4efb
A
AAAAAA
B
BBBBBB
C
30

```

<br>

### 리플렉션 정리
  - 리플렉션 사용시 주의할 것
    - 지나친 사용은 성능 이슈를 야기할 수 있으므로 반드시 필요한 경우에만 사용할 것
    - 컴파일 타임에 확인되지 않고 런타임시에만 발생하는 문제를 만들 가능성이 있다.
    - 접근 지시자를 무시할 수 있다.(private -> setAccessible(true))
  - 스프링
    - 의존성 주입(DI)
    - MVC 뷰에서 넘어온 데이터를 객체에 바인딩 할 때
  - 하이버네이트
    - @Entity 클래스에 Setter가 없다면 리플렉션을 사용한다.
  - JUnit
    - https://junit.org/junit5/docs/5.0.3/api/org/junit/platform/commons/util/ReflectionUtils.html
