package com.c503.sc.service.impl;

import static com.c503.sc.util.FileUtil.deleteDir;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.stereotype.Service;

import com.c503.sc.entity.GeneratorEntity;
import com.c503.sc.service.IGenService;
import com.c503.sc.util.FileUtil;

/**
 * 
 * @ClassName: GenServiceImpl
 * @Description: 业务实现类
 * @author liumm
 * @date 2017-11-20 上午09:19:18
 * 
 */
@Service(value = "genService")
public class GenServiceImpl implements IGenService {
    
    @Override
    public String generateCode(GeneratorEntity entity) {
        String path = "mybatisUI";
        // 清空临时文件夹下所有内容
        deleteDir(new File(System.getProperty("user.dir") + "/mybatisUI"));
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            File dirFile = new File(path);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File conf =
                new File(System.getProperty("user.dir")
                    + "/conf/Configuration.xml");
            ConfigurationParser parser = new ConfigurationParser(warnings);
            Configuration config = parser.parseConfiguration(conf);
            Context context = config.getContexts().get(0);
            // 数据库配置 判断数据库类型
            JDBCConnectionConfiguration jdbcConnectionConfiguration =
                context.getJdbcConnectionConfiguration();
            jdbcConnectionConfiguration.setConnectionURL(entity.getUrl());
            jdbcConnectionConfiguration.setUserId(entity.getUserName());
            jdbcConnectionConfiguration.setPassword(entity.getPassWord());
            jdbcConnectionConfiguration.setDriverClass(entity.getDriverClass());
            // entity 配置
            JavaModelGeneratorConfiguration javaModelGeneratorConfiguration =
                context.getJavaModelGeneratorConfiguration();
            javaModelGeneratorConfiguration.setTargetPackage(entity.getModelPackageName());
            javaModelGeneratorConfiguration.setTargetProject(path);
            // DAO 配置
            JavaClientGeneratorConfiguration javaClientGeneratorConfiguration =
                context.getJavaClientGeneratorConfiguration();
            javaClientGeneratorConfiguration.setTargetPackage(entity.getDaoPackageName());
            javaClientGeneratorConfiguration.setTargetProject(path);
            // Mapper 配置
            SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration =
                context.getSqlMapGeneratorConfiguration();
            sqlMapGeneratorConfiguration.setTargetPackage(entity.getMapperPath());
            sqlMapGeneratorConfiguration.setTargetProject(path);
            // Table 配置
            List<TableConfiguration> tableConfigurations =
                context.getTableConfigurations();
            tableConfigurations.clear();
            for (int i = 0; i < entity.getTableNames().length; i++) {
                if (!entity.getTableNames()[i].isEmpty()
                    && !entity.getTableModels()[i].isEmpty()) {
                    TableConfiguration tableConfiguration =
                        new TableConfiguration(context);
                    tableConfiguration.setTableName(entity.getTableNames()[i]);
                    tableConfiguration.setDomainObjectName(entity.getTableModels()[i]);
                    tableConfiguration.setCountByExampleStatementEnabled(false);
                    tableConfiguration.setDeleteByExampleStatementEnabled(false);
                    tableConfiguration.setSelectByExampleStatementEnabled(false);
                    tableConfiguration.setUpdateByExampleStatementEnabled(false);
                    tableConfiguration.getProperties()
                        .setProperty("useActualColumnNames", "false");
                    tableConfigurations.add(tableConfiguration);
                }
            }
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator =
                new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            FileUtil.compress("mybatisUI", System.getProperty("user.dir")
                + "/result/", "mybatisUI.zip");
        }
        catch (SQLException e) {
            e.printStackTrace();
            return "01";
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return "02";
        }
        catch (InterruptedException ite) {
            ite.printStackTrace();
            return "03";
        }
        catch (InvalidConfigurationException ice) {
            ice.printStackTrace();
            return "04";
        }
        catch (XMLParserException xmle) {
            xmle.printStackTrace();
            return "05";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "06";
        }
        return "00";
    }
    
    @Override
    public Connection getConn(GeneratorEntity entity) {
        Connection conn = null;
        try {
            Class.forName(entity.getDriverClass());
            conn =
                DriverManager.getConnection(entity.getUrl(),
                    entity.getUserName(),
                    entity.getPassWord());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
