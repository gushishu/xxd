package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdGoodsParametricMapper;
import com.xxd.models.XxdGoodsParametric;
import com.xxd.services.XxdGoodsParametricS;

@Service
public class XxdGoodsParametricSI implements XxdGoodsParametricS{

	@Autowired
	private XxdGoodsParametricMapper mapper;

	@Override
	public Integer insert(XxdGoodsParametric model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdGoodsParametric model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdGoodsParametric selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdGoodsParametric> selectAll() {
		return  null;//mapper.selectAll();
	}

	@Override
	public ArrayList<XxdGoodsParametric> selectByGoodsId(Integer goodsId) {
		return mapper.selectByGoodsId(goodsId);
	}

}
