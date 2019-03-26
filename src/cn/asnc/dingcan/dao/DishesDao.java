package cn.asnc.dingcan.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.asnc.dingcan.util.DBUtil;
import cn.asnc.dingcan.vo.Dishes;

public class DishesDao {
	/**
	 * 读出数据库中dishes表中所有记录
	 * @return
	 */
	public List<Dishes> selectAllDishes() {
		List<Dishes> dishes=new ArrayList<>();
		ResultSet rs = null;
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="select * from dishes";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Dishes d=new Dishes();
				d.setG_no(rs.getInt(1));
				d.setG_name(rs.getString(2));
				d.setG_price(rs.getDouble(3));
				d.setG_like_num(rs.getInt(4));
				d.setG_state(rs.getInt(5));
				dishes.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dishes;
		
	}
	/**
	 * 读出数据库中dishes表中热销记录
	 * @return
	 */
	public List<Dishes> selectAllsDishes() {
		List<Dishes> dishes=new ArrayList<>();
		ResultSet rs = null;
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="select * from dishes where g_state=0";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Dishes d=new Dishes();
				d.setG_no(rs.getInt(1));
				d.setG_name(rs.getString(2));
				d.setG_price(rs.getDouble(3));
				d.setG_like_num(rs.getInt(4));
				d.setG_state(rs.getInt(5));
				dishes.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dishes;
		
	}
	/**
	 * 将菜品信息写入dishes表中
	 * @param name
	 * @param price
	 */
	public void insertDishes(String name,double price) {
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="insert into dishes values (g_seq.nextval,'"+name+"',"+price+",0,0)";
			stmt.executeUpdate(sql);
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 System.out.println("数据库错误!");
			e.printStackTrace();
		}
	}
	/**
	 * 在dishes表中按菜品名称查找指定记录
	 * @param name
	 * @return
	 */
	public Dishes selectDishesBYName(String name) {
		Dishes d=new Dishes();
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="select * from dishes where g_name='"+name+"'";
			ResultSet rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				d.setG_no(rs.getInt(1));
				d.setG_name(rs.getString(2));
				d.setG_price(rs.getDouble(3));
				d.setG_like_num(rs.getInt(4));
				d.setG_state(rs.getInt(5));
			}
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	/**
	 * 修改dishes表中按菜品编号修改g_state状态信息
	 * @param id
	 */
	public void updateDishes(String id) {
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="update dishes set g_state=decode(g_state,0,1,1,0) where g_no="+id;
			stmt.executeUpdate(sql);
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 System.out.println("数据库错误!");
			e.printStackTrace();
		}
	}
	/**
	 * 在dishes表中按菜品编号删除指定记录
	 * @param id
	 */
	public void deleteDishes(String id) {
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="update dishes set g_state=1 where g_no="+id;
			stmt.executeUpdate(sql);
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库错误!");
			e.printStackTrace();
		}
	}
/**
 * 在dishes表中将g_like_num加1
 * @param dishesId
 */
	public void updateDishesLikeNum(String dishesId) {
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="update dishes set g_like_num=(g_like_num+1) where g_no="+dishesId;
			stmt.executeUpdate(sql);
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库错误!");
			e.printStackTrace();
		}
	}
	/**
	 * 在dishes表中查找指定菜品编号的记录
	 * @param dishesId
	 * @return
	 */
	public Dishes checkDishesNoIsExist(String dishesId) {
		Dishes d=new Dishes();
		try {
			Connection conn=DBUtil.getconn();
			Statement stmt=conn.createStatement();
			String sql="select * from dishes where g_no="+dishesId;
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				d.setG_no(rs.getInt(1));
				d.setG_name(rs.getString(2));
				d.setG_price(rs.getDouble(3));
				d.setG_like_num(rs.getInt(4));
				d.setG_state(rs.getInt(5));
			}
			DBUtil.closeAll(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
}
