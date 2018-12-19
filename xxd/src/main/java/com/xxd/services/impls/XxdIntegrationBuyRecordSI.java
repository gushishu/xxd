package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdIntegrationBuyRecordMapper;
import com.xxd.models.XxdIntegrationBuyRecord;
import com.xxd.services.XxdIntegrationBuyRecordS;

@Service
public class XxdIntegrationBuyRecordSI implements XxdIntegrationBuyRecordS{

	@Autowired
	private XxdIntegrationBuyRecordMapper mapper;

	@Override
	public Integer insert(XxdIntegrationBuyRecord model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdIntegrationBuyRecord model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdIntegrationBuyRecord selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdIntegrationBuyRecord> selectAll() {
		return mapper.selectAll();
	}

}
