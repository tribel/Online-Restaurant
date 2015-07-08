package com.tribel.jsf.beans;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.tribel.jpa.entity.KitchenPending;
import com.tribel.jpa.entity.OrderDishes;
import com.tribel.jpa.service.OrderDishesService;
import com.tribel.jpa.service.OrdersService;

@Named
@Scope("session")
public class CookBean {

	private List<KitchenPending> kitchenDishList;

	@Inject
	private OrderDishesService orderDishesService;
	
	@Inject
	private OrdersService ordersService;
	
	private KitchenPending kitchenDish;

	public CookBean() {
		kitchenDish = new KitchenPending();
	}

	public List<KitchenPending> getKitchenDishList() {
		return kitchenDishList;
	}

	public void setKitchenDishList(List<KitchenPending> kitchenDishList) {
		this.kitchenDishList = kitchenDishList;
	}

	public OrderDishesService getOrderDishesService() {
		return orderDishesService;
	}

	public OrdersService getOrdersService() {
		return ordersService;
	}

	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	public void setOrderDishesService(OrderDishesService orderDishesService) {
		this.orderDishesService = orderDishesService;
	}

	public KitchenPending getKitchenDish() {
		return kitchenDish;
	}

	public void setKitchenDish(KitchenPending kitchenDish) {
		this.kitchenDish = kitchenDish;
	}
	
	public void refreshList() {
		kitchenDishList = orderDishesService.getPendingList();
	}
	
	public void setDishAsDone(int orderDishId) {
		orderDishesService.setDishAsDone(orderDishId);
		OrderDishes odTmp = orderDishesService.findById(orderDishId);
		List<Integer> pendingsTmp = orderDishesService.getNotDoneOrder(odTmp.getOrder().getId());
		
		if (pendingsTmp.isEmpty()) {
			ordersService.setOrderStatus(odTmp.getOrder().getId(), "ready for shipment");
		}
		refreshList();
		
	}
	
	public void setOrderStatus() {
		
	}

}
