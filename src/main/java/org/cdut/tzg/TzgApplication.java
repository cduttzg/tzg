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
        System.out.println("--------------ajaxtest---------");
        //ajaxtest
        //商品测试 /api/goods/addToCart
        System.out.println("test页面  http://localhost:" + serverPort+"/test/jcqTestHtml");
        System.out.println("test页面  http://localhost:" + serverPort+"/test/zrTestHtml");
        System.out.println("test页面  http://localhost:" + serverPort+"/test/xlfTestHtml");
        System.out.println("test页面  http://localhost:" + serverPort+"/test/lhyTestHtml");

        //首页测试 /api/home
        System.out.println("-------------首页测试--------");
        System.out.println("OrderInfo  http://localhost:" + serverPort+"/api/home/OrderInfo?number=2");
        System.out.println("home  http://localhost:" + serverPort+"/api/home/");
        System.out.println();

        //商品测试 /api/goods/
        System.out.println("-------------商品测试-----------");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/goods/getInfo?goodsId=5");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/goods/gallery?type=1");
        System.out.println();

        //后台数据连接
        System.out.println("-----------后台数据连接----------");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/backstage/getData");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/backstage/manageOrder?orderId=100");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/backstage/getFrozenUser");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/backstage/freezeUser?schoolNum=201613160833");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/backstage/addAdmin?schoolNum=201613160833");
        System.out.println();
    }

}

