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
        System.out.println("tzg started at http://localhost:" + serverPort);;
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/home/cartInfo?username=jack");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/goods/getInfo?goodsId=3");
    }

}
