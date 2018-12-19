package com.xxd.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.annotations.AdminLogin;
import com.xxd.models.XxdCount;
import com.xxd.models.XxdUser;
import com.xxd.services.impls.XxdUserSI;
import com.xxd.utils.Constans;
import com.xxd.utils.U;

@Controller
@RequestMapping(value = "/XxdUser")
public class XxdUserC {

	@Autowired
	private XxdUserSI serviceImpl;
	

	/**
	 * 添加用户信息总方法（适用于总管理员）
	 * @param model
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/insert")
	public String insert(XxdUser model){
		//后台添加销总，只能添加一级销总
		model.setLeader_level(Constans.FIRSTLEADERLEVEL);
		Integer con = serviceImpl.insert(model);
		if(con == 2) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}
	
	/**
	 * 添加团导
	 * @param model
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/insertLeader")
	public String insertLeader(XxdUser model, HttpServletRequest request){
		//添加当前用户类型(团导)
		model.setType(Constans.LEADERUSER);
		//后台添加销总，只能添加一级销总
		model.setLeader_level(Constans.FIRSTLEADERLEVEL);
		//为当前添加的用户添加所属管理者（总管理员管理管理员，管理员管理团导，团导管理团员）
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		model.setParent_id(userInfo.getUid());
		Integer con = serviceImpl.insert(model);
		if(con == 2) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
		
	}
	
	/**
	 * 添加管理员
	 * @param model
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/insertManager")
	public String insertManager(XxdUser model, HttpServletRequest request){
		//添加当前用户类型(管理员)
		model.setType(Constans.MANAGERUSER);
		//为当前添加的用户添加所属管理者（总管理员管理管理员，管理员管理团导，团导管理团员）
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		model.setParent_id(userInfo.getUid());
		Integer con = serviceImpl.insert(model);
		if(con == 2) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}
	
	/**
	 * 添加团员
	 * @param model
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/insertMember")
	public String insertMember(XxdUser model, HttpServletRequest request){
		//添加当前用户类型(团员)
		model.setType(Constans.MEMBERUSER);
		//为当前添加的用户添加所属管理者（总管理员管理管理员，管理员管理团导，团导管理团员）
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		model.setParent_id(userInfo.getUid());
		Integer con = serviceImpl.insert(model);
		if(con == 2) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}

	/**
	 * 删除多个用户总方法（适用于总管理员）
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
	
	/**
	 * 删除多个团导方法
	 * @param primaryKey
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/datadelLeader")
	@ResponseBody
	public HashMap<String, Object> datadelLeader(String uids, HttpServletRequest request){
		Integer[] uid = U.getUid(uids);
		int result = 0;
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		for(int i = 0;i < uid.length;i ++) {
			result += serviceImpl.delete(uid[i], Constans.LEADERUSER, userInfo.getUid());
		}
		return Constans.returnCon(result, null);
	}
	
	/**
	 * 删除多个团员方法
	 * @param primaryKey
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/datadelMember")
	@ResponseBody
	public HashMap<String, Object> datadelMember(String uids, HttpServletRequest request){
		Integer[] uid = U.getUid(uids);
		int result = 0;
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		for(int i = 0;i < uid.length;i ++) {
			result += serviceImpl.delete(uid[i], Constans.MEMBERUSER, userInfo.getUid());
		}
		return Constans.returnCon(result, null);
	}
	
	/**
	 * 删除多个管理员方法
	 * @param primaryKey
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/datadelManager")
	@ResponseBody
	public HashMap<String, Object> datadelManager(String uids, HttpServletRequest request){
		Integer[] uid = U.getUid(uids);
		int result = 0;
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		for(int i = 0;i < uid.length;i ++) {
			result += serviceImpl.delete(uid[i], Constans.MANAGERUSER, userInfo.getUid());
		}
		return Constans.returnCon(result, null);
	}
	
	public static void main(String[] args) {
//		String uids = ",1,";
//		String[] str = uids.split(",");
//		int strLength = 0;
//		if(str[0].equals("") || str[0] == null) strLength ++;
//		String[] strs = new String[str.length - strLength];
//		for(int i = strLength;i < str.length;i ++) {
//			strs[i-strLength] = str[i];
//			System.out.println(i + "_" + str[i]);
//		}
		
		String uids = "1,2,3,";
		if(uids.indexOf(",") == 0) uids = uids.substring(1);
		if(uids.lastIndexOf(",") == uids.length()-1) uids = uids.substring(0, uids.length()-1);
		System.out.println(uids);
	}
	
	/**
	 * 删除用户总方法（适用于总管理员）
	 * @param primaryKey
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/delete")
	@ResponseBody
	public HashMap<String, Object> delete(Integer primaryKey){
		Integer con = serviceImpl.deleteByPrimaryKey(primaryKey);
		return Constans.returnCon(con, null);
	}
	
	/**
	 * 删除团导
	 * @param primaryKey
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/deleteLeader")
	@ResponseBody
	public HashMap<String, Object> deleteLeader(Integer uid, HttpServletRequest request){
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		Integer con = serviceImpl.delete(uid, Constans.LEADERUSER, userInfo.getUid());
		return Constans.returnCon(con, null);
	}
	
	/**
	 * 删除管理员
	 * @param primaryKey
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/deleteManager")
	@ResponseBody
	public HashMap<String, Object> deleteManager(Integer uid, HttpServletRequest request){
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		Integer con = serviceImpl.delete(uid, Constans.MANAGERUSER, userInfo.getUid());
		return Constans.returnCon(con, null);
	}
	
	/**
	 * 删除团员
	 * @param primaryKey
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/deleteMember")
	@ResponseBody
	public HashMap<String, Object> deleteMember(Integer uid, HttpServletRequest request){
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		Integer con = serviceImpl.delete(uid, Constans.MEMBERUSER, userInfo.getUid());
		return Constans.returnCon(con, null);
	}

	/**
	 * 修改用户总方法（适用于总管理员）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update")
	public String update(XxdUser model){
		U.cancelDisAbleStr(model);
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		if(con == 1) {
			return Constans.UPDATESUCCESSHTML;
		}else {
			return Constans.UPDATEERRORHTML;
		}
	}
	
	/**
	 * 修改用户启用状态总方法（适用于总管理员）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateSta")
	@ResponseBody
	public HashMap<String, Object> updateSta(XxdUser model){
		U.cancelDisAbleStr(model);
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}
	
	/**
	 * 修改团导
	 * @param model
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/updateLeader")
	public String updateLeader(XxdUser model, HttpServletRequest request){
		//防止前台恶意修改用户类型
		model.setType(Constans.LEADERUSER);
		//设置当前修改的用户所属管理者id为当前用户id
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		model.setParent_id(userInfo.getUid());
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		if(con == 1) {
			return Constans.UPDATESUCCESSHTML;
		}else {
			return Constans.UPDATEERRORHTML;
		}
	}
	
	/**
	 * 修改团导启用状态
	 * @param model
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/updateLeaderSta")
	@ResponseBody
	public HashMap<String, Object> updateLeaderSta(XxdUser model, HttpServletRequest request){
		//防止前台恶意修改用户类型
		model.setType(Constans.LEADERUSER);
		//设置当前修改的用户所属管理者id为当前用户id
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		model.setParent_id(userInfo.getUid());
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}
	
	/**
	 * 修改管理员
	 * @param model
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/updateManager")
	public String updateManager(XxdUser model, HttpServletRequest request){
		//防止前台恶意修改用户类型
		model.setType(Constans.MANAGERUSER);
		//设置当前修改的用户所属管理者id为当前用户id
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		model.setParent_id(userInfo.getUid());
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		if(con == 1) {
			return Constans.UPDATESUCCESSHTML;
		}else {
			return Constans.UPDATEERRORHTML;
		}
	}
	
	/**
	 * 修改管理员启用状态
	 * @param model
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/updateManagerSta")
	@ResponseBody
	public HashMap<String, Object> updateManagerSta(XxdUser model, HttpServletRequest request){
		//防止前台恶意修改用户类型
		model.setType(Constans.MANAGERUSER);
		//设置当前修改的用户所属管理者id为当前用户id
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		model.setParent_id(userInfo.getUid());
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}
	
	/**
	 * 修改团员
	 * @param model
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/updateMember")
	public String updateMember(XxdUser model, HttpServletRequest request){
		//防止前台恶意修改用户类型
		model.setType(Constans.MEMBERUSER);
		//设置当前修改的用户所属管理者id为当前用户id
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		//判断如果是团导修改，那么就强制加上当前团导的id为当前修改的用户父id
		if(userInfo.getType() == 2) model.setParent_id(userInfo.getUid());
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		if(con == 1) {
			return Constans.UPDATESUCCESSHTML;
		}else {
			return Constans.UPDATEERRORHTML;
		}
	}
	
	/**
	 * 修改团员启用状态
	 * @param model
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/updateMemberSta")
	@ResponseBody
	public HashMap<String, Object> updateMemberSta(XxdUser model, HttpServletRequest request){
		//防止前台恶意修改用户类型
		model.setType(Constans.MEMBERUSER);
		//设置当前修改的用户所属管理者id为当前用户id
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		//判断如果是团导修改，那么就强制加上当前团导的id为当前修改的用户父id
		if(userInfo.getType() == 2) model.setParent_id(userInfo.getUid());
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}
	
	/**
	 * 升级团员
	 * @param uid
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/pass")
	@ResponseBody
	public HashMap<String, Object> pass(Integer uid){
		XxdUser user = new XxdUser();
		user.setUid(uid);
		user.setType(Constans.LEADERUSER);
		user.setParent_id(-1);
		return Constans.returnCon(serviceImpl.updateByPrimaryKeySelective(user), null);
	}

	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){
		XxdUser con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? -1 : 1, con);
	}
	
	@RequestMapping(value = "/selectByType")
	@ResponseBody
	public HashMap<String, Object> selectByType(Short type){
		return Constans.returnCon(1, serviceImpl.selectByUserType(type));
	}
	
	@RequestMapping(value = "/selectByTypeNonInt")
	@ResponseBody
	public HashMap<String, Object> selectByTypeNonInt(Short type, Integer parent_id, HttpServletRequest request){
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		return Constans.returnCon(1, serviceImpl.selectByTypeNonInt(type, (parent_id == 0) ? userInfo.getUid() : parent_id));
	}
	
	@RequestMapping(value = "/selectByTypeParentId")
	@ResponseBody
	public HashMap<String, Object> selectByTypeParentId(Short type, Integer parent_id){
		return Constans.returnCon(1, serviceImpl.selectByTypeParentId(type, parent_id));
	}
	
	@RequestMapping(value = "/selectUserByUsername")
	@ResponseBody
	public HashMap<String, Object> selectUserByUsername(String username){
		return Constans.returnCon(1, serviceImpl.selectUserByUsername(username));
	}
	
	@RequestMapping(value = "/selectLikeUsername")
	@ResponseBody
	public HashMap<String, Object> selectLikeUsername(String username){
		return Constans.returnCon(1, serviceImpl.selectLikeUsername(username+"%"));
	}
	
	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public HashMap<String, Object> selectAll(){
		ArrayList<XxdUser> con = serviceImpl.selectAll();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}
	
	
	@RequestMapping(value = "/selectAllUser")
	@ResponseBody
	public HashMap<String, Object> selectAllUser(){
		Integer countOne = serviceImpl.selectOneLeverCount();
		Integer countTwo = serviceImpl.selectTwoLeverCount();
		Integer countMember = serviceImpl.selectMemberCount();
		HashMap<String,Object> con = new HashMap<String,Object>();
		con.put("oneLever",countOne);
		con.put("twoLever",countTwo);
		con.put("menber",countMember);
		return Constans.returnCon(null == con ? 1 : -1,con);
	}
	
	@RequestMapping(value = "/selectUserIncrease")
	@ResponseBody
	public HashMap<String, Object> selectUserIncrease(String time){
		ArrayList<XxdCount> allUserIncrease = serviceImpl.selectUserIncrease(time);
		ArrayList<XxdCount> oneLeverUserIncrease = serviceImpl.selectOneLeverIncrease(time);
		ArrayList<XxdCount> twoLeverUserIncrease = serviceImpl.selectTwoLeverIncrease(time);
		ArrayList<XxdCount> memberUserIncrease = serviceImpl.selectMembrtIncrease(time);
		int [] countAllUserIncrease = new int[24];
		int [] countOneLeverUserIncrease = new int [24];
		int [] countTwoLeverUserIncrease = new int [24];
		int [] countMemberUserIncrease = new int [24];
		HashMap<String,Object> con = new HashMap<String,Object>();
		
		for (Integer i = 0; i < 24;i++){
			for(Integer j = 0; j < allUserIncrease.size();j++){
				if(allUserIncrease.get(j).getHours() == i){
					countAllUserIncrease[i] = allUserIncrease.get(j).getCounts();
				}
			}
			for(Integer j = 0; j < oneLeverUserIncrease.size();j++){
				if(oneLeverUserIncrease.get(j).getHours() == i){
					countOneLeverUserIncrease[i] = oneLeverUserIncrease.get(j).getCounts();
				}
			}
			for(Integer j = 0; j < twoLeverUserIncrease.size();j++){
				if(twoLeverUserIncrease.get(j).getHours() == i){
					countTwoLeverUserIncrease[i] = twoLeverUserIncrease.get(j).getCounts();
				}
			}
			for(Integer j = 0; j < memberUserIncrease.size();j++){
				if(memberUserIncrease.get(j).getHours() == i){
					countMemberUserIncrease[i] = memberUserIncrease.get(j).getCounts();
				}
			}
		}
		con.put("allUserIncrease",countAllUserIncrease);
		con.put("oneLeverUserIncrease",countOneLeverUserIncrease);
		con.put("twoLeverUserIncrease",countTwoLeverUserIncrease);
		con.put("memberUserIncrease",countMemberUserIncrease);
		return Constans.returnCon(null == con ? 1 : -1,con);
	}

}
