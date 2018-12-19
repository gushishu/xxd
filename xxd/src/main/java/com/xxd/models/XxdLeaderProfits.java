package com.xxd.models;

import java.math.BigDecimal;

public class XxdLeaderProfits {

	private Integer id;
	
	private String name;
	
	private BigDecimal value;
	
	private String description;

	public XxdLeaderProfits(Integer id, String name, BigDecimal value, String description) {
		super();
		setId(id);
		setName(name);
		setValue(value);
		setDescription(description);
	}

	public final Integer getId() {
		return id;
	}

	public final void setId(Integer id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final BigDecimal getValue() {
		return value;
	}

	public final void setValue(BigDecimal value) {
		this.value = value;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}
	
}
