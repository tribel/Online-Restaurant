package com.tribel.jpa.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.tribel.jpa.dao.MenuDao;
import com.tribel.jpa.entity.Menu;

@Named
public class MenuServiceImpl implements MenuService{
	@Inject
	private MenuDao menuDao;

	@Override
	public List<Menu> getFoodListByCategory(String category) {
		return menuDao.getFoodListByCategory(category);
	}

	@Override
	@Transactional
	public void addDishToMenu(Menu dish) {
		menuDao.addDishToMenu(dish);		
	}

	@Override
	@Transactional
	public void setActiveDish(int dishId) {
		menuDao.setActiveDish(dishId);
	}

	@Override
	@Transactional
	public void deleteDish(int dishId) {
		menuDao.deleteDish(dishId);
	}

	@Override
	@Transactional
	public void editDishName(int dishId, String name,String kitchenMade) {
		menuDao.editDishName(dishId, name, kitchenMade);
	}

	@Override
	@Transactional
	public void editDishPrice(int dishId, double price, String kitchenMade) {
		menuDao.editDishPrice(dishId, price, kitchenMade);
	}

	@Override
	public List<Menu> getMenuFromWebService() {
		return menuDao.getMenuFromWebService();
	}

	@Override
	public List<Menu> getFoodListAll() {
		return menuDao.getFoodListAll();
	}

	@Override
	public Menu findById(int id) {
		return menuDao.findById(id);
	}

	@Override
	@Transactional
	public void setKitchenMade(int dishId) {
		 menuDao.setKitchenMade(dishId);
	}

	@Override
	public boolean isActive(int dishId) {
		return menuDao.isActive(dishId);
	}
	

}
