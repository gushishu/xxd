package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdGoodsGroupMapper;
import com.xxd.models.XxdGoodsGroup;
import com.xxd.services.XxdGoodsGroupS;

@Service
public class XxdGoodsGroupSI implements XxdGoodsGroupS{


	@Autowired
	private XxdGoodsGroupMapper mapper;
	
	@Override
	public Integer insert(XxdGoodsGroup model) {
		return mapper.insert(model);
	}

	@Override
	public ArrayList<XxdGoodsGroup> selectByGoodsId(Integer goodsId) {
		return mapper.selectBygoodsId(goodsId);
	}

	@Override
	public Integer updateInsertByPrimaryKeySelective(XxdGoodsGroup model) {
		return mapper.updateByPrimaryKey(model);
	}

	@Override
	public Integer selectByGroupTimes(Integer groupTimes) {
		
		return mapper.selectByGroupTimes(groupTimes);
	}
	
	

}
