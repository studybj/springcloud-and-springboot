/**
 * 
 */
package com.bj.wechatserver.util.menu;


import com.bj.wechatserver.entity.AccessToken;
import com.bj.wechatserver.entity.menu.*;
import com.bj.wechatserver.util.WeixinUtil;

/**
 * @author yl
 *
 * 菜单管理器类
 */
public class MenuManager {
		
	/**
	 * 
	 */
	public MenuManager() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static MenuInfo getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("天气预报");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("公交查询");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("周边搜索");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("历史上的今天");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn21 = new CommonButton();
		btn21.setName("歌曲点播");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("经典游戏");
		btn22.setType("click");
		btn22.setKey("22");

		CommonButton btn23 = new CommonButton();
		btn23.setName("美女电台");
		btn23.setType("click");
		btn23.setKey("23");

		CommonButton btn24 = new CommonButton();
		btn24.setName("人脸识别");
		btn24.setType("click");
		btn24.setKey("24");

		CommonButton btn25 = new CommonButton();
		btn25.setName("聊天唠嗑");
		btn25.setType("click");
		btn25.setKey("25");

		CommonButton btn31 = new CommonButton();
		btn31.setName("Q友圈");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("电影排行榜");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("幽默笑话");
		btn33.setType("click");
		btn33.setKey("33");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("生活助手");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("休闲驿站");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("更多体验");
		mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });

		MenuInfo menu = new MenuInfo();
		menu.setButton(new Button[] { btn11, mainBtn2, mainBtn3 });
		Matchrule matchrule = new Matchrule();
		matchrule.setSex("1");//过滤男性
		menu.setMatchrule(matchrule);
		return menu;
	}

	public void testMenu(){
		System.out.println("testMenu begin!");
		
	}
	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId =/*"wx4e042e54157594c3";*/"wx1a0c4faa6e7e6b65";
		// 第三方用户唯一凭证密钥
		String appSecret =/* "9035c1349c6b589601dd19288cd11de6";*/"cdcfdcb581f0390b0d1224fba35f4fc0";
		System.out.println(appId);
		System.out.println(appSecret);
		
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			System.out.println("Token="+at.getToken());
			// 调用接口创建菜单
			//int result = MenuUtil.createMenu(getMenu(), at.getToken());
			String result = MenuUtil.createMatchruleMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			System.out.println("菜单创建返回结果：" + result);
		}
	}
	
}
