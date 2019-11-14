package com.bj.test.demo.controller;

import com.bj.test.demo.ImageCodeUtil;
import com.bj.test.demo.common.ImageCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class ImageCodeController {

    @RequestMapping(value = "/imageCode", method = RequestMethod.GET)
    public void imageCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        //生成imageCode对象
        ImageCode imageCode = ImageCodeUtil.createImageCode();
        //将图形验证码存入到session中
        request.getSession().setAttribute("imageCode", imageCode);
        // 将生成的图片写到接口的响应中
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }
}