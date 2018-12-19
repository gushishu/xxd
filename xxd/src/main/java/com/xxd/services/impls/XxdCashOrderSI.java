package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdCashOrderMapper;
import com.xxd.models.XxdCashOrder;
import com.xxd.services.XxdCashOrderS;

@Service
public class XxdCashOrderSI implements XxdCashOrderS{

	@Autowired
	private XxdCashOrderMapper mapper;

	@Override
	public Integer insert(XxdCashOrder model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdCashOrder model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdCashOrder selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdCashOrder> selectAll() {
		return mapper.selectAll();
	}

}
