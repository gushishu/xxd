package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdHopeBuyMapper;
import com.xxd.models.XxdUserHopeBuy;
import com.xxd.services.XxdUserHopeBuyS;

@Service
public class XxdUserHopeBuySI implements XxdUserHopeBuyS {

	@Autowired
	private XxdHopeBuyMapper xxdHopeBuyMapper;
	
	@Override
	public ArrayList<XxdUserHopeBuy> selectAllUserHopeBuy() {
		return xxdHopeBuyMapper.selectAllUserHopeBuy();
	}

}
