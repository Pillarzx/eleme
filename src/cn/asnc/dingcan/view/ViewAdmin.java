package cn.asnc.dingcan.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cn.asnc.dingcan.dao.Num;
import cn.asnc.dingcan.service.DishesService;
import cn.asnc.dingcan.service.OrdersService;
import cn.asnc.dingcan.vo.Dishes;
import cn.asnc.dingcan.vo.Orders;

public class ViewAdmin implements Num{
	Scanner in=new Scanner(System.in);
	DishesService ds=new DishesService();
	OrdersService os=new OrdersService();
	public void viewAdmin() {
		System.out.println("***********************************");
		System.out.println("*                                 *");
		System.out.println("*       欢迎进入饿了没管理员系统          *");
		System.out.println("*                                 *");
		System.out.println("***********************************");
		System.out.println("*        1.查看菜单信息                           *");
		System.out.println("*        2.录入菜品                                  *");
		System.out.println("*        3.修改菜品                                  *");
		System.out.println("*        4.查看顾客订单                           *");
		System.out.println("*        5.删除菜单                                  *");
		System.out.println("*        6.配送菜单                                  *");
		System.out.println("*        7.返回上一级                               *");
		System.out.println("***********************************");
		System.out.println(" 请输入您需要的服务：");

		String index=in.nextLine();
		switch (index) {
		case "1":
			showMenu();
			break;
		case "2":
			setMenu();
			break;
		case "3":
			updateDishes();
			break;
		case "4":
			showOrders();
			break;
		case "5":
			deleteDishes();
			break;
		case "6":
			sendOrders();
			break;
		case "7":
			View view=new View();
			view.admin();
			break;

		default:
			break;
		}
		viewAdmin();
	}
/**
 * 查看菜单列表
 */
	public void showMenu() {
		List<Dishes> d=ds.queryAll();
		for(int i=0;i<d.size();i++) {
			System.out.println(d.get(i));
		}
	}
	/**
	 * 录入菜品 
	 */
	public void setMenu() {
		System.out.println("请输入菜品名称：");
		String name=in.nextLine();
		System.out.println("请输入菜品价格：");
		double price=in.nextDouble();
		
		int index=ds.setDishes(name, price);
		switch (index) {
		case Success:
			System.out.println("录入成功！");
			break;
		case Exist:
			System.out.println("菜品已存在！");
			break;
		case None:
			System.out.println("error！");
			break;

		default:
			break;
		}
	}
	/**
	 * 修改菜品
	 */
	public void updateDishes() {
		showMenu();
		System.out.println("请输入修改的菜品编号：");
		String id=in.nextLine();
		int index=ds.updateDishes(id);
		switch (index) {
		case Success:
			System.out.println("修改成功！");
			break;
		case NumError:
			System.out.println("菜品编号不存在，请重新输入！");
			updateDishes();
			break;

		default:
			break;
		}
	}
	/**
	 * 删除菜品
	 */
	public void deleteDishes() {
		showMenu();
		System.out.println("请输入要删除的菜品编号：");
		String id=in.nextLine();
		int index=ds.deleteDishes(id);
		switch (index) {
		case Success:
			System.out.println("删除成功！");
			break;
		case NumError:
			System.out.println("菜品编号不存在，请重新输入！");
			deleteDishes();
			break;

		default:
			break;
		}
	}
	
	/**
	 * 查看顾客订单
	 */
	public void showOrders() {
		List<Orders> o=os.showOrders();
		for(int i=0;i<o.size();i++) {
			System.out.println(o.get(i));
		}
	}
	/**
	 * 配送菜单
	 */
	public void sendOrders() {
		List<Orders> o=os.showTaskOrders();
		for(int i=0;i<o.size();i++) {
			System.out.println(o.get(i));
		}
		System.out.println("请输入派送订单编号：");
		String id=in.nextLine();
		if(os.sendOrders(id)==Success) {
			System.out.println(id+" 订单派送中。。。");
		}else {
			System.out.println(id+"订单号不存在！");
		}
	}
}
