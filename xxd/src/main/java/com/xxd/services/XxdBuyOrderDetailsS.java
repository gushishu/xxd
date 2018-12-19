package com.xxd.services;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.xxd.models.XxdBuyOrderDetails;

public interface XxdBuyOrderDetailsS {

	public Integer insert(XxdBuyOrderDetails model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdBuyOrderDetails model);

	public XxdBuyOrderDetails selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdBuyOrderDetails> selectAll();
	
	public ArrayList<XxdBuyOrderDetails> selectAllByOrderId(Integer order_id, Integer uid);
	
	public ArrayList<XxdBuyOrderDetails> selectAllByOrderId1(Integer order_id);
	
	public BigDecimal selectBuyCarProfit();
	
	public BigDecimal selectUpCarProfit();

}
