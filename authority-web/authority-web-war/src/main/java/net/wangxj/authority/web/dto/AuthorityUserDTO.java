package net.wangxj.authority.web.dto;

import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:42
 */
@JSONType
public class AuthorityUserDTO extends DTO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7298436600689696669L;
	// 主键 	
	@JSONField(name = "user_uuid")
	private String userUuid;
    // 登录名 由字母，数字，下划线组合 
	@JSONField(name = "user_login_name")
	private String userLoginName;
    // 登陆密码 	
	@JSONField(name = "user_login_password")
	private String userLoginPwd;
    // 用户邮箱 	
	@JSONField(name = "user_email")
	private String userEmail;
    // 用户电话 
	@JSONField(name = "user_phone")
	private String userPhone;
    // 用户状态: 已注册未激活: 1 已注册并激活:2 已锁定:3 	
	@JSONField(name = "user_status")
	private java.lang.Integer userStatus;
	@JSONField(name = "user_status_name")
	private String userStatusName;
    // 添加用户时间 yyyy-MM-dd HH:mm:ss
	@JSONField(name = "user_add_time")
	private String userAddTime;
    // 添加用户人(用户主键)
	@JSONField(name = "user_add_by")
	private String userAddBy;
	@JSONField(name = "user_add_by_name")
	private String userAddByName;
    // 删除用户人（用户主键） 	
	@JSONField(name = "user_delete_by" ,serialize = false)
	private String userDelBy;
    // 用户类型： 内部用户:1 外部用户：2 	
	@JSONField(name = "user_type")
	private java.lang.Integer userType;
	@JSONField(name = "user_type_name")
	private String userTypeName;
    // 添加用户类型: 被内部用户添加：1 自己注册:2 	
	@JSONField(name = "user_add_type")
	private java.lang.Integer userAddType;
	@JSONField(name = "user_add_type_name")
	private String userAddTypeName;
	//编辑人
	@JSONField(name = "user_edit_by")
	private String userEditBy;
	@JSONField(name = "user_edit_by_name")
	private String userEditByName;
	//编辑时间
	@JSONField(name = "user_edit_time")
	private String userEditTime;
	
	public AuthorityUserDTO() {
		super();
	}

	public AuthorityUserDTO(String userUuid, String userLoginName, String userLoginPwd, String userEmail,
			String userPhone, Integer userStatus, String userStatusName, String userAddTime, String userAddBy,
			String userAddByName, String userDelBy, Integer userType, String userTypeName, Integer userAddType,
			String userAddTypeName, String userEditBy, String userEditByName, String userEditTime) {
		super();
		this.userUuid = userUuid;
		this.userLoginName = userLoginName;
		this.userLoginPwd = userLoginPwd;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userStatus = userStatus;
		this.userStatusName = userStatusName;
		this.userAddTime = userAddTime;
		this.userAddBy = userAddBy;
		this.userAddByName = userAddByName;
		this.userDelBy = userDelBy;
		this.userType = userType;
		this.userTypeName = userTypeName;
		this.userAddType = userAddType;
		this.userAddTypeName = userAddTypeName;
		this.userEditBy = userEditBy;
		this.userEditByName = userEditByName;
		this.userEditTime = userEditTime;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getUserLoginPwd() {
		return userLoginPwd;
	}

	public void setUserLoginPwd(String userLoginPwd) {
		this.userLoginPwd = userLoginPwd;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public java.lang.Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(java.lang.Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserStatusName() {
		return userStatusName;
	}

	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}

	public String getUserAddTime() {
		return userAddTime;
	}

	public void setUserAddTime(String userAddTime) {
		this.userAddTime = userAddTime;
	}

	public String getUserAddBy() {
		return userAddBy;
	}

	public void setUserAddBy(String userAddBy) {
		this.userAddBy = userAddBy;
	}

	public String getUserAddByName() {
		return userAddByName;
	}

	public void setUserAddByName(String userAddByName) {
		this.userAddByName = userAddByName;
	}

	public String getUserDelBy() {
		return userDelBy;
	}

	public void setUserDelBy(String userDelBy) {
		this.userDelBy = userDelBy;
	}

	public java.lang.Integer getUserType() {
		return userType;
	}

	public void setUserType(java.lang.Integer userType) {
		this.userType = userType;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public java.lang.Integer getUserAddType() {
		return userAddType;
	}

	public void setUserAddType(java.lang.Integer userAddType) {
		this.userAddType = userAddType;
	}

	public String getUserAddTypeName() {
		return userAddTypeName;
	}

	public void setUserAddTypeName(String userAddTypeName) {
		this.userAddTypeName = userAddTypeName;
	}

	public String getUserEditBy() {
		return userEditBy;
	}

	public void setUserEditBy(String userEditBy) {
		this.userEditBy = userEditBy;
	}

	public String getUserEditByName() {
		return userEditByName;
	}

	public void setUserEditByName(String userEditByName) {
		this.userEditByName = userEditByName;
	}

	public String getUserEditTime() {
		return userEditTime;
	}

	public void setUserEditTime(String userEditTime) {
		this.userEditTime = userEditTime;
	}

	@Override
	public String toString() {
		return "AuthorityUserDTO [userUuid=" + userUuid + ", userLoginName=" + userLoginName + ", userLoginPwd="
				+ userLoginPwd + ", userEmail=" + userEmail + ", userPhone=" + userPhone + ", userStatus=" + userStatus
				+ ", userStatusName=" + userStatusName + ", userAddTime=" + userAddTime + ", userAddBy=" + userAddBy
				+ ", userAddByName=" + userAddByName + ", userDelBy=" + userDelBy + ", userType=" + userType
				+ ", userTypeName=" + userTypeName + ", userAddType=" + userAddType + ", userAddTypeName="
				+ userAddTypeName + ", userEditBy=" + userEditBy + ", userEditByName=" + userEditByName
				+ ", userEditTime=" + userEditTime + "]";
	}

}

