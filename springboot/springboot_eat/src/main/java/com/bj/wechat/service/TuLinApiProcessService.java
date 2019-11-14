package com.bj.wechat.service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.bj.wechat.entity.tuling.req.TulingReqUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
/**
 * 调用图灵机器人api接口，获取智能回复内容
 * @author pamchen-1
 *
 */
@Slf4j
public class TuLinApiProcessService {
    /**
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果,图灵机器人V1.0
     * @param content
     * @return
     */
    public static String getTulingResult(String content){
        /** 此处为图灵api接口，参数key需要自己去注册申请，先以11111111代替 */ //旧key1845f28acda94fae698c4bf86a1c2c48
        String apiUrl = "http://www.tuling123.com/openapi/api?key=c80b35b2beba4fdaa69708762a572b81&info=";
        String param = "";
        try {
            param = apiUrl+URLEncoder.encode(content,"utf-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } //将参数转为url编码

        /** 发送httpget请求 */
        HttpGet request = new HttpGet(param);
        String result = "";
        try {
            HttpResponse response = new DefaultHttpClient().execute(request);
            if(response.getStatusLine().getStatusCode()==200){
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 请求失败处理 */
        if(null==result){
            return "对不起，你说的话真是太高深了……";
        }
        try {
            JSONObject json = JSONObject.fromObject(result);
            //以code=100000为例，参考图灵机器人api文档
            if(100000==json.getInt("code")){
                result = json.getString("text");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    //存储APIkey
    public static final String API_KEY = "3660f1b9c5b148f588a2a31838db9bc2";
    //存储接口请求地址
    public static final String API_URL = "http://openapi.tuling123.com/openapi/api/v2";
    // 用户id
    public static final String USER_ID = "311630";
    /**
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果,图灵机器人V2.0,
     * 参考   https://blog.csdn.net/m0_38044453/article/details/81874467，https://blog.csdn.net/qq_36706625/article/details/80992275
     * @return
     */
    public static String getTulingResultV2(String content){
        /** 发送httppost请求 */
        HttpPost request = new HttpPost(API_URL);
        String result = "";
        try {
            request.setEntity(new ByteArrayEntity(content.getBytes("UTF-8")));
            HttpResponse response = new DefaultHttpClient().execute(request);
            if(response.getStatusLine().getStatusCode()==200){
                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
            /** 请求失败处理 */
            if(null == result){
                return "对不起，你说的话真是太高深了……";
            }
            JSONObject jsonObject = JSONObject.fromObject(result);
            JSONObject intentObject = (JSONObject) jsonObject.get("intent");
            JSONArray ja = jsonObject.getJSONArray("results");
            String code = intentObject.get("code").toString();
            if(code.startsWith("4") || code.startsWith("5") || code.startsWith("6") || code.startsWith("7") || code.startsWith("8")){
                JSONObject jo = ja.getJSONObject(ja.size() - 1);
                result = "出现异常了，异常信息为：" + jo.getJSONObject("values").getString("text");
            }else{

            }
            return result;
        } catch (ClientProtocolException e) {
            log.info("图灵机器人请求失败");
            e.printStackTrace();
        } catch (IOException e) {
            log.info("图灵机器人请求失败");
            e.printStackTrace();
        }
        return result;
    }

   /* public static String getTulingResult(String content){

    }*/
    public static void main(String[] args){
        System.out.println(getTulingResultV2(JSONObject.fromObject(TulingReqUtil.getTulingReq(0,"test tuling", null)).toString()));
        //System.out.println(JSONObject.fromObject(TulingReqUtil.getTulingReq(0,"test tuling", null)).toString());
    }
}
