package cn.asnc.dingcan.service;

import java.util.List;

import cn.asnc.dingcan.dao.Num;
import cn.asnc.dingcan.dao.OrdersDao;
import cn.asnc.dingcan.dao.UserDao;
import cn.asnc.dingcan.vo.Orders;

import java.util.ArrayList;

public class OrdersService implements Num{
	
	UserService us=new UserService();
	/**
	 * 查询所有订单
	 * @return
	 */
	public List<Orders> showOrders() {
		OrdersDao od=new OrdersDao();
		return od.selectAllOrders();
	}
	/**
	 * 查询本用户订单
	 * @param user
	 * @return
	 */
	public List<Orders> showMyOrders(String user) {
		OrdersDao od=new OrdersDao();
		return od.selectMyOrders(user);
	}
	/**
	 * 管理员派送已接受订单
	 * @param id 
	 * @return
	 */
	public int sendOrders(String id) {
		OrdersDao od=new OrdersDao();
		if(checkTaskOrderNo(id)) {
			od.updateOrdersState(id);
			return Success;
		}else {
			return NotExist;
		}
	}
	/**
	 * 在所有订单中检查订单是否存在
	 * @param id
	 * @return
	 */
	
	public boolean checkOrderNo(String id) {
		OrdersDao od=new OrdersDao();
		Orders o=od.checkOrdersNo(id);
		int di=Integer.parseInt(id);
		if(di==o.getO_no()) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 用户订单
	 * @param dishesid 菜品编号
	 * @param user 订餐用户
	 * @param num 订餐数量
	 * @param address 订餐地址	
	 * @return int值
	 */
	public int orderDishes(String dishesid, String user,int num, String address) {
		UserDao ud=new UserDao();
		OrdersDao od=new OrdersDao();
		if(od.checkOrderPrice(dishesid,num)>us.showMoney(user)) {
			return Moneyless;
		}else {
			od.insertUserOrders(dishesid,user,num,address);
			ud.updateMoney(user, od.checkOrderPrice(dishesid,num));
			return Success;
		}
	}
/**
 * 用户签收派送中的订单
 * @param user
 * @param id
 * @return
 */
	public int updateUserOrderState(String user,String id) {
		Orders o=new Orders();
		OrdersDao od=new OrdersDao();
		o=od.checkUserOrdersNo(user,id);
		int no=Integer.parseInt(id);
		if(no==o.getO_no() && o.getO_state()==1) {
			od.updateUserOrderState(user,id);
			return Success;
		}else {
			return NumError;
		}
	}
/**
 * 用户取消订单
 * @param user
 * @param orderId
 * @return
 */
	public int cancelOrders(String user, String orderId) {
		Orders o=new Orders();
		OrdersDao od=new OrdersDao();
		o=od.checkUserOrdersNo(user,orderId);
		int di=Integer.parseInt(orderId);
		if(di==o.getO_no()) {
			od.deleteOrders(user,orderId);
			return Success;
		}else {
			return NumError;
		}
	}
	/**
	 * 遍历未派送的订单
	 * @return
	 */
	public List<Orders> showTaskOrders() {
		OrdersDao od=new OrdersDao();
		return od.selectTaskOrders();
	}
	/**
	 * 在未派送列表中检查订单是否存在
	 * @param id
	 * @return
	 */
	public boolean checkTaskOrderNo(String id) {
		OrdersDao od=new OrdersDao();
		Orders o=od.checkTaskOrdersNo(id);
		int i=Integer.parseInt(id);
		if(i == o.getO_no()) {
			return true;
		}else {
			return false;
		}
	}
}
