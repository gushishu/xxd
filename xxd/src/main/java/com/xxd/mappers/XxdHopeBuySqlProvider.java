package com.xxd.mappers;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.xxd.models.XxdHopeBuy;

public class XxdHopeBuySqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_hope_buy
     *
     * @mbggenerated
     */
    public String insertSelective(XxdHopeBuy record) {
        BEGIN();
        INSERT_INTO("xxd_hope_buy");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getContents() != null) {
            VALUES("contents", "#{contents,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsimg() != null) {
            VALUES("goodsImg", "#{goodsimg,jdbcType=VARCHAR}");
        }
        
        if (record.getUid() != null) {
            VALUES("uid", "#{uid,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_hope_buy
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(XxdHopeBuy record) {
        BEGIN();
        UPDATE("xxd_hope_buy");
        
        if (record.getContents() != null) {
            SET("contents = #{contents,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsimg() != null) {
            SET("goodsImg = #{goodsimg,jdbcType=VARCHAR}");
        }
        
        if (record.getUid() != null) {
            SET("uid = #{uid,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}