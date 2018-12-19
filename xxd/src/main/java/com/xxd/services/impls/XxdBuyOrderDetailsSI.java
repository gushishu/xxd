package com.xxd.services.impls;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdBuyOrderDetailsMapper;
import com.xxd.models.XxdBuyOrderDetails;
import com.xxd.models.XxdModel;
import com.xxd.services.XxdBuyOrderDetailsS;
import com.xxd.utils.U;

@Service
public class XxdBuyOrderDetailsSI implements XxdBuyOrderDetailsS{

	@Autowired
	private XxdBuyOrderDetailsMapper mapper;

	@Override
	public Integer insert(XxdBuyOrderDetails model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdBuyOrderDetails model) {
		//当修改状态为2时,添加时间
		if(model.getSta() == 2) {
			model.setTime(U.getNowTime());
		}
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdBuyOrderDetails selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdBuyOrderDetails> selectAll() {
		return mapper.selectAll();
	}
	
	@Override
	public ArrayList<XxdBuyOrderDetails> selectAllByOrderId(Integer order_id, Integer uid){
		XxdModel record = new XxdModel();
		record.setOrder_id(order_id);
		record.setUid(uid);
		return mapper.selectAllByOrderId(record);
	}
	
	@Override
	public ArrayList<XxdBuyOrderDetails> selectAllByOrderId1(Integer order_id){
		XxdModel record = new XxdModel();
		record.setOrder_id(order_id);
		return mapper.selectAllByOrderId1(record);
	}

	@Override
	public BigDecimal selectBuyCarProfit() {
		return mapper.selectBuyCarProfit();
	}
	
	@Override
	public BigDecimal selectUpCarProfit() {
		return mapper.selectUpCarProfit();
	}

}
