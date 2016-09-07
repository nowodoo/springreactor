package com.concretepage.reactor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.Reactor;
import reactor.event.Event;

public class MessageProducer {
	@Autowired
    Reactor reactor;
    @Autowired
    CountDownLatch latch;
    public void publishEmployee(int numberOfEmp) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        AtomicInteger counter = new AtomicInteger(0);
        for (int i=0; i < numberOfEmp; i++) {
            reactor.notify("employees", Event.wrap(counter.incrementAndGet()));
        }

        //等待所有的线程执行完毕
        latch.await();
        long endTime = System.currentTimeMillis();

        System.out.println("consume time:"+(endTime - startTime));
        System.out.println("-------Done-------");
    }

}
