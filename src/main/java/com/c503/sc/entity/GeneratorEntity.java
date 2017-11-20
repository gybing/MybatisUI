/**   
 * @Title: GeneratorEntity.java 
 * @Package com.c503.sc.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liumm   
 * @date 2017年11月17日 下午7:45:54 
 * @version V1.0   
 */
package com.c503.sc.entity;

import com.c503.sc.constant.CommonCostant;

/**
 * @ClassName: GeneratorEntity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author liumm
 * @date 2017年11月17日 下午7:45:54
 * 
 */
public class GeneratorEntity {

	private String ip;

	private String db;

	private String port;

	private String type;

	private String userName;

	private String passWord;

	private String modelPackageName;

	private String daoPackageName;

	private String mapperPath;

	private String tableNames[];

	private String tableModels[];

	// 根据不同的类型获取数据库链接的URL地址
	public String getUrl() {
		String url = "";
		if (CommonCostant.MYSQL_TYPE.equals(getType())) {
			url = "jdbc:mysql://"
					+ getIp()
					+ ":"
					+ getPort()
					+ "/"
					+ getDb()
					+ "?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=true";
		} else if (CommonCostant.POSTGRESQL_TYPE.equals(getType())) {
			url = "jdbc:postgresql://" + getIp() + ":" + getPort() + "/"
					+ getDb();
		} else if (CommonCostant.ORACLE_TYPE.equals(getType())) {
			url = "jdbc:oracle:thin:@" + getIp() + ":" + getPort() + ":"
					+ getDb();
		} else if (CommonCostant.SQLSERVER_TYPE.equals(getType())) {

		} else {

		}
		return url;
	}

	/**
	 * 
	 * @Title: getDriverClass <br/>
	 * @Description: 获取数据库驱动 <br/>
	 * @param @return 设定文件 <br/>
	 * @return String 返回类型 <br/>
	 * @throws
	 */
	public String getDriverClass() {
		String driverClass = "";
		if (CommonCostant.MYSQL_TYPE.equals(getType())) {
			driverClass = "com.mysql.jdbc.Driver";
		} else if (CommonCostant.POSTGRESQL_TYPE.equals(getType())) {
			driverClass = "org.postgresql.Driver";
		} else if (CommonCostant.ORACLE_TYPE.equals(getType())) {
			driverClass = "oracle.jdbc.driver.OracleDriver";
		} else if (CommonCostant.SQLSERVER_TYPE.equals(getType())) {

		} else {

		}
		return driverClass;
	}

	/**
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            要设置的 ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return db
	 */
	public String getDb() {
		return db;
	}

	/**
	 * @param db
	 *            要设置的 db
	 */
	public void setDb(String db) {
		this.db = db;
	}

	/**
	 * @return port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port
	 *            要设置的 port
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            要设置的 userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * @return modelPackageName
	 */
	public String getModelPackageName() {
		return modelPackageName;
	}

	/**
	 * @param modelPackageName
	 *            要设置的 modelPackageName
	 */
	public void setModelPackageName(String modelPackageName) {
		this.modelPackageName = modelPackageName;
	}

	/**
	 * @return daoPackageName
	 */
	public String getDaoPackageName() {
		return daoPackageName;
	}

	/**
	 * @param daoPackageName
	 *            要设置的 daoPackageName
	 */
	public void setDaoPackageName(String daoPackageName) {
		this.daoPackageName = daoPackageName;
	}

	/**
	 * @return mapperPath
	 */
	public String getMapperPath() {
		return mapperPath;
	}

	/**
	 * @param mapperPath
	 *            要设置的 mapperPath
	 */
	public void setMapperPath(String mapperPath) {
		this.mapperPath = mapperPath;
	}

	/**
	 * @return tableNames
	 */
	public String[] getTableNames() {
		return tableNames;
	}

	/**
	 * @param tableNames
	 *            要设置的 tableNames
	 */
	public void setTableNames(String[] tableNames) {
		this.tableNames = tableNames;
	}

	/**
	 * @return tableModels
	 */
	public String[] getTableModels() {
		return tableModels;
	}

	/**
	 * @param tableModels
	 *            要设置的 tableModels
	 */
	public void setTableModels(String[] tableModels) {
		this.tableModels = tableModels;
	}

}
