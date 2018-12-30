package com.xxd.models;

import java.math.BigDecimal;

/**
 * 订单信息实体类
 * @author Liang
 * @version 1.0
 */

public class XxdOrder {
	
	private Integer id;
	private String order_id;
	private String name;
	private String show_img_dir;
	private Integer num;
	private BigDecimal price;
	private BigDecimal prices;
	private String format_name;
	private Integer uid;
	private String username;
	private Integer addr_id;
	private Short sta;
	private String staCon;
	private String time;
	private String expressNo;
	
	public XxdOrder() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShow_img_dir() {
		return show_img_dir;
	}

	public void setShow_img_dir(String show_img_dir) {
		this.show_img_dir = show_img_dir;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getFormat_name() {
		return format_name;
	}

	public void setFormat_name(String format_name) {
		this.format_name = format_name;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAddr_id() {
		return addr_id;
	}

	public void setAddr_id(Integer addr_id) {
		this.addr_id = addr_id;
	}

	public Short getSta() {
		return sta;
	}

	public void setSta(Short sta) {
		this.sta = sta;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStaCon() {
		return staCon;
	}

	public void setStaCon(String staCon) {
		this.staCon = staCon;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public final BigDecimal getPrices() {
		return prices;
	}

	public final void setPrices(BigDecimal prices) {
		this.prices = prices;
	}
	
}
