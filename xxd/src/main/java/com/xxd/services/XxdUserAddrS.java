package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdUserAddr;

public interface XxdUserAddrS {

	public Integer insert(XxdUserAddr model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdUserAddr model);

	public XxdUserAddr selectByPrimaryKey(Integer primaryKey);
	
	public ArrayList<XxdUserAddr> selectByUid(Integer uid);

	public ArrayList<XxdUserAddr> selectAll();

}
