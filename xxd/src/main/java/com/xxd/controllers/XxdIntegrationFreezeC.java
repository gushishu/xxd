package com.xxd.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.models.XxdIntegration;
import com.xxd.models.XxdIntegrationFreeze;
import com.xxd.services.impls.XxdIntegrationFreezeSI;
import com.xxd.services.impls.XxdIntegrationSI;
import com.xxd.utils.Constans;

@Controller
@RequestMapping(value = "/XxdIntegrationFreeze")
public class XxdIntegrationFreezeC {

	@Autowired
	private XxdIntegrationFreezeSI serviceImpl;
	
	@Autowired
	private XxdIntegrationSI iServiceImpl;

	@RequestMapping(value = "/insert")
	@ResponseBody
	public HashMap<String, Object> insert(XxdIntegrationFreeze model){
		//当添加冻结积分时，需要所冻结积分用户进行重新算积分
		XxdIntegration intModel = iServiceImpl.selectByPrimaryKey(model.getId());
		iServiceImpl.updateByPrimaryKeySelective(intModel);
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
	public HashMap<String, Object> updateByPrimaryKeySelective(XxdIntegrationFreeze model){
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){
		XxdIntegrationFreeze con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public HashMap<String, Object> selectAll(){
		ArrayList<XxdIntegrationFreeze> con = serviceImpl.selectAll();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}
	
}
