package com.tengyun.modules.sys.user.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.tengyun.modules.sys.office.entity.Office;
import com.tengyun.modules.sys.role.entity.Role;
/**
 * 用户entity
 * @author x67658
 * @version 2017-12-08
 */                   
public class User{
	
		/**
		 * User实体特有的字段
		 */
		private String loginName;// 登录名
		private String password;// 密码
		private String name; // 姓
		private String email; // 邮箱
		private String phone; // 电话
		private String userType;// 用户类型
		private String loginIp; // 最后登陆IP
		private Date loginDate; // 最后登陆日期
		private String photo; // 头像
		
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
		
		private String token;

		public User() {
			super();
		}

		public User(String id) {
			this.id = id;
		}

		public User(String id, String loginName) {
			this.id = id;
			this.loginName = loginName;
		}

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public String getId() {
			return id;
		}
		
		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		@JsonIgnore
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}


		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getRemarks() {
			return remarks;
		}

		public String getUserType() {
			return userType;
		}

		public void setUserType(String userType) {
			this.userType = userType;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public String getLoginIp() {
			return loginIp;
		}

		public void setLoginIp(String loginIp) {
			this.loginIp = loginIp;
		}

		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		public Date getLoginDate() {
			return loginDate;
		}

		public void setLoginDate(Date loginDate) {
			this.loginDate = loginDate;
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCreateBy() {
			return createBy;
		}

		public void setCreateBy(String createBy) {
			this.createBy = createBy;
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

		public void setId(String id) {
			this.id = id;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}

		/**
		 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
		 */
//		public String getRoleNames() {
//			return Collections3.extractToString(roleList, "name", ",");
//		}

		public boolean isAdmin() {
			return isAdmin(this.id);
		}
		public static boolean isAdmin(Serializable id) {
			return id != null && Long.valueOf(1).equals(id);
		}

		public static boolean isAdmin(Long id) {
			return id != null && Long.valueOf(1).equals(id);
		}

		@Override
		public String toString() {
			return id.toString();
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

}
