package com.xxd.models;

import java.math.BigDecimal;

public class XxdDeclaration {
	
	//需要升级的团员用户信息
	private Integer uid;
	private String username;
	private String name;
	private String phonenumber;
	private String idCard;
	private String bankCard;
	private String bankLocation;
	//通过用户类型类判断当前团员是否被审核通过
	private Short type;
	
	//所属团导用户信息
	private Integer parentUid;
	private String parentUsername;
	private String parentName;

	//所购买的产品包中自有商品和团导商品价格
	private Integer id;
	private BigDecimal totalPrice;
	private BigDecimal ownerPrice;
	private BigDecimal leaderPrice;
	
	public XxdDeclaration() {}
	
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getBankLocation() {
		return bankLocation;
	}
	public void setBankLocation(String bankLocation) {
		this.bankLocation = bankLocation;
	}
	public Integer getParentUid() {
		return parentUid;
	}
	public void setParentUid(Integer parentUid) {
		this.parentUid = parentUid;
	}
	public String getParentUsername() {
		return parentUsername;
	}
	public void setParentUsername(String parentUsername) {
		this.parentUsername = parentUsername;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public BigDecimal getOwnerPrice() {
		return ownerPrice;
	}
	public void setOwnerPrice(BigDecimal ownerPrice) {
		this.ownerPrice = ownerPrice;
	}
	public BigDecimal getLeaderPrice() {
		return leaderPrice;
	}
	public void setLeaderPrice(BigDecimal leaderPrice) {
		this.leaderPrice = leaderPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}
	
}
