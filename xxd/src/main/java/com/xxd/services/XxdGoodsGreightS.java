package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdGoodsGreight;

public interface XxdGoodsGreightS {

	public Integer insert(XxdGoodsGreight model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdGoodsGreight model);

	public XxdGoodsGreight selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdGoodsGreight> selectAll();
	
	public ArrayList<XxdGoodsGreight> selectAllByGid(Integer gid);

}
