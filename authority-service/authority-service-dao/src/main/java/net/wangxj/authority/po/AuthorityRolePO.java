
package net.wangxj.authority.po;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import net.wangxj.util.constant.RegexConstant;
import net.wangxj.util.validate.Severity.Error;
import net.wangxj.util.validate.Severity.Info;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */
public class AuthorityRolePO implements PO{
	
    // 主键 	
	private String roleUuid;
    // 角色名称 	
	@Pattern(regexp = RegexConstant.TWO_TO_SIXTEEN_CHINESECHAR, message = "角色名称必须为2-16个汉字组合字符串", groups = {AddValidate.class,EditValidate.class}, payload = {Info.class})
	@NotBlank(message = "角色名称不可为空", groups = {AddValidate.class}, payload = {Info.class})
	private String roleName;
    // 角色状态: 已添加： 已激活: 	
	@Max(value = 2, message = "角色状态不合法", groups = {AddValidate.class, EditValidate.class}, payload = {Info.class})
	@Min(value = 1, message = "角色状态不合法", groups = {AddValidate.class, EditValidate.class}, payload = {Info.class})
	@NotNull(message = "角色状态不可为空", groups = {AddValidate.class}, payload = {Info.class})
	private java.lang.Integer roleStatus;
    // 添加时间 yyyy-MM-dd HH:mm:ss 	
	private String roleAddTime;
    // 添加人uuid 	
	@Pattern(regexp = RegexConstant.UUID_32, message = "添加人不合法", groups = {AddValidate.class}, payload = {Error.class})
	@NotBlank(message = "添加人不可为空", groups = {AddValidate.class}, payload = {Error.class})
	private String roleAddBy;
    // 删除时间 yyyy-MM-dd HH:mm:ss 	
	private String roleDelTime;
    // 删除人uuid 	
	@Pattern(regexp = RegexConstant.UUID_32, message = "删除人不合法" , groups = {DeleteValidate.class}, payload = {Error.class})
	@NotBlank(message = "删除人不可为空", groups = {DeleteValidate.class}, payload = {Error.class})
	private String roleDelBy;
    // 是否已被删除: 2:未 1：已被删除 	
	private java.lang.Integer roleIsDelete;
    // 平台uuid 	
	@Pattern(regexp = RegexConstant.UUID_32, message = "平台不合法" , groups = {AddValidate.class, EditValidate.class}, payload = {Info.class})
	@NotBlank(message = "平台不合法", groups = {AddValidate.class}, payload = {Info.class})
	private String rolePlatformUuid;
	//编辑人
	@Pattern(regexp = RegexConstant.UUID_32, message = "编辑人不合法", groups = {EditValidate.class}, payload = {Error.class})
	@NotBlank(message = "编辑人不可为空", groups = {EditValidate.class}, payload = {Error.class})
	private String roleEditBy;
	//编辑时间
	private String roleEditTime;
	
	
	public AuthorityRolePO(){
		super();
	}


	public AuthorityRolePO(String roleUuid, String roleName, Integer roleStatus, String roleAddTime, String roleAddBy,
			String roleDelTime, String roleDelBy, Integer roleIsDelete, String rolePlatformUuid, String roleEditBy,
			String roleEditTime) {
		super();
		this.roleUuid = roleUuid;
		this.roleName = roleName;
		this.roleStatus = roleStatus;
		this.roleAddTime = roleAddTime;
		this.roleAddBy = roleAddBy;
		this.roleDelTime = roleDelTime;
		this.roleDelBy = roleDelBy;
		this.roleIsDelete = roleIsDelete;
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


	public String getRoleEditTime() {
		return roleEditTime;
	}


	public void setRoleEditTime(String roleEditTime) {
		this.roleEditTime = roleEditTime;
	}
	
	
}

