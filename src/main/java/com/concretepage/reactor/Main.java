package com.concretepage.reactor;

import static reactor.event.selector.Selectors.$;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import reactor.core.Reactor;

public class Main {
	public static void main(String[] args) throws InterruptedException {
	   final int NUMBER_OF_EMP = CommonConstant.ACTOR_NUMBER;
	   AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
       ctx.register(ReactorConfig.class);
       ctx.refresh();
       Reactor reactor = (Reactor)ctx.getBean("reactor");
       MessageConsumer consumer = (MessageConsumer)ctx.getBean("consumer");
       MessageProducer publisher = (MessageProducer)ctx.getBean("producer");
       reactor.on($("employees"), consumer);
       //在这里决定起了几个客户端
       publisher.publishEmployee(NUMBER_OF_EMP);
	}
}
