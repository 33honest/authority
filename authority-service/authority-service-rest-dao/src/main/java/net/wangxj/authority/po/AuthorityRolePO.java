package net.wangxj.authority.po;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.apache.commons.collections4.sequence.EditCommand;
import org.hibernate.validator.constraints.NotBlank;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

import net.wangxj.util.annotation.NotRepeat;
import net.wangxj.util.constant.RegexConstant;
import net.wangxj.util.validate.groups.AddValidate;
import net.wangxj.util.validate.groups.DeleteValidate;
import net.wangxj.util.validate.groups.EditValidate;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:40
 */
@JSONType
public class AuthorityRolePO extends PO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6729576837311889919L;
	// 主键 
	@Pattern(regexp = RegexConstant.UUID_32, message = "role_uuid非法" , groups = {EditValidate.class, DeleteValidate.class})
	@Null(message = "无法识别role_uuid,请样个按照API文档调用" , groups = {AddValidate.class})
	@NotBlank(message = "role_uuid为必填值" , groups = {EditValidate.class, DeleteValidate.class})
	@JSONField(name = "role_uuid")
	private String roleUuid;
    // 角色名称 	
	@Pattern(regexp = RegexConstant.TWO_TO_SIXTEEN_CHINESECHAR, message = "角色名称必须为2-16个汉字组合字符串", groups = {AddValidate.class,EditValidate.class})
	@NotBlank(message = "角色名称不可为空", groups = {AddValidate.class})
	@NotRepeat(message = "该role_name已存在")
	@JSONField(name = "role_name")
	private String roleName;
    // 角色状态: 已添加： 已激活: 	
	@Max(value = 2, message = "角色状态不合法", groups = {AddValidate.class, EditValidate.class})
	@Min(value = 1, message = "角色状态不合法", groups = {AddValidate.class, EditValidate.class})
	@NotNull(message = "角色状态不可为空", groups = {AddValidate.class})
	@JSONField(name = "role_status")
	private java.lang.Integer roleStatus;
    // 添加时间 yyyy-MM-dd HH:mm:ss 	
	@Null(message = "无法识别role_add_time,请严格按照API文档调用" , groups = {AddValidate.class,EditValidate.class})
	@JSONField(name = "role_add_time")
	private String roleAddTime;
    // 添加人uuid 	
	@Pattern(regexp = RegexConstant.UUID_32, message = "添加人不合法", groups = {AddValidate.class})
	@NotBlank(message = "添加人不可为空", groups = {AddValidate.class})
	@Null(message = "无法识别role_add_by,请严格按照API文档调用" , groups = {EditValidate.class})
	@JSONField(name = "role_add_by")
	private String roleAddBy;
    // 删除时间 yyyy-MM-dd HH:mm:ss 	
	@JSONField(serialize = false , deserialize = false)
	private String roleDelTime;
    // 删除人uuid 	
	@Pattern(regexp = RegexConstant.UUID_32, message = "删除人不合法" , groups = {DeleteValidate.class})
	@NotBlank(message = "删除人不可为空", groups = {DeleteValidate.class})
	@Null(message = "无法识别role_delete_by,请严格按照API文档调用" , groups = {AddValidate.class,EditValidate.class})
	@JSONField(name = "role_delete_by" , serialize = false)
	private String roleDelBy;
    // 是否已被删除: 2:未 1：已被删除 	
	@JSONField(deserialize =false , serialize = false)
	private java.lang.Integer roleIsDelete;
    // 平台uuid 	
	@Pattern(regexp = RegexConstant.UUID_32, message = "平台不合法" , groups = {AddValidate.class, EditValidate.class})
	@NotBlank(message = "平台不合法", groups = {AddValidate.class})
	@JSONField(name = "role_platform_uuid")
	private String rolePlatformUuid;
	//编辑人
	@Pattern(regexp = RegexConstant.UUID_32, message = "编辑人不合法", groups = {EditValidate.class})
	@NotBlank(message = "编辑人不可为空", groups = {EditValidate.class})
	@Null(message = "无法识别role_edit_by,请严格按照API文档调用" , groups = {AddValidate.class})
	@JSONField(name = "role_edit_by")
	private String roleEditBy;
	//编辑时间
	@Null(message = "无法识别role_edit_time,请严格按照API文档调用" , groups = {AddValidate.class,EditValidate.class})
	@JSONField(name = "role_edit_time")
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

