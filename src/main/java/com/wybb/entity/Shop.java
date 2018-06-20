package com.wybb.entity;

public class Shop {
	private int id;
	private String shopName;
	private int userId;
	private String shopCreateTime;
	private int shopType;
	private String shopDesc;
	private String shopEndTime;
	private long shopVisitor;
	private int shopStatus;
	
	public int getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(int shopStatus) {
		this.shopStatus = shopStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getShopCreateTime() {
		return shopCreateTime;
	}
	public void setShopCreateTime(String shopCreateTime) {
		this.shopCreateTime = shopCreateTime;
	}
	public int getShopType() {
		return shopType;
	}
	public void setShopType(int shopType) {
		this.shopType = shopType;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public String getShopEndTime() {
		return shopEndTime;
	}
	public void setShopEndTime(String shopEndTime) {
		this.shopEndTime = shopEndTime;
	}
	public long getShopVisitor() {
		return shopVisitor;
	}
	public void setShopVisitor(long shopVisitor) {
		this.shopVisitor = shopVisitor;
	}
	
	
}
