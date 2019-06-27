package org.cdut.tzg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String testImpage(String data){
        System.out.println(data);
        return "ssss";
    }

}
