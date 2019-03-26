package cn.asnc.dingcan.dao;

import java.util.List;

import cn.asnc.dingcan.util.DBUtil;
import cn.asnc.dingcan.vo.Orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrdersDao {
	/**
	 * 读取出所有Orders
	 * @return
	 */
	
	public List<Orders> selectAllOrders() {
		List<Orders> oList=new ArrayList<>();
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="SELECT orders.o_no,users.u_name,dishes.g_name,dishes.g_price,orders.o_num,orders.o_time,orders.o_address,orders.o_num*dishes.g_price,orders.o_state FROM users,dishes,orders WHERE orders.u_no=users.u_no AND orders.g_no=dishes.g_no";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Orders o=new Orders();
				o.setO_no(rs.getInt(1));
				o.setU_name(rs.getString(2));
				o.setG_name(rs.getString(3));
				o.setG_price(rs.getDouble(4));
				o.setO_num(rs.getInt(5));
				o.setO_time(rs.getString(6));
				o.setO_address(rs.getString(7));
				o.setO_totle(rs.getDouble(8));
				o.setO_state(rs.getInt(9));
				oList.add(o);
			}
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oList;
	}
/**
 * 读取指定用户的Orders
 * @param user
 * @return
 */
	public List<Orders> selectMyOrders(String user) {
		List<Orders> oList=new ArrayList<>();
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="SELECT orders.o_no,users.u_name,dishes.g_name,dishes.g_price,orders.o_num,orders.o_time,orders.o_address,orders.o_num*dishes.g_price,orders.o_state FROM users,dishes,orders WHERE orders.u_no=users.u_no AND orders.g_no=dishes.g_no and users.u_name='"+user+"'";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Orders o=new Orders();
				o.setO_no(rs.getInt(1));
				o.setU_name(rs.getString(2));
				o.setG_name(rs.getString(3));
				o.setG_price(rs.getDouble(4));
				o.setO_num(rs.getInt(5));
				o.setO_time(rs.getString(6));
				o.setO_address(rs.getString(7));
				o.setO_totle(rs.getDouble(8));
				o.setO_state(rs.getInt(9));
				oList.add(o);
			}
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oList;
	}
	/**
	 * 管理员将订单置为派送中
	 * @param id
	 */
	public void updateOrdersState(String id) {
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="update orders set o_state=1 where o_no="+id;
			stmt.executeUpdate(sql);
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 按订单编号查询
	 * @param id
	 * @return
	 */
	public Orders checkOrdersNo(String id) {
		Orders o=new Orders();
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="SELECT orders.o_no,users.u_name,dishes.g_name,dishes.g_price,orders.o_num,orders.o_time,orders.o_address,orders.o_num*dishes.g_price,orders.o_state FROM users,dishes,orders WHERE orders.u_no=users.u_no AND orders.g_no=dishes.g_no and orders.o_no="+id;
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				o.setO_no(rs.getInt(1));
				o.setU_name(rs.getString(2));
				o.setG_name(rs.getString(3));
				o.setG_price(rs.getDouble(4));
				o.setO_num(rs.getInt(5));
				o.setO_time(rs.getString(6));
				o.setO_address(rs.getString(7));
				o.setO_totle(rs.getDouble(8));
				o.setO_state(rs.getInt(9));
			}
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
/**
 * 写入用户订餐信息
 * @param dishesid 所订菜品编号
 * @param user 订餐人姓名
 * @param num 订餐数量
 * @param address 地址
 */
	public void insertUserOrders(String dishesid, String user,int num, String address) {
		int userNo=0;
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			//==========================
			/**
			 * 查找user对应编号
			 */
			String sql0="select u_no from users where u_name='"+user+"'";
			ResultSet rs=stmt.executeQuery(sql0);
			while(rs.next()) {
				userNo=rs.getInt(1);
			}
			//===========================

			String sql="insert into orders values (o_seq.nextval,"+userNo+","+dishesid+","+num+",Sysdate,'"+address+"',0)";
			stmt.executeUpdate(sql);
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询单个指定菜品单价
	 * @param dishesid
	 * @return
	 */
	public double checkOrderPrice(String dishesid,int num) {
		double price = 0;
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="select g_price from dishes where g_no="+dishesid;
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				price=rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return price*num;
	}
/**
 * 指定用户签收指定订单
 * @param user 用户名
 * @param orderId 要签收的订单编号
 */
	public void updateUserOrderState(String user,String orderId) {
		int userNo=0;
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			//==========================
			/**
			 * 查找user对应编号
			 */
			String sql0="select u_no from users where u_name='"+user+"'";
			ResultSet rs=stmt.executeQuery(sql0);
			while(rs.next()) {
				userNo=rs.getInt(1);
			}
			//===========================
			String sql="update orders set o_state=2 where u_no="+userNo+" and o_no="+orderId;
			stmt.executeUpdate(sql);
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
/**
 * 删除指定订单记录
 * @param user
 * @param orderId
 */
	public void deleteOrders(String user, String orderId) {
		int userNo=0;
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			//==========================
			/**
			 * 查找user对应编号
			 */
			String sql0="select u_no from users where u_name='"+user+"'";
			ResultSet rs0=stmt.executeQuery(sql0);
			while(rs0.next()) {
				userNo=rs0.getInt(1);
			}
			//===========================
			String sql="delete from orders where u_no="+userNo+" and o_no="+orderId;
			stmt.executeUpdate(sql);
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
/**
 * 查询指定用户的编号是否存在Orders表中
 * @param user
 * @param id
 * @return
 */
	public Orders checkUserOrdersNo(String user, String id) {
		Orders o=new Orders();
		int userNo=0;
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			//==========================
			/**
			 * 查找user对应编号
			 */
			String sql0="select u_no from users where u_name='"+user+"'";
			ResultSet rs0=stmt.executeQuery(sql0);
			while(rs0.next()) {
				userNo=rs0.getInt(1);
			}
			//===========================
			String sql="SELECT orders.o_no,users.u_name,dishes.g_name,dishes.g_price,orders.o_num,orders.o_time,orders.o_address,orders.o_num*dishes.g_price,orders.o_state FROM users,dishes,orders WHERE orders.u_no=users.u_no AND orders.g_no=dishes.g_no and orders.u_no="+userNo+" and o_no="+id;
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				o.setO_no(rs.getInt(1));
				o.setU_name(rs.getString(2));
				o.setG_name(rs.getString(3));
				o.setG_price(rs.getDouble(4));
				o.setO_num(rs.getInt(5));
				o.setO_time(rs.getString(6));
				o.setO_address(rs.getString(7));
				o.setO_totle(rs.getDouble(8));
				o.setO_state(rs.getInt(9));
			}
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
/**
 * 管理员查询所有已接受订单
 * @return
 */
	public List<Orders> selectTaskOrders() {
		List<Orders> oList=new ArrayList<>();
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="SELECT orders.o_no,users.u_name,dishes.g_name,dishes.g_price,orders.o_num,orders.o_time,orders.o_address,orders.o_num*dishes.g_price,orders.o_state FROM users,dishes,orders WHERE orders.u_no=users.u_no AND orders.g_no=dishes.g_no and orders.o_state=0";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				Orders o=new Orders();
				o.setO_no(rs.getInt(1));
				o.setU_name(rs.getString(2));
				o.setG_name(rs.getString(3));
				o.setG_price(rs.getDouble(4));
				o.setO_num(rs.getInt(5));
				o.setO_time(rs.getString(6));
				o.setO_address(rs.getString(7));
				o.setO_totle(rs.getDouble(8));
				o.setO_state(rs.getInt(9));
				oList.add(o);
			}
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oList;
	}
/**
 * 检查指定已签收订单编号是否存在于Orders表中
 * @param id
 * @return
 */
	public Orders checkTaskOrdersNo(String id) {
		Orders o=new Orders();
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="SELECT orders.o_no,users.u_name,dishes.g_name,dishes.g_price,orders.o_num,orders.o_time,orders.o_address,orders.o_num*dishes.g_price,orders.o_state FROM users,dishes,orders WHERE orders.u_no=users.u_no AND orders.g_no=dishes.g_no and orders.o_state=0 and orders.o_no="+id;
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				o.setO_no(rs.getInt(1));
				o.setU_name(rs.getString(2));
				o.setG_name(rs.getString(3));
				o.setG_price(rs.getDouble(4));
				o.setO_num(rs.getInt(5));
				o.setO_time(rs.getString(6));
				o.setO_address(rs.getString(7));
				o.setO_totle(rs.getDouble(8));
				o.setO_state(rs.getInt(9));
			}
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}
	
}
