package net.wangxj.authority.constant;

public class RegexConstant {
	
	/**
	 * 以大小写字母与下划线混合字符串，下划线位于中间
	 */
	public static final String LETTER_UNDERLINE = "^[A-Za-z]+_?[A-Za-z]{2,32}+$";
	/**
	 * 域名
	 */
	public static final String DOMAIN = "^((http://)|(https://))?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
	/**
	 * 两个以上汉字
	 */
	public static final String MORETHAN_TWO_CHINESECHAR= "^[\u4e00-\u9fa5]{2,25}$";
	/**
	 * 32位uuid
	 */
	public static final String UUID_32= "^[a-z0-9]{32}$";

}
