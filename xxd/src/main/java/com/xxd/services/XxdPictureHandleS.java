package com.xxd.services;

import java.util.ArrayList;

import com.xxd.models.XxdPicture;
import com.xxd.models.XxdPictureHandle;
import com.xxd.models.XxdPictureUnion;

public interface XxdPictureHandleS {

	/*public ArrayList<>*/
	
	public Integer insert(XxdPicture model);
	
	public ArrayList<XxdPictureUnion> downLoadSeclect(Integer id);
	
	public ArrayList<XxdPictureHandle> selectTenRecord(); 
	
	//图片处理存储记录表
	public Integer insert(XxdPictureHandle model);
	
	//检测图片修改名字是否存在
	public ArrayList<XxdPictureHandle> checkPictureName(String goodName);
	
	//获取所有图片处理记录
	public ArrayList<XxdPictureHandle> selectAllRecord();
	
	//统计所有处理数量
	public Integer selectCountRecord();
	
	//查找图片处理记录
	public ArrayList<XxdPictureHandle> lookUpHandleRecord(String goodName,String handleTime,String handleTime2);
}
