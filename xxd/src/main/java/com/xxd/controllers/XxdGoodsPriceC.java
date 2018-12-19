package com.xxd.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.models.XxdGoods;
import com.xxd.models.XxdGoodsPrice;
import com.xxd.services.impls.XxdGoodsPriceSI;
import com.xxd.services.impls.XxdGoodsSI;
import com.xxd.utils.Constans;
import com.xxd.utils.ImgU;
import com.xxd.utils.U;

@Controller
@RequestMapping(value = "/XxdGoodsPrice")
public class XxdGoodsPriceC {

	@Autowired
	private XxdGoodsPriceSI serviceImpl;
	
	@Autowired
	private XxdGoodsSI goodsService;

	@RequestMapping(value = "/insert")
	public String insert(XxdGoodsPrice model, HttpServletRequest request){
		//先查询该规格对应的商品的统一保存地址
		XxdGoods goodsModel = goodsService.selectByPrimaryKey(model.getGoodsId());
		String dir = (goodsModel.getShowImgDir().split("/"))[0];
		HashMap<String, String> co = new HashMap<String, String>();
		String time = U.md5Hex(System.currentTimeMillis()+"");
		String nowTime = time.substring(28);
		co.put("file", Constans.GOODSIMGDIR + dir + Constans.GOODSPRICEIMGDIR + nowTime);
		ImgU.uploadImg(request, co);
		model.setImg(dir + Constans.GOODSPRICEIMGDIR + nowTime + ".jpg");
		Integer con = serviceImpl.insert(model);
		if(con == 1) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}

	@RequestMapping(value = "/deleteByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> deleteByPrimaryKey(Integer primaryKey){
		Integer con = serviceImpl.deleteByPrimaryKey(primaryKey);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective")
	public String updateByPrimaryKeySelective(XxdGoodsPrice model, HttpServletRequest request){
		//先查询该规格对应的商品的统一保存地址
		XxdGoods goodsModel = goodsService.selectByPrimaryKey(model.getGoodsId());
		String dir = (goodsModel.getShowImgDir().split("/"))[0];
		HashMap<String, String> co = new HashMap<String, String>();
		String time = U.md5Hex(System.currentTimeMillis()+"");
		String nowTime = time.substring(28);
		co.put("file", Constans.GOODSIMGDIR + dir + Constans.GOODSPRICEIMGDIR + nowTime);
		ImgU.uploadImg(request, co);
		model.setImg(null);
		if(co.get("file").equals("")) model.setImg(dir + Constans.GOODSPRICEIMGDIR + nowTime + ".jpg");
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		if(con == 1) {
			return Constans.UPDATESUCCESSHTML;
		}else {
			return Constans.UPDATEERRORHTML;
		}
	}

	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){
		XxdGoodsPrice con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public HashMap<String, Object> selectAll(){
		ArrayList<XxdGoodsPrice> con = serviceImpl.selectAll();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

}
