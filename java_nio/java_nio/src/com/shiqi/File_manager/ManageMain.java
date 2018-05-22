package com.shiqi.File_manager;

import java.io.File;
import java.io.IOException;
import java.nio.channels.Channel;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class ManageMain {
	private static ArrayDeque<User> us = new ArrayDeque<User>();

	public static void main(String[] args) {
		String pathname = "D:\\Java\\java\\java3\\java_nio";
		System.out.println("*****************************************");
		System.out.println("*                                       *");
		System.out.println("*                                       *");
		System.out.println("*                                       *");
		System.out.println("*                                       *");
		System.out.println("*               �ļ�����ϵͳ                           *");
		System.out.println("*                                       *");
		System.out.println("*                                       *");
		System.out.println("*                                       *");
		System.out.println("*                                       *");
		System.out.println("*                                       *");
		System.out.println("*****************************************");
		Random random = new Random();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("��ѡ���ܣ��û���¼(A)\t�û�ע��(B)\tע��(Q)");

			User user = new User();
			String select = sc.nextLine();
			// �û���¼(A)
			if ("a".equalsIgnoreCase(select)) {
				// �˺�
				System.out.println("�����������û��˺�");
				String name = sc.nextLine();
				// ѭ������
				for (User arrayDeque : us) {
					if (name.equals(arrayDeque.getName())) {
						System.out.println("����������");
						String password = sc.nextLine();
						// ����
						if (password.equals(arrayDeque.getPassword())) {
							// ��¼�ɹ�
							System.out.println("��ӭ��¼�ļ�����ϵͳ��");
							System.out.println("*************************************");
							while (true) {
								System.out.println("�޸�����(A)\t���µ�¼(B)\t�����ļ�(C)\t�˳�ϵͳ(Q)");

								// �޸�����(A)
								select = sc.nextLine();
								if ("a".equalsIgnoreCase(select)) {
									System.out.println("����������Ҫ�޸�������û���");
									name = sc.nextLine();
									// ѭ������
									for (User user2 : us) {
										if (name.equals(user2.getName())) {
											while (true) { 
											System.out.println("��������������");
											String password2 = sc.nextLine();
												if (password2.equals(password)) {
													System.out.println("������������");
													String newPassword = sc.nextLine();
													user2.setPassword(newPassword);
													System.out.println("�����޸ĳɹ��������µ�¼��");
													break;
												} else {
													System.out.println("���������������������");
												}
											}
										}
									}
									// �˳�����¼����
									break;
								}
								// ���µ�¼(B)
								else if ("b".equalsIgnoreCase(select)) {
									break;
								}
								// �����ļ�(C)
								else if ("c".equalsIgnoreCase(select)) {
									
									//�г��ļ�·��
//									System.out.println("�������ļ�·��");
									File f = new File(pathname);
									File[] files = f.listFiles();
									for (File file : files) {
										System.out.println(file.getName());
									}
									System.out.println("����˵�");
									System.out.println("**********************************************");
									System.out.println("�������ļ�(A)\t������Ŀ¼ɾ���ļ�(B)\tɾ��Ŀ¼�������ļ�(C)\t������һ��(Q)");
									
									//�������ļ�(A)
									select = sc.nextLine();
									if ("a".equalsIgnoreCase(select)) {
										System.out.println("�������ļ�����");
										String fileName = sc.nextLine();
										File fa = new File(pathname,fileName);
												try {
													fa.createNewFile();
												} catch (IOException e) {
													e.printStackTrace();
												}
									}
									//������Ŀ¼ɾ���ļ�(B)
									else if ("b".equalsIgnoreCase(select)) {
										while(true) {
										System.out.println("������Ŀ¼(A)\tɾ���ļ�(B)");
										select = sc.nextLine();
										
										//������Ŀ¼(A)
										if("a".equalsIgnoreCase(select)) {
										f.mkdirs();
										System.out.println("�����ɹ���");
										}//ɾ���ļ�(B)
										else if("b".equalsIgnoreCase(select)) {
											for (File file : files) {
												System.out.println(file.getName());
											}
											System.out.println("��������Ҫɾ�����ļ���");
											String delete=sc.nextLine();
											File deleteFile = new File(pathname,delete);
											deleteFile.delete();
											System.out.println("ɾ���ɹ�!");
										}else {
											System.out.println("�Ƿ�����,���������룡");
											System.out.println("**************************************");
										}
									}
									}
									//ɾ��Ŀ¼�������ļ�(C)
									else if("c".equalsIgnoreCase(select)) {
										System.out.println("ɾ��Ŀ¼(A)\t�������ļ�(B)");
										//ɾ��Ŀ¼(A)
										select = sc.nextLine();
										if("a".equalsIgnoreCase(select)) {
											System.out.println("��������Ҫɾ����Ŀ¼��");
											String delelist = sc.nextLine();
											File listdele = new File(pathname,delelist);
											delelist(listdele);
										}
										//�������ļ�(B)
										else if("b".equalsIgnoreCase(select)) {
											System.out.println("��������Ҫ���������ļ���");
											String filename = sc.nextLine();
											File fs = new File(pathname,filename);
											Channel channel = 
										}else {
											System.out.println("�Ƿ�����������������");
										}
									}
									//������һ��(Q)
									else if("q".equalsIgnoreCase(select)) {
										break;
									}

								}
								// �˳�ϵͳ(Q)
								else if ("q".equalsIgnoreCase(select)) {
									System.exit(0);
								} else {
									System.out.println("�����Ƿ���");
									System.out.println("****************************************");
								}
							}
						}
					}
				}

			}

			// �û�ע��(B)
			else if ("b".equalsIgnoreCase(select)) {
				System.out.println("�����������˺�");
				String name = sc.nextLine();
				System.out.println("��������������");
				String password = sc.nextLine();
				System.out.println("���ٴ�������������");
				String password2 = sc.nextLine();
				if (password.equals(password2)) {

					us.add(new User(name, password));
					System.out.println("ע��ɹ���");
					System.out.println("*************************************");
				} else {
					System.out.println("ע��ʧ�ܣ�");
					System.out.println("*************************************");
					break;
				}

			}
			// ע��(Q)
			else if ("q".equalsIgnoreCase(select)) {
				System.exit(0);
				System.out.println("ע���ɹ�, ��ӭ�´�ʹ�ã�");
			}
		}
	}

	//ɾ��Ŀ¼
	private static void delelist(File fl) {
		
		if(fl.isDirectory()) {
			File[] files = fl.listFiles();
			for(File fa : files) {
				delelist(fa);
			}
		}
		fl.delete();
	}
	

}
