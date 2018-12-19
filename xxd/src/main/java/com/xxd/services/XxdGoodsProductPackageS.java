package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdGoodsProductPackage;

public interface XxdGoodsProductPackageS {

	public Integer insert(XxdGoodsProductPackage model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdGoodsProductPackage model);

	public XxdGoodsProductPackage selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdGoodsProductPackage> selectAll();

}
