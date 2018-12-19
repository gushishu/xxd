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
import com.xxd.models.XxdGoodsProductPackage;
import com.xxd.services.impls.XxdGoodsProductPackageSI;
import com.xxd.utils.Constans;
import com.xxd.utils.ImgU;
import com.xxd.utils.U;

@Controller
@RequestMapping(value = "/XxdGoodsProductPackage")
public class XxdGoodsProductPackageC {

	@Autowired
	private XxdGoodsProductPackageSI serviceImpl;

	@RequestMapping(value = "/insert")
	public String insert(XxdGoodsProductPackage model, HttpServletRequest request){
		model.setType(Constans.GOODS);
		if(model.getOwnershipLeaderId() == 0) model.setType(Constans.GOODSOWNER);
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
		XxdGoodsProductPackage xg = serviceImpl.selectByPrimaryKey(id);
		String dir = type == 1 ? xg.getImgFontDir() : xg.getShowImgDir();
		HashMap<String, String> co = new HashMap<String, String>();
		String[] str = dir.split("/");
		String dirs = str[0] + "/" + str[1] + "/" + (Integer.parseInt(str[2])+1);
		co.put("file", "/goods/" + dirs);
		ImgU.uploadImg(request, co);
		XxdGoodsProductPackage model = new XxdGoodsProductPackage();
		if(type == 1) {
			model.setImgFontDir(dirs);
		}else {
			model.setShowImgDir(dirs);
		}
		model.setId(id);
		Integer result = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(result, null);
	}
	
	@RequestMapping("/deleteImg")
	@ResponseBody
	public HashMap<String, Object> deleteImg(String ids, Integer id, Integer type){
		XxdGoodsProductPackage xg = serviceImpl.selectByPrimaryKey(id);
		String dir = type == 1 ? xg.getImgFontDir() : xg.getShowImgDir();
		Integer[] idss = U.getUid(ids);
		String[] str = dir.split("/");
		String dirs = str[0] + "/" + str[1] + "/";
		for(int i = 0;i < idss.length;i ++) {
			ImgU.deleteImg("/goods/" + dirs + idss[i] + ".jpg");
		}
		XxdGoodsProductPackage model = new XxdGoodsProductPackage();
		if(str[1].equals("if")) {
			model.setImgFontDir(dirs + ImgU.makeImg("/goods/" + dirs));
		}else {
			model.setShowImgDir(dirs + ImgU.makeImg("/goods/" + dirs));
		}
		model.setId(id);
		Integer result = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(result, null);
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
	
	@RequestMapping(value = "/deleteByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> deleteByPrimaryKey(Integer primaryKey){
		Integer con = serviceImpl.deleteByPrimaryKey(primaryKey);
		return Constans.returnCon(con, null);
	}
	
	@RequestMapping(value = "/updateSta")
	@ResponseBody
	public HashMap<String, Object> updateSta(XxdGoodsProductPackage model){
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		return Constans.returnCon(con, null);
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective")
	public String updateByPrimaryKeySelective(XxdGoodsProductPackage model, HttpServletRequest request){
		String dir = model.getShowImgDir();
		HashMap<String, String> co = new HashMap<String, String>();
		String time = U.md5Hex(System.currentTimeMillis()+"");
		String nowTime = time.substring(28);
		co.put("file", Constans.GOODSIMGDIR + dir + "/" + nowTime);
		ImgU.uploadImg(request, co);
		//判断是否有图片上传，如果有的话就删除现有的图片
		if(co.get("file").equals("")) {
			ImgU.deleteImgOne(model.getImgFontDir());
			model.setImgFontDir(null);
			model.setShowImgDir(dir + "/" + nowTime + ".jpg");
		}else {
			model.setShowImgDir(null);
			model.setImgFontDir(null);
		}
		Integer con = serviceImpl.updateByPrimaryKeySelective(model);
		if(con == 1){
			return Constans.UPDATESUCCESSHTML;
		}else {
			return Constans.UPDATEERRORHTML;
		}
	}

	@RequestMapping(value = "/selectByPrimaryKey")
	@ResponseBody
	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){
		XxdGoodsProductPackage con = serviceImpl.selectByPrimaryKey(primaryKey);
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public HashMap<String, Object> selectAll(){
		ArrayList<XxdGoodsProductPackage> con = serviceImpl.selectAll();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}

}
