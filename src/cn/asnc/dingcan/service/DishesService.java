package cn.asnc.dingcan.service;

import java.util.List;

import cn.asnc.dingcan.dao.DishesDao;
import cn.asnc.dingcan.dao.Num;
import cn.asnc.dingcan.vo.Dishes;

public class DishesService implements Num{
	
	DishesDao dd=new DishesDao();
	/**
	 * 管理员查询所有菜品
	 * @return
	 */
	public List<Dishes> queryAll() {
		return dd.selectAllDishes();
		
	}
	/**
	 * 用户查询热销中菜品
	 * @return
	 */
	public List<Dishes> querysAll() {
		return dd.selectAllsDishes();
		
	}
	/**
	 * 录入菜品信息
	 * @param name
	 * @param price
	 * @return
	 */
	public int setDishes(String name,double price) {
		if(checkDishesName(name)==true) {
			return Exist;
		}else{
			dd.insertDishes(name, price);
			return Success;
		}
		
	}
	/**
	 * 检查菜品名称是否存在
	 * @param name
	 * @return
	 */
	public boolean checkDishesName(String name) {
		Dishes d=dd.selectDishesBYName(name);
		if(name.equals(d.getG_name())) {
			return true;
		}
		return false;
	}
	/**
	 * 更改菜品状态
	 * @param id
	 * @return
	 */
	public int updateDishes(String id) {
		Dishes d=new Dishes();
		d=dd.checkDishesNoIsExist(id);
		int di=Integer.parseInt(id);
		if(di==d.getG_no()) {
			dd.updateDishes(id);
			return Success;
		}else {
			return NumError;
		}
		
	}
	/**
	 * 删除菜品
	 * @param id
	 * @return
	 */
	public int deleteDishes(String id) {
		Dishes d=new Dishes();
		d=dd.checkDishesNoIsExist(id);
		int di=Integer.parseInt(id);
		if(di==d.getG_no()) {
			dd.deleteDishes(id);
			return Success;
		}else {
			return NumError;
		}
	}
/**
 * 点赞
 * @param dishesId
 * @return
 */
	public int niceShoot(String dishesId) {
		Dishes d=dd.checkDishesNoIsExist(dishesId);
		int di=Integer.parseInt(dishesId);
		if(di==d.getG_no()) {
			dd.updateDishesLikeNum(dishesId);
			return Success;
		}else {
			return NumError;
		}
	}
}
