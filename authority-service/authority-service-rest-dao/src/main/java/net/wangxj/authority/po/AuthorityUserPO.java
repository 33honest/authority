package net.wangxj.authority.po;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.ws.rs.QueryParam;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
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
 * created time	: 2016-12-26 18:06:42
 */
@JSONType
public class AuthorityUserPO extends PO implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7298436600689696669L;
	// 主键 	
	@Null(message = "无法识别user_uuid,请严格按照API文档调用" , groups = {AddValidate.class})
	@Pattern(regexp = RegexConstant.UUID_32 , message = "用户uuid非法" , groups = {EditValidate.class , DeleteValidate.class})
	@NotBlank(message = "用户uuid为必填值" , groups = {EditValidate.class})
	@QueryParam("user_uuid")
	@JSONField(name = "user_uuid")
	private String userUuid;
    // 登录名 由字母，数字，下划线组合 
	@Pattern(regexp=RegexConstant.LETTER_NUMBER_UNDERLINE,message="登录名必须由字母数字下划线组合，下划线不可开头与结尾",groups={AddValidate.class,EditValidate.class})
	@Length(min=2,max=64,message="长度必须在2-64个字符长度",groups={AddValidate.class,EditValidate.class})
	@NotRepeat(message = "该user_login_name已被注册")
	@QueryParam("user_login_name")
	@JSONField(name = "user_login_name")
	private String userLoginName;
    // 登陆密码 	
	@Pattern(regexp=RegexConstant.LETTER_NUMBER_UNDERLINE_FREE,message="必须由字母数字下划线自由组合",groups={AddValidate.class,EditValidate.class})
	@Length(min=6,max=20,message="长度必须在6-20个字符长度",groups={AddValidate.class,EditValidate.class})
	@NotBlank(message="密码不可为空",groups={AddValidate.class})
	@JSONField(name = "user_login_password")
	private String userLoginPwd;
    // 用户邮箱 	
	@Email(regexp=RegexConstant.EMAIL,message="不符合邮箱格式", groups={AddValidate.class,EditValidate.class})
	@NotBlank(message="邮箱不可为空", groups={AddValidate.class})
	@NotRepeat(message = "该user_email已被注册")
	@QueryParam("user_email")
	@JSONField(name = "user_email")
	private String userEmail;
    // 用户电话 
	@Pattern(regexp=RegexConstant.PHONE, message="不符合电话或手机格式", groups={AddValidate.class, EditValidate.class})
	@NotRepeat(message = "该user_phone已被使用")
	@JSONField(name = "user_phone")
	@QueryParam("user_phone")
	private String userPhone;
    // 用户状态: 已注册未激活: 1 已注册并激活:2 已锁定:3 	
	@Min(value=1,message="用户状态不合法", groups={AddValidate.class, EditValidate.class})
	@Max(value=3,message="用户状态不合法", groups={AddValidate.class, EditValidate.class})
	@NotNull(message="用户状态不可为空",groups={AddValidate.class})
	@JSONField(name = "user_status")
	private java.lang.Integer userStatus;
    // 添加用户时间 yyyy-MM-dd HH:mm:ss
	@Null(message = "无法识别user_add_time,请严格按照API文档调用" , groups = {AddValidate.class,EditValidate.class})
	@JSONField(name = "user_add_time")
	private String userAddTime;
    // 添加用户人(用户主键)
	@Pattern(regexp=RegexConstant.UUID_32, message="增加人不合法", groups={AddValidate.class})
	@NotBlank(message="增加人不可为空", groups=AddValidate.class)
	@Null(message = "无法识别user_add_by,请严格按照API文档调用" , groups = {EditValidate.class})
	@JSONField(name = "user_add_by")
	private String userAddBy;
    // 删除用户时间 yyyy-MM-dd HH:mm:ss 	
	@Null(message = "无法识别user_delete_time,请严格按照API文档调用" , groups = {AddValidate.class , EditValidate.class})
	@JSONField(serialize =false, deserialize = false)
	private String userDelTime;
    // 删除用户人（用户主键） 	
	@Pattern(regexp=RegexConstant.UUID_32, message= "删除人不合法", groups=DeleteValidate.class)
	@NotBlank(message="删除人不可为空", groups=DeleteValidate.class)
	@Null(message = "无法识别user_delete_by,请严格按照API文档调用" , groups = {AddValidate.class, EditValidate.class})
	@JSONField(name = "user_delete_by" ,serialize = false)
	private String userDelBy;
    // 用户类型： 内部用户:1 外部用户：2 	
	@Min(value=1,message="用户类型不合法",groups={AddValidate.class,EditValidate.class})
	@Max(value=2,message="用户类型不合法",groups={AddValidate.class,EditValidate.class})
	@NotNull(message="用户类型不可为空", groups={AddValidate.class})
	@JSONField(name = "user_type")
	private java.lang.Integer userType;
    // 添加用户类型: 被内部用户添加：1 自己注册:2 	
	@Min(value=1,message="添加用户类型非法", groups={AddValidate.class,EditValidate.class})
	@Max(value=2,message="添加用户类型非法", groups={AddValidate.class,EditValidate.class})
	@NotNull(message="添加用户类型不可为空", groups=AddValidate.class)
	@JSONField(name = "user_add_type")
	private java.lang.Integer userAddType;
    // 是否已被删除: 2：未 1：已被删除 	
	@Null(message = "无法是被user_is_delete,请严格按照API文档调用" , groups = {AddValidate.class , EditValidate.class})
	@JSONField(name = "user_is_delete" , deserialize = false , serialize = false)
	private java.lang.Integer userIsDelete;
	//编辑人
	@Null(message = "无法识别user_edit_by , 请严格按照API文档调用" , groups = {AddValidate.class})
	@Pattern(regexp=RegexConstant.UUID_32, message="编辑人不合法", groups=EditValidate.class)
	@NotBlank(message="编辑人不可为空", groups=EditValidate.class)
	@JSONField(name = "user_edit_by")
	private String userEditBy;
	//编辑时间
	@Null(message = "无法识别user_edit_time, 请严格按照API" , groups = {AddValidate.class, EditValidate.class})
	@JSONField(name = "user_edit_time")
	private String userEditTime;
	
	public AuthorityUserPO(){
		super();
	}

	public AuthorityUserPO(String userUuid, String userLoginName, String userLoginPwd, String userEmail,
			String userPhone, Integer userStatus, String userAddTime, String userAddBy, String userDelTime,
			String userDelBy, Integer userType, Integer userAddType, Integer userIsDelete, String userEditBy,
			String userEditTime) {
		super();
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
		this.userEditBy = userEditBy;
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

	public String getUserDelTime() {
		return userDelTime;
	}

	public void setUserDelTime(String userDelTime) {
		this.userDelTime = userDelTime;
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

	public java.lang.Integer getUserAddType() {
		return userAddType;
	}

	public void setUserAddType(java.lang.Integer userAddType) {
		this.userAddType = userAddType;
	}

	public java.lang.Integer getUserIsDelete() {
		return userIsDelete;
	}

	public void setUserIsDelete(java.lang.Integer userIsDelete) {
		this.userIsDelete = userIsDelete;
	}

	public String getUserEditBy() {
		return userEditBy;
	}

	public void setUserEditBy(String userEditBy) {
		this.userEditBy = userEditBy;
	}

	public String getUserEditTime() {
		return userEditTime;
	}

	public void setUserEditTime(String userEditTime) {
		this.userEditTime = userEditTime;
	}

	@Override
	public String toString() {
		return "AuthorityUserPO [userUuid=" + userUuid + ", userLoginName=" + userLoginName + ", userLoginPwd="
				+ userLoginPwd + ", userEmail=" + userEmail + ", userPhone=" + userPhone + ", userStatus=" + userStatus
				+ ", userAddTime=" + userAddTime + ", userAddBy=" + userAddBy + ", userDelTime=" + userDelTime
				+ ", userDelBy=" + userDelBy + ", userType=" + userType + ", userAddType=" + userAddType
				+ ", userIsDelete=" + userIsDelete + ", userEditBy=" + userEditBy + ", userEditTime=" + userEditTime
				+ "]";
	}
	
}

