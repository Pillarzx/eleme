package cn.asnc.dingcan.dao;

/**
 * 用于分辨Service类返回值，View和Service类均实现Num接口
 * @author Administrator
 *
 */

public interface Num {
	public final int None=0;
	public final int User=1;
	public final int Admin=2;
	public final int Exist=3;
	public final int Tellerror=4;
	public final int Passerror=5;
	public final int Success=6;
	public final int NotExist=7;
	public final int PassWrong=8;
	public final int UserWrong=9;
	public final int Moneyless=10;
	public final int RechargeError=11;
	public final int NumError=12;
	
}
