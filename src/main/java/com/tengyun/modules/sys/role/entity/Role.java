package com.tengyun.modules.sys.role.entity;

import java.util.Date;

/**
 * 角色entity
 * @author x67658
 * @version 2014-12-04
 */
public class Role{

	/**
	 * Role实体特有的字段
	 */
	private String name; 	// 角色名称
	private String enname;	// 英文名称
	private String useable; 		//是否是可用
	

	/**
	 * 公共部分
	 */
	protected String id;//实体编号
	protected String createBy;//创建者Id
	protected Date createDate;//创建日期
	protected String updateBy;// 更新者Id
	protected Date updateDate;//更新日期
	protected String state;// 状态标记
	protected String remarks;//备注
	
	public Role() {
		super();
	}
	
	public Role(String id){
		this.id = id;
	}
	
	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
