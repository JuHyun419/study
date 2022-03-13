package chapter10.customdsl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableIntegration
public class SpringIntegrationConfig {

    @Bean
    public MessageSource<?> integerMessageSource() {
        MethodInvokingMessageSource source = new MethodInvokingMessageSource();
        source.setObject(new AtomicInteger());
        source.setMethodName("getAndIncrement");
        return source;
    }

    @Bean
    public DirectChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow myFlow() {
        return IntegrationFlows.from(this.integerMessageSource(), c -> c.poller(Pollers.fixedRate(10)))
                .channel(this.inputChannel())
                .filter((Integer p) -> p % 2 == 0)
                .transform(Object::toString)
                .channel(MessageChannels.queue("queueChannel"))
                .get();
    }

}
