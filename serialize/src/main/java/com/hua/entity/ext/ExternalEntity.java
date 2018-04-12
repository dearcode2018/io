/**
 * ExternalEntity.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.entity.ext;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.Time;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.hua.util.DateTimeUtil;

/**
 * ExternalEntity
 * 描述: 实现 java.io.Externalizable
 * @author  qye.zheng
 */
public class ExternalEntity implements Externalizable
{
	
	/**
	 实现 java.io.Externalizable的方式 没有序列化版本号，
	 因此，在对类进行修改之后，解析的时候可能
	 会出现版本不一致的情况.
	 */
	
	/* 登录-用户名 */
	private String username;
	
	/* 登录-密码 */
	private String password;
	
	/* 本次登录-时间 */
	private Time loginTime;
	
	/* 本次登录-IP地址 */
	private transient String loginIp;
	
	/**
	 * 必须提供一个默认的构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public ExternalEntity()
	{
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * @param username
	 * @param password
	 */
	public ExternalEntity(String username, String password)
	{
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * 描述:  当序列化对象时,该方法自动调用
	 * @author  qye.zheng
	 * @param out
	 * @throws IOException
	 */
	@Override
	public void writeExternal(final ObjectOutput out) throws IOException
	{
		/*
		 自定义序列化内容，可以在序列化时写非自身的变量 
		 可以详细指定只序列化哪些字段
		 */
		// 在序列化时写非自身的变量
		final Date now = DateTimeUtil.getDateTime();
		System.out.println("now: " + now);
		out.writeObject(now);
		// 只序列化某些字段
		out.writeObject(username);
		out.writeObject(password);
	}

	/**
	 * 描述: 当反序列化对象时,该方法自动调用
	 * @author  qye.zheng
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Override
	public void readExternal(final ObjectInput in) throws IOException,
			ClassNotFoundException
	{
		/**
		 反序列化: 按照write的顺序逐个解析对象
		 */
		// 严格按照顺序来解析
		// 读取 now
		final Date now = (Date) in.readObject();
		System.out.println("now: " + now);
		this.username = (String) in.readObject();
		this.password = (String) in.readObject();
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
