/**   
 * @Title: GeneratorEntity.java 
 * @Package com.c503.sc.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liumm   
 * @date 2017年11月17日 下午7:45:54 
 * @version V1.0   
 */
package com.c503.sc.entity;

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

	private String password;

	private String modelPackageName;

	private String daoPackageName;

	private String mapperPath;

	private String tableNames[];

	private String tableModels[];

	/** 
	 * @return ip 
	 */
	public String getIp() {
		return ip;
	}

	/** 
	 * @param ip 要设置的 ip 
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
	 * @param db 要设置的 db 
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
	 * @param port 要设置的 port 
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
	 * @param type 要设置的 type 
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
	 * @param userName 要设置的 userName 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** 
	 * @return password 
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * @param password 要设置的 password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * @return modelPackageName 
	 */
	public String getModelPackageName() {
		return modelPackageName;
	}

	/** 
	 * @param modelPackageName 要设置的 modelPackageName 
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
	 * @param daoPackageName 要设置的 daoPackageName 
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
	 * @param mapperPath 要设置的 mapperPath 
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
	 * @param tableNames 要设置的 tableNames 
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
	 * @param tableModels 要设置的 tableModels 
	 */
	public void setTableModels(String[] tableModels) {
		this.tableModels = tableModels;
	}

}
