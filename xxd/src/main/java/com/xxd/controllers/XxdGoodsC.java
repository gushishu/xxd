package com.xxd.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.annotations.AdminLogin;
import com.xxd.models.XxdGoods;
import com.xxd.services.impls.XxdGoodsSI;
import com.xxd.utils.Constans;
import com.xxd.utils.ImgU;
import com.xxd.utils.U;


@Controller
@RequestMapping(value = "/XxdGoods")
public class XxdGoodsC {
	
	
	
	@Autowired
	private XxdGoodsSI serviceImpl;

	@RequestMapping(value = "/insert")
	public String insert(XxdGoods model, HttpServletRequest request){
		HashMap<String, String> co = new HashMap<String, String>();
		String time = U.md5Hex(System.currentTimeMillis()+"");
		String nowTime = time.substring(time.length()/2);
		//图文详情
		for(int i = 1;i <= 25;i ++) {
			co.put("if"+i, Constans.GOODSIMGDIR + nowTime + Constans.GOODSIMGFONTDIR + i);
		}
		//轮播详情
		for(int i = 1;i <= 25;i ++) {
			co.put("si"+i, Constans.GOODSIMGDIR + nowTime + Constans.GOODSSHOWIMGDIR + i);
		}
		HashMap<String, String> con = ImgU.uploadImg(request, co);
		int siNum = 0;
		int ifNum = 0;
		for(int i = 1;i <= 25;i ++) {
			if(con.get("si"+i).equals("")) siNum++;
			if(con.get("if"+i).equals("")) ifNum++;
		}
		model.setImgFontDir(nowTime + Constans.GOODSIMGFONTDIR + ifNum);
		model.setShowImgDir(nowTime + Constans.GOODSSHOWIMGDIR + siNum);
		Integer result = serviceImpl.insert(model);
		if(result == 1) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}
	
	@RequestMapping(value = "/insert1")
	public String insert1(XxdGoods model, HttpServletRequest request){
		ImgU.makeImg(Constans.GOODSIMGDIR + model.getSaveDir() + "/if");
		ImgU.makeImg(Constans.GOODSIMGDIR + model.getSaveDir() + "/si");
		
		model.setImgFontDir(model.getSaveDir() + Constans.GOODSIMGFONTDIR + model.getIfImg() );
		model.setShowImgDir(model.getSaveDir() + Constans.GOODSSHOWIMGDIR + model.getSiImg());
		
		Integer result = serviceImpl.insert(model);
		if(result == 1) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}
	
	
	
	@RequestMapping(value = "/insertPic")
	public String insertPic(XxdGoods model, HttpServletRequest request){
		ImgU.makeImg(Constans.GOODSIMGDIR + model.getSaveDir() + "/if");
		ImgU.makeImg(Constans.GOODSIMGDIR + model.getSaveDir() + "/si");
		model.setImgFontDir(model.getSaveDir() + Constans.GOODSIMGFONTDIR + model.getIfImg());
		model.setShowImgDir(model.getSaveDir() + Constans.GOODSSHOWIMGDIR + model.getSiImg());
		Integer result = serviceImpl.insert(model);
		if(result == 1) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}
	
	@RequestMapping("/addImg")
	@ResponseBody
	public HashMap<String, Object> addImg(Integer fileName, Integer id, Integer type, HttpServletRequest request){
		XxdGoods xg = serviceImpl.selectByPrimaryKey(id);
		String dir = type == 1 ? xg.getImgFontDir() : xg.getShowImgDir();
		HashMap<String, String> co = new HashMap<String, String>();
		String[] str = dir.split("/");
		String dirs = str[0] + "/" + str[1] + "/" + (Integer.parseInt(str[2])+1);
		co.put("file", Constans.GOODSIMGDIR + dirs);
		ImgU.uploadImg(request, co);
		XxdGoods model = new XxdGoods();
		if(type == 1) {
			model.setImgFontDir(dirs);
		}else {
			model.setShowImgDir(dirs);
		}
		model.setId(id);
		Integer result = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(result, null);
	}
	
	@RequestMapping("/addImg1")
	public String addImg1(Integer id, String saveDir, Integer siImg, Integer ifImg){
		XxdGoods model = new XxdGoods();
		model.setId(id);
		ImgU.makeImg(Constans.GOODSIMGDIR + saveDir + Constans.GOODSSHOWIMGDIR);
		ImgU.makeImg(Constans.GOODSIMGDIR + saveDir + Constans.GOODSIMGFONTDIR);
		model.setImgFontDir(saveDir + Constans.GOODSIMGFONTDIR + ifImg);
		model.setShowImgDir(saveDir + Constans.GOODSSHOWIMGDIR + siImg);
		Integer result = serviceImpl.updateByPrimaryKeySelective(model);
		if(result == 1) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}
	
	@RequestMapping("/deleteImg")
	@ResponseBody
	public HashMap<String, Object> deleteImg(String ids, Integer id, Integer type){
		XxdGoods xg = serviceImpl.selectByPrimaryKey(id);
		String dir = type == 1 ? xg.getImgFontDir() : xg.getShowImgDir();
		Integer[] idss = U.getUid(ids);
		String[] str = dir.split("/");
		String dirs = str[0] + "/" + str[1] + "/";
		for(int i = 0;i < idss.length;i ++) {
			ImgU.deleteImg(Constans.GOODSIMGDIR + dirs + idss[i] + ".jpg");
		}
		XxdGoods model = new XxdGoods();
		if(str[1].equals("if")) {
			model.setImgFontDir(dirs + ImgU.makeImg(Constans.GOODSIMGDIR + dirs));
		}else {
			model.setShowImgDir(dirs + ImgU.makeImg(Constans.GOODSIMGDIR + dirs));
		}
		model.setId(id);
		Integer result = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(result, null);
	}

	@RequestMapping(value = "/deleteByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> deleteByPrimaryKey(Integer primaryKey){
		Integer con = serviceImpl.deleteByPrimaryKey(primaryKey);
		return Constans.returnCon(con, null);
	}
	
	/**
	 * 删除多个商品总方法（适用于总管理员）
	 * @param primaryKey
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/datadel")
	@ResponseBody
	public HashMap<String, Object> datadel(String ids){
		Integer[] id = U.getUid(ids);
		int result = 0;
		for(int i = 0;i < id.length;i ++) {
			result += serviceImpl.deleteByPrimaryKey(id[i]);
			
		}
		return Constans.returnCon(result, null);
	}
	
	/**
	 * 删除商品图文详情图片和轮播图片方法
	 * @param primaryKey
	 * @return
	 */
	@AdminLogin
	@RequestMapping(value = "/datadelImg")
	@ResponseBody
	public HashMap<String, Object> datadelImg(Integer id, String saveDir){
		XxdGoods model = serviceImpl.selectByPrimaryKey(id);
		model.setShowImgDir(saveDir + Constans.GOODSSHOWIMGDIR + 0);
		model.setImgFontDir(saveDir + Constans.GOODSIMGFONTDIR + 0);
		//同时删除本地的图片
		ImgU.datadelImg(Constans.GOODSIMGDIR + saveDir + Constans.GOODSSHOWIMGDIR);
		ImgU.datadelImg(Constans.GOODSIMGDIR + saveDir + Constans.GOODSIMGFONTDIR);
		Integer result = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(result, null);
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective")
	public String updateByPrimaryKeySelective(XxdGoods model){
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		if(con == 1) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}
	
	@RequestMapping(value = "/updateSta")
	@ResponseBody
	public HashMap<String, Object> updateSta(XxdGoods model){
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){
		XxdGoods con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public HashMap<String, Object> selectAll(){
		ArrayList<XxdGoods> con = serviceImpl.selectAll();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

}
