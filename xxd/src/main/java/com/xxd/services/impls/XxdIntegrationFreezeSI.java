package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdBuyOrderDetailsMapper;
import com.xxd.mappers.XxdIntegrationFreezeMapper;
import com.xxd.mappers.XxdIntegrationMapper;
import com.xxd.models.XxdBuyOrderDetails;
import com.xxd.models.XxdIntegration;
import com.xxd.models.XxdIntegrationFreeze;
import com.xxd.services.XxdIntegrationFreezeS;
import com.xxd.utils.Constans;
import com.xxd.utils.IntegrationU;
import com.xxd.utils.U;

@Service
public class XxdIntegrationFreezeSI implements XxdIntegrationFreezeS{

	@Autowired
	private XxdIntegrationFreezeMapper mapper;
	
	@Autowired
	private XxdIntegrationMapper iMapper;
	
	@Autowired
	private XxdBuyOrderDetailsMapper bMapper;

	@Override
	public Integer insert(XxdIntegrationFreeze model) {
		//添加用户的添加时间
		model.setTime(U.getNowTime());
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdIntegrationFreeze model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdIntegrationFreeze selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}
	
	@Override
	public ArrayList<XxdIntegrationFreeze> selectByUid(Integer uid) {
		return mapper.selectByUid(uid);
	}
	
	@Override
	public XxdIntegrationFreeze selectByPrimaryKeyParentId(Integer primaryKey, Integer parent_id) {
		return mapper.selectByPrimaryKeyParentId(primaryKey, parent_id);
	}

	@Override
	public ArrayList<XxdIntegrationFreeze> selectAll() {
		return mapper.selectAll();
	}
	
	

	@Scheduled(fixedRate = 60000)
	public void thawIntegration() {
		//查询冻结积分表格中是否已经有可以解冻的积分了
		ArrayList<XxdIntegrationFreeze> freezeInte = mapper.selectAbleFreeze();
		if(null == freezeInte) return;
		XxdIntegration con = null;
		String ids = "";
		for(XxdIntegrationFreeze freezeIntegration : freezeInte) {
			ids += freezeIntegration.getId() + "-";
			//修改冻结解冻时间
			freezeIntegration.setTimes(U.getNowTime());
			mapper.updateByPrimaryKeySelective(freezeIntegration);
			//如果积分所属用户为0，则不需要分配积分
			if(freezeIntegration.getUid() == 0) continue;
			//修改冻结积分所属用户的积分
			con = iMapper.selectByUid(freezeIntegration.getUid());
			con.setDisabled(con.getDisabled().subtract(freezeIntegration.getIntegration()));
			con.setAbled(con.getAbled().add(freezeIntegration.getIntegration()));
			iMapper.updateByPrimaryKeySelective(con);
			//修改当前订单的状态为4（商品已发送，但是由于某种原因还未达到买家的手中，请耐心等待）
			XxdBuyOrderDetails buyOrderDetails = bMapper.selectByPrimaryKey(freezeIntegration.getOdid());
			buyOrderDetails.setSta(Constans.BUYORDERWAITEND);
			bMapper.updateByPrimaryKeySelective(buyOrderDetails);
		}
		U.logAction("thawIntegration" + ids);
		freezeInte = null;
		con = null;
	}
	
	/*@Scheduled(fixedRate = 1000)
	public void delInvalidOrder() {
		//删除过期的订单信息
		int result = mapper.delInvalidOrder();
		U.logAction("delInvalidOrder" + result);
	}*/

}
