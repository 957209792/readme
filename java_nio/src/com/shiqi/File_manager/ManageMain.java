package com.shiqi.File_manager;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class ManageMain {
	private static ArrayDeque<User> us = new ArrayDeque<User>();

	public static void main(String[] args) {
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
											user2.getPassword();
										}
									}
								}
								// ���µ�¼(B)
								else if ("b".equalsIgnoreCase(select)) {
									break;
								}
								// �����ļ�(C)
								else if ("c".equalsIgnoreCase(select)) {
									
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
}
