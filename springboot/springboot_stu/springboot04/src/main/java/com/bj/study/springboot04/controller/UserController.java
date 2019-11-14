package com.bj.study.springboot04.controller;

import com.bj.study.springboot04.bean.UserInfo2;
import com.bj.study.springboot04.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class UserController {

    @Resource
    UserService userService;
    private static String UPLOADED_FOLDER = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<UserInfo2> users=userService.getUserList();
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

   /* @RequestMapping("/add")
    public String add(UserInfo2 user) {
        System.out.println(user.getUrl());
        System.out.println(user.getUserName()+"-----"+user.getUrl());
        userService.save(user);
        return "redirect:/list";
    }*/
   @RequestMapping("/add")
   public String add(HttpServletRequest request) {
       UserInfo2 user = new UserInfo2();
       String contentType = request.getContentType();
       if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
           MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
           MultipartFile file = multipartRequest.getFile("url");
           try{
               byte[] bytes = file.getBytes();
               Path path = Paths.get(UPLOADED_FOLDER + "static/upload/" + file.getOriginalFilename());
               Files.write(path, bytes);
           }catch (Exception e){
               e.printStackTrace();
               return "";
           }
           user.setUrl("/upload/"+file.getOriginalFilename());
       }

       String userName=request.getParameter("userName");
       String password=request.getParameter("password");
       int age=Integer.parseInt(request.getParameter("age"));
       user.setUserName(userName);
       user.setPassword(password);
       user.setAge(age);
       userService.save(user);
       return "redirect:/list";
   }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id) {
        UserInfo2 user=userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(UserInfo2 user) {
        userService.edit(user);
        return "redirect:/list";
    }


    @RequestMapping("/delete")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/list";
    }
}
