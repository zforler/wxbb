package com.wybb.service;

import com.wybb.entity.Shop;

public interface IShopService {
	
	int addShop(Shop shop);
	
	boolean validateShopName(String name);
}
