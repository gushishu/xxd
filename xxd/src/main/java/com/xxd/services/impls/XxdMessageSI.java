package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdMessageMapper;
import com.xxd.models.XxdMessage;
import com.xxd.services.XxdMessageS;

@Service
public class XxdMessageSI implements XxdMessageS{

	@Autowired
	private XxdMessageMapper mapper;

	@Override
	public Integer insert(XxdMessage model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdMessage model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdMessage selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdMessage> selectAll() {
		return mapper.selectAll();
	}

}
