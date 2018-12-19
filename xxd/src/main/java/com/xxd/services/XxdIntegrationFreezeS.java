package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdIntegrationFreeze;

public interface XxdIntegrationFreezeS {

	public Integer insert(XxdIntegrationFreeze model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdIntegrationFreeze model);

	public XxdIntegrationFreeze selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdIntegrationFreeze> selectAll();
	
	public XxdIntegrationFreeze selectByPrimaryKeyParentId(Integer primaryKey, Integer parent_id);
	
	public ArrayList<XxdIntegrationFreeze> selectByUid(Integer uid);
	

}
