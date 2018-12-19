package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdCount;
import com.xxd.models.XxdUser;
import com.xxd.models.XxdUserPowerGroup;
import com.xxd.models.XxdUsers;

public interface XxdUserS {

	public Integer insert(XxdUser model);

	public Integer delete(Integer uid, Short type, Integer parent_id);
	
	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdUser model);

	public XxdUser selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdUser> selectAll();
	
	public XxdUser adminLogin(String username);
	
	public ArrayList<XxdUser> selectByUserType(Short type);
	
	public ArrayList<XxdUsers> selects(Short type, Integer parent_id);
	
	public ArrayList<XxdUsers> select(Short type);
	
	public ArrayList<XxdUser> selectByTypeNonInt(Short type, Integer parent_id);
	
	public ArrayList<XxdUser> selectByTypeParentsId(Short type, Integer parent_id);
	
	public ArrayList<XxdUser> selectByTypeParentId(Short type, Integer parent_id);
	
	public XxdUser selectUserByUsername(String username);
	
	public ArrayList<XxdUser> selectLikeUsername(String username);

	public ArrayList<XxdUser> selectByParentId(Integer parent_id);
	
	public ArrayList<XxdUser> selectPowerByUserType(Short type);
	
	public ArrayList<XxdUserPowerGroup> selectUserPowerGroup();
	
	//一级销总统计
	public Integer  selectOneLeverCount();
	
	//二级销总统计
	public Integer  selectTwoLeverCount();
	
	//会员统计
	public Integer  selectMemberCount();
	
	//统计会员增长
	public ArrayList<XxdCount>  selectUserIncrease(String time);
	
	//统计一级销总数量增长
	public ArrayList<XxdCount>  selectOneLeverIncrease(String time);
	
	//统计耳机销总数量增长
	 public ArrayList<XxdCount> selectTwoLeverIncrease(String time);
	 
	 //统计会员数量增长
	 public ArrayList<XxdCount> selectMembrtIncrease(String time);
}
