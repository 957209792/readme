package org.java.nio.d20180309.prictice;

import java.nio.ByteBuffer;

/**
 * NIO������ʾ��
 * NIO Buffer: �Ǵ洢���ݵĵط�,�������ݽ����ݴӻ����ȡ��������ݽ����ݴӻ�����д��
 * 
 *  ����������
 *  ��ֱ�ӻ�����: ������JVM�еĻ�����
 *   ֱ�ӻ�����:  ������OS�еĻ�����
 * 
 *  ������ʵ����:
 *  ByteBuffer: �ֽڻ�����
 *  CharBuffer: �ַ�������
 *  DoubleBuffer: ˫���ȸ��㻺����
 *  FloatBuffer: �����ȸ��㻺����
 *  ShortBuffer: �����ͻ�����
 *  LongBuffer: �����ͻ�����
 *  IntBuffer: ���ͻ�����
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
 *  
 *  
 *  
 * @author VIC
 *
 */
public class BufferDemo {
public static void main(String[] args) {
	//����������
	ByteBuffer byteBuffer = ByteBuffer.allocate(5);
	System.out.println("*********************��������ʼ����Ϣ********************");
	printBufferInfo(byteBuffer);
	
	//�洢��д�����ݵ�������
	//дģʽ�£���Ϊ�����ظ�����д�����ݣ����Գ�ʼ��������С�Ƕ��٣��Ϳ���д�����
//	byte v = 1;
	String value = "abcde";
	byteBuffer.put(value.getBytes());
//	byteBuffer.put(v);
//	byteBuffer.put(v);
//	byteBuffer.put(v);
//	byteBuffer.put(v);
//	byteBuffer.put(v);
	System.out.println("********������д���������Ϣ**********");
	printBufferInfo(byteBuffer);
	
	//�л���ģʽ
	byteBuffer.flip();
	System.out.println("***********�л�����дģʽ*****************");
	printBufferInfo(byteBuffer);
	
	//�ӻ�������ȡ����
	//��ģʽ�£��������д��˶��ٸ��ֽ����ݣ���limit()�鿴�Ŀɶ����ݾ��Ƕ���
	byte result = byteBuffer.get();
	System.out.println("��ȡ���ݣ�" + new String(new byte[]{result}));
	printBufferInfo(byteBuffer);
}


//��������ʼ����Ϣ����
private static void printBufferInfo(ByteBuffer byteBuffer) {
	System.out.println("��ʼ��С��" + byteBuffer.capacity());
	System.out.println("��ǰ����/д����ʼλ��" + byteBuffer.position());
	System.out.println("��(��/д)���ݴ�С" + byteBuffer.limit());
	System.out.println();
}
}
