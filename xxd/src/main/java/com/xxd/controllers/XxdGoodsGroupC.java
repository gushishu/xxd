package com.xxd.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.xxd.models.XxdGoods;
import com.xxd.models.XxdGoodsGroup;
import com.xxd.models.XxdGoodsPrice;
import com.xxd.models.XxdGoodsPriceGroup;
import com.xxd.services.XxdGoodsS;
import com.xxd.services.impls.XxdGoodsGroupSI;
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
	private XxdGoodsGroupSI serviceImpl;
	
	@Autowired
	private XxdGoodsS xxdGoodsService;
	
	@RequestMapping(value = "/insert")
	public String inert(XxdGoodsGroup model,HttpServletRequest request) throws IllegalStateException, IOException{
		//先查询该规格对应的商品的统一保存地址
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
		        //删除图片
				model.setGroupPicture(dirs + ".jpg");
				Integer con = serviceImpl.insert(model);
				
				if(con == 1) {
					return Constans.UPDATESUCCESSHTML;
				}else {
					return Constans.UPDATEERRORHTML;
				}
	}
	
	@RequestMapping(value= "/updateGroupGoods")
	@ResponseBody
	public String insertUpdateGoodsGroup(XxdGoodsGroup model, HttpServletRequest request) throws IllegalStateException, IOException{
		String jsonStr = request.getParameter("datas");
		ArrayList<XxdGoodsPriceGroup> xxdGoodsPriceGoup = new ArrayList<XxdGoodsPriceGroup>();
		XxdGoodsPriceGroup xxdGoodsPriceGroups= null;
		ObjectMapper mapper = new ObjectMapper();
		List<XxdGoodsPriceGroup> xxdGoodsPriceGroup = mapper.readValue(jsonStr, new TypeReference<List<XxdGoodsPriceGroup>>() {});
		//先查询该规格对应的商品的统一保存地址
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
        //删除图片
		model.setGroupPicture(dirs + ".jpg");
		Integer con;
		if( null == serviceImpl.selectByGoodsId(model.getGoodsId())) {
			con = serviceImpl.insert(model);
		}else {
			con = serviceImpl.updateInsertByPrimaryKeySelective(model);
		}
		if(con == 1) {
			return Constans.UPDATESUCCESSHTML;
		}else {
			return Constans.UPDATEERRORHTML;
		}
	}
}
