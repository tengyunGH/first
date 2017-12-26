package com.tengyun.modules.sys.office.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 机构entity
 * @author x67658
 * @version 2017-12-08
 */
public class Office{

	/**
	 * 机构实体独有的字段
	 */
	private Office parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String parentNames;		// 所有父级名称
	private String name;		// 名称
	private String code;		// 组织编码
	private Long sort;		// 排序
	private String type;		// 组织类型
	private String grade;		// 组织等级
	private Long virtualFlag;		// 是否虚拟组织
	private String address;		// 联系地址
	private String zipCode;		// 邮政编码
	private String master;		// 负责人
	private String phone;		// 电话
	private String fax;		// 传真
	private String email;		// 邮箱
	
	/**
	 * 公共部分
	 */
	protected String id;//实体编号
	protected String platformId;//平台id
	protected String createBy;//创建者Id
	protected Date createDate;//创建日期
	protected String updateBy;// 更新者Id
	protected Date updateDate;//更新日期
	private Integer version = 1;//乐观锁版本
	protected String state;// 状态标记
	protected String remarks;//备注
	
	public Office() {
		super();
	}

	@JsonBackReference
	public Office getParent() {
		return parent;
	}

	public void setParent(Office parent) {
		this.parent = parent;
	}
	
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public String getParentNames() {
		return parentNames;
	}

	public void setParentNames(String parentNames) {
		this.parentNames = parentNames;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Long getVirtualFlag() {
		return virtualFlag;
	}

	public void setVirtualFlag(Long virtualFlag) {
		this.virtualFlag = virtualFlag;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
