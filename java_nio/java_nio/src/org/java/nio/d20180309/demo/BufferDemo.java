package org.java.nio.d20180309.demo;

import java.nio.ByteBuffer;

/**
 * NIO������ʾ��
 * NIO Buffer: �Ǵ洢���ݵĵط�,�������ݽ����ݴӻ����ȡ��������ݽ����ݴӻ�����д��
 * 
 *  ����������
 *  ��ֱ�ӻ�����: ������JVM�еĻ�����,ʹ��allocate()��������
 *   ֱ�ӻ����� :  ������OS�еĻ�����,�����Ч��,ʹ��allocateDirect()��������ֱ�ӻ�����
 * 
 *  ������ʵ����:
 *  ByteBuffer: �ֽڻ�����
 *  CharBuffer: �ַ�������
 *  DoubleBuffer: ˫���ȸ��㻺����
 *  FloatBuffer: �����ȸ��㻺����
 *  ShortBuffer: �����ͻ�����
 *  LongBuffer: �����ͻ�����
 *  IntBuffer: ���ͻ�����
 *  ���ϻ���������ͨ��allocate()��������,Ĭ��дģʽ
 *  
 *  ������ʵ�����������:
 *  capacity: ��ʾ��������С
 *  limit: ��ʾ���������ݿ��ô�С
 *  position: ��ʾ��ǰ�����������ݵ���ʼλ��,Ĭ��Ϊ0����д���ݵ���ʼλ��.
 *  mark: ��ʾ��ȡ���ݵı��λ��
 *  
 *  ʹ�÷���:
 *  get: ��ȡ�����ֽ�
 *  put: д�뵥���ֽ�
 *  flip: �л���дģʽ
 *  allocate: ����һ���µķ�ֱ�ӻ�����
 *  allocateDirect: ����һ���µ�ֱ�ӻ�����
 *  rewind: �ظ���ȡ����
 *  mark: ��¼��ǰλ�ñ��
 *  reset: �ָ���¼�ı��λ��
 *  
 *  �����쳣��
 *  java.nio.BufferOverflowException�� ���������,�����ݴ�С������������С
 *  
 * @author VIC
 *
 */
public class BufferDemo {
	
	public static void main(String[] args) {
		
		//BufferDemo1();
		//BufferDemo2();
		
		// ʹ��allocateDirect()��������ֱ�ӻ�����,ֱ�ӽ�����(����ϵͳ)�����ڴ���,����߶�д����
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
		System.out.println("�Ƿ���ֱ�ӻ�����: " + byteBuffer.isDirect());
		
		System.out.println("*************������д���ݺ���Ϣ****************");
		String value = "buffer";
		byteBuffer.put(value.getBytes());
		printBufferInfo(byteBuffer);
		
		// �л���������ģʽ
		byteBuffer.flip();
		
		System.out.println("*************��ȡ����������****************");
		System.out.println("��ȡ����: " + (char)byteBuffer.get());
		printBufferInfo(byteBuffer);
		
		// ����clear()������ջ�����(ע: ��պ����ݲ�û����ʧ,���ڻ�������,ֻ�Ǵ�������״̬)
		byteBuffer.clear();
		System.out.println("*************��ջ�����****************");
		printBufferInfo(byteBuffer);
		
		System.out.println("*************��ջ��������ȡ����****************");
		System.out.println("��ȡ����: " + (char)byteBuffer.get());
		printBufferInfo(byteBuffer);
		
		
	}

	private static void BufferDemo2() {
		// ������ֱ�ӻ�����,��������С5�ֽ�
		System.out.println("*************��������ʼ����Ϣ****************");
		ByteBuffer byteBuffer = ByteBuffer.allocate(6);
		printBufferInfo(byteBuffer);
		String value = "buffer";
		byteBuffer.put(value.getBytes());
		
		// �л���������ģʽ
		byteBuffer.flip();
		
		System.out.println("*************��ȡ����������****************");
		System.out.println("��ȡ����: " + (char)byteBuffer.get());
		printBufferInfo(byteBuffer);
		
		// ����rewind()�����ظ���ȡ����
		byteBuffer.rewind();
		
		System.out.println("*************�ظ���ȡ����������****************");
		System.out.println("��������: " + (char)byteBuffer.get());
		System.out.println("��������: " + (char)byteBuffer.get());
		printBufferInfo(byteBuffer);
		
		// ����mark()��Ƕ�ȡλ��
		byteBuffer.mark();
		System.out.println("*************��Ǻ��ظ���ȡ����������****************");
		System.out.println("��Ǻ��ȡ����: " + (char)byteBuffer.get());
		System.out.println("��Ǻ��ȡ����: " + (char)byteBuffer.get());
		System.out.println("��Ǻ��ȡ����: " + (char)byteBuffer.get());
		
		// ����reset()�ָ���־λ
		byteBuffer.reset();
		System.out.println("*************�ָ���Ǻ��ȡ����������****************");
		System.out.println("�ָ���Ǻ��ȡ����: " + (char)byteBuffer.get());
		System.out.println("�ָ���Ǻ��ȡ����: " + (char)byteBuffer.get());
		System.out.println("�ָ���Ǻ��ȡ����: " + (char)byteBuffer.get());
	}

	private static void BufferDemo1() {
		// ������ֱ�ӻ�����
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 10);
		System.out.println("*************��������ʼ����Ϣ****************");
		printBufferInfo(byteBuffer);
		
		// �洢���ݵ�������
		byte v = 1;
		byteBuffer.put(v);
		byteBuffer.put(v);
		byteBuffer.put(v);
		byteBuffer.put(v);
		byteBuffer.put(v);
		System.out.println("*************������д�����ݺ���Ϣ****************");
		printBufferInfo(byteBuffer);
		
		// �л���ģʽ
		byteBuffer.flip();
		System.out.println("*************�������л�����ģʽ****************");
		printBufferInfo(byteBuffer);
		
		// �ӻ�������ȡ����
		byte result = byteBuffer.get();
		System.out.println("��ȡ����: " + result);
		System.out.println();
		System.out.println("*************����������ȡ�ݺ���Ϣ****************");
		printBufferInfo(byteBuffer);
	}

	private static void printBufferInfo(ByteBuffer byteBuffer) {
		System.out.println("��ʼ��С: " + byteBuffer.capacity());
		System.out.println("��ǰ(��/д)��ʼλ��: " + byteBuffer.position());
		System.out.println("��(��/д)���ݴ�С: " + byteBuffer.limit());
		System.out.println();
	}

}
