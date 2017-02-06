
package net.wangxj.authority.dto;

import java.io.Serializable;

import net.wangxj.authority.constant.DataDictionaryConstant;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */

public class AuthorityRoleDTO implements DTO,Serializable{ 
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -691774655724238161L;
	// 主键 	
	private String roleUuid;
    // 角色名称 	
	private String roleName;
    // 角色状态: 已添加： 已激活: 	
	private java.lang.Integer roleStatus;
	//角色状态名
	private String roleStatusName;
    // 添加时间 yyyy-MM-dd HH:mm:ss 	
	private String roleAddTime;
    // 添加人uuid 	
	private String roleAddBy;
	//添加人名
	//添加人名
	private String roleAddByName;
    // 删除时间 yyyy-MM-dd HH:mm:ss 	
	private String roleDelTime;
    // 删除人uuid 	
	private String roleDelBy;
	//删除人名
	private String roleDelByName;
    // 是否已被删除: 0:未 1：已被删除 	
	private java.lang.Integer roleIsDelete;
    // 平台uuid 	
	private String rolePlatformUuid;
	//编辑人
	private String roleEditBy;
	//编辑人名
	private String roleEditByName;
	//编辑时间
	private String roleEditTime;
	
	public AuthorityRoleDTO(){
		super();
	}

	public AuthorityRoleDTO(String roleUuid, String roleName, Integer roleStatus, String roleAddTime, String roleAddBy,
			String roleAddByName, String roleDelTime, String roleDelBy, String roleDelByName, Integer roleIsDelete,
			String rolePlatformUuid, String roleEditBy, String roleEditByName, String roleEditTime) {
		super();
		this.roleUuid = roleUuid;
		this.roleName = roleName;
		this.roleStatus = roleStatus;
		this.roleAddTime = roleAddTime;
		this.roleAddBy = roleAddBy;
		this.roleAddByName = roleAddByName;
		this.roleDelTime = roleDelTime;
		this.roleDelBy = roleDelBy;
		this.roleDelByName = roleDelByName;
		this.roleIsDelete = roleIsDelete;
		this.rolePlatformUuid = rolePlatformUuid;
		this.roleEditBy = roleEditBy;
		this.roleEditByName = roleEditByName;
		this.roleEditTime = roleEditTime;
	}

	public String getRoleUuid() {
		return roleUuid;
	}

	public void setRoleUuid(String roleUuid) {
		this.roleUuid = roleUuid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public java.lang.Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(java.lang.Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getRoleAddTime() {
		return roleAddTime;
	}

	public void setRoleAddTime(String roleAddTime) {
		this.roleAddTime = roleAddTime;
	}

	public String getRoleAddBy() {
		return roleAddBy;
	}

	public void setRoleAddBy(String roleAddBy) {
		this.roleAddBy = roleAddBy;
	}

	public String getRoleAddByName() {
		return roleAddByName;
	}

	public void setRoleAddByName(String roleAddByName) {
		this.roleAddByName = roleAddByName;
	}

	public String getRoleDelTime() {
		return roleDelTime;
	}

	public void setRoleDelTime(String roleDelTime) {
		this.roleDelTime = roleDelTime;
	}

	public String getRoleDelBy() {
		return roleDelBy;
	}

	public void setRoleDelBy(String roleDelBy) {
		this.roleDelBy = roleDelBy;
	}

	public String getRoleDelByName() {
		return roleDelByName;
	}

	public void setRoleDelByName(String roleDelByName) {
		this.roleDelByName = roleDelByName;
	}

	public java.lang.Integer getRoleIsDelete() {
		return roleIsDelete;
	}

	public void setRoleIsDelete(java.lang.Integer roleIsDelete) {
		this.roleIsDelete = roleIsDelete;
	}

	public String getRolePlatformUuid() {
		return rolePlatformUuid;
	}

	public void setRolePlatformUuid(String rolePlatformUuid) {
		this.rolePlatformUuid = rolePlatformUuid;
	}

	public String getRoleEditBy() {
		return roleEditBy;
	}

	public void setRoleEditBy(String roleEditBy) {
		this.roleEditBy = roleEditBy;
	}

	public String getRoleEditByName() {
		return roleEditByName;
	}

	public void setRoleEditByName(String roleEditByName) {
		this.roleEditByName = roleEditByName;
	}

	public String getRoleEditTime() {
		return roleEditTime;
	}

	public void setRoleEditTime(String roleEditTime) {
		this.roleEditTime = roleEditTime;
	}
	

	public String getRoleStatusName() {
		return DataDictionaryConstant.getRoleStatusKey(this.roleStatus);
	}

	public void setRoleStatusName(String roleStatusName) {
		this.roleStatusName = roleStatusName;
	}

	@Override
	public String toString() {
		return "AuthorityRoleDTO [roleUuid=" + roleUuid + ", roleName=" + roleName + ", roleStatus=" + roleStatus
				+ ", roleStatusName=" + roleStatusName + ", roleAddTime=" + roleAddTime + ", roleAddBy=" + roleAddBy
				+ ", roleAddByName=" + roleAddByName + ", roleDelTime=" + roleDelTime + ", roleDelBy=" + roleDelBy
				+ ", roleDelByName=" + roleDelByName + ", roleIsDelete=" + roleIsDelete + ", rolePlatformUuid="
				+ rolePlatformUuid + ", roleEditBy=" + roleEditBy + ", roleEditByName=" + roleEditByName
				+ ", roleEditTime=" + roleEditTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleUuid == null) ? 0 : roleUuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorityRoleDTO other = (AuthorityRoleDTO) obj;
		if (roleUuid == null) {
			if (other.roleUuid != null)
				return false;
		} else if (!roleUuid.equals(other.roleUuid))
			return false;
		return true;
	}

	
}

