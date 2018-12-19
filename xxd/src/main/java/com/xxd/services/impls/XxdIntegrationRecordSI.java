package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdIntegrationRecordMapper;
import com.xxd.models.XxdIntegrationRecord;
import com.xxd.services.XxdIntegrationRecordS;

@Service
public class XxdIntegrationRecordSI implements XxdIntegrationRecordS{

	@Autowired
	private XxdIntegrationRecordMapper mapper;

	@Override
	public Integer insert(XxdIntegrationRecord model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdIntegrationRecord model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdIntegrationRecord selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdIntegrationRecord> selectAll() {
		return mapper.selectAll();
	}

}
