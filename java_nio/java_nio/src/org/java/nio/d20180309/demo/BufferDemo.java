package org.java.nio.d20180309.demo;

import java.nio.ByteBuffer;

/**
 * NIO缓冲区示例
 * NIO Buffer: 是存储数据的地方,输入数据将数据从缓冲读取，输出数据将数据从缓冲区写出
 * 
 *  缓冲区分类
 *  非直接缓冲区: 建立在JVM中的缓冲区,使用allocate()方法创建
 *   直接缓冲区 :  建立在OS中的缓冲区,能提高效率,使用allocateDirect()方法建立直接缓冲区
 * 
 *  缓冲区实现类:
 *  ByteBuffer: 字节缓冲区
 *  CharBuffer: 字符缓冲区
 *  DoubleBuffer: 双精度浮点缓冲区
 *  FloatBuffer: 单精度浮点缓冲区
 *  ShortBuffer: 短整型缓冲区
 *  LongBuffer: 长整型缓冲区
 *  IntBuffer: 整型缓冲区
 *  以上缓冲区都是通过allocate()方法创建,默认写模式
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
 *  rewind: 重复读取数据
 *  mark: 记录当前位置标记
 *  reset: 恢复记录的标记位置
 *  
 *  常见异常：
 *  java.nio.BufferOverflowException： 缓冲区溢出,即数据大小超出缓冲区大小
 *  
 * @author VIC
 *
 */
public class BufferDemo {
	
	public static void main(String[] args) {
		
		//BufferDemo1();
		//BufferDemo2();
		
		// 使用allocateDirect()方法建立直接缓冲区,直接建立在(操作系统)物理内存中,能提高读写性能
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
		System.out.println("是否是直接缓冲区: " + byteBuffer.isDirect());
		
		System.out.println("*************缓冲区写数据后信息****************");
		String value = "buffer";
		byteBuffer.put(value.getBytes());
		printBufferInfo(byteBuffer);
		
		// 切换到读数据模式
		byteBuffer.flip();
		
		System.out.println("*************读取缓冲区数据****************");
		System.out.println("读取数据: " + (char)byteBuffer.get());
		printBufferInfo(byteBuffer);
		
		// 调用clear()方法清空缓冲区(注: 清空后数据并没有消失,还在缓存区中,只是处于遗忘状态)
		byteBuffer.clear();
		System.out.println("*************清空缓冲区****************");
		printBufferInfo(byteBuffer);
		
		System.out.println("*************清空缓冲区后读取数据****************");
		System.out.println("读取数据: " + (char)byteBuffer.get());
		printBufferInfo(byteBuffer);
		
		
	}

	private static void BufferDemo2() {
		// 创建非直接缓冲区,缓冲区大小5字节
		System.out.println("*************缓冲区初始化信息****************");
		ByteBuffer byteBuffer = ByteBuffer.allocate(6);
		printBufferInfo(byteBuffer);
		String value = "buffer";
		byteBuffer.put(value.getBytes());
		
		// 切换到读数据模式
		byteBuffer.flip();
		
		System.out.println("*************读取缓冲区数据****************");
		System.out.println("读取数据: " + (char)byteBuffer.get());
		printBufferInfo(byteBuffer);
		
		// 调用rewind()设置重复读取数据
		byteBuffer.rewind();
		
		System.out.println("*************重复读取缓冲区数据****************");
		System.out.println("复读数据: " + (char)byteBuffer.get());
		System.out.println("复读数据: " + (char)byteBuffer.get());
		printBufferInfo(byteBuffer);
		
		// 调用mark()标记读取位置
		byteBuffer.mark();
		System.out.println("*************标记后重复读取缓冲区数据****************");
		System.out.println("标记后读取数据: " + (char)byteBuffer.get());
		System.out.println("标记后读取数据: " + (char)byteBuffer.get());
		System.out.println("标记后读取数据: " + (char)byteBuffer.get());
		
		// 调用reset()恢复标志位
		byteBuffer.reset();
		System.out.println("*************恢复标记后读取缓冲区数据****************");
		System.out.println("恢复标记后读取数据: " + (char)byteBuffer.get());
		System.out.println("恢复标记后读取数据: " + (char)byteBuffer.get());
		System.out.println("恢复标记后读取数据: " + (char)byteBuffer.get());
	}

	private static void BufferDemo1() {
		// 创建非直接缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 10);
		System.out.println("*************缓冲区初始化信息****************");
		printBufferInfo(byteBuffer);
		
		// 存储数据到缓冲区
		byte v = 1;
		byteBuffer.put(v);
		byteBuffer.put(v);
		byteBuffer.put(v);
		byteBuffer.put(v);
		byteBuffer.put(v);
		System.out.println("*************缓冲区写入数据后信息****************");
		printBufferInfo(byteBuffer);
		
		// 切换读模式
		byteBuffer.flip();
		System.out.println("*************缓冲区切换到读模式****************");
		printBufferInfo(byteBuffer);
		
		// 从缓冲区读取数据
		byte result = byteBuffer.get();
		System.out.println("读取数据: " + result);
		System.out.println();
		System.out.println("*************缓冲区数读取据后信息****************");
		printBufferInfo(byteBuffer);
	}

	private static void printBufferInfo(ByteBuffer byteBuffer) {
		System.out.println("初始大小: " + byteBuffer.capacity());
		System.out.println("当前(读/写)起始位置: " + byteBuffer.position());
		System.out.println("可(读/写)数据大小: " + byteBuffer.limit());
		System.out.println();
	}

}
