package com.bj.wechat.entity.tuling.req;

public class TulingReqUtil {

    private static final String API_KEY = "3660f1b9c5b148f588a2a31838db9bc2";

    private static final String USER_ID = "311630";

    /** 图灵请求信息组装
     * @param type 请求类型 :0-文本(默认)、1-图片、2-音频
     * @param info 请求消息，文本是消息，图片或音频是url
     * @param location 位置信息
     * @return
     */
    public static TulingReq getTulingReq(int type,String info, SelfLocation location){
        TulingReq tulingReq = new TulingReq();

        tulingReq.setReqType(type);

        Perception perception = new Perception();
        if(type == 0){//0-文本(默认)
            InputText inputText = new InputText();
            inputText.setText(info);
            perception.setInputText(inputText);
        }else if(type == 1){//1-图片
            InputImage inputImage = new InputImage();
            inputImage.setUrl(info);
            perception.setInputImage(inputImage);
        }else if(type == 2){//2-音频
            InputMedia inputMedia = new InputMedia();
            inputMedia.setUrl(info);
            perception.setInputMedia(inputMedia);
        }
        if(location != null){
            SelfInfo selfInfo = new SelfInfo();
            selfInfo.setLocation(location);
            perception.setSelfInfo(selfInfo);
        }else{
            SelfInfo selfInfo = new SelfInfo();
            SelfLocation lo = new SelfLocation();
            lo.setCity("");
            lo.setProvince("");
            lo.setStreet("");
            selfInfo.setLocation(lo);
            perception.setSelfInfo(selfInfo);
        }
        tulingReq.setPerception(perception);

        TuLingUser tuser = new TuLingUser();
        tuser.setApiKey(API_KEY);
        tuser.setUserId(USER_ID);
        tulingReq.setUserInfo(tuser);
        return tulingReq;
    }
}
