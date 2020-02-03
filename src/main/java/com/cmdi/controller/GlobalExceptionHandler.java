/**   
 * @ClassName:  GlobalExceptionHandler   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @date:	2018年6月14日
 * @author: 高宗宝 
*/
package com.cmdi.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmdi.util.ResponseReturnUtils;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseBody
	public void handleUnauthorizedException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		result.put("error", "未授权，禁止访问");
		ResponseReturnUtils.returnReponseEntity(request, response, result);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public void handleMissingServletRequestParameterException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		result.put("error", "缺少必要参数");
		ResponseReturnUtils.returnReponseEntity(request, response, result);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public void handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		result.put("msg", ex.getMessage());
		ex.printStackTrace();
		ResponseReturnUtils.returnReponseEntity(request, response, result);
	}
}
