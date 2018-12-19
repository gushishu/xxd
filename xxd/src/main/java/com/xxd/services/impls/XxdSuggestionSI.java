package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdSuggestionMapper;
import com.xxd.models.XxdSuggestion;
import com.xxd.services.XxdSuggestionS;

@Service
public class XxdSuggestionSI implements XxdSuggestionS{

	@Autowired
	private XxdSuggestionMapper mapper;

	@Override
	public Integer insert(XxdSuggestion model) {
		return mapper.insert(model);
	}

	@Override
	public Integer deleteByPrimaryKey(Integer primaryKey) {
		return mapper.deleteByPrimaryKey(primaryKey);
	}

	@Override
	public Integer updateByPrimaryKeySelective(XxdSuggestion model) {
		return mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public XxdSuggestion selectByPrimaryKey(Integer primaryKey) {
		return mapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public ArrayList<XxdSuggestion> selectAll() {
		return mapper.selectAll();
	}

}
