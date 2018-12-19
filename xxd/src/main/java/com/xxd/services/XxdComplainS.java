package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdComplain;

public interface XxdComplainS {

	public Integer insert(XxdComplain model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdComplain model);

	public XxdComplain selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdComplain> selectAll();

}
