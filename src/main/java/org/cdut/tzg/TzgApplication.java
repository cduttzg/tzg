package org.cdut.tzg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TzgApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TzgApplication.class, args);
        ApplicationContext context = SpringApplication.run(TzgApplication.class, args);
        String serverPort = context.getEnvironment().getProperty("server.port");
        //ajaxtest
        System.out.println("test页面  http://localhost:" + serverPort+"/test/testHtml");

        System.out.println("tzg started at http://localhost:" + serverPort);
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/home/cartInfo?username=jack");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/home/cartInfo?username=ffffff");
        //后台数据连接
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/backstage/getData");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/backstage/manageOrder?orderId=100");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/backstage/getFrozenUser");
    }

}
