package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdBuyOrder;
import com.xxd.models.XxdDeclaration;
import com.xxd.models.XxdOrder;

public interface XxdBuyOrderS {

	public Integer insert(XxdBuyOrder model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdBuyOrder model);

	public XxdBuyOrder selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdBuyOrder> selectAll();
	
	public int[] selectAllBuyOrderTime(String date);
	
	public int[] selectUpMemberOderTime(String date);
	
	public int[] selectMemberOderTime(String date);
	
	public ArrayList<XxdBuyOrder> selectAllByUidType(Integer uid, Short type);
	
	public ArrayList<XxdOrder> selectAllsByUidType(Integer buy_id, Short type);
	
	public ArrayList<XxdBuyOrder> selectAllByType(Short type);
	
	public ArrayList<XxdDeclaration> selectProductPackage();

}
