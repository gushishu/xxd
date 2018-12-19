package com.xxd.services.impls;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxd.mappers.XxdPictureHandleMapper;
import com.xxd.mappers.XxdPictureMapper;
import com.xxd.models.XxdImageMake;
import com.xxd.models.XxdPicture;
import com.xxd.models.XxdPictureHandle;
import com.xxd.models.XxdPictureUnion;
import com.xxd.services.XxdPictureHandleS;
import com.xxd.utils.Constans;

import freemarker.debug.impl.DebuggerService;

@Service
public class XxdPictureHandleSI implements XxdPictureHandleS {

	@Autowired
	private XxdPictureMapper mapper;
	
	@Autowired
	private XxdPictureHandleMapper handleMapper;
	
	
	@Override
	public Integer insert(XxdPicture model) {	
		return mapper.insert(model);
	}

	@Override
	public ArrayList<XxdPictureUnion> downLoadSeclect(Integer id) {
		return mapper.selectDownload(id);
	}

	@Override
	public ArrayList<XxdPictureHandle> selectTenRecord() {
		return handleMapper.selectTenRecord();
	}
	
	@Override
	public Integer insert(XxdPictureHandle model) {
		
		return handleMapper.insert(model);
	}

	@Override
	public ArrayList<XxdPictureHandle> checkPictureName(String goodName) {
		return handleMapper.selectByGoodName(goodName);
	}

	@Override
	public ArrayList<XxdPictureHandle> selectAllRecord() {
		return handleMapper.selectAllRecord();
	}

	@Override
	public Integer selectCountRecord() {
		return handleMapper.selectCountRecord();
	}

	@Override
	public ArrayList<XxdPictureHandle> lookUpHandleRecord(String goodName,String handleTime,String handleTime2) {
		XxdImageMake im = new XxdImageMake();
		im.setGoodName("%" + goodName + "%");
		im.setHandleTime(handleTime);
		im.setHandleTime2(handleTime2);
		return handleMapper.selectLookUpHandleRecord(im);
	}

}
