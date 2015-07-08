package com.tribel.jpa.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.tribel.jpa.dao.OrderDishesDao;
import com.tribel.jpa.entity.KitchenPending;
import com.tribel.jpa.entity.OrderDishes;

@Named
public class OrderDishesServiceImpl implements OrderDishesService{

	@Inject
	private OrderDishesDao orderDishesDao;

	@Override
	public double getIndividualPrice(int orderId, int dishId) {
		return orderDishesDao.getIndividualPrice(orderId, dishId);
	}

	@Override
	public double getTotalOrderPrice(int orderId) {
		return orderDishesDao.getTotalOrderPrice(orderId);
	}

	@Override
	@Transactional
	public void setDishAsDone(int orderDishId) {
		orderDishesDao.setDishAsDone(orderDishId);
	}

	@Override
	public List<KitchenPending> getPendingList() {
		return orderDishesDao.getPendingList();
	}

	@Override
	public List<Integer> getNotDoneOrder(int odId) {
		return orderDishesDao.getNotDoneOrder(odId);
	}

	@Override
	public OrderDishes findById(int id) {
		return orderDishesDao.findById(id);
	}


	
}

