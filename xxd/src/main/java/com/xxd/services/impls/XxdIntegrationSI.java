package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdIntegrationMapper;
import com.xxd.models.XxdIntegration;
import com.xxd.models.XxdModel;
import com.xxd.services.XxdIntegrationS;

@Service
public class XxdIntegrationSI implements XxdIntegrationS{

	@Autowired
	private XxdIntegrationMapper mapper;

	@Override
	public Integer insert(XxdIntegration model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdIntegration model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdIntegration selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}
	
	@Override
	public XxdIntegration selectByPrimaryKeyParentId(Integer id, Integer parent_id) {
		return mapper.selectByPrimaryKeyParentId(new XxdModel(id, parent_id));
	}
	
	@Override
	public XxdIntegration selectByPrimaryKeyParentsId(Integer id, Integer parent_id) {
		return mapper.selectByPrimaryKeyParentsId(new XxdModel(id, parent_id));
	}
	
	@Override
	public XxdIntegration selectIntegrationByUsername(String username) {
		return mapper.selectIntegrationByUsername(username);
	}

	@Override
	public ArrayList<XxdIntegration> selectAll() {
		return mapper.selectAll();
	}
	
	@Override
	public ArrayList<XxdIntegration> selectAllParentId(Integer parent_id){
		return mapper.selectAllUserParentId(parent_id);
	}
	
	@Override
	public XxdIntegration selectUserInteParentId(Integer id, Integer parent_id) {
		return mapper.selectUserInteParentId(new XxdModel(id, parent_id));
	}
	
	@Override
	public XxdIntegration selectUserInteParentIds(Integer id) {
		return mapper.selectUserInteParentIds(id);
	}

}
