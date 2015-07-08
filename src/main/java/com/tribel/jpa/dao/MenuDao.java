package com.tribel.jpa.dao;

import java.util.List;

import com.tribel.jpa.entity.Menu;

public interface MenuDao {
	public Menu findById(int id);
	
	public List<Menu> getFoodListByCategory(String category);
	public List<Menu> getFoodListAll();

	public void addDishToMenu(Menu dish);
	
	public void setActiveDish(int dishId);
	public void setKitchenMade(int dishId);
	public void deleteDish(int dishId);
	public void editDishName(int dishId, String name, String kitchenMade);
	public void editDishPrice(int dishId, double price, String kitchenMade);
	
	public boolean isActive(int dishId);
	public List<Menu> getMenuFromWebService();
}
