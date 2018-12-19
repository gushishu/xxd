package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdGoodsPrice;

public interface XxdGoodsPriceS {

	public Integer insert(XxdGoodsPrice model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdGoodsPrice model);

	public XxdGoodsPrice selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdGoodsPrice> selectAll();
	
	public ArrayList<XxdGoodsPrice> selectAllByGoodsId(Integer goods_id);

}
