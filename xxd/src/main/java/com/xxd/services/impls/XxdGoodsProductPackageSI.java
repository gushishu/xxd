package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdGoodsProductPackageMapper;
import com.xxd.models.XxdGoodsProductPackage;
import com.xxd.services.XxdGoodsProductPackageS;

@Service
public class XxdGoodsProductPackageSI implements XxdGoodsProductPackageS{

	@Autowired
	private XxdGoodsProductPackageMapper mapper;

	@Override
	public Integer insert(XxdGoodsProductPackage model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdGoodsProductPackage model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdGoodsProductPackage selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdGoodsProductPackage> selectAll() {
		return mapper.selectAll();
	}

}
