package com.xxd.models;

public class XxdModel {
	
	private Integer id;
	
	private Integer parent_id;
	
	private Integer uid;
	
	private Integer order_id;
	
	public XxdModel() {}
	
	public XxdModel(Integer id, Integer parent_id) {
		setId(id);
		setParent_id(parent_id);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	
}
