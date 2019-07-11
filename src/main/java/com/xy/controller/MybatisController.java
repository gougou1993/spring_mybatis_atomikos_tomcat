package com.xy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xy.service.NameService;

/**
 * Created by helloworld on 2014/11/22.
 */
@Controller
public class MybatisController {

    @Autowired
    private  NameService nameService;

    @RequestMapping(value = "/addName")
    ModelMap addName(@RequestParam("hasException") boolean hasException) {
        try {
        	System.out.println("hasException"+hasException);
            nameService.addQaAndDev(hasException);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelMap("false");
        }
        return new ModelMap("true");
    }
}