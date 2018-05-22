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
		System.out.println("*               文件管理系统                           *");
		System.out.println("*                                       *");
		System.out.println("*                                       *");
		System.out.println("*                                       *");
		System.out.println("*                                       *");
		System.out.println("*                                       *");
		System.out.println("*****************************************");
		Random random = new Random();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("请选择功能：用户登录(A)\t用户注册(B)\t注销(Q)");

			User user = new User();
			String select = sc.nextLine();
			// 用户登录(A)
			if ("a".equalsIgnoreCase(select)) {
				// 账号
				System.out.println("请输入您的用户账号");
				String name = sc.nextLine();
				// 循环集合
				for (User arrayDeque : us) {
					if (name.equals(arrayDeque.getName())) {
						System.out.println("请输入密码");
						String password = sc.nextLine();
						// 密码
						if (password.equals(arrayDeque.getPassword())) {
							// 登录成功
							System.out.println("欢迎登录文件管理系统！");
							System.out.println("*************************************");
							while (true) {
								System.out.println("修改密码(A)\t重新登录(B)\t管理文件(C)\t退出系统(Q)");

								// 修改密码(A)
								select = sc.nextLine();
								if ("a".equalsIgnoreCase(select)) {
									System.out.println("请输入您想要修改密码的用户名");
									name = sc.nextLine();
									// 循环集合
									for (User user2 : us) {
										if (name.equals(user2.getName())) {
											user2.getPassword();
										}
									}
								}
								// 重新登录(B)
								else if ("b".equalsIgnoreCase(select)) {
									break;
								}
								// 管理文件(C)
								else if ("c".equalsIgnoreCase(select)) {
									
								}
								// 退出系统(Q)
								else if ("q".equalsIgnoreCase(select)) {
									System.exit(0);
								} else {
									System.out.println("操作非法！");
									System.out.println("****************************************");
								}
							}
						}
					}
				}

			}

			// 用户注册(B)
			else if ("b".equalsIgnoreCase(select)) {
				System.out.println("请输入您的账号");
				String name = sc.nextLine();
				System.out.println("请输入您的密码");
				String password = sc.nextLine();
				System.out.println("请再次输入您的密码");
				String password2 = sc.nextLine();
				if (password.equals(password2)) {

					us.add(new User(name, password));
					System.out.println("注册成功！");
					System.out.println("*************************************");
				} else {
					System.out.println("注册失败！");
					System.out.println("*************************************");
					break;
				}

			}
			// 注销(Q)
			else if ("q".equalsIgnoreCase(select)) {
				System.exit(0);
				System.out.println("注销成功, 欢迎下次使用！");
			}
		}
	}
}
