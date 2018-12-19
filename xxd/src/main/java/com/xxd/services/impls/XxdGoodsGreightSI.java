package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdGoodsGreightMapper;
import com.xxd.models.XxdGoodsGreight;
import com.xxd.services.XxdGoodsGreightS;

@Service
public class XxdGoodsGreightSI implements XxdGoodsGreightS{

	@Autowired
	private XxdGoodsGreightMapper mapper;

	@Override
	public Integer insert(XxdGoodsGreight model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdGoodsGreight model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdGoodsGreight selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdGoodsGreight> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public ArrayList<XxdGoodsGreight> selectAllByGid(Integer gid) {
		return mapper.selectAllByGid(gid);
	}
}
