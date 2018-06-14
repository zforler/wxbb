package com.wybb.util.wx;

import com.wybb.util.http.HttpUtil;

public class WxUtil {
	
	private final static String APPID = "wxe7a76ac644798b81";
	private final static String APPSECRET = "01c4495a7ccc145617c7e775be30677a";
	
	public static String getWxOpenIdAndSesseionKey(String code){
	        String url  = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+APPSECRET+"&js_code="+code+"&grant_type=authorization_code";
	        String str = HttpUtil.httpsRequest(url, "GET", null);
	        return str;
	}
}
