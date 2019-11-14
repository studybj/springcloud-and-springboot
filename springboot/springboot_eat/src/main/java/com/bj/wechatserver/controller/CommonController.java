package com.bj.wechatserver.controller;

import com.bj.eat.utils.ResultVoUtil;
import com.bj.eat.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @RequestMapping("/upload")
    public @ResponseBody ResultVo upload(HttpServletRequest request) throws IllegalStateException, IOException {
        log.info("上传中。。。");
        String upload = request.getParameter("upload");
        String type = request.getParameter("type");
        String basePath = "E:/springUpload/";
        if(!StringUtils.isEmpty(upload)){
            basePath += upload + "/";
        }
        if(!StringUtils.isEmpty(type)){
            basePath += type + "/";
        }
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        Map map = new HashMap();
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            List list = new ArrayList();
            while(iter.hasNext()){
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if(file != null) {
                    String localpath = basePath + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";
                    new File(localpath).mkdirs();
                    String path = localpath + file.getOriginalFilename();

                    list.add(path);
                    map.put("list", list);
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
            return ResultVoUtil.success(map);
        }
}
