package com.wybb.service;

import com.wybb.entity.user.User;

public interface IUserService {
	int addUser(User user);
	
	User findUserByOpenId(String openId);
}
