package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdGoodsClassMapper;
import com.xxd.mappers.XxdGoodsMapper;
import com.xxd.models.XxdGoodsClass;
import com.xxd.services.XxdGoodsClassS;

@Service
public class XxdGoodsClassSI implements XxdGoodsClassS{

	@Autowired
	private XxdGoodsClassMapper mapper;
	
	@Autowired
	private XxdGoodsMapper goodsMapper;

	@Override
	public Integer insert(XxdGoodsClass model) {
		model.setLevel(Short.parseShort("1"));
		model.setParentId(0);
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		//删除之前检测是否被占用
		if(goodsMapper.selectAllByClassId(primaryKey).size() > 0) {
			return -1;
		}
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdGoodsClass model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdGoodsClass selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdGoodsClass> selectAll() {
		return mapper.selectAll();
	}

}
