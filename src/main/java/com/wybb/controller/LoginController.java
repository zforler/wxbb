package com.wybb.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wybb.entity.user.User;
import com.wybb.util.http.HttpUtil;
import com.wybb.util.wx.WxUtil;

@RestController
public class LoginController {
	
	
   @PostMapping("/login")
    public User getJson(@RequestBody Map<String,Object> reqMap) {
        User user = new User();
        user.setUserId("123");
        user.setUserName("Tom");
        String code = (String) reqMap.get("code");
        String str = WxUtil.getWxOpenIdAndSesseionKey(code);
        System.out.println(str);
        
        return user;
    }
}
