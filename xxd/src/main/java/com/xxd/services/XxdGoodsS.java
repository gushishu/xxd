package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdGoods;
import com.xxd.models.XxdPictureHandle;

public interface XxdGoodsS {

	public Integer insert(XxdGoods model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdGoods model);
	
	public Integer updateIdById(XxdGoods model);

	public XxdGoods selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdGoods> selectAll();

	public ArrayList<XxdGoods> selectByUid(Integer uid);
	
	public ArrayList<XxdGoods> selectAllByType(Short type);
	
	public ArrayList<Integer> selectGoodsIds();
	
}
