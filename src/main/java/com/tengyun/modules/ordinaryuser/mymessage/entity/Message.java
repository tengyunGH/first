package com.tengyun.modules.ordinaryuser.mymessage.entity;

import java.util.Date;
/**
 * 发送消息的模块from A to B
 * @author x67658
 * @version 2017-12-27
 */
public class Message {

	private Long id;						//每条消息的唯一标识
	private Long fromId;				//消息的发送者的id 
	private Long toId;					//消息的接受者的id
	private String message;			//消息主体
	private String visibility;			//消息来源可见性  0表示匿名发送 1表示不匿名
	private Date createDate;			//发送时间
	private Date deleteDate;			//删除时间
	private String state;					//消息状态 0表示发送还没阅读 1表示已阅读 2表示消息已由发送者删除
	private String remarks;			//备注
	
	public Message() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFromId() {
		return fromId;
	}
	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}
	public Long getToId() {
		return toId;
	}
	public void setToId(Long toId) {
		this.toId = toId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
