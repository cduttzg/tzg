package org.cdut.tzg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
