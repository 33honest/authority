package net.wangxj.authority.constant;

public class RegexConstant {
	
	/**
	 * 以大小写字母与下划线混合字符串，下划线位于中间
	 */
	public static final String LETTER_UNDERLINE = "^[A-Za-z]+_?[A-Za-z]+$";
	/**
	 * 域名
	 */
	public static final String DOMAIN = "^((http://)|(https://))?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";

}
