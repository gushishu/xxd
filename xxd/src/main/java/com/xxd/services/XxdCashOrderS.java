package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdCashOrder;

public interface XxdCashOrderS {

	public Integer insert(XxdCashOrder model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdCashOrder model);

	public XxdCashOrder selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdCashOrder> selectAll();

}
