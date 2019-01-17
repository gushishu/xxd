package com.xxd.models;

import java.math.BigDecimal;

public class XxdGoodsPriceGroup {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_goods_price_group.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_goods_price_group.goods_group_id
     *
     * @mbggenerated
     */
    private Integer goodsGroupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_goods_price_group.goods_price_stock
     *
     * @mbggenerated
     */
    private Integer goodsPriceStock;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_goods_price_group.format_name
     *
     * @mbggenerated
     */
    private String formatName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_goods_price_group.market_price
     *
     * @mbggenerated
     */
    private BigDecimal marketPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_goods_price_group.member_price
     *
     * @mbggenerated
     */
    private BigDecimal memberPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_goods_price_group.group_member_price
     *
     * @mbggenerated
     */
    private BigDecimal groupMemberPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_goods_price_group.img
     *
     * @mbggenerated
     */
    private String img;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_goods_price_group.price
     *
     * @mbggenerated
     */
    private BigDecimal price;
    
    private Integer sign;
    
    

    public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_goods_price_group.id
     *
     * @return the value of xxd_goods_price_group.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_goods_price_group.id
     *
     * @param id the value for xxd_goods_price_group.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_goods_price_group.goods_group_id
     *
     * @return the value of xxd_goods_price_group.goods_group_id
     *
     * @mbggenerated
     */
    public Integer getGoodsGroupId() {
        return goodsGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_goods_price_group.goods_group_id
     *
     * @param goodsGroupId the value for xxd_goods_price_group.goods_group_id
     *
     * @mbggenerated
     */
    public void setGoodsGroupId(Integer goodsGroupId) {
        this.goodsGroupId = goodsGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_goods_price_group.goods_price_stock
     *
     * @return the value of xxd_goods_price_group.goods_price_stock
     *
     * @mbggenerated
     */
    public Integer getGoodsPriceStock() {
        return goodsPriceStock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_goods_price_group.goods_price_stock
     *
     * @param goodsPriceStock the value for xxd_goods_price_group.goods_price_stock
     *
     * @mbggenerated
     */
    public void setGoodsPriceStock(Integer goodsPriceStock) {
        this.goodsPriceStock = goodsPriceStock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_goods_price_group.format_name
     *
     * @return the value of xxd_goods_price_group.format_name
     *
     * @mbggenerated
     */
    public String getFormatName() {
        return formatName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_goods_price_group.format_name
     *
     * @param formatName the value for xxd_goods_price_group.format_name
     *
     * @mbggenerated
     */
    public void setFormatName(String formatName) {
        this.formatName = formatName == null ? null : formatName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_goods_price_group.market_price
     *
     * @return the value of xxd_goods_price_group.market_price
     *
     * @mbggenerated
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_goods_price_group.market_price
     *
     * @param marketPrice the value for xxd_goods_price_group.market_price
     *
     * @mbggenerated
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_goods_price_group.member_price
     *
     * @return the value of xxd_goods_price_group.member_price
     *
     * @mbggenerated
     */
    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_goods_price_group.member_price
     *
     * @param memberPrice the value for xxd_goods_price_group.member_price
     *
     * @mbggenerated
     */
    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_goods_price_group.group_member_price
     *
     * @return the value of xxd_goods_price_group.group_member_price
     *
     * @mbggenerated
     */
    public BigDecimal getGroupMemberPrice() {
        return groupMemberPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_goods_price_group.group_member_price
     *
     * @param groupMemberPrice the value for xxd_goods_price_group.group_member_price
     *
     * @mbggenerated
     */
    public void setGroupMemberPrice(BigDecimal groupMemberPrice) {
        this.groupMemberPrice = groupMemberPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_goods_price_group.img
     *
     * @return the value of xxd_goods_price_group.img
     *
     * @mbggenerated
     */
    public String getImg() {
        return img;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_goods_price_group.img
     *
     * @param img the value for xxd_goods_price_group.img
     *
     * @mbggenerated
     */
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_goods_price_group.price
     *
     * @return the value of xxd_goods_price_group.price
     *
     * @mbggenerated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_goods_price_group.price
     *
     * @param price the value for xxd_goods_price_group.price
     *
     * @mbggenerated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}