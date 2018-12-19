package com.xxd.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.annotations.AdminLogin;
import com.xxd.models.XxdIntegration;
import com.xxd.models.XxdUser;
import com.xxd.services.impls.XxdIntegrationSI;
import com.xxd.services.impls.XxdUserSI;
import com.xxd.utils.Constans;
import com.xxd.utils.U;

@Controller
@RequestMapping(value = "/XxdIntegration")
public class XxdIntegrationC {

	@Autowired
	private XxdIntegrationSI serviceImpl;
	
	@Autowired
	private XxdUserSI userServiceImpl;

	@RequestMapping(value = "/insert")
	@ResponseBody
	public HashMap<String, Object> insert(XxdIntegration model){
		Integer con = serviceImpl.insert(model);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/deleteByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> deleteByPrimaryKey(Integer primaryKey){
		Integer con = serviceImpl.deleteByPrimaryKey(primaryKey);
		return Constans.returnCon(con, null);
	}
	
	/**
	 * 删除多个用户积分信息方法（适用于总管理员）
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

	@RequestMapping(value = "/updateByPrimaryKeySelective")
	@ResponseBody
	public HashMap<String, Object> updateByPrimaryKeySelective(XxdIntegration model){
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){
		XxdIntegration con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? 1 : -1, con);
	}
	
	@RequestMapping(value = "/selectIntegrationByUsername")
	@ResponseBody
	public HashMap<String, Object> selectIntegrationByUsername(String username){
		//先查询用户是否存在
		XxdUser userModel = userServiceImpl.selectUserByUsername(username);
		if(null == userModel) {
			return Constans.returnCon(-2, null);
		}else {
			XxdIntegration inteModel = serviceImpl.selectByPrimaryKey(userModel.getUid());
			if(null != inteModel) {
				return Constans.returnCon(-1, inteModel);
			}else {
				return Constans.returnCon(1, userModel.getUid());
			}
		}
	}

	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public HashMap<String, Object> selectAll(){
		ArrayList<XxdIntegration> con = serviceImpl.selectAll();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

}
