/**   
 * Copyright © 2018 cmdi All rights reserved.
 * 
 * @Package: com.cmdi.realm 
 * @author: 高宗宝   
 * @date: 2018年6月14日 下午6:38:10 
 */
package com.cmdi.realm;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/** 
 * @ClassName: MyFormAuthenticationFilter 
 * @Description: 自定义认证过滤器
 * @author: 高宗宝
 * @date: 2018年6月14日 下午6:38:10  
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	//验证码
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//验证码校验
		HttpSession session = ((HttpServletRequest)request).getSession();
		String validatecode = (String) session.getAttribute("validatecode");
		String randomcode = request.getParameter("randomcode");
		//验证码校验失败那么不进行用户名密码验证，返回true
		if(validatecode != null && randomcode != null) {
			if(!validatecode.equals(randomcode)) {
				request.setAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, "randomCodeError");
				return false;//实际需要true
//				return true;
			}
		}
		return super.onAccessDenied(request, response);
	}
	//自定义用户名和密码的值

}
