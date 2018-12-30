package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdUserAddrMapper;
import com.xxd.models.XxdUserAddr;
import com.xxd.services.XxdUserAddrS;

@Service
public class XxdUserAddrSI implements XxdUserAddrS{

	@Autowired
	private XxdUserAddrMapper mapper;

	@Override
	public Integer insert(XxdUserAddr model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdUserAddr model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdUserAddr selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}
	
	@Override
	public ArrayList<XxdUserAddr> selectByUid(Integer uid) {
		return mapper.selectByUid(uid);
	}

	@Override
	public ArrayList<XxdUserAddr> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public XxdUserAddr selectById(Integer primaryKey) {
		return mapper.selectById(primaryKey);
	}

}
