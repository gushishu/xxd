package com.xxd.services.impls;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.xxd.mappers.XxdBuyOrderMapper;
import com.xxd.models.XxdBuyOrder;
import com.xxd.models.XxdDeclaration;
import com.xxd.models.XxdOrder;
import com.xxd.services.XxdBuyOrderS;

@Service
public class XxdBuyOrderSI implements XxdBuyOrderS{

	@Autowired
	private XxdBuyOrderMapper mapper;

	@Override
	public Integer insert(XxdBuyOrder model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdBuyOrder model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdBuyOrder selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdBuyOrder> selectAll() {
		return mapper.selectAll();
	}
	
	@Override
	public ArrayList<XxdBuyOrder> selectAllByUidType(Integer uid, Short type){
		return mapper.selectAllByUidType(new XxdBuyOrder(uid, type));
	}
	
	@Override
	public ArrayList<XxdOrder> selectAllsByUidType(Integer buy_id, Short type){
		return mapper.selectOwnerAllsByUidType(new XxdBuyOrder(buy_id, type));
	}

	@Override
	public ArrayList<XxdBuyOrder> selectAllByType(Short type) {
		return mapper.selectAllByType(type);
	}

	@Override
	public ArrayList<XxdDeclaration> selectProductPackage() {
		return mapper.selectProductPackage();
	}

	@Override
	public int[]  selectAllBuyOrderTime(String date) {
		if(date == null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.format(new Date());
		}
		//获得一天的所有订单
		ArrayList<XxdBuyOrder> allOrder  = mapper.selectAllBuyTime(date);
		//计数数组
		int [] allCount = new int[24];
		//计数
		for(XxdBuyOrder xbo : allOrder){
			try {
				String time = xbo.getTime();
				SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date2 = sdf.parse(time);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date2);
				int hour =  calendar.get(Calendar.HOUR_OF_DAY);
				for(Integer i = 0; i < allCount.length; i++){
					if( hour== i){
						allCount[i] +=1;
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allCount;
	}

	@Override
	public int[] selectUpMemberOderTime(String date) {
		if(date == null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.format(new Date());
		}
		ArrayList<XxdBuyOrder> upMemberOrder  = mapper.selectUpMemberBuyTime(date);
		
		int [] upMemberCount = new int[24];
		
		for(XxdBuyOrder xbo : upMemberOrder){
			try {
			String time = xbo.getTime();
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2 = sdf.parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date2);
			int hour =  calendar.get(Calendar.HOUR_OF_DAY);
			for(Integer i = 0; i < upMemberCount.length; i++){
				if( hour== i){
					upMemberCount[i] +=1;
				}
			}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return upMemberCount;
	}

	@Override
	public int[] selectMemberOderTime(String date) {
		if(date == null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.format(new Date());
		}
		ArrayList<XxdBuyOrder> memberOrder  = mapper.selectMemberBuyTime(date);
		
		int [] memberCount = new int[24];
		
		for(XxdBuyOrder xbo : memberOrder){
			try {
			String time = xbo.getTime();
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2 = sdf.parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date2);
			int hour =  calendar.get(Calendar.HOUR_OF_DAY);
			for(Integer i = 0; i < memberCount.length; i++){
				if( hour== i){
					memberCount[i] +=1;
				}
			}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return memberCount;
	}
	
}
