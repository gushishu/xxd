package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdUserPower;

public interface XxdUserPowerS {

	public Integer insert(XxdUserPower model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdUserPower model);

	public XxdUserPower selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdUserPower> selectAll();
	
	public XxdUserPower selectByUid(Integer uid);

}
