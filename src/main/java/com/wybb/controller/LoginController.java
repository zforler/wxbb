package com.wybb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wybb.entity.User;
import com.wybb.service.impl.UserService;
import com.wybb.util.cache.TokenCache;
import com.wybb.util.result.Code;
import com.wybb.util.result.Result;
import com.wybb.util.wx.WxUtil;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登陆
	 * @author lqf
	 * @param params
	 * @param session
	 * @return
	 */
    @PostMapping("/login")
    public Result getJson(@RequestBody Map<String, String> params) {

	   String code = params.get("code");
	   String str = WxUtil.getWxOpenIdAndSesseionKey(code);
	   ObjectMapper mapper = new ObjectMapper();
	   Map<String,String> map = new HashMap<>();
	   try {
		    map = mapper.readValue(str, Map.class);
	   }
	   catch (IOException e) {
			e.printStackTrace();
	   }
	   
	   String openId = map.get("openid");
//	   String sessionKey = map.get("session_key");
	   
	   User u = userService.findUserByOpenId(openId);
	   System.out.println(u.getUserName());
	   Result result = new Result();
	   if(u != null){
		   result.setCode(Code.SUCCESS.getCode());
    	   result.setMsg(Code.SUCCESS.getMsg());
    	   u.setCode(code);
    	   
    	   result.setData(u);
    	   
    	   TokenCache.set(code,u);
    	   return result;
	   }
	   
	   User user = new User();
	   user.setAvatarUrl(params.get("avatarUrl"));
	   user.setUserName(params.get("nickName"));
	   user.setGender(params.get("gender"));
	   long time = System.currentTimeMillis();
	   user.setSiginTime(time);
	   user.setLoginTime(time);
	   user.setCode(code);
	   user.setOpenId(openId);
	   user.setUserType("1");
	   
       int res = userService.addUser(user);
       
      
       if(1 == res){
    	   result.setCode(Code.SUCCESS.getCode());
    	   result.setMsg(Code.SUCCESS.getMsg());
    	   result.setData(user);
    	   
    	   TokenCache.set(code,u);
       }
       else{
    	   result.setCode(Code.FAILED.getCode());
    	   result.setMsg(Code.FAILED.getMsg());
       }
       
        return result;
    }
}
