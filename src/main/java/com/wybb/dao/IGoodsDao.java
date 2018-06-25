package com.wybb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wybb.entity.Goods;

public interface IGoodsDao {
	@Insert("insert into goods (goodsType,goodsName,shopId,goodsDesc,goodsPrice,goodsStatus,goodsCreateTime) "
			+ " values("
			+ "#{goods.goodsType}"
			+ ",#{goods.goodsName}"
			+ ",#{goods.shopId}"
			+ ",#{goods.goodsDesc}"
			+ ",#{goods.goodsPrice}"
			+ ",#{goods.goodsStatus}"
			+ ",#{goods.goodsCreateTime})")
	int addShop(@Param("goods")Goods goods);
	
	@Select("select LAST_INSERT_ID()")
	int getLastId();
}
