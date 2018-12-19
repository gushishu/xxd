package com.xxd.models;

public class XxdUserPowerGroup {

	//管理员信息
	private Integer uid;
	private String username;
	private String name;
	
	//权限组名称
	private Integer pgid;
	private String powerGroupName;
	
	//权限信息
	private String powers;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPowers() {
		return powers;
	}
	public void setPowers(String powers) {
		this.powers = powers;
	}
	public String getPowerGroupName() {
		return powerGroupName;
	}
	public void setPowerGroupName(String powerGroupName) {
		this.powerGroupName = powerGroupName;
	}
	public Integer getPgid() {
		return pgid;
	}
	public void setPgid(Integer pgid) {
		this.pgid = pgid;
	}
	
}
