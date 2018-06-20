package com.wybb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wybb.entity.Shop;

public interface IShopDao {
	
	@Insert("insert into shop (shopName,userId,shopCreateTime,shopType,shopDesc,shopEndTime)"
			+ " values(#{shop.shopName},#{shop.userId},#{shop.shopCreateTime},"
			+ "#{shop.shopType},#{shop.shopDesc},#{shop.shopEndTime})")
	int addShop(@Param("shop")Shop shop);
	
	
	@Select("select * from shop where shopName=#{shopName}")
	Shop findShopByName(@Param("shopName")String name);
}
