package com.wybb.service;

import com.wybb.entity.User;

public interface IUserService {
	int addUser(User user);
	
	User findUserByOpenId(String openId);
}
