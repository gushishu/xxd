package com.xxd.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.annotations.AdminLogin;
import com.xxd.models.XxdPictureHandle;
import com.xxd.models.XxdPictureUnion;
import com.xxd.services.impls.XxdPictureHandleSI;
import com.xxd.utils.Constans;
import com.xxd.utils.ImgU;
import com.xxd.utils.ProperU;
import com.xxd.utils.ZipUtils;

@Controller
@RequestMapping(value = "/XxdImage")
public class XxdPictureHandleC {
	
	//图片工具类的配置文件
	private static String pro = ProperU.read(Constans.PROSOURCE, "img");
	//图片大小处理路径文件夹
	private static String imgChangeDir = ProperU.read(pro,"imgChageDir");
	
	@Autowired
	private XxdPictureHandleSI pictureHandleServiceImp;

	/**
     * 打包压缩下载文件
     */
	
    @RequestMapping(value = "/downLoadZipFile")
    public void downLoadZipFile(HttpServletResponse response,Integer id) throws IOException{
        List<XxdPictureUnion> fileList = pictureHandleServiceImp.downLoadSeclect(id);//查询数据库中记录
        String zipName = id + ".zip";
        response.setContentType("APPLICATION/OCTET-STREAM");  
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            for(Iterator<XxdPictureUnion> it = fileList.iterator();it.hasNext();){
            	XxdPictureUnion file = it.next();
            	ZipUtils.doCompress(file.getUrl()+file.getName(), out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }
    
    @RequestMapping(value = "/insertPictureHandle")
	public String insertPictureHandle(XxdPictureHandle model, HttpServletRequest request) throws IOException{
		
		ImgU.makeImg(Constans.GOODSIMGDIR + model.getSaveDir());
		model.setUrl(imgChangeDir+Constans.GOODSIMGDIR + model.getSaveDir() + "/");
		String goodName = model.getGoodName();
		System.out.println(goodName);
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setHandleTime(formatter.format(currentTime));
		Integer result = pictureHandleServiceImp.insert(model);
		//上传文件会变成压缩包
		/*String goodName = "2";
		List<XxdPictureUnion> fileList = pictureHandleServiceImp.downLoadSeclect(goodName);
		ZipOutputStream out = null;
		for(Iterator<XxdPictureUnion> it = fileList.iterator();it.hasNext();){
        XxdPictureUnion file = it.next();
        //ZipUtils.doCompress(file.getUrl()+file.getName(), out);
		 }
        ZipCompress zipCompress = new ZipCompress(imgChangeDir+Constans.GOODSIMGDIR + model.getSaveDir()+".zip",imgChangeDir+Constans.GOODSIMGDIR + model.getSaveDir());
        try {
			zipCompress.zip();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if(result == 1) {
			return Constans.ADDSUCCESSHTML;
		}else {
			return Constans.ADDERRORHTML;
		}
	}
	
	@AdminLogin
	@RequestMapping(value = "/tenRecord")
	public HashMap<String,Object> tenRecord(){
		ArrayList<XxdPictureHandle> con =  pictureHandleServiceImp.selectTenRecord();
		return Constans.returnCon(null == con ? 1 : -1, con);
	}
	
	@RequestMapping(value = "/regist")
	@ResponseBody
	public HashMap<String,Object> pictureHandName(String goodName){
		//检查商品名字是否存在
		ArrayList<XxdPictureHandle> con = pictureHandleServiceImp.checkPictureName(goodName);
		return  Constans.returnCon(0 == con.size() ? 1 :-1, con);
	}
	
	/*@RequestMapping(value = "/CountRecord")
	public HashMap<String,Object> CountRecordPictureHandle(){
		Integer con = pictureHandleServiceImp.selectCountRecord();
		return Constans.returnCon(null == con ?1:-1, con);
	}*/
	
	@RequestMapping(value="/lookUpHandleRecord")
	@ResponseBody
	public HashMap<String,Object> lookUpHandleRecord(String goodName,String handleTime,String handleTime2){
		ArrayList<XxdPictureHandle> con = pictureHandleServiceImp.lookUpHandleRecord(goodName, handleTime, handleTime2);
		return Constans.returnCon(con == null? 1 : -1, con);
	}
	
	@RequestMapping(value="/handleAllRecord")
	@ResponseBody
	public HashMap<String,Object> handleAllRecord(){
		ArrayList<XxdPictureHandle> con =  pictureHandleServiceImp.selectAllRecord();
		return Constans.returnCon(con == null ? 1 : -1, con);
	}
}
