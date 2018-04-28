package com.shuxin.foodsstore.controller.system;

import com.shuxin.foodsstore.commons.configuration.WechatAccountConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;


    @RequestMapping("/")
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView("/index");

        return modelAndView;
    }

    @RequestMapping("/index.html")
    public ModelAndView getIndexHtml() {
        return new ModelAndView("/index2");
    }
}
