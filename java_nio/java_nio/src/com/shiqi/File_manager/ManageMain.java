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
											while (true) { 
											System.out.println("请输入您的密码");
											String password2 = sc.nextLine();
												if (password2.equals(password)) {
													System.out.println("请输入新密码");
													String newPassword = sc.nextLine();
													user2.setPassword(newPassword);
													System.out.println("密码修改成功，请重新登录！");
													break;
												} else {
													System.out.println("密码错误！请重新输入密码");
												}
											}
										}
									}
									// 退出到登录界面
									break;
								}
								// 重新登录(B)
								else if ("b".equalsIgnoreCase(select)) {
									break;
								}
								// 管理文件(C)
								else if ("c".equalsIgnoreCase(select)) {
									
									//列出文件路径
//									System.out.println("请输入文件路径");
									File f = new File(pathname);
									File[] files = f.listFiles();
									for (File file : files) {
										System.out.println(file.getName());
									}
									System.out.println("管理菜单");
									System.out.println("**********************************************");
									System.out.println("创建新文件(A)\t创建新目录删除文件(B)\t删除目录重命名文件(C)\t返回上一级(Q)");
									
									//创建新文件(A)
									select = sc.nextLine();
									if ("a".equalsIgnoreCase(select)) {
										System.out.println("请输入文件命名");
										String fileName = sc.nextLine();
										File fa = new File(pathname,fileName);
												try {
													fa.createNewFile();
												} catch (IOException e) {
													e.printStackTrace();
												}
									}
									//创建新目录删除文件(B)
									else if ("b".equalsIgnoreCase(select)) {
										while(true) {
										System.out.println("创建新目录(A)\t删除文件(B)");
										select = sc.nextLine();
										
										//创建新目录(A)
										if("a".equalsIgnoreCase(select)) {
										f.mkdirs();
										System.out.println("创建成功！");
										}//删除文件(B)
										else if("b".equalsIgnoreCase(select)) {
											for (File file : files) {
												System.out.println(file.getName());
											}
											System.out.println("请输入您要删除的文件名");
											String delete=sc.nextLine();
											File deleteFile = new File(pathname,delete);
											deleteFile.delete();
											System.out.println("删除成功!");
										}else {
											System.out.println("非法操作,请重新输入！");
											System.out.println("**************************************");
										}
									}
									}
									//删除目录重命名文件(C)
									else if("c".equalsIgnoreCase(select)) {
										System.out.println("删除目录(A)\t重命名文件(B)");
										//删除目录(A)
										select = sc.nextLine();
										if("a".equalsIgnoreCase(select)) {
											System.out.println("请输入您要删除的目录名");
											String delelist = sc.nextLine();
											File listdele = new File(pathname,delelist);
											delelist(listdele);
										}
										//重命名文件(B)
										else if("b".equalsIgnoreCase(select)) {
											System.out.println("请输入您要重命名的文件名");
											String filename = sc.nextLine();
											File fs = new File(pathname,filename);
											Channel channel = 
										}else {
											System.out.println("非法操作！请重新输入");
										}
									}
									//返回上一级(Q)
									else if("q".equalsIgnoreCase(select)) {
										break;
									}

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

	//删除目录
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
