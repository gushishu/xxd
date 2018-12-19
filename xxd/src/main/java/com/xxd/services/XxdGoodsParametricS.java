package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdGoodsParametric;

public interface XxdGoodsParametricS {

	public Integer insert(XxdGoodsParametric model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdGoodsParametric model);

	public XxdGoodsParametric selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdGoodsParametric> selectAll();
	
	public ArrayList<XxdGoodsParametric> selectByGoodsId(Integer goodsId);

}
