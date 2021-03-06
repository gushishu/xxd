package com.xxd.mappers;

import com.xxd.models.XxdPicture;
import com.xxd.models.XxdPictureUnion;

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

public interface XxdPictureMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_picture
     *
     * @mbggenerated
     */
    @Delete({
        "delete from xxd_picture",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_picture
     *
     * @mbggenerated
     */
    @Insert({
        "insert into xxd_picture (id, save_dir, ",
        "picture_name)",
        "values (#{id,jdbcType=INTEGER}, #{saveDir,jdbcType=VARCHAR}, ",
        "#{pictureName,jdbcType=VARCHAR})"
    })
    int insert(XxdPicture record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_picture
     *
     * @mbggenerated
     */
    @InsertProvider(type=XxdPictureSqlProvider.class, method="insertSelective")
    int insertSelective(XxdPicture record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_picture
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "id, save_dir, picture_name",
        "from xxd_picture",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="save_dir", property="saveDir", jdbcType=JdbcType.VARCHAR),
        @Result(column="picture_name", property="pictureName", jdbcType=JdbcType.VARCHAR)
    })
    XxdPicture selectByPrimaryKey(Integer id);
    
    @Select({
    	"SELECT xp.picture_name name,xpd.url url",
    	"FROM xxd_picture xp", 
    	"INNER JOIN xxd_picture_handle xpd",
    	"WHERE xp.save_dir = xpd.save_dir AND xpd.id = #{id,jdbcType = INTEGER}"
    })
   
    ArrayList<XxdPictureUnion> selectDownload(Integer id);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_picture
     *
     * @mbggenerated
     */
    @UpdateProvider(type=XxdPictureSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(XxdPicture record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table xxd_picture
     *
     * @mbggenerated
     */
    @Update({
        "update xxd_picture",
        "set save_dir = #{saveDir,jdbcType=VARCHAR},",
          "picture_name = #{pictureName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(XxdPicture record);
}