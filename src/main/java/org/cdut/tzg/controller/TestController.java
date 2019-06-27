package org.cdut.tzg.controller;

import ch.qos.logback.core.util.CloseUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.cdut.tzg.utils.CDUTUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.cdut.tzg.utils.MD5Utils.signByMd5;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/jcqTestHtml")
    public String jcqTest(){
        return "jcqTest";
    }
    @RequestMapping("/zrTestHtml")
    public String zrTest(){
        return "zrTest";
    }
    @RequestMapping("/xlfTestHtml")
    public String xlfTest(){
        return "xlfTest";
    }
    @RequestMapping("/lhyTestHtml")
    public String lhyTest(){
        return "lhyTest";
    }
    @RequestMapping("/testPostImage")
    public String testPostHtml(){
        return "testPostImage";
    }

    @RequestMapping(value = "/image",method = RequestMethod.POST)
    @ResponseBody
    public String testImpage(@RequestParam("avatar") MultipartFile file,@RequestParam("username") String username){
        System.out.println(file);
        System.out.println(file.getOriginalFilename());
        System.out.println(username);
//        System.out.println(request);
//        System.out.println(request.getParameterMap().entrySet());
//        System.out.println(request.getParameter("username"));
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        System.out.println(request.getParameterMap().entrySet());
//        String name = multipartRequest.getParameter("name");
        return "ssss";
    }


    @RequestMapping("/spider")
    @ResponseBody
    public String testSpider() {
        System.out.println(CDUTUtils.isStudent("201613160616","510922199608255211"));
        System.out.println(CDUTUtils.isStudent("201613160618","510922199608255211"));
        System.out.println(CDUTUtils.isStudent("201613160820","510121199807058819"));
        return "sss";
    }




}
