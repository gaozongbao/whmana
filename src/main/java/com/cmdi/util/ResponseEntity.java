/**   
 * @ClassName:  ResponseEntity   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: 高宗宝 
*/
package com.cmdi.util;

import java.io.Serializable;

public class ResponseEntity implements Serializable {

	private static final int SUCCESS_CODE = 0;
	private static final String SUCCESS_MSG = "数据请求成功";
	private static final int FAIL_CODE = 1;
	private static final String FAIL_MSG = "数据请求失败";

	//状态码
	private Integer code;
	//描述信息
	private String msg;
	//响应内容
	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResponseEntity(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 成功的响应
	 * @param data
	 * @param msg
	 * @return
	 */
	public static ResponseEntity successResponse(Object data, String msg) {
		return new ResponseEntity(SUCCESS_CODE,msg,data);
	}

	/**
	 * 成功的响应
	 * @param data
	 * @return
	 */
	public static ResponseEntity successResponse(Object data) {
		return new ResponseEntity(SUCCESS_CODE,SUCCESS_MSG,data);
	}

	/**
	 * 失败的响应 数据
	 * @return
	 */
	public static ResponseEntity failResponse(Object data, String msg) {
		return new ResponseEntity(SUCCESS_CODE,msg,data);
	}

	/**
	 * 失败的响应
	 * @param data
	 * @return
	 */
	public static ResponseEntity failResponse(Object data) {
		return new ResponseEntity(SUCCESS_CODE,FAIL_MSG,data);
	}

	/**
	 * 失败的响应 数据
	 * @return
	 */
	public static ResponseEntity failResponse(String msg) {
		return new ResponseEntity(SUCCESS_CODE,msg,null);
	}

	/**
	 * 失败的响应 无数据
	 * @return
	 */
	public static ResponseEntity failResponse() {
		return new ResponseEntity(SUCCESS_CODE,FAIL_MSG,null);
	}

}
