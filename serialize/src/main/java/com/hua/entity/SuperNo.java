/**
 * SuperNo.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.entity;

/**
 * SuperNo
 * 描述: 没有序列化的父类
 * @author  qye.zheng
 */
public class SuperNo
{
	
	private String id;
	
	private String name;
	
	/* 声明为 static 和 transient 类型的成员数据不能被串行化. static代表类的状态， 
		transient代表对象的临时数据 */
	
	/* 瞬态属性 */
	private transient int amount;

	/* 静态属性 */
	private static String description;
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public SuperNo()
	{
		/*
		 该类没有实现串行化接口，在反系列化的时候，
		 默认构造方法会被调用；
		 
		 而若该类实现了串行化接口，则在反系列化的时候，
		 其默认构造方法不会被调用，直接从流里获取其对象数据来生成一个对象实例
		 
		若父类没有实现java.io.Serializable接口，则反序列化的时候，需要调用父类的无参构造方法来构造父类对象 
		 */
		System.out.println("SuperNo.SuperNo()");
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * @param id
	 * @param name
	 */
	public SuperNo(String id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the amount
	 */
	public int getAmount()
	{
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	/**
	 * @return the description
	 */
	public static String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public static void setDescription(String description)
	{
		SuperNo.description = description;
	}
	
}
