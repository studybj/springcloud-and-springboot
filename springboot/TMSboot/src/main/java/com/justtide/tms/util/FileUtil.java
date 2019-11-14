package com.justtide.tms.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();
        File dest = new File(path + fileName);
        try {
            file.transferTo(dest);
            return dest.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("上传出错");
        }
    }
    public static String download(MultipartFile file, String path){
        //todo
        return null;
    }
}
