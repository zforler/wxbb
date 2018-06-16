package com.wybb;


import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wybb.util.http.HttpUtil;

@SpringBootApplication
@MapperScan("com.wybb.dao")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
