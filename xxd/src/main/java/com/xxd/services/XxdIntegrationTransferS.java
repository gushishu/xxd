package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdIntegrationTransfer;

public interface XxdIntegrationTransferS {

	public Integer insert(XxdIntegrationTransfer model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdIntegrationTransfer model);

	public XxdIntegrationTransfer selectByPrimaryKey(Integer primaryKey);
	
	public ArrayList<XxdIntegrationTransfer> selectByUid(Integer uid);

	public ArrayList<XxdIntegrationTransfer> selectAll();

}
