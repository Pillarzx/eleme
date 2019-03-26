package cn.asnc.dingcan.service;

import cn.asnc.dingcan.dao.Num;
import cn.asnc.dingcan.dao.UserDao;
import cn.asnc.dingcan.vo.Users;

public class UserService implements Num{
	/**
	 * µÇÂ¼Âß¼­
	 * @param user
	 * @param pass
	 * @return
	 */
	public int login(String user,String pass) {
		UserDao ud=new UserDao();
		Users userspass=ud.selectByNamePass(user,pass);
		Users users=ud.selectByName(user);
		if(userspass.getU_name()!=null) {
			if(userspass.getU_role()==0) {
				return Admin;
			}else {
				return User;
			}
		}else if(users.getU_name()!=null) {
			return PassWrong;
		}else {
			return UserWrong;
		}
	}
	/**
	 * ×¢²áÂß¼­
	 * @param user
	 * @param pass
	 * @param tell
	 * @return
	 */
	public int register(String user,String pass,String tell) {
		UserDao ud=new UserDao();
		if(checkName(user)) {
			return Exist;
		}
		else if(pass.length()<8 ||pass.length()>16){
			return Passerror;
		}
		else if(tell.length()!=11) {
			return Tellerror;
		}
		else {
			long tells=Long.parseLong(tell);
			ud.insertUsers(user, pass, tells);
			return Success;
		}
		
	}
	/**
	 * ¼ì²éĞÕÃûÊÇ·ñ´æÔÚ
	 * @param user
	 * @return
	 */
	public boolean checkName(String user) {
		UserDao userDao=new UserDao();
		Users u=userDao.selectByName(user);
		if(u.getU_name()!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * ²éÑ¯Óà¶î
	 */
	public double showMoney(String name) {
		UserDao ud=new UserDao();
		double money=ud.selectByMoney(name);
		return money;
		
	}
/**
 * ÓÃ»§³äÖµ
 * @param user
 * @param rechargeMoney
 * @return
 */
	public int recharge(String user, double rechargeMoney) {
		UserDao ud=new UserDao();
		if(rechargeMoney==20 || rechargeMoney==50 || rechargeMoney==100) {
			ud.updateUserMoney(user,rechargeMoney);
			return Success;
		}else {
			return RechargeError;
		}
		
	}
}
