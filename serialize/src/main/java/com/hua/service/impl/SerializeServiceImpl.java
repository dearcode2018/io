/**
 * SerializeServiceImpl.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.hua.service.SerializeService;

/**
 * SerializeServiceImpl
 * 描述: 序列化 - 服务
 * @author  qye.zheng
 */
public final class SerializeServiceImpl<T> extends CoreServiceImpl implements
		SerializeService<T>
{
	
	private ObjectOutput objectOutput;
	
	private ObjectInput objectInput;
	
	/**
	 * 
	 * 描述: 将对象序列化输出 
	 * @author  qye.zheng
	 * @param target
	 * @param outputStream
	 * @return
	 */
	@Override
	public boolean writeObject(final T target, final OutputStream outputStream)
	{
		boolean flag = false;
		try
		{
			// 对象输出 - 接口对象
			objectOutput = new ObjectOutputStream(outputStream);
			
			// 输出 目标对象
			objectOutput.writeObject(target);
			// 刷新
			objectOutput.flush();
			flag = true;
		} catch (IOException e)
		{
			log.error("writeObject =====> ", e);
		} finally 
		{
			// 关闭流
			try
			{
				if (null != objectOutput)
				{
					objectOutput.close();
				}
				if (null != outputStream)
				{
					outputStream.close();
				}
			} catch (IOException e2)
			{

			}
		}
	
		return flag;
	}
	
	/**
	 * 
	 * 描述: 读取序列化对象
	 * @author  qye.zheng
	 * @param clazz
	 * @param inputStream
	 * @return
	 */
	@SuppressWarnings(value = {"unchecked"})
	@Override
	public T readObject(final InputStream inputStream)
	{
		T t = null;
		try
		{
			// 对象输入 - 接口对象
			objectInput = new ObjectInputStream(inputStream);
			
			// 读取对象
			final Object object = objectInput.readObject();
			// 强转为T类型
			t = (T) object;
		} catch (IOException e)
		{
			log.error("readObject =====> ", e);
		} catch (ClassNotFoundException e)
		{
			log.error("readObject =====> ", e);
		} finally {
			// 关闭流
			try
			{
				if (null != objectInput)
				{
					objectInput.close();
				}
				if (null != inputStream)
				{
					inputStream.close();
				}
			} catch (IOException e2)
			{

			}
		}
		
		return t;
	}
	
	
	
	/**
		
		2）ObjectInputStream/ObjectOutputStream
		
		3）实现java.io.Serializable接口的类的对象属性会被序列化；
		如果想自定义序列化的内容时，需要实现java.io.Externalizable接口，
		而且该类必须有一个无参的构造方法。
		
		4）java.io.Serializable接口实现的操作是对象中的全部属性进行序列化，
		当然也可以使用我们上使用是java.io.Externalizable接口以实现部分属性的序列化，
		但是这样的操作比较麻烦，当我们使用java.io.Serializable接口实现序列化操作的时候，
		如果一个对象的某一个属性不想被序列化保存下来，那么我们可以使用transient关键字进行说明.
		
		 对象 （序列化）→ 文件 
		OutputStream os = new FileOutputStream("D:/abc.txt");
		ObjectOutputStream oos = new ObjectOutputStream(os);
		Animal a = new Animal();
		a.setName("zhangsan");
		a.setAge(25);
		oos.writeObject(a);
		oos.flush();
		
		 文件 （反序列化）→ 对象 
		InputStream is = new FileInputStream("D:/abc.txt");
		ObjectInputStream ois = new ObjectInputStream(is);
		Object obj = ois.readObject();
		Animal a = (Animal) obj;
		System.out.println(a.getName() + ", " + a.getAge());
		 若不希望某个字段被序列化，则加关键字     
		private transient int age;
		
		1、Java 串行化技术可以使你将一个对象的状态写入一个Byte 流里，
		并且可以从其它地方把该Byte 流里的数据读出来，重新构造一个相同的对象。
		这种机制允许你将对象通过网络进行传播，并可以随时把对象持久化到数据库、文件等系统里。
		Java的串行化机制是RMI、EJB等技术的技术基础。
		
		用途：利用对象的串行化实现保存应用程序的当前工作状态，
		下次再启动的时候将自动地恢复到上次执行的状态。
		 RMI: Remote Method Invocation 
		 
		序列化就是一种用来处理对象流的机制，所谓对象流也就是将对象的内容进行流化。
		可以对流化后的对象进行读写操作，也可将流化后的对象传输于网络之间。
		序列化是为了解决在对对象流进行读写操作时所引发的问题。
		
		然后使用一个输出流(如：java.io.FileOutputStream)来构造一个java.io.ObjectOutputStream(对象流)对象，
		接着，使用java.io.ObjectOutputStream对象的writeObject(Object obj)方法就可以将参数为obj的对象写出(即保存其状态)，
		要恢复的话则用输入流（java.io.ObjectInputStream）。
		
		2、串行化的特点：
		1）如果某个类能够被串行化，其子类也可以被串行化。如果该类有父类，则分两种情况来考虑，
		如果该父类已经实现了可串行化接口。则其父类的相应字段及属性的处理和该类相同；
		如果该类的父类没有实现可串行化接口，则该类的父类所有的字段属性将不会串行化。
		
		2）声明为 static 和 transient 类型的成员数据不能被串行化。因为static代表类的状态， 
		transient代表对象的临时数据。
		
		3）相关的类和接口：在java.io包中提供的涉及对象的串行化的类与接口有java.io.ObjectOutput接口、
		java.io.ObjectOutputStream类、java.io.ObjectInput接口、java.io.ObjectInputStream类。
		
		4）java.io.ObjectOutput接口：它继承DataOutput接口并且支持对象的串行化，
		其内的writeObject()方法实现存储一个对象。
		ObjectInput接口：它继承java.io.DataInput接口并且支持对象的串行化，
		其内的readObject()方法实现读取一个对象。
		
		5）java.io.ObjectOutputStream类：它继承java.io.OutputStream类并且实现ObjectOutput接口。
		利用该类来实现将对象存储（调用ObjectOutput接口中的writeObject()方法）。
		java.io.ObjectInputStream类：它继承java.io.InputStream类并且实现java.io.ObjectInput接口。
		利用该类来实现读取一个对象（调用ObjectInput接口中的readObject()方法）。
		
		6） 对于父类的处理，如果父类没有实现串行化接口，则其必须有默认的构造函数，
		否则编译的时候就会报错。在反序列化的时候，默认构造方法会被调用。但是若把父类标记为可以串行化，
		则在反串行化的时候，其默认构造方法不会被调用。这是为什么呢？
		这是因为Java 对序列化的对象进行反序列化的时候，
		直接从流里获取其对象数据来生成一个对象实例，而不是通过其构造方法来完成。
		若父类没有实现java.io.Serializable接口，则反序列化的时候，需要调用父类的无参构造方法来构造父类对象 
		
			 
			 
	 
	
	 
	 
	 */
	
	/**
	 serialVersionUID 实现java.io.Serializable 接口的对象序列化版本唯一id
	 若生成的序列化数据的serialVersionUID和当前类的serialVersionUID不同，
	 则无法读取/解析该二进制数据流，抛java.io.InvalidClassException异常
	 [类似: stream classdesc serialVersionUID = 1, local class serialVersionUID = 2]
	 
	 因此，serialVersionUID的定义和变更非常重要，通过这个值可以控制
	 序列化版本.
	 
	 
	 1) 父类无序列化，子类无序列化 --> 不能序列化
	 
	 2) 父类无序列化，子类有序列化 --> 可以序列化
	 
	 3) 父类有序列化，子类无序列化 --> 可以序列化
	 
	 4) 父类有序列化，子类有序列化 --> 可以序列化
	 
	 
	 单一的类实现了序列化接口，在反系列化的时候，
	 直接由数据来构建对象，而不是调用其构造方法.
	 
	 只有一种情况下反系列化必须提供默认构造方法: 
	 父类没有实现串行化接口，为了反系列化可以成功
	 构造对象，父类必须提供默认构造方法.
	 
	 
	 
	 */
	
	
	
}
