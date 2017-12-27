package com.tengyun.modules.ordinaryuser.commentary.entity;

import java.util.Date;

/**
 * 评论表  在某个Thought下面评论 
 * @author x67658
 * @version 2017-12-27
 */
public class Commentary {

	private Long id;							//每条评论的id
	private String commentary;		//评论内容
	private String type;						//评论类型 1表示给thought评论 2表示给commentary评论
	private Long thoughtId;				//评论的thought的id
	private Long commentatorId;		//评论的commentary的id
	private Date createDate;				//评论时间
	private Date delDate;					//删除时间
	private String state;						//状态位 默认为零
	private String visibility;				//评论的可见性 0表示仅评论者和被评论者可见 1表示所有人均可见
	
	public Commentary() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCommentary() {
		return commentary;
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getThoughtId() {
		return thoughtId;
	}
	public void setThoughtId(Long thoughtId) {
		this.thoughtId = thoughtId;
	}
	public Long getCommentatorId() {
		return commentatorId;
	}
	public void setCommentatorId(Long commentatorId) {
		this.commentatorId = commentatorId;
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

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
}
