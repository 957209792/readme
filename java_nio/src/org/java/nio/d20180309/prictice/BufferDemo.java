package org.java.nio.d20180309.prictice;

import java.nio.ByteBuffer;

/**
 * NIO缓冲区示例
 * NIO Buffer: 是存储数据的地方,输入数据将数据从缓冲读取，输出数据将数据从缓冲区写出
 * 
 *  缓冲区分类
 *  非直接缓冲区: 建立在JVM中的缓冲区
 *   直接缓冲区:  建立在OS中的缓冲区
 * 
 *  缓冲区实现类:
 *  ByteBuffer: 字节缓冲区
 *  CharBuffer: 字符缓冲区
 *  DoubleBuffer: 双精度浮点缓冲区
 *  FloatBuffer: 单精度浮点缓冲区
 *  ShortBuffer: 短整型缓冲区
 *  LongBuffer: 长整型缓冲区
 *  IntBuffer: 整型缓冲区
 *  
 *  缓冲区实现类核心属性:
 *  capacity: 表示缓冲区大小
 *  limit: 表示缓冲区数据可用大小
 *  position: 表示当前缓冲区读数据的起始位置,默认为0。或写数据的起始位置.
 *  mark: 表示读取数据的标记位置
 *  
 *  使用方法:
 *  get: 读取单个字节
 *  put: 写入单个字节
 *  flip: 切换读写模式
 *  allocate: 分配一个新的非直接缓冲区
 *  allocateDirect: 分配一个新的直接缓冲区
 *  
 *  
 *  
 * @author VIC
 *
 */
public class BufferDemo {
public static void main(String[] args) {
	//创建缓冲区
	ByteBuffer byteBuffer = ByteBuffer.allocate(5);
	System.out.println("*********************缓冲区初始化信息********************");
	printBufferInfo(byteBuffer);
	
	//存储（写）数据到缓冲区
	//写模式下，因为可以重复覆盖写入数据，所以初始缓冲区大小是多少，就可以写入多少
//	byte v = 1;
	String value = "abcde";
	byteBuffer.put(value.getBytes());
//	byteBuffer.put(v);
//	byteBuffer.put(v);
//	byteBuffer.put(v);
//	byteBuffer.put(v);
//	byteBuffer.put(v);
	System.out.println("********缓冲区写入后数据信息**********");
	printBufferInfo(byteBuffer);
	
	//切换读模式
	byteBuffer.flip();
	System.out.println("***********切换到读写模式*****************");
	printBufferInfo(byteBuffer);
	
	//从缓冲区读取数据
	//读模式下，缓冲区中存了多少个字节数据，用limit()查看的可读数据就是多少
	byte result = byteBuffer.get();
	System.out.println("读取数据：" + new String(new byte[]{result}));
	printBufferInfo(byteBuffer);
}


//缓冲区初始化信息方法
private static void printBufferInfo(ByteBuffer byteBuffer) {
	System.out.println("初始大小：" + byteBuffer.capacity());
	System.out.println("当前（读/写）起始位置" + byteBuffer.position());
	System.out.println("可(读/写)数据大小" + byteBuffer.limit());
	System.out.println();
}
}
