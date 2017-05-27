package net.wangxj.authority.web.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */
@JSONType
public class AuthorityRoleDTO extends DTO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6729576837311889919L;
	// 主键 
	@JSONField(name = "role_uuid")
	private String roleUuid;
    // 角色名称 	
	@JSONField(name = "role_name")
	private String roleName;
    // 角色状态: 已添加：1 已激活: 	2
	@JSONField(name = "role_status")
	private java.lang.Integer roleStatus;
    // 添加时间 yyyy-MM-dd HH:mm:ss 	
	@JSONField(name = "role_add_time")
	private String roleAddTime;
    // 添加人uuid 	
	@JSONField(name = "role_add_by")
	private String roleAddBy;
    // 删除人uuid 	
	@JSONField(name = "role_delete_by")
	private String roleDelBy;
    // 平台uuid 	
	@JSONField(name = "role_platform_uuid")
	private String rolePlatformUuid;
	//编辑人
	@JSONField(name = "role_edit_by")
	private String roleEditBy;
	//编辑时间
	@JSONField(name = "role_edit_time")
	private String roleEditTime;
	
	
	
	public AuthorityRoleDTO() {
		super();
	}



	public AuthorityRoleDTO(String roleUuid, String roleName, Integer roleStatus, String roleAddTime, String roleAddBy,
			String roleDelBy, String rolePlatformUuid, String roleEditBy, String roleEditTime) {
		super();
		this.roleUuid = roleUuid;
		this.roleName = roleName;
		this.roleStatus = roleStatus;
		this.roleAddTime = roleAddTime;
		this.roleAddBy = roleAddBy;
		this.roleDelBy = roleDelBy;
		this.rolePlatformUuid = rolePlatformUuid;
		this.roleEditBy = roleEditBy;
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



	public String getRoleDelBy() {
		return roleDelBy;
	}



	public void setRoleDelBy(String roleDelBy) {
		this.roleDelBy = roleDelBy;
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



	public String getRoleEditTime() {
		return roleEditTime;
	}



	public void setRoleEditTime(String roleEditTime) {
		this.roleEditTime = roleEditTime;
	}



	@Override
	public String toString() {
		return "AuthorityRoleDTO [roleUuid=" + roleUuid + ", roleName=" + roleName + ", roleStatus=" + roleStatus
				+ ", roleAddTime=" + roleAddTime + ", roleAddBy=" + roleAddBy + ", roleDelBy=" + roleDelBy
				+ ", rolePlatformUuid=" + rolePlatformUuid + ", roleEditBy=" + roleEditBy + ", roleEditTime="
				+ roleEditTime + "]";
	}
	
	
}

