package com.xxd.controllers;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.xxd.models.XxdGoods;
import com.xxd.models.XxdGoodsGroup;
import com.xxd.models.XxdGoodsParametric;
import com.xxd.models.XxdGoodsPrice;
import com.xxd.models.XxdGoodsPriceGroup;
import com.xxd.services.XxdGoodsPriceGroupS;
import com.xxd.services.XxdGoodsS;
import com.xxd.services.impls.XxdGoodsGroupSI;
import com.xxd.services.impls.XxdGoodsParametricSI;
import com.xxd.services.impls.XxdGoodsPriceSI;
import com.xxd.utils.Constans;
import com.xxd.utils.ProperU;
import com.xxd.utils.U;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
@RequestMapping(value="/XxdGoodsGroup")
public class XxdGoodsGroupC {

	@Autowired
	private XxdGoodsGroupSI xxdGoodsGroupS;
	
	@Autowired
	private XxdGoodsS xxdGoodsService;
	
	@Autowired
	private XxdGoodsPriceGroupS xxdGoodsPriceGroups;
	
	@RequestMapping(value = "/uploadImg")
	@ResponseBody
	public void uploadImg(XxdGoodsGroup model,HttpServletRequest request) throws IllegalStateException, IOException{
		//先查询该规格对应的商品的统一保存地址
				ModelAndView mav = new ModelAndView();
				XxdGoods goodsModel = xxdGoodsService.selectByPrimaryKey(model.getGoodsId());
				String time = U.md5Hex(System.currentTimeMillis()+"");
				String nowTime = time.substring(28);
				
				String dirs = (goodsModel.getShowImgDir().split("/"))[0] + Constans.GOODSGROUPUMGDIR + nowTime;
				String dir = Constans.GOODSIMGDIR + dirs;
				//使用okhttp发起请求，传输图片
				OkHttpClient client = new OkHttpClient();
				//form表单上传
				MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
				Map<String, MultipartFile> files = multiRequest.getFileMap();
				MultipartFile file = files.get("file");
				String path = ProperU.read(ProperU.read(Constans.PROSOURCE, "img"), "imgSaveDir") + Constans.GOODSIMGDIR + Constans.GOODSTMPDIR + nowTime + ".jpg";
				new File(path.substring(0, path.lastIndexOf("/"))).mkdirs();
				File tmpFile = new File(path);
				file.transferTo(tmpFile);
				RequestBody body = RequestBody.create(MediaType.parse("image/*"), tmpFile);
				String filename = tmpFile.getName();
				requestBody.addFormDataPart("file", filename, body);
				Request req = new Request.Builder().url(ProperU.read(Constans.PROSOURCE, "host") + "/page/goodsPriceImgAdd?dir="+dir).post(requestBody.build()).build();
				// readTimeout("请求超时时间" , 时间单位);
		        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(req).enqueue(new Callback() {
		            public void onFailure(Call call, IOException e) {
		                U.exceptionLog(e, "goodsPriceException");
		            }
		            public void onResponse(Call call, Response response) throws IOException {
		                if (!response.isSuccessful()) {
		                	U.logAction("goodsPriceFailed");
		                }
		            }
		        });
		      /*  mav.setViewName("/admin/success");
		       return mav;*/
	}
	
	@RequestMapping(value= "/updateGroupGoods")
	public ModelAndView UpdateGoodsGroup( HttpServletRequest request) throws IllegalStateException, IOException, ParseException{
		XxdGoodsGroup xxdGoodsGroup = new XxdGoodsGroup();
		Integer conn = 0;
		Integer con = 0;
		Integer goodsGroupId = 0;
		ModelAndView mav = new ModelAndView();
		ObjectMapper mapper = new ObjectMapper();
		
		String xxdGoodsGroupStr = request.getParameter("datas2");
		if(xxdGoodsGroupStr != null) {
			List<XxdGoodsGroup> xxdGoodsGroupStrr = mapper.readValue(xxdGoodsGroupStr, new TypeReference<List<XxdGoodsGroup>>() {});
			XxdGoodsGroup xxdGoodsGroupTwo = xxdGoodsGroupStrr.get(0);
			XxdGoods goodsModel = xxdGoodsService.selectByPrimaryKey(xxdGoodsGroupTwo.getGoodsId());
			String time = U.md5Hex(System.currentTimeMillis()+"");
			String nowTime = time.substring(28);
			String dirs = (goodsModel.getShowImgDir().split("/"))[0] + Constans.GOODSGROUPUMGDIR + nowTime;
			String dir = Constans.GOODSIMGDIR + dirs;
			/*//使用okhttp发起请求，传输图片
			OkHttpClient client = new OkHttpClient();
			//form表单上传
			MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			Map<String, MultipartFile> files = multiRequest.getFileMap();
			MultipartFile file = files.get("file");
			String path = ProperU.read(ProperU.read(Constans.PROSOURCE, "img"), "imgSaveDir") + Constans.GOODSIMGDIR + Constans.GOODSTMPDIR + nowTime + ".jpg";
			new File(path.substring(0, path.lastIndexOf("/"))).mkdirs();
			File tmpFile = new File(path);
			file.transferTo(tmpFile);
			RequestBody body = RequestBody.create(MediaType.parse("image/*"), tmpFile);
			String filename = tmpFile.getName();
			requestBody.addFormDataPart("file", filename, body);
			Request req = new Request.Builder().url(ProperU.read(Constans.PROSOURCE, "host") + "/page/goodsPriceImgAdd?dir="+dir).post(requestBody.build()).build();
			// readTimeout("请求超时时间" , 时间单位);
	        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(req).enqueue(new Callback() {
	            public void onFailure(Call call, IOException e) {
	                U.exceptionLog(e, "goodsPriceException");
	            }
	            public void onResponse(Call call, Response response) throws IOException {
	                if (!response.isSuccessful()) {
	                	U.logAction("goodsPriceFailed");
	                }
	            }
	        });*/
			Integer frequency = 0;
			ArrayList<XxdGoodsGroup> xxdGoodsGroups = xxdGoodsGroupS.selectByGoodsId(xxdGoodsGroupTwo.getGoodsId());
			if(xxdGoodsGroups.size() == 0) {
				frequency  = 1;
			}else {
				for(Integer i = 0 ; i<xxdGoodsGroups.size();i++) {
					if(xxdGoodsGroups.get(i).getGroupTimes() > frequency) {
						frequency = xxdGoodsGroups.get(i).getGroupTimes();
					}
				}
				frequency +=1;
			}
			String xxdGoodsGroupStartTime = xxdGoodsGroupTwo.getGroupStartTime() +" "+ xxdGoodsGroupTwo.getGroupStartTimeHour() + ":00:00";
			String xxdGoodsGroupEndTime = xxdGoodsGroupTwo.getGroupEndTime() +" "+ xxdGoodsGroupTwo.getGroupEndTimeHour() + ":00:00";
			Date date  = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
			String createTime = sdf.format(date);
			Date startTime = sdf.parse(xxdGoodsGroupStartTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startTime);
			long startTimeSeconds = calendar.getTimeInMillis();
			Date endTime = sdf.parse(xxdGoodsGroupEndTime);
			calendar.setTime(endTime);
			long endTimeSeconds = calendar.getTimeInMillis();
			long seconds = endTimeSeconds - startTimeSeconds;
			long hours = seconds / 3600000;
			Integer hours2 = new Long(hours).intValue();
			Short hours3 = hours2.shortValue();
			Integer sta = 0;
			Short sta2 = sta.shortValue();
			xxdGoodsGroup.setGoodsId(xxdGoodsGroupTwo.getGoodsId());
			xxdGoodsGroup.setGroupNum(xxdGoodsGroupTwo.getGroupNum());
			xxdGoodsGroup.setGroupStartTime(xxdGoodsGroupStartTime);
			xxdGoodsGroup.setGroupEndTime(xxdGoodsGroupEndTime);
			xxdGoodsGroup.setGroupVaildTime(hours3);
			xxdGoodsGroup.setGroupTimes(frequency);
			xxdGoodsGroup.setTime(createTime);
			xxdGoodsGroup.setGroupSta(sta2);
			xxdGoodsGroup.setGroupTitle(xxdGoodsGroupTwo.getGroupTitle());
			xxdGoodsGroup.setGroupPicture(dirs + ".jpg");
			conn = xxdGoodsGroupS.insert(xxdGoodsGroup);
			goodsGroupId = xxdGoodsGroupS.selectByGroupTimes(frequency);
		}
		String jsonStr = request.getParameter("datas");
		if(jsonStr != null) {
			List<XxdGoodsPriceGroup> xxdGoodsPriceGroup = mapper.readValue(jsonStr, new TypeReference<List<XxdGoodsPriceGroup>>() {});
			for(Integer i = 0 ; i < xxdGoodsPriceGroup.size();i ++) {
				String img  = xxdGoodsPriceGroup.get(i).getImg();
				Integer begin = img.indexOf("goods/");
				String str = "goods/";
				begin = begin + str.length();
				Integer last = img.length();
				xxdGoodsPriceGroup.get(i).setImg(img.substring(begin,last));
				xxdGoodsPriceGroup.get(i).setGoodsGroupId(goodsGroupId);
				con = xxdGoodsPriceGroups.insert(xxdGoodsPriceGroup.get(i));
			}
		}
		if(conn == 1 && con == 1) {
			mav.setViewName("/admin/success");
		}else {
			mav.setViewName("/admin/error");
		}
		return mav;
	}
}
