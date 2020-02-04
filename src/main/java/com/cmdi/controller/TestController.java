package com.cmdi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.cmdi.service.GroupInfoService;
import com.cmdi.util.CustomerContextHolder;
import com.cmdi.util.ResponseEntity;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * 测试Controller
 *
 * @author: 高宗宝
 * @Description:无
 */
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/test", method = {RequestMethod.POST})
public class TestController {

    @Autowired
    private GroupInfoService testService;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 测试
     *
     * @author 高宗宝
     */
    @ResponseBody
    @RequestMapping(value = "/testcode", method = {RequestMethod.POST, RequestMethod.GET})
    public void testentitycode(@RequestParam(value = "name", required = true) String name,
                               HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        System.out.println("start test");
        //切换到pg数据库
        if ("a".equals(name)) {
            HashMap<String, Object> result = new HashMap<String, Object>();
            result.put("name", name);
            result.put("value", null);
            returnReponseEntity(request, response, ResponseEntity.successResponse(result, "成功"));
        } else {
            returnReponseEntity(request, response, ResponseEntity.failResponse("错误"));
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/ttt", method = {RequestMethod.POST, RequestMethod.GET})
    @RequiresPermissions("aaa:bbb")
    public void test(@RequestParam(value = "name", required = false) String name,
                               HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        System.out.println("start test");
        //切换到pg数据库
        System.out.println("jinlail");
    }
    
    @ResponseBody
    @RequestMapping(value = "/ccc", method = {RequestMethod.POST, RequestMethod.GET})
    @RequiresPermissions("aaa:bbbbbb")
    public void test2(@RequestParam(value = "name", required = true) String name,
                               HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        System.out.println("start test");
        //切换到pg数据库
        System.out.println("jinlail");
    }
    
    @ResponseBody
    @RequestMapping(value = "/down", method = {RequestMethod.POST, RequestMethod.GET})
    public void testdow(@RequestParam(value = "name", required = true) String name,
                               HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        System.out.println("start test");
        //切换到pg数据库
        System.out.println("jinlail");
    }


    public void returnReponseEntity(HttpServletRequest request, HttpServletResponse response, ResponseEntity result)
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


