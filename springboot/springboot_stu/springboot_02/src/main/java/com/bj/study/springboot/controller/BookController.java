package com.bj.study.springboot.controller;

import com.bj.study.springboot.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @RequestMapping("")
    public ModelAndView index(){
        System.out.println("-------------------------test---");
        List<Book> learnList =new ArrayList<Book>();
        Book bean =new Book("官方参考文档","Spring Boot Reference Guide","http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#getting-started-first-application");
        learnList.add(bean);
        bean =new Book("官方SpriongBoot例子","官方SpriongBoot例子","https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples");
        learnList.add(bean);
        bean =new Book("龙国学院","Spring Boot 教程系列学习","http://www.roncoo.com/article/detail/125488");
        learnList.add(bean);

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("learnList", learnList);

        return modelAndView;
    }
}
