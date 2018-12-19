package com.xxd.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.annotations.AdminLogin;
import com.xxd.models.XxdPower;
import com.xxd.services.impls.XxdPowerSI;
import com.xxd.utils.Constans;
import com.xxd.utils.U;

@Controller
@RequestMapping(value = "/XxdPower")
public class XxdPowerC {

	@Autowired
	private XxdPowerSI serviceImpl;

	@RequestMapping(value = "/insert")
	public String insert(XxdPower model){
		Integer con = serviceImpl.insert(model);
		if(con == 1) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}

	/**
	 * 删除多个权限总方法（适用于总管理员）
	 * @param primaryKey
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/datadel")
	@ResponseBody
	public HashMap<String, Object> datadel(String uids, HttpServletRequest request){
		Integer[] uid = U.getUid(uids);
		int result = 0;
		for(int i = 0;i < uid.length;i ++) {
			result += serviceImpl.deleteByPrimaryKey(uid[i]);
		}
		return Constans.returnCon(result, null);
	}
	
	@RequestMapping(value = "/deleteByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> deleteByPrimaryKey(Integer primaryKey){
		Integer con = serviceImpl.deleteByPrimaryKey(primaryKey);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective")
	@ResponseBody
	public HashMap<String, Object> updateByPrimaryKeySelective(XxdPower model){
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){
		XxdPower con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public HashMap<String, Object> selectAll(){
		ArrayList<XxdPower> con = serviceImpl.selectAll();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

}
