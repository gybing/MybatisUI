package com.c503.sc.service;

import java.sql.Connection;
import com.c503.sc.entity.GeneratorEntity;

/**
 * 
 * @ClassName: IGenService
 * @Description: 业务接口类
 * @author liumm
 * @date 2017-11-20 上午09:17:58
 * 
 */
public interface IGenService {
    
    /**
     * 
     * @Title: generateCode <br/>
     * @Description: 代码生成打包接口类 <br/>
     * @param @param entity
     * @param @return 设定文件 <br/>
     * @return String 返回类型 <br/>
     * @throws
     */
    public String generateCode(GeneratorEntity entity);
    
    /**
     * 
     * @Title: getConn <br/>
     * @Description: 数据库链接测试类 <br/>
     * @param @param entity
     * @param @return 设定文件 <br/>
     * @return Connection 返回类型 <br/>
     * @throws
     */
    public Connection getConn(GeneratorEntity entity);
}
