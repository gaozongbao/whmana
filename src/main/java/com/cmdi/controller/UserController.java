package com.cmdi.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.cmdi.util.ResponseReturnUtils;
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user", method = { RequestMethod.POST })
public class UserController {
	@ResponseBody
	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public void login(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("start test");
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			result.put("code2", "loginok");
		}catch (UnknownAccountException e) {
			System.out.println("账号或密码不正确");
			result.put("code2", "nologin");
		}catch (IncorrectCredentialsException e) {
			System.out.println("账号或密码有误");
			result.put("code2", "nologin");
		}catch (LockedAccountException e) {
			System.out.println("账号锁定,请联系管理员");
			result.put("code2", "nologin");
		}catch (AuthenticationException e) {
			System.out.println("账户验证失败");
			result.put("code2", "nologin");
		}
		
		
		// shiro在认证过程中出现错误后将异常类路径通过request返回
//				String exceptionClassName = (String) request
//						.getAttribute("shiroLoginFailure");
//				System.out.println(exceptionClassName);
//				if(exceptionClassName!=null){
//					if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
//						//throw new Exception("账号不存在");
//						result.put("code", "账号不存在");
//					} else if (IncorrectCredentialsException.class.getName().equals(
//							exceptionClassName)) {
//						//throw new Exception("用户名/密码错误");
//						result.put("code", "用户名/密码错误");
//					} else if("randomCodeError".equals(exceptionClassName)){
//						//throw new Exception("验证码错误");
//						result.put("code", "验证码错误");
//					} else{
//						//throw new Exception();//最终在异常处理器生成未知错误
//						result.put("code", "未知错误");
//					}
//				}
		//如果验证成功的话，那么会跳转到shiro.xml中配置的successUrl界面，如果不配置，那么默认跳转到上个页面
		//如果登录失败的话，那么执行下面的代码
		
		ResponseReturnUtils.returnReponseEntity(request, response, result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/loginsuccess", method = { RequestMethod.POST, RequestMethod.GET })
	public void loginsucc(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("ok");
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		//使用SecurityUtils拿到登录的用户信息，即主体
		Subject subject = SecurityUtils.getSubject();
		//得到com.cmdi.realm.CustomRealm.doGetAuthenticationInfo(AuthenticationToken)返回的SimpleAuthenticationInfo的Principal
		//身份信息，可以是一个Object，这取决于SimpleAuthenticationInfo中的Principal
		String username = (String) subject.getPrincipal();
		result.put("code", "ok");
		result.put("username", username);
		ResponseReturnUtils.returnReponseEntity(request, response, result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/refuse", method = { RequestMethod.POST, RequestMethod.GET })
	public void refuse(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("refuse");
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("code", "禁止访问");
		ResponseReturnUtils.returnReponseEntity(request, response, result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/logout", method = { RequestMethod.POST, RequestMethod.GET })
	public void logout(HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("start test222222");
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("code", "aaa");
		ResponseReturnUtils.returnReponseEntity(request, response, result);
	}

}
