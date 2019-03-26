package cn.asnc.dingcan.view;

import java.util.List;
import java.util.Scanner;

import cn.asnc.dingcan.dao.Num;
import cn.asnc.dingcan.dao.OrdersDao;
import cn.asnc.dingcan.service.DishesService;
import cn.asnc.dingcan.service.OrdersService;
import cn.asnc.dingcan.service.UserService;
import cn.asnc.dingcan.vo.Dishes;
import cn.asnc.dingcan.vo.Orders;

public class ViewUser implements Num{
	int nice=0;
	Scanner in=new Scanner(System.in);
	DishesService ds=new DishesService(); 
	OrdersService os=new OrdersService();
	UserService us=new UserService();
	public void viewUser(String user) {
		System.out.println("***********************************");
		System.out.println("*                                 *");
		System.out.println("*       欢迎进入饿了没用户系统              *");
		System.out.println("*                                 *");
		System.out.println("***********************************");
		System.out.println("*      1.查看菜品信息                             *");
		System.out.println("*      2.我要订餐                                    *");
		System.out.println("*      3.查看订单                                    *");
		System.out.println("*      4.签收订单                                    *");
		System.out.println("*      5.取消订单                                    *");
		System.out.println("*      6.我要点赞                                    *");
		System.out.println("*      7.充值                                           *");
		System.out.println("*      8.返回上一级                                 *");
		System.out.println("***********************************");
		System.out.println(" 请输入您需要的服务：");
		
		String index=in.nextLine();
		switch (index) {
		case "1":
			showMenu();
			break;
		case "2":
			orderDishes(user);
			break;
		case "3":
			showMyOrders(user);
			break;
		case "4":
			doneDeal(user);
			break;
		case "5":
			cancelOrders(user);
			break;
		case "6":
			give666();
			break;
		case "7":
			recharge(user);
			break;
		case "8":
			View view=new View();
			view.admin();
			break;

		default:
			break;
		}
		viewUser(user);
	}
		
/**
 * 显示所有菜品
 */
	public void showMenu() {
		List<Dishes> d=ds.querysAll();
		for(int i=0;i<d.size();i++) {
			System.out.println(d.get(i));
		}
	}
/**
 * 我要订餐
 * @param user
 */
	private void orderDishes(String user) {
		List<Dishes> d=ds.queryAll();
		for(int i=0;i<d.size();i++) {
			System.out.println(d.get(i));
		}
		OrdersDao od=new OrdersDao();
		
		System.out.println("请输入购买的菜品编号：");
		String dishesid = in.next();
		System.out.println("请输入订餐数量：");
		int num=in.nextInt();
		while(num<1 || num>200) {
			System.out.println("请输入正确的数量：");
			num=in.nextInt();
		}
		System.out.println("请输入送餐地点：");
		String address=in.next();
		int index=os.orderDishes(dishesid,user,num,address);
		switch (index) {
		case Success:
			System.out.println("下单成功！您的账户扣除"+od.checkOrderPrice(dishesid, num));
			break;
		case Moneyless:
			System.out.println("账户余额不足，仅剩"+us.showMoney(user)+"元!");
			break;

		default:
			break;
		}
	}
	/**
	 * 查看顾客订单
	 */
	public void showMyOrders(String user) {
		List<Orders> o=os.showMyOrders(user);
		for(int i=0;i<o.size();i++) {
			System.out.println(o.get(i));
		}
	}
	
	/**
	 * 签收订单
	 * @param user
	 */
	public void doneDeal(String user) {
		showMyOrders(user);
		System.out.println("请输入已收到的订单编码：");
		String orderId=in.next();
		int index=os.updateUserOrderState(user,orderId);
		switch (index) {
		case Success:
			System.out.println("已签收！");
			break;
		case NumError:
			System.out.println("您无法签收编号为"+orderId+"的订单记录，请重新输入");
			doneDeal(user);
			break;

		default:
			break;
		}
	}
	/**
	 * 取消订单
	 * @param user
	 */
	public void cancelOrders(String user) {
		showMyOrders(user);
		System.out.println("请输入取消订单编号：");
		String orderId=in.next();
		int index=os.cancelOrders(user,orderId);
		switch (index) {
		case Success:
			System.out.println("取消成功！");
			break;
		case NumError:
			System.out.println("未找到该订单，请重新输入：");
			cancelOrders(user);
			break;

		default:
			break;
		}
	}
	
	/**
	 * 点赞
	 */
	public void give666() {
		showMenu();
		System.out.println("请输入要点赞的菜品编号：");
		String dishesId=in.next();
		int index=ds.niceShoot(dishesId);
		switch (index) {
		case Success:
			System.out.println("点赞成功！感谢您的支持！");
			nice++;
			break;
		case NumError:
			System.out.println("您输入的编号不存在，请重新输入：");
			give666();
			break;

		default:
			break;
		}
	}
	/**
	 * 充值
	 * @param user
	 */
	public void recharge(String user) {
		System.out.println("请输入要充值的数额，本系统只支持20,50,100元：");
		double rechargeMoney=in.nextDouble();
		int index=us.recharge(user,rechargeMoney);
		switch (index) {
		case Success:
			System.out.println("充值成功！");
			break;
		case RechargeError:
			System.out.println("充值失败！");
			break;
		default:
			break;
		}
	}
}
