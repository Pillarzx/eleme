package cn.asnc.dingcan.vo;

public class Orders {
	private int o_no;
	private String u_name;
	private String g_name;
	private double g_price;
	private int o_num;
	private String o_time;
	private String o_address;
	private double o_totle;
	private int o_state;
	public int getO_no() {
		return o_no;
	}
	public void setO_no(int o_no) {
		this.o_no = o_no;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public double getG_price() {
		return g_price;
	}
	public void setG_price(double g_price) {
		this.g_price = g_price;
	}
	public int getO_num() {
		return o_num;
	}
	public void setO_num(int o_num) {
		this.o_num = o_num;
	}
	public String getO_time() {
		return o_time;
	}
	public void setO_time(String o_time) {
		this.o_time = o_time;
	}
	public String getO_address() {
		return o_address;
	}
	public void setO_address(String o_address) {
		this.o_address = o_address;
	}
	public double getO_totle() {
		return o_totle;
	}
	public void setO_totle(double o_totle) {
		this.o_totle = o_totle;
	}
	public int getO_state() {
		return o_state;
	}
	public void setO_state(int o_state) {
		this.o_state = o_state;
	}
	@Override
	public String toString() {
		String state;
		if(o_state==0) {
			state="商家已接单";
		}else if(o_state==1) {
			state="派送中。。。";
		}else {
			state="已完成";
		}
		return "\t订单编号：" + o_no + "\t用户名：" + u_name + "\t菜名：" + g_name + "\t单价：" + g_price
				+ "\t数量：" + o_num + "\t送餐时间：" + o_time + "\t送餐地点：" + o_address + "\t总价：" + o_totle
				+ "\t"+state;
	}
	
	
}
