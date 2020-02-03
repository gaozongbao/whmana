package com.cmdi.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/** 
 * @ClassName: ResponseReturnUtils 
 * @Description: response加入消息头
 * @author: 高宗宝
 * @date: 2018年6月26日
 * @version: 1.0 
 */
public class ResponseReturnUtils {
	
	public static void returnReponseEntity(HttpServletRequest request, HttpServletResponse response, ResponseEntity result)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	public static void returnReponseEntity(HttpServletRequest request, HttpServletResponse response, Object result)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
		response.getWriter().flush();
		response.getWriter().close();
	}
}
