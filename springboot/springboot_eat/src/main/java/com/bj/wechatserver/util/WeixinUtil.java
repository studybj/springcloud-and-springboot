package com.bj.wechatserver.util;

import com.bj.wechatserver.entity.AccessToken;
import com.bj.wechatserver.entity.UserInfo;
import com.bj.wechatserver.entity.UserTag;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class WeixinUtil {
    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //用户管理开始
    //用户标签
    //创建标签 http请求方式：POST（请使用https协议）
    public final static String create_usrtag_url = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
    //获取公众号已创建的标签，http请求方式：GET（请使用https协议）
    public final static String get_usrtag_url = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
    //编辑标签,http请求方式：POST（请使用https协议）
    public final static String edit_usrtag_url = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
    //删除标签,http请求方式：POST（请使用https协议）
    public final static String del_usrtag_url = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN";

    // 获取用户基本信息（GET） 限200（次/天）
    public final static String userinfo_url ="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    // 更改用户信息的备注名 post
    public final static String update_remark_url ="https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";

    //用户管理结束
    //临时素材开始
    //新增临时素材,http请求方式：POST/FORM，使用https
    public final static String add_lsmaterial_url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    //获取临时素材，http请求方式: GET,https调用
    public final static String get_lsmaterial_url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
    //临时素材结束
    //永久素材开始
    //新增图文素材，http请求方式: POST，https协议
    public final static String add_twmaterial_url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
    //上传图文消息内的图片获取URL，http请求方式: POST，https协议
    public final static String add_twimagematerial_upload_url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
    //修改永久图文素材,http请求方式: POST,https
    public final static String update_twmaterial_url = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
    //新增永久素材，http请求方式: POST，https协议
    public final static String add_yjmaterial_url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
    //获取永久素材，http请求方式: POST，https协议
    public final static String get_yjmaterial_url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
    //删除永久素材，http请求方式: POST
    public final static String del_yjmaterial_url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
    //永久素材结束

    /*public final static String APPID = "wx4e042e54157594c3";
    public final static String APPSECRET = "9035c1349c6b589601dd19288cd11de6";*/
    //测试号
    public final static String APPID = "wx1a0c4faa6e7e6b65";
    public final static String APPSECRET = "cdcfdcb581f0390b0d1224fba35f4fc0";

    public static String getAccessToken() {
        return getAccessToken(APPID,APPSECRET).getToken();
    }
    /**
     * 获取access_token
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken accessToken = null;
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        JSONObject jsonObject = httpUtil.httpRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return accessToken;
    }
    /**
     * 获取用户信息
     * @param openId
     * @return
     */
    public static UserInfo getUserInfo(String openId){
        AccessToken accesstoken = getAccessToken(APPID,APPSECRET);
        UserInfo user = null;
        String requestUrl = userinfo_url.replace("ACCESS_TOKEN", accesstoken.getToken()).replace("OPENID", openId);
        JSONObject jsonObject = httpUtil.httpRequest(requestUrl, "GET", null);
        if(jsonObject != null){
            try {
                //System.out.println("openId = " + jsonObject.getString("tagid_list"). + "");
                jsonObject.put("tagid_list", null);
                //jsonObject.put("subscribe_time", new Date(Long.valueOf(jsonObject.getString("subscribe_time"))));
                user = (UserInfo) JSONObject.toBean(jsonObject, UserInfo.class);
            } catch (JSONException e) {
                user = null;
                // 获取用户信息失败
                e.printStackTrace();
                log.error("获取用户信息失败 errmsg:{}", jsonObject.getString("errmsg"));
            }
        }
        return user;
    }
    /**
     * 设置用户的备注名
     * @param openId
     * @param remark
     * @return
     */
    public static Map updateremark(String openId, String remark){
        AccessToken accesstoken = getAccessToken(APPID,APPSECRET);
        String requestUrl = update_remark_url.replace("ACCESS_TOKEN", accesstoken.getToken());
        JSONObject jsonObject = httpUtil.httpRequest(requestUrl, "POST", "{openid: "+ openId +",remark" + remark + "}");
        Map resMap  = new HashMap();
        resMap.put("errcode", -1);
        resMap.put("errmsg","异常");

        if(jsonObject != null){
            resMap.clear();
            resMap.put(jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
        }
        return resMap;
    }
    /**
     * 操作公众号已创建的标签
     * @return
     */
    public static Map getUserTag(Integer method, UserTag tag){
        AccessToken accesstoken = getAccessToken(APPID,APPSECRET);
        String url = "";
        JSONObject jsonObject = null;
        if(method == 1){//新增
            url = create_usrtag_url.replace("ACCESS_TOKEN", accesstoken.getToken());
            jsonObject = httpUtil.httpRequest(url, "POST", tag.getName());
        }else if(method == 2){//删除
            url = del_usrtag_url.replace("ACCESS_TOKEN", accesstoken.getToken());
            jsonObject = httpUtil.httpRequest(url, "POST", tag.getId().toString());
        }else if(method == 3){//修改
            url = edit_usrtag_url.replace("ACCESS_TOKEN", accesstoken.getToken());
            jsonObject = httpUtil.httpRequest(url, "POST", tag.getId().toString());
        }
        Map resMap  = new HashMap();
        resMap.put("errcode", -1);
        resMap.put("errmsg","异常");

        if(jsonObject != null){
            resMap.clear();
            resMap.put(jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
        }
        return resMap;
    }
    public static String material(Integer method, UserTag tag){
        return null;
    }
}
