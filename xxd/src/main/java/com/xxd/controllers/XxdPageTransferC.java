package com.xxd.controllers;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 * @author Liang
 * @version 1.0
 */

@Controller
@RequestMapping("/pageTransfer")
public class XxdPageTransferC {
	
	@RequestMapping("/{pageName}")
	public String pageTransfer(@Param("pageName") String pageName) {
		return pageName;
	}
}
