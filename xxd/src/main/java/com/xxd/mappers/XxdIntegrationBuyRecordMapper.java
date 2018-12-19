package com.xxd.mappers;

import com.xxd.models.XxdIntegrationBuyRecord;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface XxdIntegrationBuyRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration_buy_record
     *
     * @mbggenerated
     */
    @Delete({
        "delete from xxd_integration_buy_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration_buy_record
     *
     * @mbggenerated
     */
    @Insert({
        "insert into xxd_integration_buy_record (id, uid, ",
        "integration, time)",
        "values (#{id,jdbcType=INTEGER}, #{uid,jdbcType=INTEGER}, ",
        "#{integration,jdbcType=DECIMAL}, #{time,jdbcType=VARCHAR})"
    })
    int insert(XxdIntegrationBuyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration_buy_record
     *
     * @mbggenerated
     */
    @InsertProvider(type=XxdIntegrationBuyRecordSqlProvider.class, method="insertSelective")
    int insertSelective(XxdIntegrationBuyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration_buy_record
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id, uid, integration, time",
        "from xxd_integration_buy_record",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.DECIMAL),
        @Result(column="time", property="time", jdbcType=JdbcType.VARCHAR)
    })
    XxdIntegrationBuyRecord selectByPrimaryKey(Integer id);
    
    @Select({
        "select",
        "id, uid, integration, time",
        "from xxd_integration_buy_record"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.DECIMAL),
        @Result(column="time", property="time", jdbcType=JdbcType.VARCHAR)
    })
    ArrayList<XxdIntegrationBuyRecord> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration_buy_record
     *
     * @mbggenerated
     */
    @UpdateProvider(type=XxdIntegrationBuyRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(XxdIntegrationBuyRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration_buy_record
     *
     * @mbggenerated
     */
    @Update({
        "update xxd_integration_buy_record",
        "set uid = #{uid,jdbcType=INTEGER},",
          "integration = #{integration,jdbcType=DECIMAL},",
          "time = #{time,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(XxdIntegrationBuyRecord record);
}