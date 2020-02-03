package com.cmdi.service;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.cmdi.model.GroupInfo;



@Component
public interface GroupInfoService {
	public GroupInfo get(String id) throws Exception;
	public void sendemail(GroupInfo id) throws Exception;
	public void update(GroupInfo id) throws Exception;
}
