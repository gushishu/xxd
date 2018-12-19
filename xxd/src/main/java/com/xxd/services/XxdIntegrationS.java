package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdIntegration;

public interface XxdIntegrationS {

	public Integer insert(XxdIntegration model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdIntegration model);

	public XxdIntegration selectByPrimaryKey(Integer primaryKey);
	
	public XxdIntegration selectByPrimaryKeyParentId(Integer id, Integer parent_id);
	
	public XxdIntegration selectByPrimaryKeyParentsId(Integer id, Integer parent_id);
	
	public XxdIntegration selectIntegrationByUsername(String username);

	public ArrayList<XxdIntegration> selectAll();
	
	public ArrayList<XxdIntegration> selectAllParentId(Integer parent_id);
	
	public XxdIntegration selectUserInteParentId(Integer id, Integer parent_id);
	
	public XxdIntegration selectUserInteParentIds(Integer id);	

}
