package org.cdut.tzg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author anlan
 * @date 2019/6/28 22:02
 */
@Controller
@RequestMapping("")
public class EntryController {

    @RequestMapping("/home")
    public String index(){
        return "index";
    }
}
