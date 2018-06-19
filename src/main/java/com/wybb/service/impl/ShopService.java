package com.wybb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wybb.dao.IShopDao;
import com.wybb.entity.user.Shop;
import com.wybb.service.IShopService;

@Service
public class ShopService implements IShopService{
	
	@Autowired
	private IShopDao shopDao;
	
	@Override
	public int addShop(Shop shop) {
		// TODO Auto-generated method stub
		return shopDao.addShop(shop);
	}

	@Override
	public boolean validateShopName(String name) {
		// TODO Auto-generated method stub
		return shopDao.findShopByName(name) != null;
	}

}
