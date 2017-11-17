package com.c503.sc.controller;

import com.alibaba.fastjson.JSONObject;
import com.c503.sc.service.GenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: IndexController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author liumm
 * @date 2017年11月16日 下午8:10:58
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	public GenService genService;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/generator")
	@ResponseBody
	public String generator(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", genService.genCode(request));
		jsonObject.put("basepath", basePath);
		return jsonObject.toJSONString();
	}

}