package com.tengyun.modules.ordinaryuser.mythought.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 评论实体 也就是每个人发表的看法 目前还不能加上图片 图片的存储还不太明白
 * @author x67658
 * @version2017-12-15
 */
public class Thought {

	private int id;						//一段体会的唯一标识  主键
	private int userId;				//发表体会的用户标识  外键
	private String headLine;		//标题
	private String thought;		//体会
	private String visibility;		//可见性 0表示仅自己可见 1表示仅朋友可见 2表示所有人可见
	
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private Date createDate;		//创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private String updateDate;	//上次修改时间
	
	private Date delDate;			//删除时间
	private String state;				//状态位
	
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getThought() {
		return thought;
	}
	public void setThought(String thought) {
		this.thought = thought;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getDelDate() {
		return delDate;
	}
	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHeadLine() {
		return headLine;
	}
	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
}
