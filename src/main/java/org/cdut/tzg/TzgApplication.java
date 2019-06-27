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
        System.out.println("home  http://localhost:" + serverPort+"/test/testPostImage");
        System.out.println("home  http://localhost:" + serverPort+"/test/spider");
        System.out.println(System.currentTimeMillis());
        System.out.println();

        //商品测试 /api/goods/
        System.out.println("-------------商品测试-----------");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/goods/getInfo?goodsId=5");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/goods/gallery?type=1");
        System.out.println();
        //购物车测试 /api/cart/
        System.out.println("-------------购物车测试-----------");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/cart/cartInfo?username=jack");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/cart/cartInfo?username=%e5%b0%8f%e6%96%8c");
        System.out.println();

        //后台数据连接
        System.out.println("-----------后台数据连接----------");
        System.out.println("tzg admin http://localhost:" + serverPort+"/api/backstage/getFrozenUser");
        //个人中心测试/api/user
        System.out.println("-----------个人中心测试----------");
        System.out.println("tzg admin http://localhost:" + serverPort + "/api/user/home/SeekInfo?username=rose");//根据用户姓名查询用户求购信息
        System.out.println("tzg admin http://localhost:" + serverPort + "/api/user/home/message?username=rock");//根据用户姓名查找用户信息
    }

}

