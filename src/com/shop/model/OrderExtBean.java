package com.shop.model;

import java.io.Serializable;

public class OrderExtBean implements Serializable {
	String id,commodityId;
	int price,buyQuantity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBuyquantity() {
		return buyQuantity;
	}

	public void setBuyquantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
}
