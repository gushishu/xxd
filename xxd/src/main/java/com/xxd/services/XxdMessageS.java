package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdMessage;

public interface XxdMessageS {

	public Integer insert(XxdMessage model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdMessage model);

	public XxdMessage selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdMessage> selectAll();

}
