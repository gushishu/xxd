package com.xxd.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.models.XxdOrderExpressNo;
import com.xxd.services.impls.XxdOrderExpressNoSI;
import com.xxd.utils.Constans;

@Controller
@RequestMapping(value = "/XxdOrderExpressNo")
public class XxdOrderExpressNoC {

	@Autowired
	private XxdOrderExpressNoSI serviceImpl;

	@RequestMapping(value = "/insert")
	@ResponseBody
	public HashMap<String, Object> insert(XxdOrderExpressNo model){
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
	public HashMap<String, Object> updateByPrimaryKeySelective(XxdOrderExpressNo model){
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){
		XxdOrderExpressNo con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

}
