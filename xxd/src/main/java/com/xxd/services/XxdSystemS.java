package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdSystem;

public interface XxdSystemS {

	public Integer insert(XxdSystem model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdSystem model);

	public XxdSystem selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdSystem> selectAll();

	public ArrayList<XxdSystem> selectLikeByName(String name);

}
