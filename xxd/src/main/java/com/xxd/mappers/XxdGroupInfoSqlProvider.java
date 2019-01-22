package com.xxd.mappers;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xxd.models.XxdGroupInfo;

public class XxdGroupInfoSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_group_info
     *
     * @mbggenerated
     */
    public String insertSelective(XxdGroupInfo record) {
        BEGIN();
        INSERT_INTO("xxd_group_info");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getUid() != null) {
            VALUES("uid", "#{uid,jdbcType=INTEGER}");
        }
        
        if (record.getNo() != null) {
            VALUES("no", "#{no,jdbcType=VARCHAR}");
        }
        
        if (record.getBuyOrderId() != null) {
            VALUES("buy_order_id", "#{buyOrderId,jdbcType=INTEGER}");
        }
        
        if (record.getPhoto() != null) {
            VALUES("photo", "#{photo,jdbcType=VARCHAR}");
        }
        
        if (record.getIntoTime() != null) {
            VALUES("into_time", "#{intoTime,jdbcType=VARCHAR}");
        }
        
        if (record.getIsHead() != null) {
            VALUES("is_head", "#{isHead,jdbcType=VARCHAR}");
        }
        
        if (record.getSta() != null) {
            VALUES("sta", "#{sta,jdbcType=SMALLINT}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_group_info
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(XxdGroupInfo record) {
        BEGIN();
        UPDATE("xxd_group_info");
        
        if (record.getUid() != null) {
            SET("uid = #{uid,jdbcType=INTEGER}");
        }
        
        if (record.getNo() != null) {
            SET("no = #{no,jdbcType=VARCHAR}");
        }
        
        if (record.getBuyOrderId() != null) {
            SET("buy_order_id = #{buyOrderId,jdbcType=INTEGER}");
        }
        
        if (record.getPhoto() != null) {
            SET("photo = #{photo,jdbcType=VARCHAR}");
        }
        
        if (record.getIntoTime() != null) {
            SET("into_time = #{intoTime,jdbcType=VARCHAR}");
        }
        
        if (record.getIsHead() != null) {
            SET("is_head = #{isHead,jdbcType=VARCHAR}");
        }
        
        if (record.getSta() != null) {
            SET("sta = #{sta,jdbcType=SMALLINT}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}