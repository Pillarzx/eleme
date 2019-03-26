package cn.asnc.dingcan.view;

import java.util.Scanner;

import cn.asnc.dingcan.dao.Num;
import cn.asnc.dingcan.service.UserService;

public class View implements Num{
	
	Scanner in=new Scanner(System.in);
	String user;
	String pass;
	UserService us=new UserService();
	public void admin() {
		
		System.out.println("****************************************");
		System.out.println("*                                      *");
		System.out.println("*                                      *");
		System.out.println("*          欢迎进入饿了没系统                                                 *");
		System.out.println("*                                      *");
		System.out.println("*                                      *");
		System.out.println("****************************************");
		System.out.println("*       1登陆           2注册           3退出                                         *");
		System.out.println("****************************************");
		
		String index=in.nextLine();
		switch (index) {
		case "1":
			//登陆
			login();
			break;
		case "2":
			//注册
			register();
			break;
		case "3":
			//退出
			System.exit(1);
			break;

		default:
			break;
		}
		
	}
	/**
	 * 用户登录
	 */
	public void login() {
		System.out.println("请输入用户名：");
		user=in.nextLine();
		System.out.println("请输入密码：");
		pass=in.nextLine();
		
		switch (us.login(user, pass)) {
		case Admin:
			System.out.println("管理员登录成功!!");
			ViewAdmin vAdmin=new ViewAdmin();
			vAdmin.viewAdmin();
			break;
		case User:
			System.out.println("用户登录成功!!");
			ViewUser vUser=new ViewUser();
			vUser.viewUser(user);
			break;
		case UserWrong:
			System.out.println("账户不存在!!");
			login();
			break;
		case PassWrong:
			System.out.println("密码错误!!");
			login();
			break;

		default:
			break;
		}
	}
	/**
	 * 用户注册
	 */
	public void register() {
		
		System.out.println("请输入用户名：");
		String user=in.nextLine();
		System.out.println("请输入密码：");
		String pass=in.nextLine();
		System.out.println("请输入手机号：");
		String tell=in.nextLine();
		
		switch (us.register(user,pass,tell)) {
		case Success:
			System.out.println("注册成功！");
			admin();
			break;
		case Exist:
			System.out.println("账户已存在！");
			register();
			break;
		case Passerror:
			System.out.println("密码不合理！");
			register();
			break;
		case Tellerror:
			System.out.println("电话号码不正确！");
			register();
			break;
		default:
			break;
		}
	}
}
