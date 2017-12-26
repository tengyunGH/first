package com.tengyun.modules.ordinaryuser.myfriends.entity;

import java.util.Date;

/**
 * 朋友关系表 A添加B为好友
 * @author x67658
 * @version 2017-12-20
 */
public class Friends {
	
	private int userId;							//主动添加好友的那一方的用户id
	private int friendId;							//被申请添加好友的那一方的用户id
	private String memoName;				//申请添加好友时设置的备注名称
	private String applyMessage;			//申请信息
	private Date applyDate;					//申请时间
	private String friendState;				//申请的状态标志位 1表示已成为朋友 2表示申请了还未同意 3表示申请后拒绝
	private Date agreeDate;					//申请同意时间
	private Date refuseDate;					//申请拒绝时间
	private String state;							//0表示是朋友关系 1表示已删除好友
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getMemoName() {
		return memoName;
	}
	public void setMemoName(String memoName) {
		this.memoName = memoName;
	}
	public String getApplyMessage() {
		return applyMessage;
	}
	public void setApplyMessage(String applyMessage) {
		this.applyMessage = applyMessage;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getFriendState() {
		return friendState;
	}
	public void setApplyState(String friendState) {
		this.friendState = friendState;
	}
	public Date getAgreeDate() {
		return agreeDate;
	}
	public void setAgreeDate(Date agreeDate) {
		this.agreeDate = agreeDate;
	}
	public Date getRefuseDate() {
		return refuseDate;
	}
	public void setRefuseDate(Date refuseDate) {
		this.refuseDate = refuseDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
