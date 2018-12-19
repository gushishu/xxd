package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdPower;

public interface XxdPowerS {

	public Integer insert(XxdPower model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdPower model);

	public XxdPower selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdPower> selectAll();
	
	public ArrayList<XxdPower> selectUserPowers(Integer uid);
	
	public ArrayList<XxdPower> selectAllUserPowers();
	
	public ArrayList<XxdPower> selectByInId(String[] ids);

}
