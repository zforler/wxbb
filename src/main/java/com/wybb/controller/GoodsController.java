package com.wybb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wybb.service.IGoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	IGoodsService goodsService;
	
	@PostMapping("/add")
	public void addGoods() {
		
	}
	
	@PostMapping("/image/upload")
	public void goodsImageUpload() {
		
	}
}
