package com.wfb.user.model;

public class AdminModel {

	private Integer adminId;

	private Integer role;

	/**
	 * 角色名称
	 */
	private String roleName;

	private String adminType;

	/**
	 * 登录名
	 */
	private String admin;

	/**
	 * 操作节点
	 */
	private String roles;

	private String password;

	/**
	 * 用户姓名
	 */
	private String adminName;

	/**
	 * 是否删除1未删除 2删除
	 */
	private String isDel;

	/**
	 * 添加时间
	 */
	private String addTime;

	/**
	 * 创建人
	 */
	private Integer creator;

	/**
	 * 编辑时间
	 */
	private String modTime;

	/**
	 * 更新人
	 */
	private Integer updater;

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAdminType() {
		return adminType;
	}

	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getModTime() {
		return modTime;
	}

	public void setModTime(String modTime) {
		this.modTime = modTime;
	}

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
