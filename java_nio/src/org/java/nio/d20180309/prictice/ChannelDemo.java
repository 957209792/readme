package org.java.nio.d20180309.prictice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * NIO ͨ��(Channel): ��ʾ��������˵����ն˻���ն˵������֮���һ������ͨ��,������һ������,��������򴫵����ݡ�
 * 
 * NIO���õ�ͨ��:
 * FileChannel: �ļ�ͨ��
 * DatagreaChannel: ���ݱ�ͨ��(UDP)
 * SocketChannel: �׽���ͨ��(TCP)
 * ServerSocketChannel: �������׽���ͨ��(TCP)
 * 
 * ��ͳIO�л�ȡͨ����ʽ:
 * 	ͨ��InputStream���е�getChannel()��ȡ
 *  ͨ��OutputStream���е�getChannel()��ȡ
 *  ͨ��RandomAccessFile����getChannel()��ȡ
 * 
 * @author VIC
 *
 */
public class ChannelDemo {
	public static void main(String[] args) {
//		iocopyFile();
		ioCopyFileDriact();
	}

	private static void ioCopyFileDriact() {
		FileChannel inFileChannel = null;
		FileChannel outFileChannel = null;
		long start1 = System.currentTimeMillis();
		try {
			//ʹ��FileChannel.open������ȡ/��ͨ��
			inFileChannel = FileChannel.open(Paths.get("D:/tools/javasetup/Office2010_2010_XiTongZhiJia.rar"), StandardOpenOption.READ);
			outFileChannel = FileChannel.open(Paths.get("D:/tools/javasetup/2.rar"), StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.READ);
			//����������(ֱ�ӻ�����)
			MappedByteBuffer inFileBuffer = inFileChannel.map(MapMode.READ_ONLY, 0, inFileChannel.size());
			MappedByteBuffer outFileBuffer = outFileChannel.map(MapMode.READ_WRITE, 0, inFileChannel.size());
			//ֱ���ڻ������������ݶ�д
			byte[] buf = new byte[inFileBuffer.limit()];
			inFileBuffer.get(buf,0,buf.length);
			outFileBuffer.put(buf);
			long end = System.currentTimeMillis();
			System.out.println("ʹ��ʱ�䣺" + (end - start1));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null != inFileChannel) {
				try {
					inFileChannel.close();
					if(null != outFileChannel) {
						outFileChannel.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void iocopyFile() {
		//��������ļ�����
		File inFile = new File("D:/��ֽ/��ͼ��ֽ��ʮ�ģ�/1.jpg");
		File outFile = new File("D:/��ֽ/��ͼ��ֽ��ʮ�ģ�/2.jpg");
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
		inputStream = new FileInputStream(inFile);
		outputStream = new FileOutputStream(outFile);
		//��ȡͨ��channel
		FileChannel inChannel = inputStream.getChannel();
		FileChannel outChannel = outputStream.getChannel();
			
		//����������
		ByteBuffer buffer = ByteBuffer.allocate(inputStream.available());
		//�������ȡ��������
		inChannel.read(buffer);
		//����������Ĭ�ϵ�дģʽ�л�����ģʽ
		buffer.flip();
		//�ӻ�������ȡ����ͨ���ܵ�д����
		outChannel.write(buffer);
		
		//�ر�ͨ��
		inChannel.close();
		outChannel.close();
		//�ر���
		inputStream.close();
		outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
