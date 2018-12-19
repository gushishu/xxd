package com.xxd.mappers;

import com.xxd.models.XxdIntegration;
import com.xxd.models.XxdModel;

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

public interface XxdIntegrationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration
     *
     * @mbggenerated
     */
    @Delete({
        "delete from xxd_integration",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration
     *
     * @mbggenerated
     */
    @Insert({
        "insert into xxd_integration (id, uid, ",
        "integration, abled, ",
        "disabled)",
        "values (#{id,jdbcType=INTEGER}, #{uid,jdbcType=INTEGER}, ",
        "#{integration,jdbcType=DECIMAL}, #{abled,jdbcType=DECIMAL}, ",
        "#{disabled,jdbcType=DECIMAL})"
    })
    int insert(XxdIntegration record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration
     *
     * @mbggenerated
     */
    @InsertProvider(type=XxdIntegrationSqlProvider.class, method="insertSelective")
    int insertSelective(XxdIntegration record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id, uid, integration, abled, disabled",
        "from xxd_integration",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.DECIMAL),
        @Result(column="abled", property="abled", jdbcType=JdbcType.DECIMAL),
        @Result(column="disabled", property="disabled", jdbcType=JdbcType.DECIMAL)
    })
    XxdIntegration selectByPrimaryKey(Integer id);
    
    @Select({
        "select",
        "xi.id id, xi.uid uid, xi.integration integration, xi.abled abled, xi.disabled disabled",
        "from xxd_integration xi,xxd_user xu",
        "where xi.id = #{id,jdbcType=INTEGER}",
        "and xi.uid = xu.uid",
        "and xu.parent_id = #{parent_id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.DECIMAL),
        @Result(column="abled", property="abled", jdbcType=JdbcType.DECIMAL),
        @Result(column="disabled", property="disabled", jdbcType=JdbcType.DECIMAL)
    })
    XxdIntegration selectByPrimaryKeyParentId(XxdModel record);
    
    @Select({
        "select",
        "xi.id id, xi.uid uid, xi.integration integration, xi.abled abled, xi.disabled disabled",
        "from xxd_integration xi,(select uid from xxd_user where parent_id in (select uid from xxd_user where parent_id = #{parent_id,jdbcType=INTEGER})) xu",
        "where xi.id = #{id,jdbcType=INTEGER}",
        "and xi.uid = xu.uid"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.DECIMAL),
        @Result(column="abled", property="abled", jdbcType=JdbcType.DECIMAL),
        @Result(column="disabled", property="disabled", jdbcType=JdbcType.DECIMAL)
    })
    XxdIntegration selectByPrimaryKeyParentsId(XxdModel record);
    
    @Select({
        "select",
        "xi.id id, xi.uid uid, xi.integration integration, xi.abled abled, xi.disabled disabled",
        "from xxd_integration xi,xxd_user xu",
        "where xu.username = #{username,jdbcType=VARCHAR}",
        "and xi.uid = xu.uid"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.DECIMAL),
        @Result(column="abled", property="abled", jdbcType=JdbcType.DECIMAL),
        @Result(column="disabled", property="disabled", jdbcType=JdbcType.DECIMAL)
    })
    XxdIntegration selectIntegrationByUsername(String username);
    
    @Select({
        "select",
        "id, uid, integration, abled, disabled",
        "from xxd_integration",
        "where uid = #{uid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.DECIMAL),
        @Result(column="abled", property="abled", jdbcType=JdbcType.DECIMAL),
        @Result(column="disabled", property="disabled", jdbcType=JdbcType.DECIMAL)
    })
    XxdIntegration selectByUid(Integer uid);
    
    @Select({
        "select",
        "id, uid, integration, abled, disabled",
        "from xxd_integration"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.DECIMAL),
        @Result(column="abled", property="abled", jdbcType=JdbcType.DECIMAL),
        @Result(column="disabled", property="disabled", jdbcType=JdbcType.DECIMAL)
    })
    ArrayList<XxdIntegration> selectAll();
    
    @Select({
    	"select",
        "xi.id id, xi.uid uid, xi.integration integration, xi.abled abled, xi.disabled disabled",
        "from xxd_integration xi,xxd_user xu",
        "where xi.uid = xu.uid",
        "and (xu.parent_id = #{parent_id,jdbcType=INTEGER}",
        "or (select parent_id from xxd_user where uid = (select parent_id from xxd_user where uid = xu.uid)) = #{parent_id,jdbcType=INTEGER})"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.DECIMAL),
        @Result(column="abled", property="abled", jdbcType=JdbcType.DECIMAL),
        @Result(column="disabled", property="disabled", jdbcType=JdbcType.DECIMAL)
    })
    ArrayList<XxdIntegration> selectAllUserParentId(Integer parent_id);
    
    @Select({
    	"select",
        "xi.id id, xi.uid uid, xi.integration integration, xi.abled abled, xi.disabled disabled",
        "from xxd_integration xi,xxd_user xu",
        "where xi.uid = xu.uid",
        "and xi.id = #{id,jdbcType=INTEGER}",
        "and (xu.parent_id = #{parent_id,jdbcType=INTEGER}",
        "or (select parent_id from xxd_user where uid = (select parent_id from xxd_user where uid = xu.uid)) = #{parent_id,jdbcType=INTEGER})"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.DECIMAL),
        @Result(column="abled", property="abled", jdbcType=JdbcType.DECIMAL),
        @Result(column="disabled", property="disabled", jdbcType=JdbcType.DECIMAL)
    })
    XxdIntegration selectUserInteParentId(XxdModel record);
    
    @Select({
    	"select",
        "xi.id id, xi.uid uid, xi.integration integration, xi.abled abled, xi.disabled disabled",
        "from xxd_integration xi,xxd_user xu",
        "where xi.uid = xu.uid",
        "and xi.id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER),
        @Result(column="integration", property="integration", jdbcType=JdbcType.DECIMAL),
        @Result(column="abled", property="abled", jdbcType=JdbcType.DECIMAL),
        @Result(column="disabled", property="disabled", jdbcType=JdbcType.DECIMAL)
    })
    XxdIntegration selectUserInteParentIds(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration
     *
     * @mbggenerated
     */
    @UpdateProvider(type=XxdIntegrationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(XxdIntegration record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_integration
     *
     * @mbggenerated
     */
    @Update({
        "update xxd_integration",
        "set uid = #{uid,jdbcType=INTEGER},",
          "integration = #{integration,jdbcType=DECIMAL},",
          "abled = #{abled,jdbcType=DECIMAL},",
          "disabled = #{disabled,jdbcType=DECIMAL}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(XxdIntegration record);
}