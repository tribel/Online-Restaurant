package com.tribel.jpa.dao;

import java.util.List;

import com.tribel.jpa.entity.KitchenPending;
import com.tribel.jpa.entity.OrderDishes;

public interface OrderDishesDao {
	public OrderDishes findById(int id);
	
	public double getIndividualPrice(int orderId, int dishId);
	public double getTotalOrderPrice(int orderId);
	public void setDishAsDone(int orderDishId);
	List<KitchenPending> getPendingList();
	
	public List<Integer> getNotDoneOrder(int odId);
}

