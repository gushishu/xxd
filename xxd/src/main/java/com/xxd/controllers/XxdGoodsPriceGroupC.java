package com.xxd.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.models.XxdGoodsPrice;
import com.xxd.models.XxdGoodsPriceGroup;
import com.xxd.services.XxdGoodsPriceGroupS;
import com.xxd.services.XxdGoodsPriceS;
import com.xxd.services.impls.XxdGoodsPriceGroupSI;
import com.xxd.utils.Constans;

@Controller
@RequestMapping(value = "/XxdGoodsPriceGroup")
public class XxdGoodsPriceGroupC {

	@Autowired
	private XxdGoodsPriceGroupS service;
	
	@Autowired
	private XxdGoodsPriceS xxdGoodsPrice;
	
	@Autowired
	private XxdGoodsPriceGroupS xxdGoodsPriceGroups;
	
	@RequestMapping(value = "/insertUpdate")
	@ResponseBody
	public void insertUpdate(HttpServletResponse response,HttpServletRequest request,HttpSession session) {
		String jsonStr = request.getParameter("datas");
		ArrayList<XxdGoodsPriceGroup> xxdGoodsPriceGoup = new ArrayList<XxdGoodsPriceGroup>();
		
		XxdGoodsPriceGroup xxdGoodsPriceGroup = null;
		/*if(result == 1) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}*/
	}
}
