package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdIntegrationBuyRecord;

public interface XxdIntegrationBuyRecordS {

	public Integer insert(XxdIntegrationBuyRecord model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdIntegrationBuyRecord model);

	public XxdIntegrationBuyRecord selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdIntegrationBuyRecord> selectAll();

}
