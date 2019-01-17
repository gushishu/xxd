package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdGoodsPriceGroup;

public interface XxdGoodsPriceGroupS {

	public Integer insert(XxdGoodsPriceGroup model);
	
	public ArrayList<XxdGoodsPriceGroup> selectByGoodsId(Integer orderId);
	
	public XxdGoodsPriceGroup selectByGoodsIdFomatName(XxdGoodsPriceGroup xxdGoodsPriceGroup);
}
