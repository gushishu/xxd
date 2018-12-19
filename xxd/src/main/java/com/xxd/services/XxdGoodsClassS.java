package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdGoodsClass;

public interface XxdGoodsClassS {

	public Integer insert(XxdGoodsClass model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdGoodsClass model);

	public XxdGoodsClass selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdGoodsClass> selectAll();

}
