package com.bj.wechatserver.util.menu;

import com.bj.wechatserver.entity.menu.MenuInfo;
import com.bj.wechatserver.util.httpUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;


@Slf4j
public class MenuUtil {
    //菜单创建
    public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //菜单查询
    public final static String menu_query_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    //个性化菜单创建
    public final static String condit_menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";

    /**
     * 创建菜单
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(MenuInfo menu, String accessToken) {
        System.out.println("menu = [" + menu + "], accessToken = [" + accessToken + "]");
        int result = 0;
        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        System.out.println("请求菜单："+jsonMenu);
        // 调用接口创建菜单
        JSONObject jsonObject = httpUtil.httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                result = jsonObject.getInt("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return result;
    }
    /**
     * 创建个性化菜单
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return
     */
    public static String createMatchruleMenu(MenuInfo menu, String accessToken) {
        String result = "";
        // 拼装创建菜单的url
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        System.out.println("请求菜单："+jsonMenu);
        // 调用接口创建菜单
        JSONObject jsonObject = httpUtil.httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
                result = jsonObject.getString("menuid");
        }
        return result;
    }

    /**
     * @param accessToken 有效的access_token
     * @return
     */
    public static JSONObject queryMenu(String accessToken) {
        // 拼装查询菜单的url
        String url = menu_query_url.replace("ACCESS_TOKEN", accessToken);
        // 调用接口查询菜单
        JSONObject jsonObject = httpUtil.httpRequest(url, "GET", null);
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                jsonObject.getInt("errcode");
                log.error("查询菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return jsonObject;
    }
}
