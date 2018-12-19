package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdIntegrationMapper;
import com.xxd.mappers.XxdIntegrationTransferMapper;
import com.xxd.models.XxdIntegration;
import com.xxd.models.XxdIntegrationTransfer;
import com.xxd.services.XxdIntegrationTransferS;
import com.xxd.utils.Constans;
import com.xxd.utils.U;

@Service
public class XxdIntegrationTransferSI implements XxdIntegrationTransferS{

	@Autowired
	private XxdIntegrationTransferMapper mapper;
	
	@Autowired
	private XxdIntegrationMapper inteMapper;

	@Override
	public Integer insert(XxdIntegrationTransfer model) {
		model.setTime(U.getNowTime());
		model.setFromId(0);
		model.setTransferType(Constans.TRANTRANINTE);
		//由于后台只有平台划拨给团导或者团员，因此只需要给划拨对象添加积分
		XxdIntegration inte = inteMapper.selectByUid(model.getToId());
		inte.setAbled(inte.getAbled().add(model.getIntegration()));
		inte.setIntegration(inte.getIntegration().add(model.getIntegration()));
		inteMapper.updateByPrimaryKey(inte);
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdIntegrationTransfer model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdIntegrationTransfer selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}
	
	@Override
	public ArrayList<XxdIntegrationTransfer> selectByUid(Integer uid) {
		return mapper.selectByUid(uid);
	}

	@Override
	public ArrayList<XxdIntegrationTransfer> selectAll() {
		return mapper.selectAll();
	}

}
