package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdIntegrationRecord;

public interface XxdIntegrationRecordS {

	public Integer insert(XxdIntegrationRecord model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdIntegrationRecord model);

	public XxdIntegrationRecord selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdIntegrationRecord> selectAll();

}
