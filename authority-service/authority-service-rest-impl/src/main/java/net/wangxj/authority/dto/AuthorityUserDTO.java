//
//package net.wangxj.authority.dto;
//
//import java.io.Serializable;
//
//import net.wangxj.authority.constant.DataDictionaryConstant;
//
///**
// * created by	: wangxj
// * created time	: 2016-12-26 18:06:42
// */
//
//public class AuthorityUserDTO implements DTO,Serializable{ 
//	
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1112019535795789715L;
//	// 主键 	
//	private String userUuid;
//    // 登录名 由字母，数字，下划线组合 	
//	private String userLoginName;
//    // 登陆密码 	
//	private String userLoginPwd;
//    // 用户邮箱 	
//	private String userEmail;
//    // 用户电话 	
//	private String userPhone;
//    // 用户状态: 已注册未激活: 1 已注册并激活:2 已锁定:3 	
//	private java.lang.Integer userStatus;
//	//用户状态名
//	private String userStatusName;
//    // 添加用户时间 yyyy-MM-dd HH:mm:ss 	
//	private String userAddTime;
//    // 添加用户人(用户主键) 	
//	private String userAddBy;
//	//添加人登录名
//	private String userAddByName;
//    // 删除用户时间 yyyy-MM-dd HH:mm:ss 	
//	private String userDelTime;
//    // 删除用户人（用户主键） 	
//	private String userDelBy;
//	//删除人登陆名
//	private String userDelByName;
//    // 用户类型： 内部用户:1 外部用户：2 	
//	private java.lang.Integer userType;
//	//用户类型名
//	private String userTypeName;
//    // 添加用户类型: 被内部用户添加：1 自己注册:2 	
//	private java.lang.Integer userAddType;
//	//添加用户类型名
//	private String userAddTypeName;
//    // 是否已被删除: 0：未 1：已被删除 	
//	private java.lang.Integer userIsDelete;
//	//是否删除名
//	private String userIsDeleteName;
//	//编辑人
//	private String userEditBy;
//	//编辑人登陆名
//	private String userEditByName;
//	//编辑时间
//	private String userEditTime;
//	
//	
//	public AuthorityUserDTO(){
//		super();
//	}
//
//
//	public AuthorityUserDTO(String userUuid, String userLoginName, String userLoginPwd, String userEmail,
//			String userPhone, Integer userStatus, String userStatusName, String userAddTime, String userAddBy,
//			String userAddByName, String userDelTime, String userDelBy, String userDelByName, Integer userType,
//			String userTypeName, Integer userAddType, String userAddTypeName, Integer userIsDelete,
//			String userIsDeleteName, String userEditBy, String userEditByName, String userEditTime) {
//		super();
//		this.userUuid = userUuid;
//		this.userLoginName = userLoginName;
//		this.userLoginPwd = userLoginPwd;
//		this.userEmail = userEmail;
//		this.userPhone = userPhone;
//		this.userStatus = userStatus;
//		this.userStatusName = userStatusName;
//		this.userAddTime = userAddTime;
//		this.userAddBy = userAddBy;
//		this.userAddByName = userAddByName;
//		this.userDelTime = userDelTime;
//		this.userDelBy = userDelBy;
//		this.userDelByName = userDelByName;
//		this.userType = userType;
//		this.userTypeName = userTypeName;
//		this.userAddType = userAddType;
//		this.userAddTypeName = userAddTypeName;
//		this.userIsDelete = userIsDelete;
//		this.userIsDeleteName = userIsDeleteName;
//		this.userEditBy = userEditBy;
//		this.userEditByName = userEditByName;
//		this.userEditTime = userEditTime;
//	}
//
//
//	public String getUserUuid() {
//		return userUuid;
//	}
//
//
//	public void setUserUuid(String userUuid) {
//		this.userUuid = userUuid;
//	}
//
//
//	public String getUserLoginName() {
//		return userLoginName;
//	}
//
//
//	public void setUserLoginName(String userLoginName) {
//		this.userLoginName = userLoginName;
//	}
//
//
//	public String getUserLoginPwd() {
//		return userLoginPwd;
//	}
//
//
//	public void setUserLoginPwd(String userLoginPwd) {
//		this.userLoginPwd = userLoginPwd;
//	}
//
//
//	public String getUserEmail() {
//		return userEmail;
//	}
//
//
//	public void setUserEmail(String userEmail) {
//		this.userEmail = userEmail;
//	}
//
//
//	public String getUserPhone() {
//		return userPhone;
//	}
//
//
//	public void setUserPhone(String userPhone) {
//		this.userPhone = userPhone;
//	}
//
//
//	public java.lang.Integer getUserStatus() {
//		return userStatus;
//	}
//
//
//	public void setUserStatus(java.lang.Integer userStatus) {
//		this.userStatus = userStatus;
//	}
//
//
//	public String getUserStatusName() {
//		return DataDictionaryConstant.getUserStatuKey(this.userStatus);
//	}
//
//
//	public void setUserStatusName(String userStatusName) {
//		this.userStatusName = userStatusName;
//	}
//
//
//	public String getUserAddTime() {
//		return userAddTime;
//	}
//
//
//	public void setUserAddTime(String userAddTime) {
//		this.userAddTime = userAddTime;
//	}
//
//
//	public String getUserAddBy() {
//		return userAddBy;
//	}
//
//
//	public void setUserAddBy(String userAddBy) {
//		this.userAddBy = userAddBy;
//	}
//
//
//	public String getUserAddByName() {
//		return userAddByName;
//	}
//
//
//	public void setUserAddByName(String userAddByName) {
//		this.userAddByName = userAddByName;
//	}
//
//
//	public String getUserDelTime() {
//		return userDelTime;
//	}
//
//
//	public void setUserDelTime(String userDelTime) {
//		this.userDelTime = userDelTime;
//	}
//
//
//	public String getUserDelBy() {
//		return userDelBy;
//	}
//
//
//	public void setUserDelBy(String userDelBy) {
//		this.userDelBy = userDelBy;
//	}
//
//
//	public String getUserDelByName() {
//		return userDelByName;
//	}
//
//
//	public void setUserDelByName(String userDelByName) {
//		this.userDelByName = userDelByName;
//	}
//
//
//	public java.lang.Integer getUserType() {
//		return userType;
//	}
//
//
//	public void setUserType(java.lang.Integer userType) {
//		this.userType = userType;
//	}
//
//
//	public String getUserTypeName() {
//		return DataDictionaryConstant.getUserTypeKey(this.userType);
//	}
//
//
//	public void setUserTypeName(String userTypeName) {
//		this.userTypeName = userTypeName;
//	}
//
//
//	public java.lang.Integer getUserAddType() {
//		return userAddType;
//	}
//
//
//	public void setUserAddType(java.lang.Integer userAddType) {
//		this.userAddType = userAddType;
//	}
//
//
//	public String getUserAddTypeName() {
//		return DataDictionaryConstant.getUserADDTypeKey(this.userAddType);
//	}
//
//
//	public void setUserAddTypeName(String userAddTypeName) {
//		this.userAddTypeName = userAddTypeName;
//	}
//
//
//	public java.lang.Integer getUserIsDelete() {
//		return userIsDelete;
//	}
//
//
//	public void setUserIsDelete(java.lang.Integer userIsDelete) {
//		this.userIsDelete = userIsDelete;
//	}
//
//
//	public String getUserIsDeleteName() {
//		return DataDictionaryConstant.getDeleteKey(this.userIsDelete);
//	}
//
//
//	public void setUserIsDeleteName(String userIsDeleteName) {
//		this.userIsDeleteName = userIsDeleteName;
//	}
//
//
//	public String getUserEditBy() {
//		return userEditBy;
//	}
//
//
//	public void setUserEditBy(String userEditBy) {
//		this.userEditBy = userEditBy;
//	}
//
//
//	public String getUserEditByName() {
//		return userEditByName;
//	}
//
//
//	public void setUserEditByName(String userEditByName) {
//		this.userEditByName = userEditByName;
//	}
//
//
//	public String getUserEditTime() {
//		return userEditTime;
//	}
//
//
//	public void setUserEditTime(String userEditTime) {
//		this.userEditTime = userEditTime;
//	}
//
//
//	@Override
//	public String toString() {
//		return "AuthorityUserDTO [userUuid=" + userUuid + ", userLoginName=" + userLoginName + ", userLoginPwd="
//				+ userLoginPwd + ", userEmail=" + userEmail + ", userPhone=" + userPhone + ", userStatus=" + userStatus
//				+ ", userStatusName=" + userStatusName + ", userAddTime=" + userAddTime + ", userAddBy=" + userAddBy
//				+ ", userAddByName=" + userAddByName + ", userDelTime=" + userDelTime + ", userDelBy=" + userDelBy
//				+ ", userDelByName=" + userDelByName + ", userType=" + userType + ", userTypeName=" + userTypeName
//				+ ", userAddType=" + userAddType + ", userAddTypeName=" + userAddTypeName + ", userIsDelete="
//				+ userIsDelete + ", userIsDeleteName=" + userIsDeleteName + ", userEditBy=" + userEditBy
//				+ ", userEditByName=" + userEditByName + ", userEditTime=" + userEditTime + "]";
//	}
//
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((userUuid == null) ? 0 : userUuid.hashCode());
//		return result;
//	}
//
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		AuthorityUserDTO other = (AuthorityUserDTO) obj;
//		if (userUuid == null) {
//			if (other.userUuid != null)
//				return false;
//		} else if (!userUuid.equals(other.userUuid))
//			return false;
//		return true;
//	}
//	
//	
//}
//
