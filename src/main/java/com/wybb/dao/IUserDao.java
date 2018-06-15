package com.wybb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.wybb.entity.user.User;

@Mapper
public interface IUserDao {
	
	@Insert("insert into wybb.user (userName,userPhone,signinTime,loginTime,avatarUrl,gender,openId)"
			+ " values(#{user.userName},#{user.userPhone},#{user.signinTime},#{user.loginTime},#{user.avatarUrl},#{user.gender},"+"#{user.openId})")
	int addUser(@Param("user")User user);
	
	@Select("select userId,userName,userType,userPhone,userStatus,signinTime,loginTime,avatarUrl,gender,openId from wybb.user where openId=#{openId}")
	User findByOpenId(@Param("openId")String openId);
}
