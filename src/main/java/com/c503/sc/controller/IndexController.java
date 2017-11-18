package com.c503.sc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.c503.sc.service.GenService;

/**
 * 
 * @ClassName: IndexController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author liumm
 * @date 2017年11月16日 下午8:10:58
 *
 */
@Controller
@RequestMapping("/generator")
public class IndexController {

	@Autowired
	public GenService genService;

	/**
	 * 
	 * @Title: generator <br/>
	 * @Description: TODO(这里用一句话描述这个方法的作用) <br/>
	 * @param @param request
	 * @param @return    设定文件 <br/>
	 * @return String    返回类型 <br/>
	 * @throws
	 */
	@RequestMapping("/code")
	@ResponseBody
	public String generator(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", GenService.genCode(request));
		jsonObject.put("basepath", basePath);
		return jsonObject.toJSONString();
	}

}