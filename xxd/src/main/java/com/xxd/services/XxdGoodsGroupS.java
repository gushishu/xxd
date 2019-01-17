package com.xxd.services;

import com.xxd.models.XxdGoodsGroup;

public interface XxdGoodsGroupS {

	public Integer insert(XxdGoodsGroup model);
	
	public Integer selectByGoodsId(Integer goodsId);
	
	public Integer updateInsertByPrimaryKeySelective(XxdGoodsGroup model);
}
