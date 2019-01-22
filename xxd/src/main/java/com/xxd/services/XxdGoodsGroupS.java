package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdGoodsGroup;

public interface XxdGoodsGroupS {

	public Integer insert(XxdGoodsGroup model);
	
	public Integer selectByGroupTimes(Integer xxdGoupTimes);
	
	public ArrayList<XxdGoodsGroup> selectByGoodsId(Integer goodsId);
	
	public Integer updateInsertByPrimaryKeySelective(XxdGoodsGroup model);
}
