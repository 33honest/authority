
package net.wangxj.authority.dto;

import java.io.Serializable;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:42
 */

public class AuthorityUserDTO implements Serializable{ 
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1112019535795789715L;
	// 主键 	
	private String userUuid;
    // 登录名 由字母，数字，下划线组合 	
	private String userLoginName;
    // 登陆密码 	
	private String userLoginPwd;
    // 用户邮箱 	
	private String userEmail;
    // 用户电话 	
	private String userPhone;
    // 用户状态: 已注册未激活: 1 已注册并激活:2 已锁定:3 	
	private java.lang.Integer userStatus;
    // 添加用户时间 yyyy-MM-dd HH:mm:ss 	
	private String userAddTime;
    // 添加用户人(用户主键) 	
	private String userAddBy;
    // 删除用户时间 yyyy-MM-dd HH:mm:ss 	
	private String userDelTime;
    // 删除用户人（用户主键） 	
	private String userDelBy;
    // 用户类型： 内部用户:1 外部用户：2 	
	private java.lang.Integer userType;
    // 添加用户类型: 被内部用户添加：1 自己注册:2 	
	private java.lang.Integer userAddType;
    // 是否已被删除: 0：未 1：已被删除 	
	private java.lang.Integer userIsDelete;
	
	public AuthorityUserDTO(){
		super();
	}
	
	public AuthorityUserDTO(String userUuid,String userLoginName,String userLoginPwd,String userEmail,String userPhone,java.lang.Integer userStatus,String userAddTime,String userAddBy,String userDelTime,String userDelBy,java.lang.Integer userType,java.lang.Integer userAddType,java.lang.Integer userIsDelete){
			this.userUuid = userUuid;
			this.userLoginName = userLoginName;
			this.userLoginPwd = userLoginPwd;
			this.userEmail = userEmail;
			this.userPhone = userPhone;
			this.userStatus = userStatus;
			this.userAddTime = userAddTime;
			this.userAddBy = userAddBy;
			this.userDelTime = userDelTime;
			this.userDelBy = userDelBy;
			this.userType = userType;
			this.userAddType = userAddType;
			this.userIsDelete = userIsDelete;
	}

	public void setUserUuid(String value) {
		this.userUuid = value;
	}
	
	public String getUserUuid() {
		return this.userUuid;
	}
	public void setUserLoginName(String value) {
		this.userLoginName = value;
	}
	
	public String getUserLoginName() {
		return this.userLoginName;
	}
	public void setUserLoginPwd(String value) {
		this.userLoginPwd = value;
	}
	
	public String getUserLoginPwd() {
		return this.userLoginPwd;
	}
	public void setUserEmail(String value) {
		this.userEmail = value;
	}
	
	public String getUserEmail() {
		return this.userEmail;
	}
	public void setUserPhone(String value) {
		this.userPhone = value;
	}
	
	public String getUserPhone() {
		return this.userPhone;
	}
	public void setUserStatus(java.lang.Integer value) {
		this.userStatus = value;
	}
	
	public java.lang.Integer getUserStatus() {
		return this.userStatus;
	}
	public void setUserAddTime(String value) {
		this.userAddTime = value;
	}
	
	public String getUserAddTime() {
		return this.userAddTime;
	}
	public void setUserAddBy(String value) {
		this.userAddBy = value;
	}
	
	public String getUserAddBy() {
		return this.userAddBy;
	}
	public void setUserDelTime(String value) {
		this.userDelTime = value;
	}
	
	public String getUserDelTime() {
		return this.userDelTime;
	}
	public void setUserDelBy(String value) {
		this.userDelBy = value;
	}
	
	public String getUserDelBy() {
		return this.userDelBy;
	}
	public void setUserType(java.lang.Integer value) {
		this.userType = value;
	}
	
	public java.lang.Integer getUserType() {
		return this.userType;
	}
	public void setUserAddType(java.lang.Integer value) {
		this.userAddType = value;
	}
	
	public java.lang.Integer getUserAddType() {
		return this.userAddType;
	}
	public void setUserIsDelete(java.lang.Integer value) {
		this.userIsDelete = value;
	}
	
	public java.lang.Integer getUserIsDelete() {
		return this.userIsDelete;
	}
	
	public String toString(){
		return "AuthorityUser [userUuid="+userUuid+", userLoginName="+userLoginName+", userLoginPwd="+userLoginPwd+", userEmail="+userEmail+", userPhone="+userPhone+", userStatus="+userStatus+", userAddTime="+userAddTime+", userAddBy="+userAddBy+", userDelTime="+userDelTime+", userDelBy="+userDelBy+", userType="+userType+", userAddType="+userAddType+", userIsDelete="+userIsDelete+"]";
	}
	
}

