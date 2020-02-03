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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmdi.model.GroupInfo;
import com.cmdi.service.GroupInfoService;
import com.cmdi.util.ResponseReturnUtils;

/**
 * 测试Controller
 * @author gaozb
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/group", method = { RequestMethod.POST })
public class GroupController {
	
	@Autowired
	private GroupInfoService groupInfoService;
	
	@ResponseBody
	@RequestMapping(value = "/getgroup", method = { RequestMethod.POST, RequestMethod.GET })
	public void getgroup(@RequestParam(value = "id", required = true) String id,
			HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("start test");
		HashMap<String, Object> result = new HashMap<String, Object>();
	
//		groupInfoService.get(id);
		
		result.put("data", groupInfoService.get(id));
		
		ResponseReturnUtils.returnReponseEntity(request, response, result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/email", method = {RequestMethod.POST, RequestMethod.GET})
	public void email(@RequestParam(value = "id", required = true) String id,
			HttpServletRequest request,HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("start test");
		HashMap<String, Object> result = new HashMap<String, Object>();
	
		GroupInfo groupInfo = groupInfoService.get(id);
//		groupInfoService.get(id);
		
		try {
			groupInfoService.sendemail(groupInfo);
			result.put("msg", "ok");
		} catch (Exception e) {
			// TODO: handle exception
			switch (e.getMessage()) {
			case "conferror":
				result.put("msg", "conferror");
				break;
			case "senderror":
				result.put("msg", "senderror");
				groupInfo.setStatus(1);
				groupInfoService.update(groupInfo);
				break;
			case "err":
				result.put("msg", "err");
				groupInfoService.update(groupInfo);
				break;
			default:
				break;
			}
		}
		
		ResponseReturnUtils.returnReponseEntity(request, response, result);
	}

}
