package com.veasymall.api.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description: 自定义响应数据结构 200：表示成功 500： 表示错误，错误信息在 msg 字段中 501： bean
 *               验证错误，不管多少个错误都以 map 形式返回 502： 拦截器拦截到用户 token 出错 555： 异常抛出信息
 * @author machaoyi
 * 
 *
 */
public class Result {

	// 定义 jackson 对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	// 响应业务状态
	private Integer errcode;

	// 响应消息
	private String errmsg;

	// 响应中的数据
	private Object data;

	public Result(Integer errcode, String errmsg, Object data) {
		this.errcode = errcode;
		this.errmsg = errmsg;
		this.data = data;
	}

	public Result(Object data) {
		this.errcode = 200;
		this.errmsg = "ok";
		this.data = data;
	}

	public static Result build(Integer status, String msg, Object data) {
		return new Result(status, msg, data);
	}

	public static Result ok(Object data) {
		return new Result(data);
	}

	public static Result ok() {
		return new Result(null);
	}

	public static Result errorMsg(String msg) {
		return new Result(500, msg, null);
	}

	public static Result errorMap(Object data) {
		return new Result(501, "error", data);
	}

	public static Result errorTokenMsg(String msg) {
		return new Result(502, msg, null);
	}

	public static Result errorException(String msg) {
		return new Result(555, msg, null);
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ObjectMapper getMapper() {
		return MAPPER;
	}

}
