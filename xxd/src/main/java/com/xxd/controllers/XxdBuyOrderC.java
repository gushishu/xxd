package com.xxd.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.aspectj.weaver.GeneratedReferenceTypeDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.models.XxdBuyOrder;
import com.xxd.services.impls.XxdBuyOrderSI;
import com.xxd.utils.Constans;

@Controller
@RequestMapping(value = "/XxdBuyOrder")
public class XxdBuyOrderC {

	@Autowired
	private XxdBuyOrderSI serviceImpl;

	@RequestMapping(value = "/insert")
	@ResponseBody
	public HashMap<String, Object> insert(XxdBuyOrder model){
		Integer con = serviceImpl.insert(model);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/deleteByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> deleteByPrimaryKey(Integer primaryKey){
		Integer con = serviceImpl.deleteByPrimaryKey(primaryKey);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective")
	@ResponseBody
	public HashMap<String, Object> updateByPrimaryKeySelective(XxdBuyOrder model){
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){
		XxdBuyOrder con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public HashMap<String, Object> selectAll(){
		ArrayList<XxdBuyOrder> con = serviceImpl.selectAll();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}
	
	@RequestMapping(value = "/selectAllBuyTime")
	@ResponseBody
	public  HashMap<String, Object> selectAllBuyTime(String  date){
		int[] con = serviceImpl.selectAllBuyOrderTime(date);
		int[] conn = serviceImpl.selectUpMemberOderTime(date);
		int [] connn = serviceImpl.selectMemberOderTime(date);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("allBuyOrderTime",con);
		map.put("upMemberOderTime",conn);
		map.put("memberOderTime",connn);
		return Constans.returnCon(null == map ? 1 : -1, map);
	}
}
