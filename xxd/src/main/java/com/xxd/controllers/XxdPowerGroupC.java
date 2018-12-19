package com.xxd.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.annotations.AdminLogin;
import com.xxd.models.XxdPowerGroup;
import com.xxd.services.impls.XxdPowerGroupSI;
import com.xxd.utils.Constans;
import com.xxd.utils.U;

@Controller
@RequestMapping(value = "/XxdPowerGroup")
public class XxdPowerGroupC {

	@Autowired
	private XxdPowerGroupSI serviceImpl;

	@RequestMapping(value = "/insert")
	public String insert(String name, String[] powersArray, Short isAbled){
		XxdPowerGroup model = new XxdPowerGroup();
		model.setCreateTime(U.getNowTime());
		model.setPowers(StringUtils.join(powersArray, ","));
		model.setName(name);
		model.setIsAbled(isAbled);
		Integer con = serviceImpl.insert(model);
		if(con == 1) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}

	/**
	 * 删除多个权限组总方法（适用于总管理员）
	 * @param primaryKey
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/datadel")
	@ResponseBody
	public HashMap<String, Object> datadel(String ids, HttpServletRequest request){
		Integer[] id = U.getUid(ids);
		int result = 0;
		for(int i = 0;i < id.length;i ++) {
			result += serviceImpl.deleteByPrimaryKey(id[i]);
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
	public String updateByPrimaryKeySelective(String name, String[] powersArray, Short isAbled, Integer id){
		XxdPowerGroup model = new XxdPowerGroup();
		model.setPowers(StringUtils.join(powersArray, ","));
		model.setName(name);
		model.setIsAbled(isAbled);
		model.setId(id);
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
		XxdPowerGroup con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public HashMap<String, Object> selectAll(){
		ArrayList<XxdPowerGroup> con = serviceImpl.selectAll();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

}
