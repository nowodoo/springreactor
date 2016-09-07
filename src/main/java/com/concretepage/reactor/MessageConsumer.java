package com.concretepage.reactor;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import reactor.event.Event;
import reactor.function.Consumer;

public class MessageConsumer implements Consumer<Event<Integer>> {
    @Autowired
    CountDownLatch latch;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void accept(Event<Integer> event) {
        //创建对象
//		EmployeeResource  empResource = restTemplate.getForObject("http://localhost:8080/empdata.jsp", EmployeeResource.class);
        EmployeeResource empResource = new EmployeeResource();
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Ram");
        empResource.setResult("success");
        empResource.setEmployee(employee);


        //发起连接
        String result = restTemplate.getForObject("http://10.10.30.72:8881/localResource/manage/resource/local/query/baseId/38781?eyJ1aWQiOiIxNDExMiIsInRva2VuIjoiU1QtMTU5NTE4Ni1SWjVZa3Z0bU5jVWI2YlpPMjkxdC1jYXMiLCJuaWNrbmFtZSI6IumprOmygeawuCIsInIiOjAuNDYzMDc3NTI3ODE2MTkxMTYsImJhc2VJZCI6Mzg3ODF9", String.class);


        System.out.println("eventData: " + event.getData() + "  threadName:" + Thread.currentThread().getName());
        System.out.println(result);

        latch.countDown();
    }
}
