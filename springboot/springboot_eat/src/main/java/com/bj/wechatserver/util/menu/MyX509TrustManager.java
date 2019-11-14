/**
 * 
 */
package com.bj.wechatserver.util.menu;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 菜单创建所需证书信任管理器（用于https请求）
 * @author 白健
 */
public class MyX509TrustManager implements X509TrustManager {
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
