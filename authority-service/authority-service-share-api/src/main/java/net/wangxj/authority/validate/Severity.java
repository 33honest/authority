package net.wangxj.authority.validate;

import javax.validation.Payload;

public class Severity {
	public static interface Error extends Payload{};
	public static interface Info extends Payload{};
}
