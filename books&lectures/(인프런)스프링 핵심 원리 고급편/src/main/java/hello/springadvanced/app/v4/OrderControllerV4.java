package hello.springadvanced.app.v4;

import hello.springadvanced.trace.TraceStatus;
import hello.springadvanced.trace.logtrace.LogTrace;
import hello.springadvanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    /* As-Is */
//    @GetMapping("/v3/request")
//    public String request2(final String itemId) {
//        TraceStatus status = null;
//        try {
//            status = trace.begin("OrderController.request()");
//            orderService.orderItem(itemId);
//            trace.end(status);
//            return "ok";
//        } catch (Exception e) {
//            trace.exception(status, e);
//            throw e; // 예외를 다시 던진다
//        }
//    }

    /* To-Be */
    @GetMapping("/v4/request")
    public String request(final String itemId) {
        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderController.request()");
    }

}
