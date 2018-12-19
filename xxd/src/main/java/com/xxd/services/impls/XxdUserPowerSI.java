package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdUserPowerMapper;
import com.xxd.models.XxdUserPower;
import com.xxd.services.XxdUserPowerS;

@Service
public class XxdUserPowerSI implements XxdUserPowerS{

	@Autowired
	private XxdUserPowerMapper mapper;

	@Override
	public Integer insert(XxdUserPower model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdUserPower model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdUserPower selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdUserPower> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public XxdUserPower selectByUid(Integer uid) {
		return mapper.selectByuid(uid);
	}

}
