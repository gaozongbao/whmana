package com.cmdi.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalVar {

	@Value("${base.p.localpath}")
	public String LOCAL_P_PATH;
	
	@Value("${base.r.localpath}")
	public String LOCAL_R_PATH;
	
	@Value("${base.url.pre}")
	public String Base_Url_Path;
}
