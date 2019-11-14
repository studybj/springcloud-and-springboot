package com.justtide.tms.constant;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

@Getter
public enum Constants {
    //说明：flag为1表示版本类型，flag为2表示发布策略，flag为3表示版本状态
    VERSION_TYPE_SYSTEMVERSION("1","系统版本", 1),
    VERSION_TYPE_SECUREFIRMWAREVERSION("2","安全固件版本", 1),
    VERSION_TYPE_TMSAPPVERSION("3","TMS应用版本", 1),
    PUBLISH_TYPE_BY_VERSION("1","按版本发布", 2),
    PUBLISH_TYPE_BY_DEVICE("2","按终端发布", 2),
    VERSION_STATE_PUBLISHED("0","已发布", 3),
    VERSION_STATE_CLOSED("1","已关闭", 3),
    VERSION_STATE_TOBEPUBLISHED("2","待发布", 3),

    VERSION_OPERATE_DETAIL("1","详情", 4),
    VERSION_OPERATE_CLOSE("2","关闭", 4),
    VERSION_OPERATE_DELETE("3","删除", 4),
    VERSION_OPERATE_PUBLISH("4","发布", 4),
    VERSION_OPERATE_START("5","开启", 4);


    private String code;
    private String msg;
    private Integer flag;

    Constants(String code, String msg, Integer flag){
        this.code = code;
        this.msg = msg;
        this.flag = flag;
    }
    /**
     *将该枚举全部转化成json
     * @return
     */
    public static JSONArray toJson(){
        JSONArray jsonArray = new JSONArray();
        for (Constants e : Constants.values()) {
            JSONObject object = new JSONObject();
            object.put("code", e.getCode());
            object.put("msg", e.getMsg());
            object.put("flag", e.getFlag());
            jsonArray.add(object);
        }
        return jsonArray;
    }

    /**
     * 重写toString，单个转化成json
     * @return
     */
    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("msg", msg);
        object.put("flag", flag);
        System.out.println(object.toString());
        return object.toString();
    }



    public static void main(String[] args) {
        for (Constants a : Constants.values()){
//            if(a.flag == 1){
//                System.out.println(a.msg);
//            }
            System.out.println(a.toString());
        }
    }
}
