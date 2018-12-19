package com.xxd.mappers;

import com.xxd.models.XxdGoodsGreight;

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

public interface XxdGoodsGreightMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_goods_greight
     *
     * @mbggenerated
     */
    @Delete({
        "delete from xxd_goods_greight",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_goods_greight
     *
     * @mbggenerated
     */
    @Insert({
        "insert into xxd_goods_greight (id, goods_id, ",
        "area, area_type, ",
        "valuation_type, basic_valuation_price, ",
        "basic_valuation_area, over_basic_valuation_price)",
        "values (#{id,jdbcType=INTEGER}, #{goodsId,jdbcType=INTEGER}, ",
        "#{area,jdbcType=VARCHAR}, #{areaType,jdbcType=SMALLINT}, ",
        "#{valuationType,jdbcType=SMALLINT}, #{basicValuationPrice,jdbcType=DECIMAL}, ",
        "#{basicValuationArea,jdbcType=SMALLINT}, #{overBasicValuationPrice,jdbcType=DECIMAL})"
    })
    int insert(XxdGoodsGreight record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_goods_greight
     *
     * @mbggenerated
     */
    @InsertProvider(type=XxdGoodsGreightSqlProvider.class, method="insertSelective")
    int insertSelective(XxdGoodsGreight record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_goods_greight
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id, goods_id, area, area_type, valuation_type, basic_valuation_price, basic_valuation_area, ",
        "over_basic_valuation_price",
        "from xxd_goods_greight",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="goods_id", property="goodsId", jdbcType=JdbcType.INTEGER),
        @Result(column="area", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="area_type", property="areaType", jdbcType=JdbcType.SMALLINT),
        @Result(column="valuation_type", property="valuationType", jdbcType=JdbcType.SMALLINT),
        @Result(column="basic_valuation_price", property="basicValuationPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="basic_valuation_area", property="basicValuationArea", jdbcType=JdbcType.SMALLINT),
        @Result(column="over_basic_valuation_price", property="overBasicValuationPrice", jdbcType=JdbcType.DECIMAL)
    })
    XxdGoodsGreight selectByPrimaryKey(Integer id);
    
    @Select({
        "select",
        "id, goods_id, area, area_type, valuation_type, basic_valuation_price, basic_valuation_area, ",
        "over_basic_valuation_price",
        "from xxd_goods_greight"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="goods_id", property="goodsId", jdbcType=JdbcType.INTEGER),
        @Result(column="area", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="area_type", property="areaType", jdbcType=JdbcType.SMALLINT),
        @Result(column="valuation_type", property="valuationType", jdbcType=JdbcType.SMALLINT),
        @Result(column="basic_valuation_price", property="basicValuationPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="basic_valuation_area", property="basicValuationArea", jdbcType=JdbcType.SMALLINT),
        @Result(column="over_basic_valuation_price", property="overBasicValuationPrice", jdbcType=JdbcType.DECIMAL)
    })
    ArrayList<XxdGoodsGreight> selectAll();
    
    @Select({
        "select",
        "id, goods_id, area, area_type, valuation_type, basic_valuation_price, basic_valuation_area, ",
        "over_basic_valuation_price",
        "from xxd_goods_greight",
        "where goods_id = #{gid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="goods_id", property="goodsId", jdbcType=JdbcType.INTEGER),
        @Result(column="area", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="area_type", property="areaType", jdbcType=JdbcType.SMALLINT),
        @Result(column="valuation_type", property="valuationType", jdbcType=JdbcType.SMALLINT),
        @Result(column="basic_valuation_price", property="basicValuationPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="basic_valuation_area", property="basicValuationArea", jdbcType=JdbcType.SMALLINT),
        @Result(column="over_basic_valuation_price", property="overBasicValuationPrice", jdbcType=JdbcType.DECIMAL)
    })
    ArrayList<XxdGoodsGreight> selectAllByGid(Integer gid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_goods_greight
     *
     * @mbggenerated
     */
    @UpdateProvider(type=XxdGoodsGreightSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(XxdGoodsGreight record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_goods_greight
     *
     * @mbggenerated
     */
    @Update({
        "update xxd_goods_greight",
        "set goods_id = #{goodsId,jdbcType=INTEGER},",
          "area = #{area,jdbcType=VARCHAR},",
          "area_type = #{areaType,jdbcType=SMALLINT},",
          "valuation_type = #{valuationType,jdbcType=SMALLINT},",
          "basic_valuation_price = #{basicValuationPrice,jdbcType=DECIMAL},",
          "basic_valuation_area = #{basicValuationArea,jdbcType=SMALLINT},",
          "over_basic_valuation_price = #{overBasicValuationPrice,jdbcType=DECIMAL}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(XxdGoodsGreight record);
}