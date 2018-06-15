package com.wybb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wybb.dao.IUserDao;
import com.wybb.entity.user.User;
import com.wybb.service.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserDao userDao;

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User findUserByOpenId(String openId) {
		return userDao.findByOpenId(openId);
	}
}
