package com.c503.sc.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.c503.sc.entity.GeneratorEntity;
import com.c503.sc.service.IGenService;
import com.c503.sc.util.RM;

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
    
    /** 业务接口 */
    @Resource(name = "genService")
    public IGenService genService;
    
    /**
     * 
     * @Title: generator <br/>
     * @Description: 生成模板代码主要方法 <br/>
     * @param @param request
     * @param @return 设定文件 <br/>
     * @return String 返回类型 <br/>
     * @throws
     */
    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @ResponseBody
    public String generator(HttpServletRequest request, GeneratorEntity entity) {
        String path = request.getContextPath();
        String basePath =
            request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + path + "/";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", genService.generateCode(entity));
        jsonObject.put("basepath", basePath);
        return jsonObject.toJSONString();
    }
    
    /**
     * 
     * @Title: linkTest <br/>
     * @Description: 数据库链接测试 <br/>
     * @param @param entity
     * @param @return
     * @param @throws SQLException 设定文件 <br/>
     * @return RM 返回类型 <br/>
     * @throws
     */
    @RequestMapping(value = "/linkTest", method = RequestMethod.POST)
    @ResponseBody
    public RM linkTest(GeneratorEntity entity)
        throws SQLException {
        Connection conn = genService.getConn(entity);
        if (null == conn) {
            return RM.error("链接失败，请查看输入的信息是否有误");
        }
        else {
            conn.close();
            return RM.success("数据库连接成功");
        }
    }
}