package hello.springadvanced.trace.hellotrace;

import hello.springadvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("Hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "Hello2");

        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("Hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "Hello2");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}