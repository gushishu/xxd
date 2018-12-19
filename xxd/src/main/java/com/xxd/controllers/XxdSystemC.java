package com.xxd.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.models.XxdSystem;
import com.xxd.services.impls.XxdSystemSI;
import com.xxd.utils.Constans;

@Controller
@RequestMapping(value = "/XxdSystem")
public class XxdSystemC {

	@Autowired
	private XxdSystemSI serviceImpl;

	@RequestMapping(value = "/insert")
	@ResponseBody
	public HashMap<String, Object> insert(XxdSystem model){
		Integer con = serviceImpl.insert(model);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/deleteByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> deleteByPrimaryKey(Integer primaryKey){
		Integer con = serviceImpl.deleteByPrimaryKey(primaryKey);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/updateLeadaerProfits")
	@ResponseBody
	public HashMap<String, Object> updateLeadaerProfits(HttpServletRequest request){
		//由于利润不止一个
		ArrayList<XxdSystem> systemModel = serviceImpl.selectLikeByName("leaderPrice%");
		Integer con = 0;
		for(int i = 1;i <= systemModel.size();i ++) {
			systemModel.get(i).setValue(request.getAttribute(systemModel.get(i).getName()) + "");
			con += serviceImpl.updateByPrimaryKeySelective(systemModel.get(i));
		}
		return Constans.returnCon(con, null);
	}
	
	@RequestMapping(value = "/updateByPrimaryKeySelective")
	@ResponseBody
	public HashMap<String, Object> updateByPrimaryKeySelective(XxdSystem model){
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){
		XxdSystem con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public HashMap<String, Object> selectAll(){
		ArrayList<XxdSystem> con = serviceImpl.selectAll();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

}
