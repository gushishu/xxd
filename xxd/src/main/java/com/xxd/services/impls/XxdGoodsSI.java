package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdGoodsMapper;
import com.xxd.mappers.XxdPictureHandleMapper;
import com.xxd.models.XxdGoods;
import com.xxd.models.XxdPictureHandle;
import com.xxd.services.XxdGoodsS;

@Service
public class XxdGoodsSI implements XxdGoodsS{

	@Autowired
	private XxdGoodsMapper mapper;

	@Override
	public Integer insert(XxdGoods model) {
		return mapper.insert(model);
	}
	

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdGoods model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdGoods selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdGoods> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public ArrayList<XxdGoods> selectByUid(Integer uid) {
		return mapper.selectByUid(uid);
	}
	
	@Override
	public ArrayList<XxdGoods> selectAllByType(Short type){
		return mapper.selectAllByType(type);
	}

	@Override
	public ArrayList<Integer> selectGoodsIds() {
		return mapper.selectGoodsIds();
	}

	@Override
	public Integer updateIdById(XxdGoods model) {
		return mapper.updateIdById(model);
	}
}
