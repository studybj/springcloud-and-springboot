package com.bj.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class FirstController {
    @RequestMapping("/index")
    public String index(){System.out.println("----------------");
        return "index2";
    }
}
