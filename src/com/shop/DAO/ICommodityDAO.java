package com.shop.DAO;

import java.util.List;

import com.shop.model.CommodityBean;
import com.shop.model.OrderExtBean;
public interface ICommodityDAO {
	List<CommodityBean> getCommodity();
	List<CommodityBean> getCommodity(String cate);
	void updateCommodity(CommodityBean commodity, boolean hasImage);
	void addCommodity(CommodityBean commodity);
	void deleteCommodity(String id);
	void getCommodityById(CommodityBean cb,String id);
	void alterQuantity(OrderExtBean[] oeb);
}
