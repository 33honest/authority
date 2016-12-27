
package net.wangxj.authority.dto;

import java.io.Serializable;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */

public class AuthorityRoleDTO implements Serializable{ 
	
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
    // 添加时间 yyyy-MM-dd HH:mm:ss 	
	private String roleAddTime;
    // 添加人uuid 	
	private String roleAddBy;
    // 删除时间 yyyy-MM-dd HH:mm:ss 	
	private String roleDelTime;
    // 删除人uuid 	
	private String roleDelBy;
    // 是否已被删除: 0:未 1：已被删除 	
	private java.lang.Integer roleIsDelete;
    // 平台uuid 	
	private String rolePlatformUuid;
	
	public AuthorityRoleDTO(){
		super();
	}
	
	public AuthorityRoleDTO(String roleUuid,String roleName,java.lang.Integer roleStatus,String roleAddTime,String roleAddBy,String roleDelTime,String roleDelBy,java.lang.Integer roleIsDelete,String rolePlatformUuid){
			this.roleUuid = roleUuid;
			this.roleName = roleName;
			this.roleStatus = roleStatus;
			this.roleAddTime = roleAddTime;
			this.roleAddBy = roleAddBy;
			this.roleDelTime = roleDelTime;
			this.roleDelBy = roleDelBy;
			this.roleIsDelete = roleIsDelete;
			this.rolePlatformUuid = rolePlatformUuid;
	}

	public void setRoleUuid(String value) {
		this.roleUuid = value;
	}
	
	public String getRoleUuid() {
		return this.roleUuid;
	}
	public void setRoleName(String value) {
		this.roleName = value;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	public void setRoleStatus(java.lang.Integer value) {
		this.roleStatus = value;
	}
	
	public java.lang.Integer getRoleStatus() {
		return this.roleStatus;
	}
	public void setRoleAddTime(String value) {
		this.roleAddTime = value;
	}
	
	public String getRoleAddTime() {
		return this.roleAddTime;
	}
	public void setRoleAddBy(String value) {
		this.roleAddBy = value;
	}
	
	public String getRoleAddBy() {
		return this.roleAddBy;
	}
	public void setRoleDelTime(String value) {
		this.roleDelTime = value;
	}
	
	public String getRoleDelTime() {
		return this.roleDelTime;
	}
	public void setRoleDelBy(String value) {
		this.roleDelBy = value;
	}
	
	public String getRoleDelBy() {
		return this.roleDelBy;
	}
	public void setRoleIsDelete(java.lang.Integer value) {
		this.roleIsDelete = value;
	}
	
	public java.lang.Integer getRoleIsDelete() {
		return this.roleIsDelete;
	}
	public void setRolePlatformUuid(String value) {
		this.rolePlatformUuid = value;
	}
	
	public String getRolePlatformUuid() {
		return this.rolePlatformUuid;
	}
	
	public String toString(){
		return "AuthorityRole [roleUuid="+roleUuid+", roleName="+roleName+", roleStatus="+roleStatus+", roleAddTime="+roleAddTime+", roleAddBy="+roleAddBy+", roleDelTime="+roleDelTime+", roleDelBy="+roleDelBy+", roleIsDelete="+roleIsDelete+", rolePlatformUuid="+rolePlatformUuid+"]";
	}
	
}

