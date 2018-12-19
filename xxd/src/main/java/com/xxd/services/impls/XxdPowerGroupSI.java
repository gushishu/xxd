package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdPowerGroupMapper;
import com.xxd.models.XxdPowerGroup;
import com.xxd.services.XxdPowerGroupS;

@Service
public class XxdPowerGroupSI implements XxdPowerGroupS{

	@Autowired
	private XxdPowerGroupMapper mapper;

	@Override
	public Integer insert(XxdPowerGroup model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdPowerGroup model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdPowerGroup selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}
	
	@Override
	public ArrayList<XxdPowerGroup> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public ArrayList<XxdPowerGroup> selectAlls() {
		return mapper.selectAlls();
	}

}
