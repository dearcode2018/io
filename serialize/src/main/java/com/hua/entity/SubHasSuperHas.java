/**
 * SubHasSuperHas.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.entity;

import java.io.Serializable;
import java.sql.Time;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * SubHasSuperHas
 * 描述: 可以序列化
 * @author  qye.zheng
 */
public class SubHasSuperHas extends SuperHas implements Serializable
{
	/* long */
	private static final long serialVersionUID = 1L;

	/* 登录-用户名 */
	private String username;
	
	/* 登录-密码 */
	private String password;
	
	/* 本次登录-时间 */
	private Time loginTime;
	
	/* 本次登录-IP地址 */
	private transient String loginIp;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public SubHasSuperHas()
	{
	}

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * @param username
	 * @param password
	 */
	public SubHasSuperHas(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return the loginTime
	 */
	public Time getLoginTime()
	{
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Time loginTime)
	{
		this.loginTime = loginTime;
	}

	/**
	 * @return the loginIp
	 */
	public String getLoginIp()
	{
		return loginIp;
	}

	/**
	 * @param loginIp the loginIp to set
	 */
	public void setLoginIp(String loginIp)
	{
		this.loginIp = loginIp;
	}
	
	 /**
	 * 描述: 
	 * @author qye.zheng
	 * @return
	 */
	@Override
	public String toString()
	{
		String result = new ReflectionToStringBuilder(this).toString();
		
		return result;
	}
	
}
