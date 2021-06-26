package bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

// 아무것도 없는 Moja에서 "Rabbit"을 꺼내는 마술
public class Masulsa {
    public static void main(String[] args) {
        ClassLoader classLoader = Masulsa.class.getClassLoader();

        try {
            // 아래부터 실행 후 주석처리 한 후 sout 실행
            new ByteBuddy().redefine(Moja.class)
                    .method(named("pullOut"))
                    .intercept(FixedValue.value("Rabbit!"))
                    .make()
                    .saveIn(new File("/Users/juhyun/Desktop/study/강의/인프런/더 자바, 코드를 조작하는 다양한 방법/sample-code"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 위에 작업 후 실행
        System.out.println(new Moja().pullOut());
    }
}