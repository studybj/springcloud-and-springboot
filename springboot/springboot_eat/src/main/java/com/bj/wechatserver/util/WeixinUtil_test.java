package com.bj.wechatserver.util;

import com.bj.wechatserver.entity.AccessToken;
import com.bj.wechatserver.entity.Account;
import com.bj.wechatserver.entity.UserInfo;
import com.bj.wechatserver.entity.UserTag;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WeixinUtil_test {
    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //生成带参数的二维码 http请求方式: POST
    public final static String create_qrcode_url =  "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
    //获取带参数的的二维码 http请求方式: POST
    public final static String get_qrcode_url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
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
     * 创建带参数的二维码
     * @return
     */
    public static JSONObject createQrCode(String info){
        AccessToken accesstoken = getAccessToken(APPID,APPSECRET);
        String url = create_qrcode_url.replace("ACCESS_TOKEN", accesstoken.getToken());
        JSONObject jsonObject = httpUtil.httpRequest(url, "POST", info);
        return jsonObject;
    }
    /**
     * 创建带参数的二维码
     * @return
     */
    public static String getRQcode(String ticket, String savepath) {
        String filepath = null;
        String requestUrl = get_qrcode_url.replace("TICKET", ticket);

        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            if (!savepath.endsWith("/")) {
                savepath += "/";
            }
            // 将ticket 作为文件名
            filepath = savepath + ticket + ".jpg";
            // 将微信服务器返回的输入流写入文件
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(filepath));

            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
            fos.close();
            bis.close();

            conn.disconnect();
            System.out.println("根据ticket换取二维码成功");
        } catch (Exception e) {
            filepath = null;
            System.out.println("根据ticket换取二维码失败" + e);
        }
        return filepath;
    }
}
