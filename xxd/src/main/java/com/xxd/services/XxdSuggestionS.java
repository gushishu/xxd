package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdSuggestion;

public interface XxdSuggestionS {

	public Integer insert(XxdSuggestion model);

	public Integer deleteByPrimaryKey(Integer primaryKey);

	public Integer updateByPrimaryKeySelective(XxdSuggestion model);

	public XxdSuggestion selectByPrimaryKey(Integer primaryKey);

	public ArrayList<XxdSuggestion> selectAll();

}
