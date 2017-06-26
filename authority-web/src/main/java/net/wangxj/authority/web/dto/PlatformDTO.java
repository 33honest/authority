package net.wangxj.authority.web.dto;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

/**
 * created by	: wangxj
 * created time	: 2016-12-26 18:06:44
 */
@JSONType
public class PlatformDTO extends DTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8336420063461219089L;
	// 主键 	
	
	@JSONField(name = "platform_uuid")
	private String platformUuid;
	// 平台名
	@JSONField(name= "platform_name")
	private String platformName;
    // 平台标识
	@JSONField(name = "platform_sign")
	private String platformSign;
    // 平台域名
	@JSONField(name = "platform_domain")
	private String platformDomainName;
    // 添加时间 yyyy-MM-dd HH:mm:ss
	@JSONField(name = "platform_add_time")
	private String platformAddTime;
    // 添加人uuid
	@JSONField(name = "platform_add_user")
	private String platformAddBy;
	@JSONField(name = "platform_add_by_name")
	private String platformAddByName;
	@JSONField(name = "platform_delete_user")
	private String platformDelBy;
    // 平台状态： 初始化: 3 激活: 1 注销: 2
	@JSONField(name = "platform_status")
	private java.lang.Integer platformStatus;
	@JSONField(name = "platform_status_name")
	private String platformStatusName;
	//编辑人uuid
	@JSONField(name = "platform_edit_user")
	private String platformEditBy;
	@JSONField(name = "platform_edit_by_name")
	private String platformEditByName;
	//编辑时间
	@JSONField(name = "platform_edit_time")
	private String platformEditTime;
	
	public PlatformDTO() {
		super();
	}

	public PlatformDTO(String platformUuid, String platformName, String platformSign, String platformDomainName,
			String platformAddTime, String platformAddBy, String platformAddByName, String platformDelBy,
			Integer platformStatus, String platformStatusName, String platformEditBy, String platformEditByName,
			String platformEditTime) {
		super();
		this.platformUuid = platformUuid;
		this.platformName = platformName;
		this.platformSign = platformSign;
		this.platformDomainName = platformDomainName;
		this.platformAddTime = platformAddTime;
		this.platformAddBy = platformAddBy;
		this.platformAddByName = platformAddByName;
		this.platformDelBy = platformDelBy;
		this.platformStatus = platformStatus;
		this.platformStatusName = platformStatusName;
		this.platformEditBy = platformEditBy;
		this.platformEditByName = platformEditByName;
		this.platformEditTime = platformEditTime;
	}

	public String getPlatformUuid() {
		return platformUuid;
	}

	public void setPlatformUuid(String platformUuid) {
		this.platformUuid = platformUuid;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getPlatformSign() {
		return platformSign;
	}

	public void setPlatformSign(String platformSign) {
		this.platformSign = platformSign;
	}

	public String getPlatformDomainName() {
		return platformDomainName;
	}

	public void setPlatformDomainName(String platformDomainName) {
		this.platformDomainName = platformDomainName;
	}

	public String getPlatformAddTime() {
		return platformAddTime;
	}

	public void setPlatformAddTime(String platformAddTime) {
		this.platformAddTime = platformAddTime;
	}

	public String getPlatformAddBy() {
		return platformAddBy;
	}

	public void setPlatformAddBy(String platformAddBy) {
		this.platformAddBy = platformAddBy;
	}

	public String getPlatformAddByName() {
		return platformAddByName;
	}

	public void setPlatformAddByName(String platformAddByName) {
		this.platformAddByName = platformAddByName;
	}

	public String getPlatformDelBy() {
		return platformDelBy;
	}

	public void setPlatformDelBy(String platformDelBy) {
		this.platformDelBy = platformDelBy;
	}

	public java.lang.Integer getPlatformStatus() {
		return platformStatus;
	}

	public void setPlatformStatus(java.lang.Integer platformStatus) {
		this.platformStatus = platformStatus;
	}

	public String getPlatformStatusName() {
		return platformStatusName;
	}

	public void setPlatformStatusName(String platformStatusName) {
		this.platformStatusName = platformStatusName;
	}

	public String getPlatformEditBy() {
		return platformEditBy;
	}

	public void setPlatformEditBy(String platformEditBy) {
		this.platformEditBy = platformEditBy;
	}

	public String getPlatformEditByName() {
		return platformEditByName;
	}

	public void setPlatformEditByName(String platformEditByName) {
		this.platformEditByName = platformEditByName;
	}

	public String getPlatformEditTime() {
		return platformEditTime;
	}

	public void setPlatformEditTime(String platformEditTime) {
		this.platformEditTime = platformEditTime;
	}

	@Override
	public String toString() {
		return "PlatformDTO [platformUuid=" + platformUuid + ", platformName=" + platformName + ", platformSign="
				+ platformSign + ", platformDomainName=" + platformDomainName + ", platformAddTime=" + platformAddTime
				+ ", platformAddBy=" + platformAddBy + ", platformAddByName=" + platformAddByName + ", platformDelBy="
				+ platformDelBy + ", platformStatus=" + platformStatus + ", platformStatusName=" + platformStatusName
				+ ", platformEditBy=" + platformEditBy + ", platformEditByName=" + platformEditByName
				+ ", platformEditTime=" + platformEditTime + "]";
	}

	
}

