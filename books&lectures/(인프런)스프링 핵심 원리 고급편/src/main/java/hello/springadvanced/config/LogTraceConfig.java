package hello.springadvanced.config;

import hello.springadvanced.trace.logtrace.FieldLogTrace;
import hello.springadvanced.trace.logtrace.LogTrace;
import hello.springadvanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        //return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }


}
