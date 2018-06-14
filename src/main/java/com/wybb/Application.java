package com.wybb;


import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wybb.util.http.HttpUtil;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
//		Map<String, Object> map = new HashMap<>();
//		map.put("a", 1);
//		map.put("b", 1);
//		String str = HttpUtil.httpsRequest("https://www.baidu.com", "GET", map);
//		System.out.println(str);
	}
}
