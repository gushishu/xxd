package com.xxd.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.models.XxdUserPower;
import com.xxd.services.impls.XxdPowerGroupSI;
import com.xxd.services.impls.XxdUserPowerSI;
import com.xxd.utils.Constans;

@Controller
@RequestMapping(value = "/XxdUserPower")
public class XxdUserPowerC {

	@Autowired
	private XxdUserPowerSI serviceImpl;
	
	@Autowired
	private XxdPowerGroupSI powerGroupService;

	@RequestMapping(value = "/insert")
	public String insert(Integer uid,Integer isGroup,Integer powerGroupId,String[] powersArray){
		XxdUserPower model = new XxdUserPower();
		//首先判断权限类型
		if(isGroup == 1){
			model.setPowerGroupId(powerGroupId);
			//然后提取权限列表
			model.setPowers(powerGroupService.selectByPrimaryKey(powerGroupId).getPowers());
		}else {
			model.setPowers(StringUtils.join(powersArray, ","));
		}
		model.setUid(uid);
		Integer con = serviceImpl.insert(model);
		if(con == 1) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}

	@RequestMapping(value = "/deleteByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> deleteByPrimaryKey(Integer primaryKey){
		Integer con = serviceImpl.deleteByPrimaryKey(primaryKey);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective")
	public String updateByPrimaryKeySelective(Integer id,Integer uid,Integer isGroup,Integer powerGroupId,String[] powersArray){
		XxdUserPower model = new XxdUserPower();
		model.setId(id);
		//首先判断权限类型
		if(isGroup == 1){
			model.setPowerGroupId(powerGroupId);
			//然后提取权限列表
			model.setPowers(powerGroupService.selectByPrimaryKey(powerGroupId).getPowers());
		}else {
			model.setPowers(StringUtils.join(powersArray, ","));
			model.setPowerGroupId(null);
		}
		model.setUid(uid);
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		if(con == 1) {
			return Constans.UPDATESUCCESSHTML;
		}else {
			return Constans.UPDATEERRORHTML;
		}
	}

	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){
		XxdUserPower con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public HashMap<String, Object> selectAll(){
		ArrayList<XxdUserPower> con = serviceImpl.selectAll();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

}
