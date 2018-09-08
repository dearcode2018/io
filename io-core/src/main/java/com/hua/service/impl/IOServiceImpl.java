/**
 * IOServiceImpl.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.service.impl;

import com.hua.service.IOService;

/**
 * IOServiceImpl
 * 描述: Input and Output - 服务
 * @author  qye.zheng
 */
public final class IOServiceImpl extends CoreServiceImpl implements
		IOService
{
	
	/**
	   Java 流在处理上分为字符流和字节流。字符流处理的单元为 2 个字节的 Unicode 字符，
	   分别操作字符、字符数组或字符串，而字节流处理单元为 1 个字节，操作字节和字节数组。
	
		Java 内用 Unicode 编码存储字符(char类型 16bit)，字符流处理类负责将外部的其他编码的字符流
		和 java 内 Unicode 字符流之间的转换。
		而类 InputStreamReader 和 OutputStreamWriter 处理字符流和字节流的转换。
		字符流（一次可以处理一个缓冲区）一次操作比字节流（一次一个字节）效率高。 
	 	java.io.Reader / java.io.Writer 都相应的缓存字符数组来协调缓冲功能.
	 
	 
	 
	 
	 
	 */
	
	
	/**
	 	
	 	ByteArrayOutputStream ： 把信息存入内存中的一个缓冲区中 . 该类实现一个以字节数组形式写入数据的输出流。

		当数据写入缓冲区时，它自动扩大。用 toByteArray() 和 toString() 能检索数据。
		
		constructor
		
		(A)--- ByteArrayOutputStream() 创建一个新的字节数组输出流。
		
		(B)--- ByteArrayOutputStream(int) 创建一个新的字节数组输出流，并带有指定大小字节的缓冲区容量。
		
		toString(String) 根据指定字符编码将缓冲区内容转换为字符串，并将字节转换为字符。
		
		write(byte[], int, int) 将指定字节数组中从偏移量 off 开始的 len 个字节写入该字节数组输出流。
		
		write(int) 将指定字节写入该字节数组输出流。
		
		将当前缓存的所有字节数组数据写入指定的输出流
		writeTo(OutputStream) 用 out.write(buf, 0, count) 调用输出流的写方法
		将该字节数组输出流的全部内容写入指定的输出流参数。 
			 
	 
	 */
	
	/**
	 
	 命名特点:
	 [input, output] [reader, writer]
	 [data, buffered] [stream, ]
	 
	 BufferedOutputStream 覆盖了父类的flush()方法，而其他输出类都只是调用OutputStream.flush()，
	 而该方法是一个空实现.因此，直接用BufferedOutputStream作为输出流动作，需要在最后flush.
	 BufferedOutputStream 默认的最大缓存是8192个字节即(byte[8192])，可以在构造BufferedOutputStream
	 的时候指定这个值. 若读取的字节超出缓存的值，则会将当前缓存中的所有数据输出到目的地，
	 相反，如果一个数据小于8192个字节，输出结束没有flush，则数据没有实现输出；
	 因此，应该形成一个规范，在构建BufferedOutputStream的时候，可以选择指定或使用默认的
	 缓存大小，在循环体中write数据的时候，不要每次都flush(这样缓存效果就不起作用)，而是在循环体
	 外面，在write完数据的时候，调用flush，这样可以确保文件无论大小，都可以实现输出或完整的输出。
	 而不是没有输出(当文件小于缓存值的时候)或者输出不完整(文件最后小于缓存值的部分没有输出).
	 
	 在循环体外面底部调用flush方法，能够确保将最后小于缓存值的数据输出到目的地.
	 
	 当然，有些输出流，在close的时候，都会在close方法中先调用flush方法，所以应该养成
	 在用完流之后主动关闭流的好习惯.
	 
	 Writer类的flush()方法是一个抽象方法，其子类一般都实现了该方法。
	 所以，一般使用字符流之后，调用一下flush()或者close()方法。
	 
	 总之，不管你使用哪种流（字符、字节、具有缓冲的流）技术，
	 不妨调用一下flush()/close()方法，防止数据无法写到输出流中。
	 */

	/**
	 
	 
	1）从输入流读取数据，将数据写入输出流。
	2）
	3）Java的输入/输出操作是基于数据流的，
	有序的字节/字符通过一个通信信道从源地址传送到目的地址。
	4）
	5）流是一个抽象概念，当Java程序需要和数据源交换数据时，
	会开启一个数据源的流。数据源可以是: 内存、文件(硬盘)、网络。

	 
	流_类型
	1）节点流包装流
	2）字节流（Byte为单位，与编码无关）、字符流（Character为单位，与编码有关）
	3）包装流
	 字节流转换成字符流 
	InputStream → InputStreamRader
	OutputStream → OutputStreamWriter
	5）使用字节流好还是字符流好呢？
	答案: 字节流。首先因为硬盘上的所有文件都是以字节的形式进行传输或者存储的，
	包括图片等内容。但是字符只有在内存中才会形成的，所以在开发中，字节流使用广泛.
	在文件加密的时候，需要面向字符进行加密，这样就需要通过字符流来处理.
	
	流IO与块IO
	1）流IO优点: 简单易用；缺点: 效率低
	2）块IO优点: 效率高；缺点: 编程复杂
	
	文件流
	1）追加到文件
	① 通过构造方法的参数来控制是否追加
	FileOutputStream
	FileWriter
	② 通过RandomAccessFile实现追加
	2）可设置_读写_文件编码
	InputStreamReader(InputStream, String)
	OutputStreamWriter(OutputStream, String)
	
	控制台流
	控制台_输入输出流
	InputStream System.in 标准输入流
	PrintStream System.out标准输出流
	PrintStream System.err标准出错流
	 控制台_输入 → 从文件输入 
	InputStream is = new FileInputStream("D:/abc.txt");
	System.setIn(is);
	Scanner sc = new Scanner(System.in);
	 从文件输入，输出到控制台
	System.out.println(sc.next());
	 控制台_输出 → 输出到文件 
	OutputStream os = new FileOutputStream("D:/abc.txt" ,true);
	 将OutputStream转成PrintStream 
	PrintStream ps = new PrintStream(os);
	System.setOut(ps);
	
	字节_输入流
	1）先谈谈输入流，输入流中跟数据源直接接触的类有: FileInputStream和ByteArrayInputStream，
	他们分别实现了从文件或者内存中的字节数组读入数据到输入流。
	2）其他的输入流处理类都是装饰类（Decorator模式），下面对他们进行一下简单介绍: 
	BufferedInputStream:  提供了缓冲功能。
	DataInputStream: 允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型。
	应用程序可以使用数据输出流写入稍后由数据输入流读取的数据。 
	
	PipedInputStream: 允许以管道的方式来处理流.当连接到一个PipedOutputStream后，
	它会读取后者输出到管道的数据.（管道输入流和管道输出流对接）
	
	PushbackInputStream: 允许放回已经读取的数据
	SequenceInputStream: 能对多个inputstream进行顺序处理
	
	字节输入流（文件_示例）
	 循环读取_单个字节，结束标志: -1 
	InputStream is = new FileInputStream("D:/abc.txt");
	int single = 0;
	while (-1 != (single = is.read())) {
		 System.out.print(single + ", ");
	}
	 循环读取_多个字节，结束标志: -1 
	InputStream is = new FileInputStream("D:/abc.txt");
	byte[] data = new byte[4];
	int available = data.length;
	while (-1 != is.read(data)) {
		for (int i = 0; i < available; i++) {
			 	System.out.print(data[i] + ", ");
			}
		if (available > is.available()) {
			available = is.available();
			}
		System.out.println();
		}
	 使用字节_输入缓冲流 
	 循环读取_单个字节，结束标志: -1 
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:/abc.txt"));
	int d = 0;
	while (-1 != (d = bis.read())) {
		System.out.print(d + ", ");
	}
	 循环读取_多个字节，结束标志: -1 
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:/abc.txt"));
	byte[] arr = new byte[4];
	int available = arr.length;
	while (-1 != bis.read(arr)) {
	for (int i = 0; i < available; i++) {
			 System.out.print(arr[i] + ", ");
		}
	if (available > bis.available()) {
	available = bis.available();
	}
	System.out.println();
	}
	 输入字节流 → 输入字符流 
	InputStream is = new FileInputStream("D:/abc.txt");
	InputStreamReader isr = new InputStreamReader(is, "UTF-8");
	
	字节_输出流
	1）基本上每个输入流类都有一个相应的输出流类，提供相应的输出流处理。
	同样，跟数据目的地直接接触的类有: FileOutputStream和ByteArrayOutputStream，
	前者实现了把数据流写入文件的功能，后者实现了一个输出流，
	其中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。
	可使用 toByteArray() 和 toString() 获取数据。 
	2）下面对其它的装饰类做一下简单介绍: 
	BufferedOutputStream:  提供了缓冲功能的输出流，在写出完成之前要调用flush来保证数据的输出。
	DataOutputStream: 数据输出流允许应用程序以适当方式将基本 Java 数据类型写入输出流中。
	然后，应用程序可以使用数据输入流将数据读入。 
	PipedOutputStream: 允许以管道的方式来处理流。
	可以将管道输出流连接到管道输入流来创建通信管道。
	管道输出流是管道的发送端。通常，数据由某个线程写入 PipedOutputStream 对象，
	并由其他线程从连接的 PipedInputStream 读取。
	3）PrintStream: 为其他输出流添加了功能，使它们能够方便地打印各种数据值表示形式。
	我们经常用到的System.out或者System.err都是PrintStream
	
	字节输出流（文件_示例）
	 输出_多个字节 
	String str = "中国a";
	OutputStream os = new FileOutputStream("D:/abc.txt");
	os.write(str.getBytes("GB2312"));
	os.flush();
	 使用字节_输出缓冲流 
	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:/abc.txt", true));
	String str = "nanjing yuhuatai";
	bos.write(str.getBytes()); 
	bos.flush();
	 输出字节流 → 输出字符流 
	OutputStream os = new FileOutputStream("D:/abc.txt");
	OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
	
	字符_输入流
	跟数据源直接接触的类: 
	1）CharArrayReader: 从内存中的字符数组中读入数据，以对数据进行流式读取。
	2）StringReader: 从内存中的字符串读入数据，以对数据进行流式读取。
	3）FileReader: 从文件中读入数据。
	注意这里读入数据时会根据JVM的默认编码对数据进行内转换，而不能指定使用的编码。
	所以当文件使用的编码不是JVM默认编码时，不要使用这种方式。
	要正确地转码，使用 InputStreamReader。
	装饰类: 
	4）BufferedReader
	提供缓冲功能，可以读取行: readLine();
	5）LineNumberReader
	提供读取行的控制: getLineNumber()
	6）InputStreamReader
	字节流通向字符流的桥梁: 它使用指定的 charset读取字节并将其解码为字符.
	
	字符输入流（文件_示例）
	 循环读取_单个字符，结束标志: -1 
	Reader reader = new FileReader("D:/abc.txt");
	int d = 0;	   
	while (-1 != (d = reader.read())) {
		   System.out.print(d + ", ");
	}
	 循环读取_多个字符，结束标志: -1 
	 文件采用UTF-8编码才无中文乱码问题 
	Reader reader = new FileReader("D:/abc.txt");
	char[] ch = new char[1];
	while (-1 != reader.read(ch)) {
		 System.out.println(ch[0]);
	}
	 使用字符_输入缓冲流 
	 循环读取_单个字符，结束标志: -1 
	BufferedReader bf = new BufferedReader(new FileReader("D:/abc.txt"));
	int d = 0;
	while (-1 != (d = bf.read())) {
		System.out.print(d + ", ");
	}
	 循环读取_多个字符，结束标志: -1 
	BufferedReader bf = new BufferedReader(new FileReader("D:/abc.txt"));
	char[] arr = new char[2];
	while (-1 != bf.read(arr)) {
		System.out.print(arr);
	}
	 循环读取_单行字符，结束标志: null 
	BufferedReader bf = new BufferedReader(new FileReader("D:/abc.txt"));
	String str = null;
	while (null != (str = bf.readLine())) {
		System.out.println(str);
	}
	
	字符_输出流
	根数据目的相关的类: 
	1）CharArrayWriter: 把内存中的字符数组写入输出流，输出流的缓冲区会自动增加大小.
	默认大小是32个字符，可以在构造时指定初始小.
	
	输出流的数据可以通过一些方法重新获取
	2）StringWriter: 一个字符流，可以用其回收在字符串缓冲区中的输出来构造字符串
	3）FileWriter: 把数据写入文件
	
	字符输出流（文件_示例）
	 输出_多个字符 
	Writer writer = new FileWriter("D:/abc.txt");
	String str = "中国，你好";
	writer.write(str);
	writer.flush();
	 使用字符_输出缓冲流 
	 写入字符串 
	BufferedWriter bw = new BufferedWriter(new FileWriter("D:/abc.txt", true));
	bw.write("\nnew town!");
	bw.flush(); 
	
	字节流_字符流
	1）Java IO流在处理上分为字节流和字符流
	2）字符流: 处理单元为2个字节的Unicode字符，操作字符、字符数组、字符串。ReaderWriter
	3）字节流: 处理单元为1个字节，操作字节、字节数组。InputStreamOutputStream
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 */
}
