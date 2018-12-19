package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdGoodsPriceMapper;
import com.xxd.models.XxdGoodsPrice;
import com.xxd.services.XxdGoodsPriceS;

@Service
public class XxdGoodsPriceSI implements XxdGoodsPriceS{

	@Autowired
	private XxdGoodsPriceMapper mapper;

	@Override
	public Integer insert(XxdGoodsPrice model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdGoodsPrice model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdGoodsPrice selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdGoodsPrice> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public ArrayList<XxdGoodsPrice> selectAllByGoodsId(Integer goods_id){
		return mapper.selectAllByGoodsId(goods_id);
	}
}
