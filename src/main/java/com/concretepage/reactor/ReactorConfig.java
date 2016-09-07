package com.concretepage.reactor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

@Configuration
@EnableAutoConfiguration
public class ReactorConfig {
    private static final int NUMBER_OF_EMP = CommonConstant.ACTOR_NUMBER;

    @Bean
    Environment env() {
        Environment env = new Environment();
        return env;
    }


    @Bean
    Reactor reactor(Environment env) {
        return Reactors.reactor()
                .env(env)
                .dispatcher(Environment.THREAD_POOL)
                .get();
    }

    @Bean
    MessageConsumer consumer() {
        return new MessageConsumer();
    }

    @Bean
    MessageProducer producer() {
        return new MessageProducer();
    }

    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(NUMBER_OF_EMP);
    }
}
