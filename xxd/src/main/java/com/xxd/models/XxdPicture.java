package com.xxd.models;

public class XxdPicture {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_picture.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_picture.save_dir
     *
     * @mbggenerated
     */
    private String saveDir;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column xxd_picture.picture_name
     *
     * @mbggenerated
     */
    private String pictureName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_picture.id
     *
     * @return the value of xxd_picture.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_picture.id
     *
     * @param id the value for xxd_picture.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_picture.save_dir
     *
     * @return the value of xxd_picture.save_dir
     *
     * @mbggenerated
     */
    public String getSaveDir() {
        return saveDir;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_picture.save_dir
     *
     * @param saveDir the value for xxd_picture.save_dir
     *
     * @mbggenerated
     */
    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir == null ? null : saveDir.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column xxd_picture.picture_name
     *
     * @return the value of xxd_picture.picture_name
     *
     * @mbggenerated
     */
    public String getPictureName() {
        return pictureName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column xxd_picture.picture_name
     *
     * @param pictureName the value for xxd_picture.picture_name
     *
     * @mbggenerated
     */
    public void setPictureName(String pictureName) {
        this.pictureName = pictureName == null ? null : pictureName.trim();
    }
}