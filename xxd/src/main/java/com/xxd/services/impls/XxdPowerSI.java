package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdPowerMapper;
import com.xxd.models.XxdPower;
import com.xxd.services.XxdPowerS;

@Service
public class XxdPowerSI implements XxdPowerS{

	@Autowired
	private XxdPowerMapper mapper;

	@Override
	public Integer insert(XxdPower model) {
		return mapper.insertSelective(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdPower model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdPower selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdPower> selectAll() {
		return mapper.selectAll();
	}
	
	@Override
	public ArrayList<XxdPower> selectUserPowers(Integer uid){
		return mapper.selectUserPowers(uid);
	}
	
	@Override
	public ArrayList<XxdPower> selectByInId(String[] ids){
		return mapper.selectByInId(ids);
	}

	@Override
	public ArrayList<XxdPower> selectAllUserPowers() {
		return mapper.selectAllUserPowers();
	}

}
