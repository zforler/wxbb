package com.wybb.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	private static SSLContext sslContext;
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	static{
		try {
		  sslContext=SSLContext.getInstance("SSL");
		  //创建SSLContext 
		  TrustManager[] tm={new MyX509TrustManager()}; 
		  //初始化 
		  sslContext.init(null, tm, new java.security.SecureRandom());
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch (KeyManagementException e) {
			e.printStackTrace();
		} 
	}
	
	public static String httpsRequest(String requestUrl,String requestMethod, Map<String, Object> params){ 
		  StringBuffer buffer=null; 
		  try{ 
			  //获取SSLSocketFactory对象 
			  SSLSocketFactory ssf=sslContext.getSocketFactory(); 
			  URL url=new URL(requestUrl); 
			  HttpsURLConnection conn=(HttpsURLConnection)url.openConnection(); 
			  conn.setDoOutput(true); 
			  conn.setDoInput(true); 
			  conn.setUseCaches(false); 
			  conn.setRequestMethod(requestMethod); 
			  //设置当前实例使用的SSLSoctetFactory 
			  conn.setSSLSocketFactory(ssf); 
			  conn.connect(); 
			  //往服务器端写内容 
			  if(params != null && params.size() > 0){ 
				  String paramsStr = objectMapper.writeValueAsString(params);
				  OutputStream os=conn.getOutputStream(); 
				  os.write(paramsStr.getBytes("utf-8")); 
				  os.close(); 
			  } 
			  //读取服务器端返回的内容 
			  InputStream is=conn.getInputStream(); 
			  InputStreamReader isr=new InputStreamReader(is,"utf-8"); 
			  BufferedReader br=new BufferedReader(isr); 
			  buffer=new StringBuffer(); 
			  String line=null; 
			  while((line=br.readLine())!=null){ 
			    buffer.append(line); 
			  } 
		  }
		  catch(Exception e){ 
		    e.printStackTrace(); 
		  } 
		  return buffer.toString(); 
	}
	//处理http请求 requestUrl为请求地址 requestMethod请求方式，值为"GET"或"POST" 
	  public static String httpRequest(String requestUrl,String requestMethod, Map<String, Object> params){ 
	    StringBuffer buffer=null; 
	    try{ 
	    URL url=new URL(requestUrl); 
	    HttpURLConnection conn=(HttpURLConnection)url.openConnection(); 
	    conn.setDoOutput(true); 
	    conn.setDoInput(true); 
	    conn.setRequestMethod(requestMethod); 
	    conn.connect(); 
	    //往服务器端写内容 也就是发起http请求需要带的参数 
	    if(params != null && params.size() > 0){ 
			  String paramsStr = objectMapper.writeValueAsString(params);
			  OutputStream os=conn.getOutputStream(); 
			  os.write(paramsStr.getBytes("utf-8")); 
			  os.close(); 
		  }  
	    //读取服务器端返回的内容 
	    InputStream is=conn.getInputStream(); 
	    InputStreamReader isr=new InputStreamReader(is,"utf-8"); 
	    BufferedReader br=new BufferedReader(isr); 
	    buffer=new StringBuffer(); 
	    String line=null; 
	    while((line=br.readLine())!=null){ 
	      buffer.append(line); 
	    } 
	    }catch(Exception e){ 
	      e.printStackTrace(); 
	    } 
	    return buffer.toString(); 
	  }
  
}
