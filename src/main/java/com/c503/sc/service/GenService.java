package com.c503.sc.service;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.stereotype.Service;

import com.c503.sc.util.FileUtil;

import javax.servlet.http.HttpServletRequest;

import static com.c503.sc.util.FileUtil.deleteDir;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: GenService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author liumm
 * @date 2017年11月16日 下午8:12:11
 *
 */
@Service
public class GenService {

	public String genCode(HttpServletRequest request) {
		String ip = request.getParameter("ip");
		String db = request.getParameter("db");
		String port = request.getParameter("port");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String modelPackageName = request.getParameter("modelpackagename");
		String daoPackageName = request.getParameter("daopackagename");
		String mapperPath = request.getParameter("mapperpath");
		String tableNames[] = request.getParameterValues("tablenames");
		String tableModels[] = request.getParameterValues("tablemodels");
		String path = "mybatisUI";
		// 清空临时文件夹下所有内容
		deleteDir(new File(System.getProperty("user.dir") + "/mybatisUI"));
		try {
			List<String> warnings = new ArrayList<>();
			boolean overwrite = true;
			File dirFile = new File(path);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			File conf = new File(System.getProperty("user.dir") + "/conf/Configuration.xml");
			ConfigurationParser parser = new ConfigurationParser(warnings);
			Configuration config = parser.parseConfiguration(conf);
			Context context = config.getContexts().get(0);
			// db
			JDBCConnectionConfiguration jdbcConnectionConfiguration = context.getJdbcConnectionConfiguration();
			String connection = "jdbc:mysql://" + ip + ":" + port + "/" + db
					+ "?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=true";
			jdbcConnectionConfiguration.setConnectionURL(connection);
			jdbcConnectionConfiguration.setUserId(userName);
			jdbcConnectionConfiguration.setPassword(password);
			// model 配置
			JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = context
					.getJavaModelGeneratorConfiguration();
			javaModelGeneratorConfiguration.setTargetPackage(modelPackageName);
			javaModelGeneratorConfiguration.setTargetProject(path);
			// DAO 配置
			JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = context
					.getJavaClientGeneratorConfiguration();
			javaClientGeneratorConfiguration.setTargetPackage(daoPackageName);
			javaClientGeneratorConfiguration.setTargetProject(path);
			// Mapper
			SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = context.getSqlMapGeneratorConfiguration();
			sqlMapGeneratorConfiguration.setTargetPackage(mapperPath);
			sqlMapGeneratorConfiguration.setTargetProject(path);
			// 表
			List<TableConfiguration> tableConfigurations = context.getTableConfigurations();
			tableConfigurations.clear();
			for (int i = 0; i < tableNames.length; i++) {
				if (!tableNames[i].isEmpty() && !tableModels[i].isEmpty()) {
					TableConfiguration tableConfiguration = new TableConfiguration(context);
					tableConfiguration.setTableName(tableNames[i]);
					tableConfiguration.setDomainObjectName(tableModels[i]);
					tableConfiguration.setCountByExampleStatementEnabled(false);
					tableConfiguration.setDeleteByExampleStatementEnabled(false);
					tableConfiguration.setSelectByExampleStatementEnabled(false);
					tableConfiguration.setUpdateByExampleStatementEnabled(false);
					tableConfiguration.getProperties().setProperty("useActualColumnNames", "false");
					tableConfigurations.add(tableConfiguration);
				}
			}
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			FileUtil.compress("mybatisUI", System.getProperty("user.dir") + "/result/", "mybatisUI.zip");
		} catch (SQLException e) {
			e.printStackTrace();
			return "01";
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return "02";
		} catch (InterruptedException ite) {
			ite.printStackTrace();
			return "03";
		} catch (InvalidConfigurationException ice) {
			ice.printStackTrace();
			return "04";
		} catch (XMLParserException xmle) {
			xmle.printStackTrace();
			return "05";
		}
		return "00";
	}

}
