package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdPowerGroup;

public interface XxdPowerGroupS {

	public Integer insert(XxdPowerGroup model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdPowerGroup model);

	public XxdPowerGroup selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdPowerGroup> selectAll();
	
	public ArrayList<XxdPowerGroup> selectAlls();
	
}
