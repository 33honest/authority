//
//package net.wangxj.authority.dto;
//
//import java.io.Serializable;
//
//import net.wangxj.authority.constant.DataDictionaryConstant;
//
///**
// * created by	: wangxj
// * created time	: 2016-12-26 18:06:44
// */
//
//public class PlatformDTO implements Serializable,DTO{ 
//	
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = -7577136037165710823L;
//	// 主键 	
//	private String platformUuid;
//    // 平台名
//	private String platformName;
//    // 平台标识
//	private String platformSign;
//    // 平台域名
//	private String platformDomainName;
//    // 添加时间 yyyy-MM-dd HH:mm:ss 	
//	private String platformAddTime;
//    // 添加人uuid
//	private String platformAddBy;
//	private String platformAddByName;
//    // 删除时间 yyyy-MM-dd HH:mm:ss 	
//	private String platformDelTime;
//    // 删除人uuid 	
//	private String platformDelBy;
//	private String platformDelByName;
//    // 平台状态： 初始化: 0 激活: 1 注销: 2
//	private java.lang.Integer platformStatus;
//	//平台状态显示名称
//	private String platformStatusName;
//    // 是否已被删除: 0：未 1：已被删除 	
//	private java.lang.Integer platformIsDelete;
//	//是否删除显示名
//	private String platformIsDeleteName;
//	//编辑人uuid
//	public String platformEditBy;
//	public String platformEditByName;
//	//编辑时间
//	public String platformEditTime;
//	
//	public PlatformDTO(){
//		super();
//	}
//	
//	public PlatformDTO(String platformUuid, String platformName, String platformSign, String platformDomainName,
//			String platformAddTime, String platformAddBy, String platformDelTime, String platformDelBy,
//			Integer platformStatus, String platformStatusName, Integer platformIsDelete, String platformIsDeleteName,
//			String platformEditBy, String platformEditTime) {
//		super();
//		this.platformUuid = platformUuid;
//		this.platformName = platformName;
//		this.platformSign = platformSign;
//		this.platformDomainName = platformDomainName;
//		this.platformAddTime = platformAddTime;
//		this.platformAddBy = platformAddBy;
//		this.platformDelTime = platformDelTime;
//		this.platformDelBy = platformDelBy;
//		this.platformStatus = platformStatus;
//		this.platformStatusName = platformStatusName;
//		this.platformIsDelete = platformIsDelete;
//		this.platformIsDeleteName = platformIsDeleteName;
//		this.platformEditBy = platformEditBy;
//		this.platformEditTime = platformEditTime;
//	}
//	public void setPlatformUuid(String value) {
//		this.platformUuid = value;
//	}
//	
//	public String getPlatformUuid() {
//		return this.platformUuid;
//	}
//	public void setPlatformName(String value) {
//		this.platformName = value;
//	}
//	
//	public String getPlatformName() {
//		return this.platformName;
//	}
//	public void setPlatformSign(String value) {
//		this.platformSign = value;
//	}
//	
//	public String getPlatformSign() {
//		return this.platformSign;
//	}
//	public void setPlatformDomainName(String value) {
//		this.platformDomainName = value;
//	}
//	
//	public String getPlatformDomainName() {
//		return this.platformDomainName;
//	}
//	public void setPlatformAddTime(String value) {
//		this.platformAddTime = value;
//	}
//	
//	public String getPlatformAddTime() {
//		return this.platformAddTime;
//	}
//	public void setPlatformAddBy(String value) {
//		this.platformAddBy = value;
//	}
//	
//	public String getPlatformAddBy() {
//		return this.platformAddBy;
//	}
//	public void setPlatformDelTime(String value) {
//		this.platformDelTime = value;
//	}
//	
//	public String getPlatformDelTime() {
//		return this.platformDelTime;
//	}
//	public void setPlatformDelBy(String value) {
//		this.platformDelBy = value;
//	}
//	
//	public String getPlatformDelBy() {
//		return this.platformDelBy;
//	}
//	public void setPlatformStatus(java.lang.Integer value) {
//		this.platformStatus = value;
//	}
//	
//	public java.lang.Integer getPlatformStatus() {
//		return this.platformStatus;
//	}
//	public void setPlatformIsDelete(java.lang.Integer value) {
//		this.platformIsDelete = value;
//	}
//	
//	public java.lang.Integer getPlatformIsDelete() {
//		return this.platformIsDelete;
//	}
//	
//	public String getPlatformStatusName() {
//		return DataDictionaryConstant.getPlatformStatusKey(this.platformStatus);
//	}
//
//	public void setPlatformStatusName(String platformStatusName) {
//		this.platformStatusName = platformStatusName;
//	}
//
//	public String getPlatformIsDeleteName() {
//		return DataDictionaryConstant.getDeleteKey(this.platformIsDelete);
//	}
//
//	public void setPlatformIsDeleteName(String platformIsDeleteName) {
//		this.platformIsDeleteName = platformIsDeleteName;
//	}
//
//	
//	
//	public String getPlatformEditBy() {
//		return platformEditBy;
//	}
//
//	public void setPlatformEditBy(String platformEditBy) {
//		this.platformEditBy = platformEditBy;
//	}
//
//	public String getPlatformEditTime() {
//		return platformEditTime;
//	}
//
//	public void setPlatformEditTime(String platformEditTime) {
//		this.platformEditTime = platformEditTime;
//	}
//	
//	public String getPlatformAddByName() {
//		return platformAddByName;
//	}
//
//	public void setPlatformAddByName(String platformAddByName) {
//		this.platformAddByName = platformAddByName;
//	}
//
//	public String getPlatformDelByName() {
//		return platformDelByName;
//	}
//
//	public void setPlatformDelByName(String platformDelByName) {
//		this.platformDelByName = platformDelByName;
//	}
//
//	public String getPlatformEditByName() {
//		return platformEditByName;
//	}
//
//	public void setPlatformEditByName(String platformEditByName) {
//		this.platformEditByName = platformEditByName;
//	}
//
//	@Override
//	public String toString() {
//		return "PlatformDTO [platformUuid=" + platformUuid + ", platformName=" + platformName + ", platformSign="
//				+ platformSign + ", platformDomainName=" + platformDomainName + ", platformAddTime=" + platformAddTime
//				+ ", platformAddBy=" + platformAddBy + ", platformAddByName=" + platformAddByName + ", platformDelTime="
//				+ platformDelTime + ", platformDelBy=" + platformDelBy + ", platformDelByName=" + platformDelByName
//				+ ", platformStatus=" + platformStatus + ", platformStatusName=" + platformStatusName
//				+ ", platformIsDelete=" + platformIsDelete + ", platformIsDeleteName=" + platformIsDeleteName
//				+ ", platformEditBy=" + platformEditBy + ", platformEditByName=" + platformEditByName
//				+ ", platformEditTime=" + platformEditTime + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((platformUuid == null) ? 0 : platformUuid.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		PlatformDTO other = (PlatformDTO) obj;
//		if (platformUuid == null) {
//			if (other.platformUuid != null)
//				return false;
//		} else if (!platformUuid.equals(other.platformUuid))
//			return false;
//		return true;
//	}
//}
//
