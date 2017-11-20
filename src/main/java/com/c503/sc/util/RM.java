package com.c503.sc.util;

import java.util.HashMap;

/**
 * 
 * @ClassName: ResultMessage
 * @Description: 数据信息返回
 * @author liumm
 * @date 2017-11-18 下午01:52:17
 * 
 */
public class RM extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public RM() {
		put("code", 1);
		put("msg", null);
		put("data", null);
	}

	/************************ ERROR *************************/

	public static RM error() {
		return RM.error(3, null, null);
	}

	public static RM error(String msg) {
		return RM.error(3, null, msg);
	}

	public static RM error(int code, String msg) {
		return RM.error(code, null, msg);
	}

	public static RM error(int code, Object data, String msg) {
		RM r = new RM();
		r.put("code", code);
		r.put("data", data);
		r.put("msg", msg);
		return r;
	}

	/************************ success *************************/

	public static RM success() {
		return RM.success(1, null, null);
	}

	public static RM success(String msg) {
		return RM.success(1, null, msg);
	}

	public static RM success(int code) {
		return RM.success(code, null, null);
	}

	public static RM success(Object data) {
		return RM.success(1, data, null);
	}

	public static RM success(int code, String msg) {
		return RM.success(code, null, msg);
	}

	public static RM success(Object data, String msg) {
		return RM.success(1, data, msg);
	}

	public static RM success(int code, Object data, String msg) {
		RM r = new RM();
		r.put("code", code);
		r.put("data", data);
		r.put("msg", msg);
		return r;
	}

}
