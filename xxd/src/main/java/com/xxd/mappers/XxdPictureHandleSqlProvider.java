package com.xxd.mappers;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xxd.models.XxdPictureHandle;

public class XxdPictureHandleSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_picture_handle
     *
     * @mbggenerated
     */
    public String insertSelective(XxdPictureHandle record) {
        BEGIN();
        INSERT_INTO("xxd_picture_handle");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getUrl() != null) {
            VALUES("url", "#{url,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodName() != null) {
            VALUES("good_name", "#{goodName,jdbcType=VARCHAR}");
        }
        
        if (record.getHandleTime() != null) {
            VALUES("handle_time", "#{handleTime,jdbcType=VARCHAR}");
        }
        
        if (record.getSaveDir() != null) {
            VALUES("save_dir", "#{saveDir,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_picture_handle
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(XxdPictureHandle record) {
        BEGIN();
        UPDATE("xxd_picture_handle");
        
        if (record.getUrl() != null) {
            SET("url = #{url,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodName() != null) {
            SET("good_name = #{goodName,jdbcType=VARCHAR}");
        }
        
        if (record.getHandleTime() != null) {
            SET("handle_time = #{handleTime,jdbcType=VARCHAR}");
        }
        
        if (record.getSaveDir() != null) {
            SET("save_dir = #{saveDir,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}