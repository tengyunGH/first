package com.tengyun.modules.sys.menu.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 菜单entity
 * @author x67658
 * @version 2017-12-04
 */
public class Menu{

	private Menu parent;	// 父级菜单
	private String parentIds; // 所有父级编号
	private List<Menu> children;	// 父级菜单
	private String name; 	// 名称
	private String href; 	// 链接
	private Integer sort; 	// 排序
	private String target; 	// 目标（ mainFrame、_blank、_self、_parent、_top）
	private String icon; 	// 图标
	
	private String isShow; 	// 是否在菜单中显示（1：显示；0：不显示）
	private String permission; // 权限标识	

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
	

	
	
	private String userId;
	
	public Menu(){
		super();
		this.sort = 30;
		this.isShow = "1";
	}
	
	public Menu(String id){
		this.id = id;
	}
	
	@JsonBackReference
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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

	@JsonIgnore
	public static String getRootId(){
		return "0";
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return name;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public List<Menu> getChildren() {
		return children;
	}
}
