package com.bj.test.demo.common;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
@Data
public class ImageCode {
    private BufferedImage image;
    private  String code;
    private LocalDateTime expireTime;//过期时间
    /**
     * @param image
     * @param code
     * @param expireInt :该参数是过期时间秒数,如60
     */
    public ImageCode(BufferedImage image, String code, int expireInt) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireInt);//当前时间加上60秒
    }
}
