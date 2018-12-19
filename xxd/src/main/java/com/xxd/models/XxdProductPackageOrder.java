package com.xxd.models;

/**
 * 产品包订单信息
 * @author Liang
 * @version 1.0
 */

public class XxdProductPackageOrder {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_product_package_order.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_product_package_order.buy_order_id
     *
     * @mbggenerated
     */
    private Integer buyOrderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_product_package_order.owner_order_id
     *
     * @mbggenerated
     */
    private Integer ownerOrderId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_product_package_order.id
     *
     * @return the value of xxd_product_package_order.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_product_package_order.id
     *
     * @param id the value for xxd_product_package_order.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_product_package_order.buy_order_id
     *
     * @return the value of xxd_product_package_order.buy_order_id
     *
     * @mbggenerated
     */
    public Integer getBuyOrderId() {
        return buyOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_product_package_order.buy_order_id
     *
     * @param buyOrderId the value for xxd_product_package_order.buy_order_id
     *
     * @mbggenerated
     */
    public void setBuyOrderId(Integer buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_product_package_order.owner_order_id
     *
     * @return the value of xxd_product_package_order.owner_order_id
     *
     * @mbggenerated
     */
    public Integer getOwnerOrderId() {
        return ownerOrderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_product_package_order.owner_order_id
     *
     * @param ownerOrderId the value for xxd_product_package_order.owner_order_id
     *
     * @mbggenerated
     */
    public void setOwnerOrderId(Integer ownerOrderId) {
        this.ownerOrderId = ownerOrderId;
    }
}