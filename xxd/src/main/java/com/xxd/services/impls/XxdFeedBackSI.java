package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdSuggestionMapper;
import com.xxd.models.XxdFeedBack;
import com.xxd.services.XxdFeedBackS;


@Service
public class XxdFeedBackSI implements XxdFeedBackS {

	@Autowired
	private XxdSuggestionMapper xxdSuggestionmapper;
	
	@Override
	public ArrayList<XxdFeedBack> selectAllFeedBack() {
		return xxdSuggestionmapper.selectAllFeedBack();
	}

}
