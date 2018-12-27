package com.xxd.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xxd.models.XxdGoods;
import com.xxd.models.XxdGoodsPrice;
import com.xxd.services.impls.XxdGoodsPriceSI;
import com.xxd.services.impls.XxdGoodsSI;
import com.xxd.utils.Constans;
import com.xxd.utils.ImgU;
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
@RequestMapping(value = "/XxdGoodsPrice")
public class XxdGoodsPriceC {

	@Autowired
	private XxdGoodsPriceSI serviceImpl;
	
	@Autowired
	private XxdGoodsSI goodsService;

	@RequestMapping(value = "/insert")
	public String insert(XxdGoodsPrice model, HttpServletRequest request, HttpServletResponse res) throws IOException{
		//先查询该规格对应的商品的统一保存地址
		XxdGoods goodsModel = goodsService.selectByPrimaryKey(model.getGoodsId());
		String time = U.md5Hex(System.currentTimeMillis()+"");
		String nowTime = time.substring(28);
		String dirs = (goodsModel.getShowImgDir().split("/"))[0] + Constans.GOODSPRICEIMGDIR + nowTime;
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
        tmpFile.delete();
		model.setImg(dirs + ".jpg");
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
