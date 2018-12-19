package com.xxd.utils;

import java.io.Serializable;

public class PictureHandleResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Short code;
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Short getCode() {
		return code;
	}

	public void setCode(Short code) {
		this.code = code;
	}

}
