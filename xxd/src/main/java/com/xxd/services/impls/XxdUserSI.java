package com.xxd.services.impls;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdIntegrationMapper;
import com.xxd.mappers.XxdUserMapper;
import com.xxd.models.XxdCount;
import com.xxd.models.XxdIntegration;
import com.xxd.models.XxdUser;
import com.xxd.models.XxdUserPowerGroup;
import com.xxd.models.XxdUsers;
import com.xxd.services.XxdUserS;
import com.xxd.utils.U;

@Service
public class XxdUserSI implements XxdUserS{

	@Autowired
	private XxdUserMapper mapper;
	
	@Autowired
	private XxdIntegrationMapper integrationService;
	
	@Override
	public Integer insert(XxdUser model) {
		//添加用户的添加时间
		model.setCreate_time(U.getNowTime());
		String salt = U.randomFour() + "";
		if(null == model.getPassword() || model.getPassword().equals("")) model.setPassword("123456");
		String password = U.md5Hex(model.getPassword() + salt);
		//添加加盐后的密码
		model.setPassword(password);
		//添加盐
		model.setSalt(salt);
		Integer result = mapper.insert(model);
		//添加用户的同时先添加积分信息
		//添加新用户的同时，添加积分信息
		XxdIntegration integration = new XxdIntegration();
		integration.setUid(model.getUid());
		integration.setIntegration(new BigDecimal(0));
		integration.setAbled(new BigDecimal(0));
		integration.setDisabled(new BigDecimal(0));
		result += integrationService.insert(integration);
		return result;
	}

	@Override
	public Integer delete(Integer uid, Short type, Integer parent_id) {
		return mapper.delete(new XxdUser(uid, type, parent_id));
	}
	
	
	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdUser model) {
		//针对修改密码的行为，如果用户不修改密码，传过来是空字符串
		if(model.getPassword() == "" || model.getPassword() == null) {
			model.setPassword(null);
		}else {
			String salt = U.randomFour() + "";
			String password = U.md5Hex(model.getPassword() + salt);
			//添加加盐后的密码
			model.setPassword(password);
			//添加盐
			model.setSalt(salt);
		}
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdUser selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdUser> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public XxdUser adminLogin(String username) {
		return mapper.adminLogin(username);
	}

	@Override
	public ArrayList<XxdUser> selectByUserType(Short type) {
		return mapper.selectByUserType(type);
	}
	
	@Override
	public ArrayList<XxdUser> selectPowerByUserType(Short type) {
		return mapper.selectPowerByUserType(type);
	}
	
	@Override
	public ArrayList<XxdUsers> selects(Short type, Integer parent_id) {
		if(type == 2) {
			return mapper.selectLeader(new XxdUser(type, parent_id));
		}else if(type == 3){
			return mapper.selectMember(new XxdUser(type, parent_id));
		}
		return null;
	}
	
	@Override
	public ArrayList<XxdUsers> select(Short type) {
		if(type == 2) {
			return mapper.selectLeaderType(type);
		}else if(type == 3){
			return mapper.selectMemberType(type);
		}
		return null;
	}
	
	@Override
	public ArrayList<XxdUser> selectByTypeParentsId(Short type, Integer parent_id) {
		return mapper.selectByTypeParentsId(new XxdUser(type, parent_id));
	}
	
	@Override
	public ArrayList<XxdUser> selectByTypeParentId(Short type, Integer parent_id) {
		return mapper.selectByTypeParentId(new XxdUser(type, parent_id));
	}
	
	@Override
	public XxdUser selectUserByUsername(String username) {
		return mapper.selectUserByUsername(username);
	}
	
	@Override
	public ArrayList<XxdUser> selectLikeUsername(String username) {
		return mapper.selectLikeUsername(username);
	}
	
	@Override
	public ArrayList<XxdUser> selectByTypeNonInt(Short type, Integer parent_id) {
		ArrayList<Integer> haveUids = mapper.selectByTypeHaveInt(new XxdUser(type, parent_id));
		ArrayList<XxdUser> uids = mapper.selectByTypeParentId(new XxdUser(type, parent_id));
		ArrayList<XxdUser> nonHaveUids = new ArrayList<XxdUser>();
		for(XxdUser uid : uids) {
			if(!haveUids.contains(uid.getUid())) {
				nonHaveUids.add(uid);
			}
		}
		return nonHaveUids;
	}
	
	@Override
	public ArrayList<XxdUser> selectByParentId(Integer parent_id){
		return mapper.selectByParentId(parent_id);
	}

	@Override
	public ArrayList<XxdUserPowerGroup> selectUserPowerGroup() {
		return mapper.selectUserPowerGroup();
	}

	@Override
	public Integer selectOneLeverCount() {
		return mapper.selectByTypeOneLever();
	}

	@Override
	public Integer selectTwoLeverCount() {
		return mapper.selectByTypeTwoLever();
	}

	@Override
	public Integer selectMemberCount() {
		return mapper.selectByTypeMemberLever();
	}

	@Override
	public ArrayList<XxdCount> selectUserIncrease(String time) {
		if(time == null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			time = sdf.format(new Date());
		}
		return mapper.selectUserIncrease(time);
	}

	@Override
	public ArrayList<XxdCount> selectOneLeverIncrease(String time) {
		if(time == null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			time = sdf.format(new Date());
		}
		return mapper.selectOneLeverUserIncrease(time);
	}

	@Override
	public ArrayList<XxdCount> selectTwoLeverIncrease(String time) {
		if(time == null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			time = sdf.format(new Date());
		}
		return mapper.selectTwoLeverUserIncrease(time);
	}

	@Override
	public ArrayList<XxdCount> selectMembrtIncrease(String time) {
		if(time == null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			time = sdf.format(new Date());
		}
		return mapper.selectMemberUserIncrease(time);
	}

}
