package com.wybb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wybb.dao.IGoodsDao;
import com.wybb.entity.Goods;
import com.wybb.service.IGoodsService;

@Service
public class GoodsService implements IGoodsService{
	
	@Autowired
	private IGoodsDao goodsDao;
	
	@Override
	public int addGoods(Goods goods) {
		int res = goodsDao.addShop(goods);
		// TODO Auto-generated method stub
		if(res> 0) {
			res = getLastId();
		}
		return res;
	}

	@Override
	public int getLastId() {
		// TODO Auto-generated method stub
		return goodsDao.getLastId();
	}

}
