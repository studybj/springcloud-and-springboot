package com.bj.wechatserver.util;

public class ImageUtil {

    /**
     * 将二维码和图片拼装成新的图片
     * @param logoImageUrl 图片的地址
     * @param qrCodeUrl 二维码图片的地址
     * @param content 文字内容
     * @return
     */
    public static String getQrImgUrl(String logoImageUrl, String qrCodeUrl, String content) {
            return null;
    }
    public static void main(String[] args){
       String url =  getQrImgUrl("http://pic16.nipic.com/20110820/6841436_171629611103_2.jpg","http://i2.sinaimg.cn/dy/c/2014-07-18/1405623657_VbFlfN.jpg","");
        System.out.println(url);
    }
}
