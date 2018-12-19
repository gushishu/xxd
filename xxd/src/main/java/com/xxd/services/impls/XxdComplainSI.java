package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdComplainMapper;
import com.xxd.models.XxdComplain;
import com.xxd.services.XxdComplainS;

@Service
public class XxdComplainSI implements XxdComplainS{

	@Autowired
	private XxdComplainMapper mapper;

	@Override
	public Integer insert(XxdComplain model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdComplain model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdComplain selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdComplain> selectAll() {
		return mapper.selectAll();
	}

}
