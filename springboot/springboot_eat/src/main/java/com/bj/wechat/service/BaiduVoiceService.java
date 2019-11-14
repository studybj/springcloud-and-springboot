package com.bj.wechat.service;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.HashMap;

/**
 * 百度语音 操作
 */
@Slf4j
public class BaiduVoiceService {
    //final static String FILE_PATH = Config.getString("download.folder");
    //设置APPID/AK/SK
    public static final String APP_ID = "16517103";
    public static final String API_KEY = "Gnm6OH8FozNXwAGEe5XUyWyY";
    public static final String SECRET_KEY = "d5N1GTaihH8NAKaeGbZBrcKcU4obK7hI";

    // 初始化一个AipSpeech
    private static AipSpeech client = null;
    private static long iniTime = 0L;
    /** 30 天 24 小时 **/
    private static final long MONTH_TIME = 30 * 24 * 60 * 60 * 1000;
    private static final Base64 base64 = new Base64();

    private static void iniAPI(){
        boolean needToReset = false;
        // 判断是否一个月了，如果一个月后，需要重新初始话
        long currentTime = System.currentTimeMillis();
        if (currentTime - iniTime > MONTH_TIME){
            needToReset = true;
        }
        if (client == null || needToReset) {
            client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
            /** 2秒超时时间 **/
            // 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);

            // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

            // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
            // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
            iniTime = System.currentTimeMillis();
        }
    }

    /**
     * 语音合成 ，即文本转语音
     * @param text
     * @param fileName
     * @return
     */
    public static String getSoundMp3(String text, String fileName){
        String rtnfileName = "";
        String path = "";
        String type = "zh";
        if (StringUtils.isEmpty(text))
            return "";
        try{
            iniAPI();
            // 设置可选参数
            HashMap<String, Object> options = new HashMap<String, Object>();
            //语速，取值0-9，默认为5中语速
            options.put("spd", "5");
            //音调，取值0-9，默认为5中语调
            options.put("pit", "5");
            //音量，取值0-15，默认为5中音量
            options.put("vol", "5");
            //发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
            options.put("per", "4");

            TtsResponse res = client.synthesis(text, type, 1, options);
            byte[] data = res.getData();//生成的音频数据

            if (data != null){
                // String uuid = UUID.randomUUID().toString().replace("-",
                // "").toLowerCase();
                String uuid = base64.encodeToString(fileName.getBytes());
                rtnfileName = type + "/" + uuid.replaceAll("=", "") + ".mp3";
                path = "E:/weixin/reply/voice/" + rtnfileName;
                File file = new File(path);
                if (!file.getParentFile().exists()&&!file.isDirectory()){
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                } else {
                    file.createNewFile();
                }
                System.out.println("text = [" + file.exists());
                if (!file.exists()) {
                    Util.writeBytesToFileSystem(data, path);
                }
            } else{
                JSONObject jsonObj = res.getResult();//服务器返回的内容，合成成功时为null,失败时包含error_no等信息
                log.info("invoke baidu synthesis API error:", jsonObj);
            }
        } catch (Exception e){
            rtnfileName = "";
            path = "";
            log.error("invoke baidu synthesis API error:", e);
        }
        return path;
    }

    /**
     * 语音识别
     * @param filePath
     * @return
     */
    public static String recognizeSound(String filePath) {
        String result = "";
        JSONObject asrRes = null;
        if (StringUtils.isEmpty(filePath))
            return "";
        try{
            iniAPI();
                /*HashMap<String, Object> options = new HashMap<>();
                options.put("dev_pid", 1737);
                asrRes = client.asr(filePath, "pcm", 16000, options);*/
                asrRes = client.asr(filePath, "pcm", 16000, null);
            result = getResult(asrRes);
        } catch (Exception e) {
            log.error("invoke baidu asr API error:", e);
        }
        return result;
    }

    private static String getResult(JSONObject asrRes) {
        String result = "";
        if (asrRes.getInt("err_no") == 0) {
            JSONArray arrayResult = asrRes.getJSONArray("result");
            StringBuilder sbResult = new StringBuilder();
            for (int i = 0; i < arrayResult.length(); i++) {
                if (i == 0) {
                    sbResult.append(arrayResult.get(i).toString());
                } else {
                    if (!StringUtils.isEmpty(arrayResult.get(i).toString()))
                        sbResult.append(";" + arrayResult.get(i).toString());
                }
            }
            result = sbResult.toString().replaceAll("，", "");
        } else {
            log.error("invoke baidu asr API error:", asrRes);
        }
        return result;
    }

    public static void main(String[] args) {
        //getSoundMp3("我爱你就想老鼠爱大米","wode");
        System.out.println(recognizeSound("nihao"));
    }
}
