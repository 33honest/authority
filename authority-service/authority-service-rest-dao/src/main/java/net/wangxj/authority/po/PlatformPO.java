package net.wangxj.authority.po;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
 * created time	: 2016-12-26 18:06:44
 * platformName,platformSign,platformDomainName,platformAddTime,platformAddBy,platformStatus,platformIsDelete(these fields must be not null)
 * platformName,platformSign,platformDomainName(these fields must be unique)
 */
@JSONType
public class PlatformPO extends PO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8336420063461219089L;
	// 主键 	
	@Pattern(regexp=RegexConstant.UUID_32, message="平台uuid非法", groups={EditValidate.class,DeleteValidate.class})
	@NotBlank(message = "平台uuid为必填项" , groups = {EditValidate.class,DeleteValidate.class})
	@Null(message = "无法识别platform_uuid,请严格按照API文档调用" , groups = AddValidate.class)
	@JSONField(name = "platform_uuid")
	private String platformUuid;
    // 平台名
	@Pattern(regexp=RegexConstant.MORETHAN_TWO_CHINESECHAR, message="平台名必须是2-25个汉字", groups={AddValidate.class, EditValidate.class})
	@NotBlank(message="平台名是必填项",groups={AddValidate.class})
	@Null(message = "无法识别platform_name,请严格按照API文档调用" , groups = {DeleteValidate.class})
	@NotRepeat(message = "该平台名已存在")
	@JSONField(name= "platform_name")
	private String platformName;
    // 平台标识
	@Pattern(regexp=RegexConstant.LETTER_UNDERLINE,message="平台标识必须是大小写字母与下滑线组合,下划线不可开头或结尾", groups={AddValidate.class,EditValidate.class})
	@NotBlank(message="平台标识为必填项", groups={AddValidate.class})
	@Null(message = "无法识别platform_sign,请严格按照API文档调用" , groups = {DeleteValidate.class})
	@NotRepeat(message = "该平台标识已存在")
	@JSONField(name = "platform_sign")
	private String platformSign;
    // 平台域名
	@Pattern(regexp=RegexConstant.DOMAIN,message="平台域名不符合域名格式", groups={AddValidate.class,EditValidate.class})
	@Length(min=5,max=30,message="平台域名长度不合法,长度必须符合5-30个字符",groups={AddValidate.class,EditValidate.class})
	@NotBlank(message="平台域名为必填值", groups={AddValidate.class})
	@Null(message = "无法识别platform_domain,请严格按照API文档调用" , groups = {DeleteValidate.class})
	@NotRepeat(message = "该平台域名已存在")
	@JSONField(name = "platform_domain")
	private String platformDomainName;
    // 添加时间 yyyy-MM-dd HH:mm:ss
	@Null(message = "无法识别platform_add_time,请严格按照API文档调用" , groups = {AddValidate.class , EditValidate.class,DeleteValidate.class})
	@JSONField(name = "platform_add_time")
	private String platformAddTime;
    // 添加人uuid
	@Pattern(regexp=RegexConstant.UUID_32, message="增加人非法", groups=AddValidate.class)
	@NotBlank(message="增加人为必填值",groups=AddValidate.class)
	@Null(message = "无法识别platform_add_user, 请严格按照API文档调用" , groups = {EditValidate.class,DeleteValidate.class})
	@JSONField(name = "platform_add_user")
	private String platformAddBy;
    // 删除时间 yyyy-MM-dd HH:mm:ss 	
	private String platformDelTime;
    // 删除人uuid 	
	@Pattern(regexp=RegexConstant.UUID_32,message="删除人非法", groups={DeleteValidate.class})
	@NotBlank(message="删除人为必填值",groups={DeleteValidate.class})
	@Null(message = "无法识别platform_delete_user,请严格按照API文档调用" , groups = {AddValidate.class, EditValidate.class})
	@JSONField(name = "platform_delete_user")
	private String platformDelBy;
    // 平台状态： 初始化: 3 激活: 1 注销: 2
	@Min(value=1,message="输入的平台状态非法", groups={AddValidate.class, EditValidate.class})
	@Max(value=3,message="输入的平台状态非法", groups={AddValidate.class, EditValidate.class})
	@NotNull(message="平台状态为必填项", groups={AddValidate.class})
	@Null(message = "无法识别platform_status,请严格按照API文档调用" , groups = {DeleteValidate.class})
	@JSONField(name = "platform_status")
	private java.lang.Integer platformStatus;
    // 是否已被删除: 2：未 1：已被删除 	
	@JSONField(serialize = false)
	private java.lang.Integer platformIsDelete;
	//编辑人uuid
	@Pattern(regexp=RegexConstant.UUID_32,message="编辑人非法",groups=EditValidate.class)
	@NotBlank(message="编辑人是必填值",groups=EditValidate.class)
	@Null(message = "无法识别platform_edit_user,请严格按照API文档调用" , groups = {AddValidate.class,DeleteValidate.class})
	@JSONField(name = "platform_edit_user")
	private String platformEditBy;
	//编辑时间
	@Null(message = "无法识别platform_edit_time,请严格按照API调用" , groups = {AddValidate.class,EditValidate.class,DeleteValidate.class})
	@JSONField(name = "platform_edit_time")
	private String platformEditTime;
	
	public PlatformPO(){
		super();
	}
	
	public PlatformPO(String platformUuid, String platformName, String platformSign, String platformDomainName,
			String platformAddTime, String platformAddBy, String platformDelTime, String platformDelBy,
			Integer platformStatus, Integer platformIsDelete, String platformEditBy, String platformEditTime) {
		super();
		this.platformUuid = platformUuid;
		this.platformName = platformName;
		this.platformSign = platformSign;
		this.platformDomainName = platformDomainName;
		this.platformAddTime = platformAddTime;
		this.platformAddBy = platformAddBy;
		this.platformDelTime = platformDelTime;
		this.platformDelBy = platformDelBy;
		this.platformStatus = platformStatus;
		this.platformIsDelete = platformIsDelete;
		this.platformEditBy = platformEditBy;
		this.platformEditTime = platformEditTime;
	}

	public void setPlatformUuid(String value) {
		this.platformUuid = value;
	}
	
	public String getPlatformUuid() {
		return this.platformUuid;
	}
	public void setPlatformName(String value) {
		this.platformName = value;
	}
	
	public String getPlatformName() {
		return this.platformName;
	}
	public void setPlatformSign(String value) {
		this.platformSign = value;
	}
	
	public String getPlatformSign() {
		return this.platformSign;
	}
	public void setPlatformDomainName(String value) {
		this.platformDomainName = value;
	}
	
	public String getPlatformDomainName() {
		return this.platformDomainName;
	}
	public void setPlatformAddTime(String value) {
		this.platformAddTime = value;
	}
	
	public String getPlatformAddTime() {
		return this.platformAddTime;
	}
	public void setPlatformAddBy(String value) {
		this.platformAddBy = value;
	}
	
	public String getPlatformAddBy() {
		return this.platformAddBy;
	}
	public void setPlatformDelTime(String value) {
		this.platformDelTime = value;
	}
	
	public String getPlatformDelTime() {
		return this.platformDelTime;
	}
	public void setPlatformDelBy(String value) {
		this.platformDelBy = value;
	}
	
	public String getPlatformDelBy() {
		return this.platformDelBy;
	}
	public void setPlatformStatus(java.lang.Integer value) {
			this.platformStatus = value;
	}
	
	public java.lang.Integer getPlatformStatus() {
		return this.platformStatus;
	}
	public void setPlatformIsDelete(java.lang.Integer value) {
			this.platformIsDelete = value;
	}
	
	public java.lang.Integer getPlatformIsDelete() {
		return this.platformIsDelete;
	}
	public String getPlatformEditBy() {
		return platformEditBy;
	}

	public void setPlatformEditBy(String platformEditBy) {
		this.platformEditBy = platformEditBy;
	}

	public String getPlatformEditTime() {
		return platformEditTime;
	}

	public void setPlatformEditTime(String platformEditTime) {
		this.platformEditTime = platformEditTime;
	}

	@Override
	public String toString() {
		return "PlatformPO [platformUuid=" + platformUuid + ", platformName=" + platformName + ", platformSign="
				+ platformSign + ", platformDomainName=" + platformDomainName + ", platformAddTime=" + platformAddTime
				+ ", platformAddBy=" + platformAddBy + ", platformDelTime=" + platformDelTime + ", platformDelBy="
				+ platformDelBy + ", platformStatus=" + platformStatus + ", platformIsDelete=" + platformIsDelete
				+ ", platformEditBy=" + platformEditBy + ", platformEditTime=" + platformEditTime + "]";
	}
	
}

