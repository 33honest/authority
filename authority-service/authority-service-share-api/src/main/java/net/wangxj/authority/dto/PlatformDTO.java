
package net.wangxj.authority.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import net.wangxj.authority.constant.DataDictionaryConstant;
import net.wangxj.authority.constant.RegexConstant;
import net.wangxj.authority.validate.Severity;
import net.wangxj.authority.validate.groups.AddValidate;
import net.wangxj.authority.validate.groups.DeleteValidate;
import net.wangxj.authority.validate.groups.EditValidate;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:44
 */

public class PlatformDTO implements Serializable{ 
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7577136037165710823L;
	// 主键 	
	private String platformUuid;
    // 平台名
	@Pattern(regexp=RegexConstant.MORETHAN_TWO_CHINESECHAR, message="平台名必须是2-25个汉字", groups={AddValidate.class, EditValidate.class},payload=Severity.Info.class)
	@NotBlank(message="平台名是必填项",groups={AddValidate.class},payload=Severity.Info.class)
	private String platformName;
    // 平台标识
	@Pattern(regexp=RegexConstant.LETTER_UNDERLINE,message="平台标识必须是字母或大小写组成的字符串，下划线不可开头", groups={AddValidate.class,EditValidate.class},payload=Severity.Info.class)
	@NotBlank(message="平台标识为必填项", groups={AddValidate.class},payload=Severity.Info.class)
	private String platformSign;
    // 平台域名
	@Pattern(regexp=RegexConstant.DOMAIN,message="平台域名不符合域名格式", groups={AddValidate.class,EditValidate.class},payload=Severity.Info.class)
	@NotBlank(message="平台域名为必填值", groups={AddValidate.class},payload=Severity.Info.class)
	@Length(min=5,max=30,message="平台域名长度不合法",groups={AddValidate.class,EditValidate.class},payload=Severity.Info.class)
	private String platformDomainName;
    // 添加时间 yyyy-MM-dd HH:mm:ss 	
	private String platformAddTime;
    // 添加人uuid
	@Pattern(regexp=net.wangxj.authority.constant.RegexConstant.UUID_32, message="增加人非法格式", groups=AddValidate.class,payload=Severity.Error.class)
	@NotBlank(message="增加人为必填值",groups=AddValidate.class,payload=Severity.Error.class)
	private String platformAddBy;
    // 删除时间 yyyy-MM-dd HH:mm:ss 	
	private String platformDelTime;
    // 删除人uuid 	
	@Pattern(regexp=RegexConstant.UUID_32,message="删除人错误", groups={DeleteValidate.class},payload=Severity.Error.class)
	@NotBlank(message="删除人为必填值",groups={DeleteValidate.class},payload=Severity.Error.class)
	private String platformDelBy;
    // 平台状态： 初始化: 0 激活: 1 注销: 2
	@Min(value=0,message="输入的平台状态不合法", groups={AddValidate.class, EditValidate.class},payload=Severity.Info.class)
	@Max(value=2,message="输入的平台状态不合法", groups={AddValidate.class, EditValidate.class},payload=Severity.Info.class)
	@NotNull(message="平台状态为必填项", groups={AddValidate.class},payload=Severity.Info.class)
	private java.lang.Integer platformStatus;
	//平台状态显示名称
	private String platformStatusName;
    // 是否已被删除: 0：未 1：已被删除 	
	@Min(value=0, message="删除错误", groups={DeleteValidate.class},payload=Severity.Error.class)
	@Max(value=1, message="删除错误", groups={DeleteValidate.class},payload=Severity.Error.class)
	@NotNull(message="删除错误", groups={DeleteValidate.class},payload=Severity.Error.class)
	private java.lang.Integer platformIsDelete;
	//是否删除显示名
	private String platformIsDeleteName;
	//编辑人uuid
	@Pattern(regexp=RegexConstant.UUID_32,message="编辑人不合法",groups=EditValidate.class,payload=Severity.Error.class)
	@NotBlank(message="编辑人是必填值",groups=EditValidate.class,payload=Severity.Error.class)
	public String platformEditBy;
	//编辑时间
	public String platformEditTime;
	
	public PlatformDTO(){
		super();
	}
	
	public PlatformDTO(String platformUuid, String platformName, String platformSign, String platformDomainName,
			String platformAddTime, String platformAddBy, String platformDelTime, String platformDelBy,
			Integer platformStatus, String platformStatusName, Integer platformIsDelete, String platformIsDeleteName) {
		this();
		this.platformUuid = platformUuid;
		this.platformName = platformName;
		this.platformSign = platformSign;
		this.platformDomainName = platformDomainName;
		this.platformAddTime = platformAddTime;
		this.platformAddBy = platformAddBy;
		this.platformDelTime = platformDelTime;
		this.platformDelBy = platformDelBy;
		this.platformStatus = platformStatus;
		this.platformStatusName = platformStatusName;
		this.platformIsDelete = platformIsDelete;
		this.platformIsDeleteName = platformIsDeleteName;
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
	
	public String getPlatformStatusName() {
		return DataDictionaryConstant.getPlatformStatusKey(this.platformStatus);
	}

	public void setPlatformStatusName(String platformStatusName) {
		this.platformStatusName = platformStatusName;
	}

	public String getPlatformIsDeleteName() {
		return DataDictionaryConstant.getDeleteKey(this.platformIsDelete);
	}

	public void setPlatformIsDeleteName(String platformIsDeleteName) {
		this.platformIsDeleteName = platformIsDeleteName;
	}

	@Override
	public String toString() {
		return "PlatformDTO [platformUuid=" + platformUuid + ", platformName=" + platformName + ", platformSign="
				+ platformSign + ", platformDomainName=" + platformDomainName + ", platformAddTime=" + platformAddTime
				+ ", platformAddBy=" + platformAddBy + ", platformDelTime=" + platformDelTime + ", platformDelBy="
				+ platformDelBy + ", platformStatus=" + platformStatus + ", platformStatusName=" + platformStatusName
				+ ", platformIsDelete=" + platformIsDelete + ", platformIsDeleteName=" + platformIsDeleteName + "]";
	}
	
	
	
}

