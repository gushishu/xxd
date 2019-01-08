package com.xxd.models;

public class XxdFeedBack {

	private Integer id;
	private String time;
	private String name;
	private String content;
	private String save_dir;
	private Integer is_read;
	private Integer uid;
	private Integer type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String conent) {
		this.content = conent;
	}
	public String getSave_dir() {
		return save_dir;
	}
	public void setSave_dir(String save_dir) {
		this.save_dir = save_dir;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getIs_read() {
		return is_read;
	}
	public void setIs_read(Integer is_read) {
		this.is_read = is_read;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "XxdFeedBack [id=" + id + ", time=" + time + ", name=" + name + ", content=" + content + ", save_dir="
				+ save_dir + ", is_read=" + is_read + ", uid=" + uid + ", type=" + type + "]";
	}
	
}
