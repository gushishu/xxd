package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdOrderExpressNo;

public interface XxdOrderExpressNoS {

	public Integer insert(XxdOrderExpressNo model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdOrderExpressNo model);

	public XxdOrderExpressNo selectByPrimaryKey(Integer primaryKey);

}
