package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdSystemMapper;
import com.xxd.models.XxdSystem;
import com.xxd.services.XxdSystemS;

@Service
public class XxdSystemSI implements XxdSystemS{

	@Autowired
	private XxdSystemMapper mapper;

	@Override
	public Integer insert(XxdSystem model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdSystem model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdSystem selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdSystem> selectAll() {
		return mapper.selectAll();
	}
	
	@Override
	public ArrayList<XxdSystem> selectLikeByName(String name) {
		return mapper.selectLikeByName(name);
	}

}
