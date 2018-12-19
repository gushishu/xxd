package com.xxd.services.impls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdOrderExpressNoMapper;
import com.xxd.models.XxdOrderExpressNo;
import com.xxd.services.XxdOrderExpressNoS;

@Service
public class XxdOrderExpressNoSI implements XxdOrderExpressNoS{

	@Autowired
	private XxdOrderExpressNoMapper mapper;

	@Override
	public Integer insert(XxdOrderExpressNo model) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setTime(sdf.format(date));
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdOrderExpressNo model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdOrderExpressNo selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

}
