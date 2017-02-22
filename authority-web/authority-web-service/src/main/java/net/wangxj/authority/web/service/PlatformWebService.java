package net.wangxj.authority.web.service;

import java.util.List;

import net.wangxj.authority.dto.PlatformDTO;

public interface PlatformWebService extends AuthorityWebService<PlatformDTO> {

	String getPlatList();
}
