package com.xxd.mappers;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xxd.models.XxdPower;

public class XxdPowerSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_power
     *
     * @mbggenerated
     */
    public String insertSelective(XxdPower record) {
        BEGIN();
        INSERT_INTO("xxd_power");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getOutline() != null) {
            VALUES("outline", "#{outline,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            VALUES("type", "#{type,jdbcType=SMALLINT}");
        }
        
        if (record.getIsAbled() != null) {
            VALUES("is_abled", "#{isAbled,jdbcType=SMALLINT}");
        }
        
        if (record.getDetails() != null) {
            VALUES("details", "#{details,jdbcType=VARCHAR}");
        }
        
        if(record.getParent_id() != null) {
        	VALUES("parent_id", "#{parent_id,jdbcType=INTEGER}");
        }
        
        if(record.getUri() != null) {
        	VALUES("uri", "#{uri, jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_power
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(XxdPower record) {
        BEGIN();
        UPDATE("xxd_power");
        
        if (record.getOutline() != null) {
            SET("outline = #{outline,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            SET("type = #{type,jdbcType=SMALLINT}");
        }
        
        if (record.getIsAbled() != null) {
            SET("is_abled = #{isAbled,jdbcType=SMALLINT}");
        }
        
        if (record.getDetails() != null) {
            SET("details = #{details,jdbcType=VARCHAR}");
        }
        
        if(record.getParent_id() != null) {
        	SET("parent_id = #{parent_id,jdbcType=INTEGER}");
        }
        
        if(record.getUri() != null) {
        	SET("uri = #{uri,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}