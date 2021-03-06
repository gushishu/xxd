package com.xxd.models;

import java.math.BigDecimal;

/**
 * 用户冻结积分
 * @author Liang
 * @version 1.0
 */

public class XxdIntegrationFreeze {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_integration_freeze.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_integration_freeze.buy_uid
     *
     * @mbggenerated
     */
    private Integer buyUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_integration_freeze.freeze_type
     *
     * @mbggenerated
     */
    private Short freezeType;
    
    private String freeTypeCon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_integration_freeze.time
     *
     * @mbggenerated
     */
    private String time;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_integration_freeze.times
     *
     * @mbggenerated
     */
    private String times;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_integration_freeze.integration
     *
     * @mbggenerated
     */
    private BigDecimal integration;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_integration_freeze.uid
     *
     * @mbggenerated
     */
    private Integer uid;
    
    private Integer odid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_integration_freeze.id
     *
     * @return the value of xxd_integration_freeze.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_integration_freeze.id
     *
     * @param id the value for xxd_integration_freeze.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_integration_freeze.buy_uid
     *
     * @return the value of xxd_integration_freeze.buy_uid
     *
     * @mbggenerated
     */
    public Integer getBuyUid() {
        return buyUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_integration_freeze.buy_uid
     *
     * @param buyUid the value for xxd_integration_freeze.buy_uid
     *
     * @mbggenerated
     */
    public void setBuyUid(Integer buyUid) {
        this.buyUid = buyUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_integration_freeze.freeze_type
     *
     * @return the value of xxd_integration_freeze.freeze_type
     *
     * @mbggenerated
     */
    public Short getFreezeType() {
        return freezeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_integration_freeze.freeze_type
     *
     * @param freezeType the value for xxd_integration_freeze.freeze_type
     *
     * @mbggenerated
     */
    public void setFreezeType(Short freezeType) {
        this.freezeType = freezeType;
    }

    public String getFreeTypeCon() {
		return freeTypeCon;
	}

	public void setFreeTypeCon(String freeTypeCon) {
		this.freeTypeCon = freeTypeCon;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_integration_freeze.time
     *
     * @return the value of xxd_integration_freeze.time
     *
     * @mbggenerated
     */
    public String getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_integration_freeze.time
     *
     * @param time the value for xxd_integration_freeze.time
     *
     * @mbggenerated
     */
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_integration_freeze.times
     *
     * @return the value of xxd_integration_freeze.times
     *
     * @mbggenerated
     */
    public String getTimes() {
        return times;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_integration_freeze.times
     *
     * @param times the value for xxd_integration_freeze.times
     *
     * @mbggenerated
     */
    public void setTimes(String times) {
        this.times = times == null ? null : times.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_integration_freeze.integration
     *
     * @return the value of xxd_integration_freeze.integration
     *
     * @mbggenerated
     */
    public BigDecimal getIntegration() {
        return integration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_integration_freeze.integration
     *
     * @param integration the value for xxd_integration_freeze.integration
     *
     * @mbggenerated
     */
    public void setIntegration(BigDecimal integration) {
        this.integration = integration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_integration_freeze.uid
     *
     * @return the value of xxd_integration_freeze.uid
     *
     * @mbggenerated
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_integration_freeze.uid
     *
     * @param uid the value for xxd_integration_freeze.uid
     *
     * @mbggenerated
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

	public final Integer getOdid() {
		return odid;
	}

	public final void setOdid(Integer odid) {
		this.odid = odid;
	}
}